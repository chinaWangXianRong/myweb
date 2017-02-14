package pers.xianRong.web.util;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by user on 2016/12/22.
 */
@Component
public class VerificationCodeUtil {
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    @CachePut( "verification")
    public  String makeVerificationCode(String email){
        String result="";

        Random random = new Random();
        for (int i=0;i<4;i++){
            result=result+VERIFY_CODES.charAt(random.nextInt(31));
        }

        return result;
    }

    @Cacheable("verification")
    public String getEmailVerificationCode(String email){

        return null;
    }
}
