package top.boyn.hfut;

import top.boyn.hfut.crawler.LoginCrawler;
import top.boyn.hfut.exception.UserException;

import java.io.IOException;
import java.util.Map;

/**
 * 凭证类,登录成功后带着凭证进行请求
 * 凭证有效期是5分钟,过期后可以用凭证类的update方法进行更新
 * @author Boyn
 * @date 2019/11/1
 */
public class Credential {
    private long ms;
    private Map<String,String> cookie;
    private String stuId;

    public Credential(Map<String, String> cookie, String stuId) {
        this.cookie = cookie;
        this.stuId = stuId;
        this.ms = System.currentTimeMillis();
    }

    /**
     * 凭证更新
     */
    public boolean update(Student student) {
        if (!stuId.equals(student.stuId)) {
            throw new UserException("非凭证所对应的学生");
        }
        try {
            cookie = LoginCrawler.login(student.getUsername(), student.getPassword());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validate(){
        long now = System.currentTimeMillis();
        return now - ms <= 5000L * 60L;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
