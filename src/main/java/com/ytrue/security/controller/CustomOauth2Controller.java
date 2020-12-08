package com.ytrue.security.controller;

import com.ytrue.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义token返回格式
 */
@RestController
@RequestMapping("oauth")
@Slf4j
public class CustomOauth2Controller {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * get方法的
     *
     * @param principal
     * @param map
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @GetMapping("token")
    public ResponseData<HashMap<String, Object>> getAccessToken(
            Principal principal,
            @RequestParam Map<String, String> map
    ) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, map).getBody());
    }

    /**
     * post方法的
     *
     * @param principal
     * @param map
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("token")
    public ResponseData<HashMap<String, Object>> postAccessToken(
            Principal principal,
            @RequestParam Map<String, String> map
    ) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, map).getBody());
    }

    /**
     * 自定义返回格式
     *
     * @param accessToken
     * @return
     */
    private ResponseData<HashMap<String, Object>> custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        HashMap<String, Object> data = new HashMap<>(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return ResponseData.success(data);
    }


//    此方法废弃，要调用两次网络请求，以及错误处理问题
//    @Autowired
//    private ServerConfig serverConfig;
//
//
//    /**
//     * 自定义返回token
//     *
//     * @param map
//     * @return
//     */
//    @PostMapping("/login")
//    public ResponseData<OAuth2AccessToken> login(@RequestParam Map<String, Object> map) {
//        MultiValueMap<String, Object> paramsMap = new LinkedMultiValueMap<>();
//        paramsMap.set("username", map.get("username"));
//        paramsMap.set("password", map.get("password"));
//        paramsMap.set("grant_type", map.get("grant_type"));
//        paramsMap.set("scope", map.get("scope"));
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin", "112233"));
//        String projectUrl = serverConfig.getUrl();
//        log.info(projectUrl);
//        OAuth2AccessToken token = restTemplate.postForObject(projectUrl + "/oauth/token", paramsMap, OAuth2AccessToken.class);
//        return ResponseData.success(token);
//    }
}
