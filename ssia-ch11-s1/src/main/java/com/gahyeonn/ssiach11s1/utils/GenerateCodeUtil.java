package com.gahyeonn.ssiach11s1.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//OTP code 생성 시에 사용
public final class GenerateCodeUtil {

    public GenerateCodeUtil() {
    }

    public static String generateCode() {
        String code;

        try {
            //임의의 int 값을 생성하는 SecureRandom이 인스턴스를 만든다.
            SecureRandom random = SecureRandom.getInstanceStrong();

            //0~8,999 사이의 값을 생성하고 1000을 더해서 1000~9999 사이의 값을 얻는다.
            int c = random.nextInt(9000) + 1000;

            code = String.valueOf(c);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code.");
        }

        return code;
    }
}
