package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.domain.vo.FeedTagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FeedTagMapperTests {
    @Autowired
    FeedTagMapper mapper;
    @Autowired
    FeedMapper feedMapper;

    @Test
    public void postFeedWrite() {
        FeedTagVO tagVO = new FeedTagVO();
        tagVO.setFeedTagContent("가벼운");
        tagVO.setFeedId(47l);
        mapper.postFeedWrite(tagVO);
    }

    @Test
    public void getFeedModify(){
        FeedDTO feedDTO = feedMapper.getFeedModify(51l);
        feedDTO.setFeedTags(mapper.getFeedList(feedDTO.getId()));
        log.info("feedDTO:{}",feedDTO);
    }

    @Test
    public void postFeedModify(){
        mapper.postModifyFeed(42l);
    }
}
