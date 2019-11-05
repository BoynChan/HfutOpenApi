package top.boyn.hfut.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import top.boyn.hfut.Credential;
import top.boyn.hfut.constant.JWXT_CONSTANT;
import top.boyn.hfut.domain.score.Score;
import top.boyn.hfut.domain.score.ScorePerExam;
import top.boyn.hfut.domain.semester.Semester;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Boyn
 * @date 2019/11/5
 */
public class ScoreCrawler {
    public static List<Score> getScoreList(Semester semester, Credential credential) throws IOException {
        String jwxtCode = semester.getJwxtCode();
        Map<String, String> cookieMap = credential.getCookie();
        String stuId = credential.getStuId();
        Connection.Response response = Jsoup.connect(JWXT_CONSTANT.SCORE_URL + stuId)
                .cookies(cookieMap)
                .ignoreContentType(true)
                .data("semester", jwxtCode)
                .method(Connection.Method.GET)
                .execute();
        Elements elements = response.parse().select("body > div > div > div:nth-child(2) > table > tbody > tr");
        List<Score> scoreList = new LinkedList<>();
        for (String s : elements.eachText()) {
            String[] scoreString = s.split(" ");
            Score score = new Score();
            score.setCourseName(scoreString[0]);
            score.setCourseCredit(scoreString[3]);
            score.setCourseGp(scoreString[4]);
            score.setScoreText(scoreString[5]);
            List<ScorePerExam> scorePerExams = new LinkedList<>();
            for (int i = 6; i < scoreString.length; i++) {
                String exam = scoreString[i];
                ScorePerExam scorePerExam = new ScorePerExam();
                scorePerExam.setType(exam.split(":")[0]);
                scorePerExam.setScoreText(exam.split(":")[1]);
                scorePerExams.add(scorePerExam);
            }
            score.setExamGrades(scorePerExams);
            scoreList.add(score);
        }
        return scoreList;
    }
}
