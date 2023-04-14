package com.gahyeonn.ssiach11s1.service;

import com.gahyeonn.ssiach11s1.entity.Otp;
import com.gahyeonn.ssiach11s1.entity.User;
import com.gahyeonn.ssiach11s1.repository.OtpRepository;
import com.gahyeonn.ssiach11s1.repository.UserRepository;
import com.gahyeonn.ssiach11s1.utils.GenerateCodeUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       OtpRepository otpRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
    }

    //사용자 추가 메서드
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user) {
        Optional<User> o = userRepository.findUserByUsername(user.getUsername());

        if (o.isPresent()) {
            User u = o.get();
            if (passwordEncoder.matches(
                    user.getPassword(),
                    u.getPassword())) {
                renewOtp(u); //암호가 맞으면 새로운 OTP 생성
            } else {
                throw new BadCredentialsException("Bad credential");
            }
        } else {
            throw new BadCredentialsException("Bad credential");
        }
    }

    private void renewOtp(User u) {
        String code = GenerateCodeUtil.generateCode(); //OTP를 위한 임으의 수 생성

        //사용자 이름으로 OTP 검색
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());

        if (userOtp.isPresent()) { //이 사용자 이름에 대한 OTP가 있으면 값 업데이트
            Otp otp = userOtp.get();
            otp.setCode(code);
        } else { //이 사용자에 대한 OTP가 없으면 생성된 값으로 새 레코드 생성
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

    //사용자의 OTP를 검증하는 메서드
    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(
                otpToValidate.getUsername());

        //디비에 OTP가 있고, 비즈니스 논리 서버에서 받은 OTP와 일치하면 True 반환
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }
        return false;
    }
}
