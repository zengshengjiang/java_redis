package com.zsj.java_redis;

import com.zsj.java_redis.utils.SendMailUtils;
import com.zsj.java_redis.utils.ValidateCodeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testMail {
    @Autowired
    private SendMailUtils sendMailUtils;
    
    @Test
    void test1(){
         sendMailUtils.sendMail("1821894145", ValidateCodeUtils.generateValidateCode(4).toString());
    }
    @Test
    void test2(){
        sendMailUtils.sendMail("1821894145", ValidateCodeUtils.generateValidateCode(4).toString());
    }
    @Test
    void test3(){
        sendMailUtils.sendMail("1821894145", ValidateCodeUtils.generateValidateCode(4).toString());
    }
}
