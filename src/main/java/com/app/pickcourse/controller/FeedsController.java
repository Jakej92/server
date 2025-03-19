// 2025.02.24 조승찬
package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.dto.RealDTO;
import com.app.pickcourse.domain.dto.ReplyActionDTO;
import com.app.pickcourse.domain.dto.ReplyListDTO;
import com.app.pickcourse.domain.vo.ReplyVO;
import com.app.pickcourse.domain.vo.ReportVO;
import com.app.pickcourse.service.FeedsService;
import com.app.pickcourse.util.PaginationOnePage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feeds")
@RequiredArgsConstructor
@Slf4j
public class FeedsController {
    private final FeedsService feedsService;

    // 댓글 목록 조회 :: 25.03.16 조승찬
    @GetMapping("/reply-list/{feedId}")
    public String getReplyList(@PathVariable Long feedId, PaginationOnePage pagination, Model model) {

        List<ReplyListDTO> replys = feedsService.getReplyList(1l, feedId, pagination);  // 로그인수정
        log.info("pagination  "+pagination.toString());
        model.addAttribute("replys", replys);  // 댓글 목록
        model.addAttribute("replyAction", new ReplyActionDTO()); // 입력될 댓글을 받아올 객체
        model.addAttribute("pagination", pagination);
        return "/feeds/reply-list";
    }

    // 댓글 목록 추가 조회 레스트컨트롤러 방식:: 25.03.16 조승찬
    @GetMapping("/reply-list/api/{feedId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getReplyListApi(@PathVariable Long feedId, PaginationOnePage pagination) {

        List<ReplyListDTO> replys = feedsService.getReplyList(1l, feedId, pagination);  //로그인수정
        Map<String, Object> response = new HashMap<>();
        response.put("replys", replys);
        response.put("pagination", pagination);
        return ResponseEntity.ok(response);

    }

    // 댓글 삭제 레스트컨트롤러 방식 => 컨트롤러 방식으로 redirect :: 25.03.16 조승찬
    @DeleteMapping("/reply-list/{id}")
    @ResponseBody
    public Map<String, Object> deleteReplyList(@PathVariable Long id,
                                               @RequestParam Long feedId) {

        feedsService.deleteReplyList(id);
        Map<String, Object> response = new HashMap<>();
        response.put("redirectUrl", "/feeds/reply-list/" + feedId + "?page=" + 1); // 1페이지부터 조회
        return response;
    }

    // 댓글 신고 25.03.16 조승찬
    @PostMapping("/reply-list/report")
    @ResponseBody
    public void postReportReplyList(@RequestBody ReportVO reportVO) {
        log.info("postReportReplyList  들어옴");

        feedsService.postReportReplyList(reportVO, 1l);  // 로그인수정
    }

    // 댓글 등록  25.03.17 조승찬
    @PostMapping("/reply-list")
    @ResponseBody
    public Map<String, Object> postReplyList(@RequestBody ReplyVO replyVO) {
        log.info("Map<String, Object> postReplyList에 들어옴 "+replyVO.toString());
        feedsService.postReplyList(replyVO, 1l);   // 로그인수정

        Map<String, Object> response = new HashMap<>();
        // 댓글 등록 후 첫 페이지로 이동 :: 등록 확인을 위해
        response.put("redirectUrl", "/feeds/reply-list/" + replyVO.getFeedId() + "?page=" + 1);

        return response;
    }

    // 나의 댓글 목록 조회 :: 25.03.17 조승찬
    @GetMapping("/my/reply-list")
    public String getMyReplyList(PaginationOnePage pagination, Model model) {

        List<ReplyListDTO> replys = feedsService.getMyReplyList(1l, pagination);  //로그인수정
        model.addAttribute("replys", replys);  // 댓글 목록
        model.addAttribute("replyAction", new ReplyActionDTO()); // 입력될 댓글을 받아올 객체
        model.addAttribute("pagination", pagination);

        return "/feeds/my-reply-list";
    }

    // 나의 댓글 목록 추가 조회 레스트컨트롤러 방식:: 25.03.17 조승찬
    @GetMapping("/my/reply-list/api")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMyReplyListApi(PaginationOnePage pagination) {

        List<ReplyListDTO> replys = feedsService.getMyReplyList(1l, pagination); // 로그인수정
        Map<String, Object> response = new HashMap<>();
        response.put("replys", replys);
        response.put("pagination", pagination);
        return ResponseEntity.ok(response);

    }

    // 나의 댓글 삭제 레스트컨트롤러 방식 => 컨트롤러 방식으로 redirect :: 25.03.17 조승찬
    @DeleteMapping("/my/reply-list/{id}")
    @ResponseBody
    public Map<String, Object> deleteMyReplyList(@PathVariable Long id) {

        feedsService.deleteReplyList(id);
        Map<String, Object> response = new HashMap<>();
        response.put("redirectUrl", "/feeds/my/reply-list?page=" + 1); // 1페이지부터 조회
        return response;
    }

    // 피드 작성 25.03.18 조승찬
    @GetMapping("/feed-write")
    public String getFeedWrite(Model model) {
        FeedDTO feedDTO = new FeedDTO();
        model.addAttribute("feedDTO", feedDTO);
        return "/feeds/feed-write";
    }

    // 피드 작성 25.03.18 조승찬
    @PostMapping("/feed-write")
    public String postFeedWrite(FeedDTO feedDTO) {
        log.info(feedDTO.toString());

        feedsService.postFeedWrite(1l, feedDTO); //로그인수정

        return "redirect:/feeds/list";
    }

    // 리얼 후기 작성 25.03.18 조승찬
    @GetMapping("/real-write")
    public String ReviewWrite(@RequestParam("planId") Long planId, Model model) {
        RealDTO realDTO = new RealDTO();
        realDTO.setPlanId(planId);
        model.addAttribute("realDTO", realDTO);
        return "/feeds/real-write";
    }

    // 리얼 후기 작성 25.03.18 조승찬
    @PostMapping("/real-write")
    public String postRealWrite(RealDTO realDTO) {
        log.info(realDTO.toString());

        feedsService.postRealWrite(1l, realDTO); //로그인수정

        return "redirect:/feeds/list";
    }

    @GetMapping("/list")
    public String getFeedList(Model model) {
        return "/feeds/list";
    }

    @GetMapping("/modify")
    public String getFeedModify(Model model) {
        return "/feeds/modify";
    }

    @PostMapping("/modify")
    public String postFeedModify(Model model) {
        return "/feeds/modify";
    }

    @GetMapping("/modify-list")
    public String getFeedModifyList(Model model) {
        return "/feeds/modifylist";
    }

    @GetMapping("/review-list")
    public String getReviewList(Model model) {
        return "/feeds/reviewlist";
    }

    @GetMapping("/review-modify")
    public String getReviewModify(Model model) {
        return "/feeds/reviewmodify";
    }

    @PostMapping("/review-modify")
    public String postReviewModify(Model model) {
        return "/feeds/reviewmodify";
    }

    @GetMapping("/tour-list")
    public String getTourList(Model model) {
        return "/feeds/tourlist";
    }
}
