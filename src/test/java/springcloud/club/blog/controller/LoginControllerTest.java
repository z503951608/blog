package springcloud.club.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAppQrcodeCreateRequest;
import com.alipay.api.response.AlipayOpenAppQrcodeCreateResponse;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;
import sun.security.provider.MD5;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LoginControllerTest {
    @Test
    public void md5() {
        System.out.print(DigestUtils.md5Hex("123456" + "admin"));
    }


    @Test
    public void queus() {
        try {
            File file = new File("C:\\Tools\\idea\\blog\\src\\test\\java\\springcloud\\club\\blog\\controller\\1.txt");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bfreader = new BufferedReader(reader);
            String line;
            while ((line = bfreader.readLine()) != null) {//包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
                String ss[] = line.split(" ");
                //System.out.println(ss[1]);
                System.out.println(ss[3]);


            }
        } catch (IOException e) {
        }


    }


    @Test
    public void queus1() {
        try {
            File file = new File("C:\\Tools\\idea\\blog\\src\\test\\java\\springcloud\\club\\blog\\controller\\2.txt");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bfreader = new BufferedReader(reader);
            String line;
            while ((line = bfreader.readLine()) != null) {//包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
                String ss[] = line.split(" ");
                //System.out.println(ss[1]);
                System.out.println(ss[3]);

            }
        } catch (IOException e) {
        }
    }


    @Test
    public void aa() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //String url = "http://114.67.238.159:15010/clb";
        String url = "http://192.168.1.1";
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置连接超时时间
        builder.connectTimeout(1, TimeUnit.SECONDS);
        builder.readTimeout(1, TimeUnit.SECONDS);
        builder.writeTimeout(1, TimeUnit.SECONDS);
        //设置代理,需要替换
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("118.89.51.66", 5000));
        //builder.proxy(proxy);
        MediaType parse = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(parse, "{\"method\":\"do\",\"login\":{\"password\":\"HH30aPwALeefbwK\"}}");
        Request request = new Request.Builder().url(url).post(body)
                .addHeader("Content-type", "application/json; charset=utf-8")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36")
                .addHeader("x-forwarded-for", "22")
                .build();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try (Response response = builder.build().newCall(request).execute()) {
                        System.out.println("输出" + response.body().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }


    @Test
    public void qrcode(){
        String URL = "https://openapi.alipay.com/gateway.do";
        String APP_ID = "2021001127674856";
        String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCQtOSiDUFuSIQELpGxUsEUQTpUBDSGSqjHjEGHYnOLMfowKlaytHA+ecWSpF6OXZsMcXdPptNrO2alizNcbkrUR8NYuHKuc6ASsDcmRrpCHtRlQo3eAJxXIkFs6EtKHKQ8c94ul36vuSlJ0RV2cgcIXD1VCPv655lUIUWiZCRz+ndCFbcZxKG5QbFLohAIW+I6PQDfaVgcjcF/r2tLOwsO3zsVirw7cjEC7Wl40crL3XnOs3Dk8p0Tw3IEpG5qXjbG5ciajX7TCECqQcihFY4ACYeYNQqqWg4uYhHAhXb8rSzUB23UdG5wfVFxOjxrbBUlo8HZuFw1vOkBphHMbhjRAgMBAAECggEAaInWFd//od1PSfoelHzVMX3B1opHK7Dp52bCvDx6fld3h64+fdFuuSOdgMev66dh6IZcDDXXzELYb8V1MFQZSOwjCogNiUvMm1OkM8mqqzO8ev6uoG/OggAkRbo3Ee5JE8cc/onBIs9gLYyXtb7Ap+CDClh4ORVxJl73AE7aKtHy3jPaGV4kTirO9s0Yo1g1Ypcz4ou/akoN8aeBEo0x1zLW/uSTQtMCpaNQea3c0YhbYTIusTbiMa2ljytYhVKyFYnbJGOkoFIL56ky6IQl0xjpkl/badDjzcZN4z6RIu0Un4BEb69k3lQz0YPhc/IxPT3JfDuSBFGVpk0BdeM9ZQKBgQDO5iUHCTMaoxfdCMSO9FnX1bQgdJN+V79BmneR5x+/rchgO72m8hAim0aV83Lx5kXiB6VuGp+sc5DH9e+AztRjPIKzupANCB6ePaMXIS4USiPHRWQLki4K5xwNyUx+3IJzQwS+G1IfOc+ojvLN6VVsJApY1Y/iDh/s3JC2VMYxXwKBgQCzDFaOZLZ1xtchyP/SION4X+RmfPkKVzkz0DsP/0oaDEkoAEDhk4Nd8gwMAPbuOc05/7lEB8y/0W46UgJ5KsWSodwSrEWoDwRdtPHn5DCRJ4uyj5LoXVq3VPPytWj4uSISDQBv+ThYiXYA4YCFK5qEZG4v7CDqqqmLvKWVKBHzzwKBgGu6kdUUCTP5b9d7VT1KJe2oLF4p1RKYBdHW6CNkQLfBePACq0FeEOC0ERDK7jLeP8ztRkoKOtHEasJzX/2ltBjnzPZgUdk3PRoP5feJI0EVSYPcsbcEqkyrAYR8KAR2NMZnQvTprNztcR7HL83fjMb+DRUsPfrqd90RC5ikk33pAoGAXcEvLb7E4CUvj2xdsJ0yH10vDw+PpH+J33zBAJ5wIfWe9Qg08Sm6Fx5geoSiyJmRwjYKv/nJmAPFUVqSGvpvnFmPEGQO0XKTmbHfHcsaN1faybRue6gukWM1njip79uZYJRYT0nT3GCWXt2124W6kmq4W/WsQtT8lMRCfDkQNXcCgYBjWppveHkw2YXkqJvdhlIoQ6kWBJykch372Xsz+MkG9xpuUVk1Yf3NtU45avapxz6x3HqJfh8jT2dyqAuFxvNs2JpMBmnk/lfFrDLBcikg7LZsQfPTwf5tUmWSh0JabAnPiM2ug3H7rvpXn0MK4LlKjwqr4Skf+h+e8iyOxXOoWQ==";
        String FORMAT= "{\n" +
                "\"name\": \"111\"\n" +
                "}";
        String CHARSET = "UTF-8";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiD7cb+10YVAZdGz5ytW057Jb+lyrWANyTE7RQQ0+g2c2meB9XDbdby9RlITO3rWS2fi8ZzpKa7FYVKztSoHm+6Db1o2wkPxe+BFtJ8j1uhCtv6wid9fBYUp4M58rqVsRxKP4/KMmMKI+qshqIsD6S1qtVXUAADG2JU1nA31UMoJH30THm7UT2KJ2Dvy3QnWFU51O3gUVthyF428GT8dEGWSxfZiJNy4RzH3g/6zlD+s2fPEMzj351Ta+JFpDYGQH/jSteIePVVrglVCiZqri5ZgqiWlPnmTV6ZZ8AwEMO7Dh+eZrrVV3OnHxQ/yu6wG/Hx6GQWPaRnbFYnfuxs487QIDAQAB";
        String SIGN_TYPE = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
        AlipayOpenAppQrcodeCreateRequest request = new AlipayOpenAppQrcodeCreateRequest();
        request.setBizContent("{" +
                "\"url_param\":\"pages/tasks/tasks\"," +
                "\"query_param\":\"x=1\"," +
                "\"describe\":\"二维码描述\"" +
                "  }");
        AlipayOpenAppQrcodeCreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

    }

    @Test
    public void abc(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置连接超时时间
        builder.connectTimeout(1, TimeUnit.SECONDS);
        builder.readTimeout(1, TimeUnit.SECONDS);
        builder.writeTimeout(1, TimeUnit.SECONDS);
        //设置代理,需要替换
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("118.89.51.66", 5000));
        //builder.proxy(proxy);
        MediaType parse = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(parse, "{\"method\":\"do\",\"login\":{\"password\":\"HH30aPwALeefbwK\"}}");
        Request request = new Request.Builder().url("https://www.baidu.com").post(body)
                .build();
        try {
            Response response = builder.build().newCall(request).execute();
            System.out.println("输出" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}