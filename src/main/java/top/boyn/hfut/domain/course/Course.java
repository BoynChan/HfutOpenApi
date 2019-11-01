package top.boyn.hfut.domain.course;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class Course {
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 教师
     */
    private String[] teachers;
    /**
     * 课程代码
     */
    private String lesson_code;
    /**
     * 上课人数
     */
    private int number;
    /**
     * 在本周的哪一天
     */
    private Integer weekDay;
    /**
     * 课程开始的节数
     */
    private Integer startUnit;
    /**
     * 课程结束的节数
     */
    private Integer endUnit;
    /**
     * 课程开始时间
     */
    private String startTime;
    /**
     * 课程结束时间
     */
    private String endTime;
    /**
     * 需要上课的周数
     */
    private String activateWeek;
    /**
     * 上课周文本
     */
    private String weekInfo;
    /**
     * 校区
     */
    private String campus;
    /**
     * 教室名
     */
    private String classRoomName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return courseName.equals(course.courseName) &&
                weekDay.equals(course.weekDay);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", teachers=" + Arrays.toString(teachers) +
                ", lesson_code='" + lesson_code + '\'' +
                ", number=" + number +
                ", weekDay=" + weekDay +
                ", startUnit=" + startUnit +
                ", endUnit=" + endUnit +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", activateWeek='" + activateWeek + '\'' +
                ", weekInfo='" + weekInfo + '\'' +
                ", campus='" + campus + '\'' +
                ", classRoomName='" + classRoomName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, weekDay);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String[] getTeachers() {
        return teachers;
    }

    public void setTeachers(String[] teachers) {
        this.teachers = teachers;
    }

    public String getLesson_code() {
        return lesson_code;
    }

    public void setLesson_code(String lesson_code) {
        this.lesson_code = lesson_code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getStartUnit() {
        return startUnit;
    }

    public void setStartUnit(Integer startUnit) {
        this.startUnit = startUnit;
    }

    public Integer getEndUnit() {
        return endUnit;
    }

    public void setEndUnit(Integer endUnit) {
        this.endUnit = endUnit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivateWeek() {
        return activateWeek;
    }

    public void setActivateWeek(String activateWeek) {
        this.activateWeek = activateWeek;
    }

    public String getWeekInfo() {
        return weekInfo;
    }

    public void setWeekInfo(String weekInfo) {
        this.weekInfo = weekInfo;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }
}
