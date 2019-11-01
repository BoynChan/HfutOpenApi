package top.boyn.hfut.crawler;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import top.boyn.hfut.constant.JWXT_CONSTANT;
import top.boyn.hfut.exception.UserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class LoginCrawler {
    public static Map<String,String> login(String username, String password) throws IOException {
        String cookieSrvid = Jsoup
                .connect(JWXT_CONSTANT.JWXT_LOGIN_URL)
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .execute()
                .cookie(JWXT_CONSTANT.COOKIE_SRVID);
        Connection.Response response = Jsoup
                .connect(JWXT_CONSTANT.JWXT_LOGIN_SALT_URL)
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .cookie(JWXT_CONSTANT.COOKIE_SRVID, cookieSrvid)
                .execute();
        String saltText = response.body();
        String cookieSession = response.cookie(JWXT_CONSTANT.COOKIE_SESSION);
        Digester digester = new Digester(DigestAlgorithm.SHA1);
        String transferPassword = digester.digestHex(saltText + "-" + password);
        JSONObject payload = new JSONObject();
        payload.put("captcha", "");
        payload.put("password", transferPassword);
        payload.put("username", username);
        Connection.Response loginResult = Jsoup.connect(JWXT_CONSTANT.JWXT_LOGIN_URL)
                .cookie(JWXT_CONSTANT.COOKIE_SRVID,cookieSrvid)
                .cookie(JWXT_CONSTANT.COOKIE_SESSION,cookieSession)
                .requestBody(payload.toJSONString())
                .header("Content-Type", "application/json")
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute();
        if (!JSON.parseObject(loginResult.body()).getBooleanValue("result")) {
            throw new UserException("用户名或密码错误");
        }
        Map<String,String> cookieMap = new HashMap<>(2);
        cookieMap.put(JWXT_CONSTANT.COOKIE_SRVID,cookieSrvid);
        cookieMap.put(JWXT_CONSTANT.COOKIE_SESSION,cookieSession);
        return cookieMap;
    }
}
