package top.boyn.hfut.service;

import top.boyn.hfut.crawler.CourseCrawler;
import top.boyn.hfut.Credential;
import top.boyn.hfut.domain.course.Course;
import top.boyn.hfut.domain.semester.Semester;
import top.boyn.hfut.exception.UserException;

import java.io.IOException;
import java.util.List;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class CourseService {
    public static List<Course> getCourseList(Credential credential, Semester semester, String week) {
        if (!credential.validate()) {
            throw new UserException("凭证已过期");
        }
        try {
            return CourseCrawler.getCourseList(week, semester, credential);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
