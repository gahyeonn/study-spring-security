package com.gahyeonn.ssiach6ex1.service;

import com.gahyeonn.ssiach6ex1.entity.User;
import com.gahyeonn.ssiach6ex1.model.CustomUserDetails;
import com.gahyeonn.ssiach6ex1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //예외 인스턴스를 만들기 위한 공급자 선언
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException(
                "Problem during authentication!"
        );

        User u = userRepository
                .findUserByUsername(username) //사용자를 포함한 Optional 인스턴스를 반환하거나 사용자가 없으면 비어있는 Optional 인스턴스를 반환
                .orElseThrow(s); //Optional 인스턴스가 비어있으면 정의된 공급자에서 생성한 예외를 투척하고, 그렇지 않으면 User 인스턴스 반환

        return new CustomUserDetails(u); //User 인스턴스를 CustomUsrDetails 데코레이터로 래핑하고 반환
    }
}
