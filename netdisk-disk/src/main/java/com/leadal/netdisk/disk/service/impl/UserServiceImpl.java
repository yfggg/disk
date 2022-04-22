package com.leadal.netdisk.disk.service.impl;

import cn.hutool.json.JSONObject;
import com.leadal.netdisk.disk.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Value("${csa.user.cas.url}")
    private String url;

    public JSONObject getUser() {

        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        String cookie = getCookie("CASTGC");
        cookies.add(cookie);
        headers.put("CASTGC", cookies);
        HttpEntity<String> request = new HttpEntity(headers);

        ResponseEntity<String> response = new RestTemplate().postForEntity(url, request, String.class);

        JSONObject json = new JSONObject(response.getBody());
        return (JSONObject) json.get("content");
    }

    public static String getCookie(String name) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length == 0) {
            return null;
        }
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
                return c.getValue();
            }
        }
        return null;
    }

}
