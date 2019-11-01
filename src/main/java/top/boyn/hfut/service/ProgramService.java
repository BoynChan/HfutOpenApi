package top.boyn.hfut.service;

import top.boyn.hfut.crawler.ProgramCrawler;
import top.boyn.hfut.Credential;
import top.boyn.hfut.domain.program.Program;
import top.boyn.hfut.exception.UserException;

import java.io.IOException;

/**
 * 培养方案
 * @author Boyn
 * @date 2019/11/1
 */
public class ProgramService {
    public static Program getProgram(Credential credential) {
        if (!credential.validate()) {
            throw new UserException("凭证已过期");
        }
        try {
            return ProgramCrawler.getProgram(credential);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
