package top.boyn.hfut.constant;

/**
 * 教务系统中常用的常量
 * @author Boyn
 * @date 2019/10/19
 */
public class JWXT_CONSTANT {

    /**
     * 表示学期的代码
     */
    public static final String SEMESTER_ID = "semesterId";
    /**
     * dataId,表示的是学生id的代码
     */
    public static final String SEMESTER_STU_ID = "dataId";
    /**
     * bizTypeId,2表示本科生
     */
    public static final String SEMESTER_BIZ_TYPE_ID = "bizTypeId";
    /**
     * 表示本科生的ID
     */
    public static final String SEMESTER_BACHELOR_ID = "2";

    /**
     * 用来解析课程表信息的URL
     */
    public static final String COURSE_TABLE_INFO_URL = "http://jxglstu.hfut.edu.cn/eams5-student/for-std/course-table/info/";

    /**
     * 用来获取课程信息的URL
     */
    public static final String COURSE_TABLE_DATA_URL = "http://jxglstu.hfut.edu.cn/eams5-student/for-std/course-table/get-data";

    /**
     * 教务系统登录的网页URL
     */
    public static final String JWXT_LOGIN_URL = "http://jxglstu.hfut.edu.cn/eams5-student/login";
    /**
     * 教务系统登录的时候用于获取盐值的接口URL
     */
    public static final String JWXT_LOGIN_SALT_URL = "http://jxglstu.hfut.edu.cn/eams5-student/login-salt";

    /**
     * 获取学生信息的接口
     */
    public static final String STUDENT_INFO_URL = "http://jxglstu.hfut.edu.cn/eams5-student/for-std/student-info";
    /**
     * 获取培养计划的接口
     */
    public static final String PROGRAM_URL = "http://jxglstu.hfut.edu.cn/eams5-student/for-std/program/root-module-json/";

    /**
     * 获取周课程信息的接口URL
     */
    public static final String SCHEDULE_DATUM_URL = "http://jxglstu.hfut.edu.cn/eams5-student/ws/schedule-table/datum";

    /**
     * cookie -- SRVID
     */
    public static final String COOKIE_SRVID = "SRVID";

    /**
     * cookie -- SESSION
     */
    public static final String COOKIE_SESSION = "SESSION";

}
