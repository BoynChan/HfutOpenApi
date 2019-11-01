package top.boyn.hfut.domain.studentInfo;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class StudentInfo {
    private String chineseName;
    private String englishName;
    private String gender;
    private String documentType;
    private String documentNumber;
    private String grade;
    private String educationLevel;
    private String studentType;
    private String depart;
    private String major;
    private String campus;
    private String admissionDate;
    private String graduateDate;
    private String province;
    private String gaoKaoScore;

    @Override
    public String toString() {
        return "StudentInfo{" +
                "chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", gender='" + gender + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", grade='" + grade + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", studentType='" + studentType + '\'' +
                ", depart='" + depart + '\'' +
                ", major='" + major + '\'' +
                ", campus='" + campus + '\'' +
                ", admissionDate='" + admissionDate + '\'' +
                ", graduateDate='" + graduateDate + '\'' +
                ", province='" + province + '\'' +
                ", gaoKaoScore='" + gaoKaoScore + '\'' +
                '}';
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getGaoKaoScore() {
        return gaoKaoScore;
    }

    public void setGaoKaoScore(String gaoKaoScore) {
        this.gaoKaoScore = gaoKaoScore;
    }
}
