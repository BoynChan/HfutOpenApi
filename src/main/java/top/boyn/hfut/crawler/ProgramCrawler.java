package top.boyn.hfut.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import top.boyn.hfut.constant.JWXT_CONSTANT;
import top.boyn.hfut.domain.base.Credential;
import top.boyn.hfut.domain.program.Program;
import top.boyn.hfut.domain.program.ProgramItem;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class ProgramCrawler {
    public static Program getProgram(Credential credential) throws IOException {
        Connection.Response response = Jsoup
                .connect(JWXT_CONSTANT.PROGRAM_URL + credential.getStuId())
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .cookies(credential.getCookie())
                .followRedirects(false)
                .execute();
        long mi = System.currentTimeMillis();
        String body = response.body();
        Program program = new Program();
        program.setGeneralScore(JSONPath.extract(body, "$.creditBySubModule.通识教育必修课").toString());
        program.setMajorRequiredScore(JSONPath.extract(body, "$.creditBySubModule.学科基础课程和专业必修课").toString());
        program.setMajorSelectiveScore(JSONPath.extract(body, "$.creditBySubModule.专业选修课程").toString());
        program.setPracticeScore(JSONPath.extract(body, "$.creditBySubModule.实践环节").toString());
        JSONArray array = JSON.parseObject(body).getJSONArray("children");
        parseCourse(array,program);
        return program;
    }

    private static void parseCourse(JSONArray array, Program program) {
        for (int i = 0; i < array.size(); i++) {
            JSONObject programObject = array.getJSONObject(i);
            if ("通识教育必修课".equals(programObject.getJSONObject("type").getString("name"))) {
                program.setGeneralList(parseCourseList(programObject.getJSONArray("planCourses")));

            }
            if ("学科基础课程和专业必修课".equals(programObject.getJSONObject("type").getString("name"))) {
                program.setMajorRequiredList(parseCourseList(programObject.getJSONArray("planCourses")));

            }
            if ("专业选修课程".equals(programObject.getJSONObject("type").getString("name"))) {
                program.setMajorSelectiveList(parseCourseList(programObject.getJSONArray("planCourses")));

            }
            if ("实践环节".equals(programObject.getJSONObject("type").getString("name"))) {
                program.setPracticeList(parseCourseList(programObject.getJSONArray("planCourses")));

            }
        }
    }

    private static List<ProgramItem> parseCourseList(JSONArray planCourses) {
        List<ProgramItem> items = new LinkedList<>();
        for (int i = 0; i < planCourses.size(); i++) {
            JSONObject object = planCourses.getJSONObject(i);
            ProgramItem item = new ProgramItem();
            item.setCode(object.getJSONObject("course").getString("code"));
            item.setDepart(object.getJSONObject("openDepartment").getString("nameZh"));
            item.setMustRead(object.getBooleanValue("compulsory") ? "是" : "否");
            item.setName(object.getJSONObject("course").getString("nameZh"));
            item.setRemark(object.getString("remark"));
            item.setScore(object.getJSONObject("course").getString("credits"));
            item.setSemesterFall(object.getJSONArray("readableTerms").getString(0));
            item.setSemesterSuggest(object.getJSONArray("readableSuggestTerms").getString(0));
            item.setType(object.getJSONObject("course").getJSONObject("courseType").getString("nameZh"));
            items.add(item);
        }
        return items;
    }
}
