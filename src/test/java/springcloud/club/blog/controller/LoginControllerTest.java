package springcloud.club.blog.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginControllerTest {
    @Test
    public void md5(){
        System.out.print(DigestUtils.md5Hex("123456" + "admin"));
    }
}