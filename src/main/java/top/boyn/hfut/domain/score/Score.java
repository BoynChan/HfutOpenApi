package top.boyn.hfut.domain.score;


import java.util.List;

/**
 * 成绩实体类
 *
 * @author Boyn
 * @date 2019/10/1
 */
public class Score {
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 绩点
     */
    private String courseGp;
    /**
     * 学分
     */
    private String courseCredit;
    /**
     * 成绩,成绩的字段直接取json中的score_text而不是score
     */
    private String scoreText;
    /**
     * 每一门课中的细分成绩列表
     */
    private List<ScorePerExam> examGrades;

    @Override
    public String toString() {
        return "Score{" +
                "courseName='" + courseName + '\'' +
                ", courseGp='" + courseGp + '\'' +
                ", courseCredit='" + courseCredit + '\'' +
                ", scoreText='" + scoreText + '\'' +
                ", examGrades=" + examGrades +
                '}';
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseGp() {
        return courseGp;
    }

    public void setCourseGp(String courseGp) {
        this.courseGp = courseGp;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getScoreText() {
        return scoreText;
    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }

    public List<ScorePerExam> getExamGrades() {
        return examGrades;
    }

    public void setExamGrades(List<ScorePerExam> examGrades) {
        this.examGrades = examGrades;
    }
}
