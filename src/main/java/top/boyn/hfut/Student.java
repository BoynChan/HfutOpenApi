package top.boyn.hfut;

import top.boyn.hfut.crawler.LoginCrawler;
import top.boyn.hfut.crawler.StudentInfoCrawler;

import java.io.IOException;
import java.util.Map;

/**
 * 入口类,包含学生个人登录的所有信息
 * @author Boyn
 * @date 2019/11/1
 */
public class Student {
    private String username;
    private String password;
    String stuId;

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Credential login(){
        Map<String,String> cookie = null;
        try {
            cookie = LoginCrawler.login(username, password);
            stuId = StudentInfoCrawler.getStuId(cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Credential(cookie, stuId);
    }
}
