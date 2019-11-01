package top.boyn.hfut.utils;

import top.boyn.hfut.domain.course.Course;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class CourseWeekParser {
    public static List<Course> parseCourseList(List<Course> courses) {
        List<Course> courseList = new LinkedList<>();
        for (Course course : courses) {
            courseList.add(parseSingleCourse(course));
        }
        return courseList;
    }

    public static Course parseSingleCourse(Course course) {
        if (course.getActivateWeek().isEmpty()) {
            if(course.getWeekInfo().isEmpty()) {
                course.setActivateWeek(parse(course.getWeekInfo()).toString());
            }
        }
        return course;
    }

    private static List<Integer> parse(String week){
        week = week.replaceAll("周","");
        week = week.replaceAll(" ","");
        List<Integer> weekList = new ArrayList<>();
        for (String s : week.split(",")) {
            if (s.contains("单")) {
                weekList.addAll(parseOddEven(s,1));
            } else if (s.contains("双")) {
                weekList.addAll(parseOddEven(s,0));
            } else if (s.contains("~")) {
                weekList.addAll(parseWeek(s));
            } else {
                weekList.add(parseSingle(s));
            }
        }
        System.out.println(weekList);
        return weekList;
    }

    private static List<Integer> parseOddEven(String week,int oe){
        int LeftParenthesisIndex;
        String[] oddEven = new String[]{"双", "单"};
        LeftParenthesisIndex = week.indexOf(oddEven[oe])-1;
        week = week.substring(0, LeftParenthesisIndex);
        List<Integer> weekList = new ArrayList<>();
        for(int i : parseWeek(week)){
            if (i%2 == oe){
                weekList.add(i);
            }
        }
        return weekList;
    }

    //处理单周的情况
    private static int parseSingle(String week) {
        return Integer.parseInt(week);
    }

    // 处理1~10这样的序列
    private static List<Integer> parseWeek(String week){
        int waveIndex = week.indexOf('~');
        int frontNumber = Integer.parseInt(week.substring(0, waveIndex));
        int backNumber = Integer.parseInt(week.substring(waveIndex + 1));
        List<Integer> weekList = new ArrayList<>();
        for (int i = frontNumber; i <= backNumber ; i++) {
            weekList.add(i);
        }
        return weekList;
    }
}
