package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.*;
import com.app.pickcourse.mapper.QuestionMapper;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.QuestionPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class PlanService {
    private final PlanDAO planDAO;
    private final ScheduleDAO scheduleDAO;
    private final WriteExcludeDAO writeExcludeDAO;
    private final WriteIncludeDAO writeIncludeDAO;
    private final WritePrepareDAO writePrepareDAO;
    private final MemberDAO memberDAO;
    private final FeedDAO feedDAO;
    private final QuestionDAO questionDAO;
    private final Pagination pagination;
    private final ScheduleVO scheduleVO;


    //    여행계획작성
    public void writePlan(PlanDTO planDTO) {
        PlanVO planVO = planDTO.toVO();

        planDAO.save(planVO);

        planDTO.getExcludeContents().forEach( exclude -> {
                    exclude.setPlanId(planVO.getId());
                    writeExcludeDAO.save(exclude.toVO());
                });
        planDTO.getIncludeContents().forEach(include -> {
            include.setPlanId(planVO.getId());
            writeIncludeDAO.save(include.toVO());
                });
        planDTO.getPrepareContents().forEach(prepare -> {
            prepare.setPlanId(planVO.getId());
            writePrepareDAO.save(prepare.toVO());
                });
        planDTO.getScheduleContents().forEach(schedule -> {
            schedule.setPlanId(planVO.getId());
            scheduleDAO.save(schedule.toVO());
            });
    }

//    나의 계획 목록
    public MyPLanListDTO getMyPlanList(@Param("pagination") Pagination pagination,
                                       @Param("memberId") Long id) {
        MyPLanListDTO planListDTO = new MyPLanListDTO();

        pagination.create(planDAO.findTotal(id));

        planListDTO.setPagination(pagination);
        planListDTO.setPlanList(planDAO.findMyPlan(pagination, id));

        return planListDTO;
    }

//    목록 갯수
    public int getTotal(Long memberId) { return planDAO.findTotal(memberId); }

//    여행 상세 조회
    public Optional<PlanDTO> getPlanById(Long id) {
        PlanDTO planDTO = new PlanDTO();
        MemberVO memberVO = new MemberVO();

        Optional<PlanDTO> foundPlan = planDAO.findById(id);

        planDTO = foundPlan.orElseThrow(()-> new RuntimeException());
        memberVO = memberDAO.findById(planDTO.getMemberId()).orElseThrow(()-> new RuntimeException());


        planDTO.setMemberNickname(memberVO.getMemberNickname());
        planDTO.setScheduleContents(scheduleDAO.findByPlanId(id));
        planDTO.setExcludeContents(writeExcludeDAO.findByPlanId(id));
        planDTO.setIncludeContents(writeIncludeDAO.findByPlanId(id));
        planDTO.setPrepareContents(writePrepareDAO.findByPlanId(id));

        return Optional.ofNullable(foundPlan.orElse(new PlanDTO()));
    }

//    상세조회
    public PlanDetailDTO getPlanDetailById(Long id) {
        PlanDetailDTO planDetailDTO = new PlanDetailDTO();
        PlanDTO planDTO = new PlanDTO();
        MemberVO memberVO = new MemberVO();
        List<FeedDTO> feedDTOList = new ArrayList<>();

        Optional<PlanDTO> foundPlan = planDAO.findById(id);

        planDTO = foundPlan.orElseThrow(()-> new RuntimeException());
        memberVO = memberDAO.findById(planDTO.getMemberId()).orElseThrow(()-> new RuntimeException());


        planDTO.setMemberNickname(memberVO.getMemberNickname());
        planDTO.setScheduleContents(scheduleDAO.findByPlanId(id));
        planDTO.setExcludeContents(writeExcludeDAO.findByPlanId(id));
        planDTO.setIncludeContents(writeIncludeDAO.findByPlanId(id));
        planDTO.setPrepareContents(writePrepareDAO.findByPlanId(id));

        planDetailDTO.setPlan(planDAO.findById(id).get());
        planDetailDTO.setFeeds(feedDTOList);

        return planDetailDTO;
    }

//    질문 작성
    public void writeQuestion(QuestionDTO questionDTO) {
        QuestionVO questionVO = questionDTO.toVO();
        questionDAO.saveQuestion(questionVO);
    }

//    planId의 질문 조회
    public QuestionListDTO findQuestionLists(Long planId) {
        QuestionListDTO questionListDTO = new QuestionListDTO();

        questionListDTO.setQuestionList(questionDAO.findQuestion(planId));

        return questionListDTO;
    }
// 계획수정
    public void updatePlan(PlanDTO planDTO) {
        planDAO.setPlan(planDTO);

        planDTO.getDeleteExcludes().forEach(writeExcludeDAO::delete);
        planDTO.getDeleteIncludes().forEach(writeIncludeDAO::delete);
        planDTO.getDeletePrepares().forEach(writePrepareDAO::delete);
        planDTO.getDeleteSchedules().forEach((schedule) -> {
            ScheduleVO scheduleVO = new ScheduleVO();
            scheduleVO.setId(schedule);
            scheduleVO.setStatus("DISABLED");
            scheduleDAO.setSchedule(scheduleVO);
        });

        planDTO.getExcludeContents().forEach( exclude -> {
            exclude.setPlanId(planDTO.getId());
            writeExcludeDAO.save(exclude.toVO());
        });
        planDTO.getIncludeContents().forEach(include -> {
            include.setPlanId(planDTO.getId());
            writeIncludeDAO.save(include.toVO());
        });
        planDTO.getPrepareContents().forEach(prepare -> {
            prepare.setPlanId(planDTO.getId());
            writePrepareDAO.save(prepare.toVO());
        });
        planDTO.getScheduleContents().forEach(schedule -> {
            schedule.setPlanId(planDTO.getId());
            scheduleDAO.save(schedule.toVO());
        });
    }

}






