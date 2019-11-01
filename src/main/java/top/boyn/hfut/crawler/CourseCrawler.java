package top.boyn.hfut.crawler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import top.boyn.hfut.constant.JWXT_CONSTANT;
import top.boyn.hfut.constant.TIME_UNIT;
import top.boyn.hfut.domain.base.Credential;
import top.boyn.hfut.domain.course.Course;
import top.boyn.hfut.domain.semester.Semester;
import top.boyn.hfut.utils.CourseWeekParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class CourseCrawler {
    public static List<Course> getCourseList(String week, Semester semester, Credential credential) throws IOException {
        String jwxtCode = semester.getJwxtCode();
        Map<String, String> cookieMap = credential.getCookie();
        String stuId = credential.getStuId();
        Connection.Response tableDataResponse = getTableDataResponse(jwxtCode, stuId, cookieMap);
        JSONArray lessons = JSON.parseObject(tableDataResponse.body()).getJSONArray("lessons");
        List<String> lessonIds = JSON.parseObject(tableDataResponse.body()).getJSONArray("lessonIds").toJavaList(String.class);
        Connection.Response DatumResponse = getDatumResponse(stuId, week, cookieMap, lessonIds);
        JSONArray scheduleList = JSON.parseObject(DatumResponse.body())
                .getJSONObject("result").getJSONArray("scheduleList");
        JSONArray lessonList = JSON.parseObject(DatumResponse.body())
                .getJSONObject("result").getJSONArray("lessonList");

        List<Course> courses = parseCourse(scheduleList, lessonList, lessons);
        //根据课程名和上课的日期进行去重
        courses = courses.stream().distinct().collect(Collectors.toList());
        return courses;
    }

    private static Connection.Response getDatumResponse(String stuId, String week, Map<String, String> cookieMap, List<String> lessonIds) throws IOException {
        JSONObject requestAttribute = new JSONObject();
        requestAttribute.put("lessonIds", lessonIds);
        requestAttribute.put("weekIndex", week);
        requestAttribute.put("studentId", stuId);
        return Jsoup.connect(JWXT_CONSTANT.SCHEDULE_DATUM_URL)
                .cookies(cookieMap)
                .ignoreContentType(true)
                .header("Content-Type", "application/json")
                .requestBody(requestAttribute.toJSONString())
                .method(Connection.Method.POST)
                .execute();
    }

    private static Connection.Response getTableDataResponse(String jwxtCode, String stuId, Map<String, String> cookieMap) throws IOException {
        return Jsoup.connect(JWXT_CONSTANT.COURSE_TABLE_DATA_URL)
                .cookies(cookieMap)
                .ignoreContentType(true)
                .data(JWXT_CONSTANT.SEMESTER_BIZ_TYPE_ID, JWXT_CONSTANT.SEMESTER_BACHELOR_ID)
                .data(JWXT_CONSTANT.SEMESTER_ID, jwxtCode)
                .data(JWXT_CONSTANT.SEMESTER_STU_ID, stuId)
                .method(Connection.Method.GET)
                .execute();
    }

    /**
     * 根据网页中的两个接口返回的数据,解析出每一周的课程表
     */
    private static List<Course> parseCourse(JSONArray scheduleList, JSONArray lessonList, JSONArray lessons) {
        Map<String, JSONObject> lessonListIdMap = new HashMap<>();
        for (int i = 0; i < lessonList.size(); i++) {
            lessonListIdMap.put(lessonList.getJSONObject(i).getString("id"), lessonList.getJSONObject(i));
        }
        Map<String, JSONObject> lessonsIdMap = new HashMap<>();
        for (int i = 0; i < lessons.size(); i++) {
            lessonsIdMap.put(lessons.getJSONObject(i).getString("id"), lessons.getJSONObject(i));
        }
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < scheduleList.size(); i++) {
            JSONObject schedule = scheduleList.getJSONObject(i);
            JSONObject lessonIdObject = lessonListIdMap.get(schedule.getString("lessonId"));
            JSONObject lessonsObject = lessonsIdMap.get(schedule.getString("lessonId"));

            Course course = new Course();
            //课程开始时间
            setCourseStartTime(schedule, course);
            //课程结束时间
            setCourseEndTime(schedule, course);
            //根据时间索引确定课程开始的节数
            course.setStartUnit(TIME_UNIT.getUnit(schedule.getString("startTime")));
            //根据时间索引确定课程结束的节数
            course.setEndUnit(TIME_UNIT.getUnit(schedule.getString("endTime")));
            //课程在一周中的第几天
            course.setWeekDay(schedule.getInteger("weekday"));
            setCourseRoomAndCampus(schedule, course);

            //课程代码
            course.setLesson_code(lessonIdObject.getString("code"));
            setCourseWeek(lessonIdObject, lessonsObject, course);

            //获取教师的列表
            setCourseTeachers(lessonIdObject, course);
            //获取课程名
            course.setCourseName(lessonIdObject.getString("courseName"));
            //获取课程学生上课的人数
            course.setNumber(lessonIdObject.getInteger("stdCount"));
            courses.add(course);
        }
        return courses;
    }

    private static void setCourseWeek(JSONObject lessonIdObject, JSONObject lessonsObject, Course course) {
        //插入周数信息
        course.setWeekInfo(lessonsObject.getString("scheduleWeeksInfo"));

        //设置课程的周数,在此处要进行排序是因为给的周数是乱的
        List<Integer> weeks = lessonIdObject.getJSONArray("suggestScheduleWeeks").toJavaList(Integer.class);
        if (weeks.isEmpty()) {
            CourseWeekParser.parseSingleCourse(course);
        }else {
            weeks = weeks.stream().sorted().collect(Collectors.toList());
            course.setActivateWeek(StrUtil.join(",", weeks));
        }



    }

    private static void setCourseTeachers(JSONObject lessonIdObject, Course course) {
        String[] teachers = new String[lessonIdObject.getJSONArray("teacherAssignmentList").size()];
        for (int j = 0; j < lessonIdObject.getJSONArray("teacherAssignmentList").size(); j++) {
            teachers[j] = lessonIdObject.getJSONArray("teacherAssignmentList").getJSONObject(j).getString("name");
        }
        course.setTeachers(teachers);
    }

    private static void setCourseRoomAndCampus(JSONObject schedule, Course course) {
        if (schedule.getJSONObject("room") == null) {
            course.setClassRoomName("未知");
            course.setCampus("未知");
        } else {
            //教室名
            course.setClassRoomName(schedule.getJSONObject("room").getString("nameZh"));
            //校区名
            course.setCampus(schedule.getJSONObject("room").getJSONObject("building").getJSONObject("campus").getString("nameZh"));
        }
    }

    private static void setCourseEndTime(JSONObject schedule, Course course) {
        course.setEndTime(
                schedule.getString("endTime").substring(0, 2)
                        + ":" + schedule.getString("endTime").substring(2));
    }

    private static void setCourseStartTime(JSONObject schedule, Course course) {
        course.setStartTime(
                (schedule.getString("startTime").length() == 3
                        ? schedule.getString("startTime").substring(0, 1) + ":" + schedule.getString("startTime").substring(1)
                        : schedule.getString("startTime").substring(0, 2) + ":" + schedule.getString("startTime").substring(2)));
    }
}
