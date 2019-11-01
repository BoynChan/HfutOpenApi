package top.boyn.hfut.service;

import top.boyn.hfut.crawler.StudentInfoCrawler;
import top.boyn.hfut.domain.base.Credential;
import top.boyn.hfut.domain.studentInfo.StudentInfo;
import top.boyn.hfut.exception.UserException;

import java.io.IOException;

/**
 * 学生个人信息
 * @author Boyn
 * @date 2019/11/1
 */
public class StudentInfoService {
    public static StudentInfo getBasicStudentInfo(Credential credential) {
        if (!credential.validate()) {
            throw new UserException("凭证已过期");
        }
        try {
            return StudentInfoCrawler.getInfo(credential);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
