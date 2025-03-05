package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
public class CourseDTO {
    private Long             id;
    private Character        courseType;
    private Character        courseIsVolunteer;
    private String           courseName;
    private String           courseDistance;
    private String           courseSchedule;
    private String           courseTheme;
    private String           courseContent;
    private String           courseFilePath;
    private String           courseFileSize;
    private String           courseFileName;
    private Long             adminId;
    private String           createdDate;
    private String           updatedDate;
    private Long             volunteerId;
    private String           volunteerStartDate;
    private String           volunteerEndDate;
    private String           volunteerDeadline;
    List<PathVO>             paths;
    List<VolunteerExcludeVO> volunteerExcludes;
    List<VolunteerIncludeVO> volunteerIncludes;
    List<VolunteerPrepareVO> volunteerPrepares;

    public CourseVO toCourseVO() {
        CourseVO courseVO = new CourseVO();
        courseVO.setId(id);
        courseVO.setCourseType(courseType);
        courseVO.setCourseIsVolunteer(courseIsVolunteer);
        courseVO.setCourseName(courseName);
        courseVO.setCourseDistance(courseDistance);
        courseVO.setCourseSchedule(courseSchedule);
        courseVO.setCourseTheme(courseTheme);
        courseVO.setCourseContent(courseContent);
        courseVO.setCourseFilePath(courseFilePath);
        courseVO.setCourseFileSize(courseFileSize);
        courseVO.setCourseFileName(courseFileName);
        courseVO.setAdminId(adminId);
        return courseVO;
    }

    public VolunteerVO toVolunteerVO() {
        VolunteerVO volunteerVO = new VolunteerVO();
        volunteerVO.setId(volunteerId);
        volunteerVO.setVolunteerStartDate(volunteerStartDate);
        volunteerVO.setVolunteerEndDate(volunteerEndDate);
        volunteerVO.setVolunteerDeadline(volunteerDeadline);
        return volunteerVO;
    }
}
