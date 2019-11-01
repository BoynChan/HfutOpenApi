package top.boyn.hfut.crawler;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import top.boyn.hfut.constant.JWXT_CONSTANT;
import top.boyn.hfut.Credential;
import top.boyn.hfut.domain.semester.Semester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class SemesterCrawler {
    public static List<Semester> getSemesterList(Credential credential) throws IOException {
        Map<String, String> cookieMap = credential.getCookie();
        String stuId = credential.getStuId();
        JSONArray uncompletedSemesterJSONArray = getUnresolvedJSONArray(cookieMap, stuId);
        List<Semester> semesters = new ArrayList<>(uncompletedSemesterJSONArray.size());
        for (int i = 0; i < uncompletedSemesterJSONArray.size(); i++) {
            Semester semester = new Semester();
            Connection.Response response = getTableDataResponse(cookieMap, stuId, uncompletedSemesterJSONArray, i);
            setSemester(uncompletedSemesterJSONArray, i, semester, response);
            semesters.add(semester);
        }
        return semesters;
    }

    private static void setSemester(JSONArray uncompletedSemesterJSONArray, int i, Semester semester, Connection.Response response) {
        semester.setSemesterYear(Integer.valueOf(uncompletedSemesterJSONArray.getJSONObject(i).getString("nameEn").split("-")[0]));
        semester.setSemesterPart(Integer.valueOf(uncompletedSemesterJSONArray.getJSONObject(i).getString("nameEn").split("-")[2]));
        semester.setJwxtCode(uncompletedSemesterJSONArray.getJSONObject(i).getString("id"));
        semester.setName(uncompletedSemesterJSONArray.getJSONObject(i).getString("nameZh"));
        semester.setStartDate(uncompletedSemesterJSONArray.getJSONObject(i).getString("startDate"));
        semester.setEndDate(uncompletedSemesterJSONArray.getJSONObject(i).getString("endDate"));
        semester.setWeeks(JSON.parseObject(response.body()).getJSONArray("weekIndices").size());
    }

    private static Connection.Response getTableDataResponse(Map<String, String> cookieMap, String stuId, JSONArray uncompletedSemesterJSONArray, int i) throws IOException {
        return Jsoup
                .connect(JWXT_CONSTANT.COURSE_TABLE_DATA_URL)
                .data(JWXT_CONSTANT.SEMESTER_BIZ_TYPE_ID, JWXT_CONSTANT.SEMESTER_BACHELOR_ID)
                .data(JWXT_CONSTANT.SEMESTER_ID, uncompletedSemesterJSONArray.getJSONObject(i).getString("id"))
                .data(JWXT_CONSTANT.SEMESTER_STU_ID, stuId)
                .cookies(cookieMap)
                .ignoreContentType(true)
                .execute();
    }

    private static JSONArray getUnresolvedJSONArray(Map<String, String> cookieMap, String stuId) throws IOException {
        Connection.Response response = Jsoup
                .connect(JWXT_CONSTANT.COURSE_TABLE_INFO_URL + stuId)
                .cookies(cookieMap)
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .execute();

        Document document = Jsoup.parse(response.body());
        String text = document.getElementsByTag("script").html();
        String regexString = ReUtil.get("var semesters = JSON.parse\\(\n([\\s\\S]{1,}?)\\);", text, 1);
        regexString = regexString.replaceAll("u5B66", "学");
        regexString = regexString.replaceAll("u5E74", "年");
        regexString = regexString.replaceAll("u7B2C", "第");
        regexString = regexString.replaceAll("u4E00", "一");
        regexString = regexString.replaceAll("u4E8C", "二");
        regexString = regexString.replaceAll("u5B66", "学");
        regexString = regexString.replaceAll("u671F", "期");
        regexString = regexString.replaceAll("\\\\", "");
        regexString = regexString.replaceAll("'", "");
        regexString = regexString.replaceAll("'", "");
        return JSON.parseArray(regexString);
    }
}
