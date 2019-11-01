package top.boyn.hfut.domain.program;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class ProgramItem {
    /**
     * 课程代码
     */
    private String code;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程类型
     */
    private String type;
    /**
     * 学分
     */
    private String score;
    /**
     * 开课学期
     */
    private String semesterFall;
    /**
     * 建议修读学期
     */
    private String semesterSuggest;
    /**
     * 是否必修
     */
    private String mustRead;
    /**
     * 开课部门
     */
    private String depart;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "ProgramItem{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", score='" + score + '\'' +
                ", semesterFall='" + semesterFall + '\'' +
                ", semesterSuggest='" + semesterSuggest + '\'' +
                ", mustRead='" + mustRead + '\'' +
                ", depart='" + depart + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSemesterFall() {
        return semesterFall;
    }

    public void setSemesterFall(String semesterFall) {
        this.semesterFall = semesterFall;
    }

    public String getSemesterSuggest() {
        return semesterSuggest;
    }

    public void setSemesterSuggest(String semesterSuggest) {
        this.semesterSuggest = semesterSuggest;
    }

    public String getMustRead() {
        return mustRead;
    }

    public void setMustRead(String mustRead) {
        this.mustRead = mustRead;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
