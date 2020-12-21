package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Jwt加密和解密
 */
public class JwtToken {

        private String secret="JO6HN3NGIU25G2FIG8V7VD6CK9B6T2Z5";

        private long expire=120*60*4L; //8小时

        /**
         * 生成jwt token
         */
        public String generateToken(String userId) {
            Date nowDate = new Date();
            Date expireDate = new Date(nowDate.getTime() + expire * 1000);

            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;    //把一段文件信息进行加密，产生加密字符串，并且设置过期时间

            return Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(userId)//被加密的字符串 （登录用户名称）
                    .setIssuedAt(nowDate)
                    .setExpiration(expireDate)   //过期时间
                    .signWith(signatureAlgorithm, secret.getBytes())  // new String(org.apache.commons.codec.binary.Base64.encodeBase64(secret.getBytes()) 把密钥用Base64加密
                    .compact();
            //.signWith(SignatureAlgorithm.HS512, secret)
            //.compact();
        }

    /**
     * 把generateToken产生的字符串进行解密,得到Claims对象
     * @param token
     * @return
     */
    public Claims getClaimByToken(String token) { //

            try {
                SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
                return Jwts.parser()
                        .setSigningKey(secret.getBytes())
                        .parseClaimsJws(token)
                        .getBody();
            } catch (Exception e) {
                System.out.println("jwt 解密失败");
                e.printStackTrace();;
                return null;
            }
        }

    /**
     * 在web环境中，获得客户端传递过来的  access_token值 ，
     * @param request
     * @return
     * @throws AuthenticationException
     */
    public  String getLoginUserId(HttpServletRequest request) throws AuthenticationException {
        //获得客户端的请求参数:access_token值 或从请求头中获得access_token值
            String access_token =  request.getParameter("access_token");
            if (access_token==null || access_token.equals("")){
                access_token =   request.getHeader("access_token");
            }
            ;
            Claims claims = getClaimByToken(access_token);//jwtToken.getClaimByToken(access_token);

            if (claims == null || com.util.JwtToken.isTokenExpired(claims.getExpiration()) ) {
                throw new AuthenticationException("access_token 过期");
            }
            //url :http://localhost:8080/rolegroup/listByPage
            //rolegroup
            //admin@operator/operatorList@rolegroup/list@dept/list@PHONEuser/list@PHONEifo/list@PHONEbill/list

            //获得用户帐号是第一个@符号前的内容

            String tokenVal  = claims.getSubject();//得到登录用户的帐号

            return tokenVal;
        }
    /*public Claims getClaimByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        String[] header = token.split("Bearer");
        token = header[1];
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            logger.debug("validate is token error ", e);
            return null;
        }
    }*/

        /**
         * token是否过期
         * @return  true：过期
         */
        public static boolean isTokenExpired(Date expiration) {
            return expiration.before(new Date());
        }

        // Getter && Setter
    }