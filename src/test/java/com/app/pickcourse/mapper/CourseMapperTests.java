package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.CourseDTO;
import com.app.pickcourse.domain.dto.CourseListDTO;
import com.app.pickcourse.domain.vo.CourseVO;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CourseMapperTests {

    @Autowired
    private CourseMapper mapper;

    @Test
    public void postAddCourseTest() {
        CourseVO course = new CourseVO();
        course.setCourseType(' ');
        course.setCourseIsVolunteer('Y');
        course.setCourseName("남원");
        course.setCourseDistance("1.6km");
        course.setCourseSchedule("3박4일");
        course.setCourseTheme("힐링");
        course.setCourseContent("즐거운 여행");
        course.setCourseFilePath("C:\\temp\\course.png");
        course.setCourseFileSize("2kb");
        course.setCourseFileName("represent");
        course.setAdminId(2l);

        mapper.postAddCourse(course);
    }

    @Test
    public void getCourseTest() {
        CourseDTO courseDTO = mapper.getCourseDetail(2L);
        log.info(courseDTO.toString());
    }

    @Test
    public void getCourseListTest() {
        Search search = new Search();
        search.setType("nwt");
        search.setKeyWord("nat");
        Pagination pagination = new Pagination();
        log.info(pagination.toString());
        List<CourseListDTO> courseList = mapper.getCourseList(pagination, search);
        log.info("courseList size:" + courseList.size());
        courseList.forEach(System.out::println);
    }

    @Test
    public void patchCourseListTest() {
        mapper.patchCourseList(10l, "B");
    }

    @Test
    public void patchEditCourseTest() {
        CourseVO course = new CourseVO();
        course.setId(4l);
        course.setCourseName("서울");
        course.setCourseDistance("3km");
        course.setCourseSchedule("5박6일");
        course.setCourseTheme("자연");
        course.setCourseContent("편안한 휴식");
        course.setCourseFilePath("C:\\temp\\relax.png");
        course.setCourseFileSize("1.5kb");
        course.setCourseFileName("hue");
        mapper.patchEditCourse(course);
    }
}
