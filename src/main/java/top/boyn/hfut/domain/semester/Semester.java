package top.boyn.hfut.domain.semester;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class Semester {
    /**
     * App代码
     */
    private String appCode;
    /**
     * 教务系统代码
     */
    private String jwxtCode;
    /**
     * 学期的名称
     */
    private String name;
    /**
     * 学期的总周数
     */
    private Integer weeks;
    /**
     * 学期开始的日期
     */
    private String startDate;
    /**
     * 学期开始的日期
     */
    private String endDate;
    /**
     * 当前学年
     */
    private Integer semesterYear;
    /**
     * 学年的第一部分还是第二部分,用1,2表示
     */
    private Integer semesterPart;

    @Override
    public String toString() {
        return "Semester{" +
                "appCode='" + appCode + '\'' +
                ", jwxtCode='" + jwxtCode + '\'' +
                ", name='" + name + '\'' +
                ", weeks=" + weeks +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", semesterYear=" + semesterYear +
                ", semesterPart=" + semesterPart +
                '}';
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getJwxtCode() {
        return jwxtCode;
    }

    public void setJwxtCode(String jwxtCode) {
        this.jwxtCode = jwxtCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(Integer semesterYear) {
        this.semesterYear = semesterYear;
    }

    public Integer getSemesterPart() {
        return semesterPart;
    }

    public void setSemesterPart(Integer semesterPart) {
        this.semesterPart = semesterPart;
    }
}
