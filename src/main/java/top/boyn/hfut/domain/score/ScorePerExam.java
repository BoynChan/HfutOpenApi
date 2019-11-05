package top.boyn.hfut.domain.score;


/**
 * 一门课中每一部分的成绩
 *
 * @author Boyn
 * @date 2019/10/1
 */
public class ScorePerExam {
    @Override
    public String toString() {
        return "ScorePerExam{" +
                "scoreText='" + scoreText + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getScoreText() {
        return scoreText;
    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 成绩,取score_test字段
     */
    private String scoreText;
    /**
     * 成绩类型
     */
    private String type;
}
