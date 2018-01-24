package me.wuwenbin.blog.v201801.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import me.wuwenbin.blog.v201801.common.SessionParam;
import me.wuwenbin.blog.v201801.model.User;
import me.wuwenbin.blog.v201801.repository.ParamRepository;
import me.wuwenbin.blog.v201801.repository.UserRepository;
import me.wuwenbin.blog.v201801.service.QqLoginService;
import me.wuwenbin.modules.utils.http.R;
import me.wuwenbin.modules.utils.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static me.wuwenbin.blog.v201801.common.ParamKey.APP_ID;
import static me.wuwenbin.blog.v201801.common.ParamKey.APP_KEY;
import static me.wuwenbin.modules.utils.http.R.error;
import static me.wuwenbin.modules.utils.http.R.ok;

/**
 * created by Wuwenbin on 2018/1/23 at 12:33
 */
@Slf4j
@Service
public class QqLoginServiceImpl implements QqLoginService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ParamRepository paramRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public R login(String callbackDomain, String code) {
        try {
            String appId = paramRepository.findValueByName(APP_ID);
            String appKey = paramRepository.findValueByName(APP_KEY);
            Map<String, Object> p1 = Maps.hashMap("grant_type", "authorization_code", "client_id", appId, "client_secret", appKey, "code", code, "redirect_uri", callbackDomain);
            String resp1 = HttpUtil.get("https://graph.qq.com/oauth2.0/token", p1);
            String accessToken = resp1.substring(13, resp1.length() - 66);
            String callback = HttpUtil.get("https://graph.qq.com/oauth2.0/me", Maps.hashMap("access_token", accessToken));
            String openId = callback.substring(45, callback.length() - 6);
            Map<String, Object> p2 = Maps.hashMap("access_token", accessToken, "oauth_consumer_key", appId, "openid", openId);
            JSONObject json2 = JSONUtil.parseObj(HttpUtil.get("https://graph.qq.com/user/get_user_info", p2));
            if (json2.getInt("ret") == 0) {
                User user = userRepository.findByOpenIdAndEnable(openId, true);
                if (user != null) {
                    request.getSession().setAttribute(SessionParam.LOGIN_USER, user);
                    request.getSession().setMaxInactiveInterval(30 * 60);
                    return ok("授权成功！", "/");
                } else {
                    User lockedUser = userRepository.findByOpenIdAndEnable(openId, false);
                    if (lockedUser != null) {
                        return error("QQ登录授权失败，原因：用户已被锁定！");
                    }
                    String nickname = json2.getStr("nickname");
                    String avatar = json2.getStr("figureurl_qq_2").replace("http://", "https://");
                    User registerUser = User.builder().nickname(nickname).avatar(avatar).openId(openId).build();
                    User afterRegisterUser = userRepository.save(registerUser);
                    if (afterRegisterUser != null) {
                        request.getSession().setAttribute(SessionParam.LOGIN_USER, afterRegisterUser);
                        request.getSession().setMaxInactiveInterval(30 * 60);
                        return ok("授权成功！", "/");
                    } else {
                        return error("QQ登录授权失败，原因：注册失败！");
                    }
                }
            } else {
                String errorMsg = json2.getStr("msg");
                log.error("QQ登录授权失败，原因：{}", errorMsg);
                return error("QQ登录授权失败，原因：{}", errorMsg);
            }
        } catch (StringIndexOutOfBoundsException e) {
            log.error("[accessToken] 返回值有误！");
            return error("请求重复或返回 [accessToken] 值有误！");
        } catch (Exception e) {
            log.error("QQ登录授权失败，原因：{}", e);
            return error("QQ登录授权失败，原因：{}", e.getMessage());
        }
    }
}
