package com.springboot.demo.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.springboot.demo.vo.UserInfo;

import java.util.*;

public class JwtUtil {

    private static final String SERCERT = "demo";
    private static String SERCERT_KEY = "";
    private static final String SUBJECT = "demo";
    private static final Integer EXPIRE_TIME_MINUTE = 10;

    static {
        SERCERT_KEY = Base64.getEncoder().encodeToString(SERCERT.getBytes());
    }

    public static String generateToken(UserInfo userInfo){
        String token = Jwts.builder()
                .setSubject(SUBJECT)
                .claim("id",userInfo.getId())
                .claim("userName",userInfo.getUsername())
                .claim("roles",userInfo.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+(EXPIRE_TIME_MINUTE*60*1000)))
                .signWith(SignatureAlgorithm.HS256,SERCERT_KEY).compact();
        return token;
    }

    public static UserInfo parseToken(String token){
        Claims claims = Jwts.parser().setSigningKey(SERCERT_KEY).parseClaimsJws(token).getBody();
        UserInfo userInfo = new UserInfo();
        userInfo.setId((Integer)claims.get("id"));
        userInfo.setUsername((String)claims.get("userName"));
        List<Map<String,String>> list =  claims.get("roles",List.class);
        Set<String> set = new HashSet<>();
        list.forEach(item->{
            item.keySet().forEach(ele->{
                set.add(item.get(ele));
            });
        });
        userInfo.setRoles(set);
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
