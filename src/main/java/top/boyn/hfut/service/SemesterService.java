package top.boyn.hfut.service;

import top.boyn.hfut.crawler.SemesterCrawler;
import top.boyn.hfut.Credential;
import top.boyn.hfut.domain.semester.Semester;
import top.boyn.hfut.exception.UserException;

import java.io.IOException;
import java.util.List;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class SemesterService {
    public static List<Semester> getSemesterList(Credential credential) {
        if (!credential.validate()) {
            throw new UserException("凭证已过期");
        }
        try {
            return SemesterCrawler.getSemesterList(credential);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
