package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.PlanVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class PlanMapperTests {
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private CourseMapper courseMapper;

//    계획 추가
    @Test
    public void testInsert() {
        MemberVO member = new MemberVO();

        member.setMemberEmail("test1@test.com");
        member.setMemberPassword("1234");

        Optional<MemberVO> loginMember = memberMapper.selectByMemberEmailAndMemberPassword(member);
        member = loginMember.orElse(new MemberVO());

        Optional<CourseDTO> readCourse = courseMapper.getCourseDetail(2L);
        CourseDTO course = readCourse.orElseThrow(() -> new RuntimeException("Course not found"));

        PlanVO planVO = new PlanVO();

        planVO.setPlanName("제목3");
        planVO.setPlanStartDate("2025-03-04");
        planVO.setPlanEndDate("2025-03-10");
        planVO.setPlanDeadline("2025-03-20");
        planVO.setPlanMaxPersonnel(10);
        planVO.setPlanMinPersonnel(1);
        planVO.setPlanPrice(25000);
        planVO.setPlanStartAddress("주소3");
        planVO.setPlanFileName("파일이름3");
        planVO.setPlanFileSize("파일크기3");
        planVO.setPlanFilePath("파일경로3");
        planVO.setMemberId(1L);
        planVO.setCourseId(1L);
        planVO.setMemberId(member.getId());
        planVO.setCourseId(course.getId());
        planVO.setPlanContent("내용3");

        planMapper.insert(planVO);
        log.info(planVO.toString());
    }

    @Test
    public void testSelectAll() {
        List<PlanVO> foundAll = planMapper.selectAll();
        log.info(foundAll.toString());
    }

    @Test
    public void testSelectById() {
        Optional<PlanDTO> foundPlanDTO = planMapper.selectById(1L);
        foundPlanDTO.ifPresent(plan -> log.info(plan.toString()));
    }

    @Test
    public void testUpdate() {
       PlanDTO planDTO = new PlanDTO();

       planDTO.setId(1L);
        planDTO.setPlanName("변경된제목1");
        planDTO.setPlanStartDate("2025-03-08");
        planDTO.setPlanEndDate("2025-03-20");
        planDTO.setPlanDeadline("2025-03-07");
        planDTO.setPlanMaxPersonnel(20);
        planDTO.setPlanMinPersonnel(4);
        planDTO.setPlanPrice(10000);
        planDTO.setPlanStartAddress("변경된주소1");
        planDTO.setPlanContent("변경된내용1");
        planDTO.setPlanFilePath("변경된경로1");
        planDTO.setPlanFileSize("변경된크기1");
        planDTO.setPlanFileName("변경된이름1");
        planDTO.setUpdatedDate("2025-03-06");

       planMapper.update(planDTO);

    }

    @Test
    public void testDelete() {
        planMapper.deleteById(3L);
    }

    @Test
    public void testSelectByMemberId() {
        PlanDTO planDTO = new PlanDTO();
        MemberVO memberVO = new MemberVO();

        memberVO.setId(1L);

        planDTO.setMemberId(memberVO.getId());

    }
    public void testSelectAllById() {
        List<PlanVO> list = planMapper.selectAllById(1L);
        list.forEach(System.out::println);

    }

    @Test
    public void testselectRanking(){
        List<Long> weeklyIds = planMapper.selectRankingWeekly();
        weeklyIds.forEach(System.out::println);

        List<Long> monthlyIds = planMapper.selectRankingMonthly();
        monthlyIds.forEach(System.out::println);

        List<Long> yearlyIds = planMapper.selectRankingYearly();
        yearlyIds.forEach(System.out::println);
    }
}
