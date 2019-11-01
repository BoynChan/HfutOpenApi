package top.boyn.hfut.domain.program;

import java.util.List;

/**
 * 培养计划
 *
 * @author Boyn
 * @date 2019/11/1
 */
public class Program {
    /**
     * 通识教育
     */
    private List<ProgramItem> generalList;
    /**
     * 通识教育课学分
     */
    private String generalScore;
    /**
     * 专业必修
     */
    private List<ProgramItem> majorRequiredList;
    /**
     * 专业必修学分
     */
    private String majorRequiredScore;
    /**
     * 专业选修
     */
    private List<ProgramItem> majorSelectiveList;
    /**
     * 专业选修学分
     */
    private String majorSelectiveScore;
    /**
     * 实践
     */
    private List<ProgramItem> practiceList;
    /**
     * 实践学分
     */
    private String practiceScore;

    @Override
    public String toString() {
        return "Program{" +
                "generalList=" + generalList +
                ", generalScore='" + generalScore + '\'' +
                ", majorRequiredList=" + majorRequiredList +
                ", majorRequiredScore='" + majorRequiredScore + '\'' +
                ", majorSelectiveList=" + majorSelectiveList +
                ", majorSelectiveScore='" + majorSelectiveScore + '\'' +
                ", practiceList=" + practiceList +
                ", practiceScore='" + practiceScore + '\'' +
                '}';
    }

    public String getGeneralScore() {
        return generalScore;
    }

    public void setGeneralScore(String generalScore) {
        this.generalScore = generalScore;
    }

    public String getMajorRequiredScore() {
        return majorRequiredScore;
    }

    public void setMajorRequiredScore(String majorRequiredScore) {
        this.majorRequiredScore = majorRequiredScore;
    }

    public String getMajorSelectiveScore() {
        return majorSelectiveScore;
    }

    public void setMajorSelectiveScore(String majorSelectiveScore) {
        this.majorSelectiveScore = majorSelectiveScore;
    }

    public String getPracticeScore() {
        return practiceScore;
    }

    public void setPracticeScore(String practiceScore) {
        this.practiceScore = practiceScore;
    }

    public List<ProgramItem> getGeneralList() {
        return generalList;
    }

    public void setGeneralList(List<ProgramItem> generalList) {
        this.generalList = generalList;
    }

    public List<ProgramItem> getMajorRequiredList() {
        return majorRequiredList;
    }

    public void setMajorRequiredList(List<ProgramItem> majorRequiredList) {
        this.majorRequiredList = majorRequiredList;
    }

    public List<ProgramItem> getMajorSelectiveList() {
        return majorSelectiveList;
    }

    public void setMajorSelectiveList(List<ProgramItem> majorSelectiveList) {
        this.majorSelectiveList = majorSelectiveList;
    }

    public List<ProgramItem> getPracticeList() {
        return practiceList;
    }

    public void setPracticeList(List<ProgramItem> practiceList) {
        this.practiceList = practiceList;
    }

}



