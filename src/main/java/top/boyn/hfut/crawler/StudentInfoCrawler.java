package top.boyn.hfut.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import top.boyn.hfut.constant.JWXT_CONSTANT;
import top.boyn.hfut.Credential;
import top.boyn.hfut.domain.studentInfo.StudentInfo;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;
import java.util.Map;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class StudentInfoCrawler {
    public static String getStuId(Map<String, String> cookieMap) throws IOException {
        Connection.Response response = Jsoup
                .connect(JWXT_CONSTANT.STUDENT_INFO_URL)
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .cookies(cookieMap)
                .followRedirects(false)
                .execute();
        return response.header("Location").split("/")[5];
    }

    public static StudentInfo getInfo(Credential credential) throws IOException{
        Connection.Response response = Jsoup
                .connect(JWXT_CONSTANT.STUDENT_INFO_URL + "/info/" + credential.getStuId())
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .cookies(credential.getCookie())
                .followRedirects(false)
                .execute();
        StudentInfo info = new StudentInfo();
        Document text = response.parse();
        info.setChineseName(Xsoup.compile("//*[@id=\"base-info\"]/div/div[1]/ul/li[4]/span[2]").evaluate(text).getElements().text());
        info.setEnglishName(Xsoup.compile("//*[@id=\"base-info\"]/div/div[1]/ul/li[5]/span[2]").evaluate(text).getElements().text());
        info.setAdmissionDate(Xsoup.compile("//*[@id=\"base-info\"]/div/div[2]/div/dl/dd[20]").evaluate(text).getElements().text());
        info.setGraduateDate(Xsoup.compile("//*[@id=\"graduate-info\"]/div/div[2]/div/dl/dd[1]").evaluate(text).getElements().text());
        info.setCampus(Xsoup.compile("//*[@id=\"base-info\"]/div/div[2]/div/dl/dd[10]").evaluate(text).getElements().text());
        info.setDepart(Xsoup.compile("//*[@id=\"base-info\"]/div/div[2]/div/dl/dd[2]").evaluate(text).getElements().text());
        info.setDocumentNumber(Xsoup.compile("//*[@id=\"base-info\"]/div/div[1]/ul/li[8]/span[2]").evaluate(text).getElements().text());
        info.setDocumentType(Xsoup.compile("//*[@id=\"base-info\"]/div/div[1]/ul/li[7]/span[2]").evaluate(text).getElements().text());
        info.setEducationLevel(Xsoup.compile("//*[@id=\"base-info\"]/div/div[2]/div/dl/dd[4]").evaluate(text).getElements().text());
        info.setEnglishName(Xsoup.compile("//*[@id=\"base-info\"]/div/div[1]/ul/li[5]/span[2]").evaluate(text).getElements().text());
        info.setGaoKaoScore(Xsoup.compile("//*[@id=\"recruit-info\"]/div/div[2]/div/dl/dd[18]").evaluate(text).getElements().text());
        info.setClassName(Xsoup.compile("/html/body/div/div/div/div[2]/div/div[1]/div/div[2]/div/dl/dd[9]").evaluate(text).getElements().text());
        info.setGender(Xsoup.compile("//*[@id=\"base-info\"]/div/div[1]/ul/li[6]/span[2]").evaluate(text).getElements().text());
        info.setGrade(Xsoup.compile("//*[@id=\"base-info\"]/div/div[2]/div/dl/dd[3]").evaluate(text).getElements().text());
        info.setMajor(Xsoup.compile("//*[@id=\"base-info\"]/div/div[2]/div/dl/dd[7]").evaluate(text).getElements().text());
        info.setProvince(Xsoup.compile("//*[@id=\"recruit-info\"]/div/div[2]/div/dl/dd[16]").evaluate(text).getElements().text());
        info.setStudentType(Xsoup.compile("//*[@id=\"base-info\"]/div/div[2]/div/dl/dd[5]").evaluate(text).getElements().text());
        return info;
    }
}
