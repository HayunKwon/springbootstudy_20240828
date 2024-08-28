package com.project.springbootstudy.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.controller.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

@SpringBootTest //실제 스프링 컨테이너 등을 다 띄우고 테스트할 수 있도록 "통합테스트"로 진행합니다.
//Repository는 실제 DB와 연결을 한 후에 테스트하므로 통합테스트로 해야합니다.
//단위 테스트는 말그대로 해당 클래스만 딱 테스트하는것 ( service)

public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void 회원가입테스트() {

        User user = new User();
        user.setUserId("test002");
        user.setPassword("pass002");
        user.setAge(30);
        user.setGender("Male");
        user.setPhone("010-1231-1231");
        user.setHomeAddr("주소구 주소동 주소리");

        userRepository.save(user);
    }

    @Test
    public void 회원수정테스트() {

        User user = new User();
        user.setUserId("test002");
        user.setPassword("555555");
        user.setAge(50);
        user.setGender("female");
        user.setPhone("01022233231");
        user.setName("twtw");

        userRepository.save(user);
    }

    @Test
    public void 회원삭제테스트() {
        String userId = "test002";

        userRepository.deleteById(userId);
    }

    @Test
    public void 회원전체조회테스트() {
        List<User> users = userRepository.findAll();

        for(User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void 특정회원조회테스트() {
        String userId = "test001";
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            System.out.println(user.get().toString());
        } else {
            System.out.println("그런 사용자는 없습니다.");
        }
    }

    //로그인 기능도 테스트해볼까요?
}
