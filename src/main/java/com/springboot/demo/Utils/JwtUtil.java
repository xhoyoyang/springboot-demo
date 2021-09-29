package com.springboot.demo.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.springboot.demo.controller.auth.UserInfo;

import java.time.Duration;
import java.util.*;

public class JwtUtil {

    private static final String SERCERT = "demo";
    private static String SERCERT_KEY = "";
    private static final String SUBJECT = "demo";
    private static final long EXPIRE_TIME_DAYS = 7L;

    private static final String USER_PREFIX = "userInfo";

    private final static ObjectMapper MAPPER = new ObjectMapper();

    static {
        SERCERT_KEY = Base64.getEncoder().encodeToString(SERCERT.getBytes());
    }

    public static String generateToken(UserInfo userInfo) throws JsonProcessingException {
        String token = Jwts.builder()
                .setSubject(SUBJECT)
                //.claim("id",userInfo.getId())
                //.claim("userName",userInfo.getUsername())
                //.claim("roles",userInfo.getAuthorities())
                .claim(USER_PREFIX,MAPPER.writeValueAsString(userInfo))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ Duration.ofDays(EXPIRE_TIME_DAYS).toMillis()))
                .signWith(SignatureAlgorithm.HS256,SERCERT_KEY).compact();
        return token;
    }

    public static UserInfo parseToken(String token) throws JsonProcessingException {
        Claims claims = Jwts.parser().setSigningKey(SERCERT_KEY).parseClaimsJws(token).getBody();
        UserInfo userInfo = new UserInfo();

        //userInfo.setId((Integer)claims.get("id"));
        //userInfo.setUserName((String)claims.get("userName"));
        //List<Map<String,String>> list =  claims.get("roles",List.class);
        //Set<String> set = new HashSet<>();
        //list.forEach(item->{
        //    item.keySet().forEach(ele->{
        //        set.add(item.get(ele));
        //    });
        //});
        //userInfo.setRoles(set);

        userInfo = MAPPER.readValue(claims.get(USER_PREFIX).toString(),UserInfo.class);
        return userInfo;
    }

    public static boolean isExpiration(String token){

        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SERCERT_KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return false;
        }
        return claims.getExpiration().before(new Date());
    }
}
