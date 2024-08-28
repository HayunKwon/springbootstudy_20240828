package com.project.springbootstudy.service.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.controller.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //사용자 회원가입
    public void join(User user) throws RuntimeException {

        //사용자ID는 최대 10자리다.
        if(user.getUserId().length() > 10) {
            throw new RuntimeException("사용자ID는 최대10자리여야 합니다.");
            //throw : 예외를 반환합니다.
            //그런데 이 예외를 UserService 클래스에서 처리하지 않고. Controller에서 처리할 수 있도록
            // 예외를 던질 겁니다. throws
        }

        if(user.getPassword().length() < 8 || user.getPassword().length() > 10) {
            throw new RuntimeException("비밀번호는 최소 8자리 , 최대 10자리입니다.");
        }

        //비밀번호는 영문자 , 숫자 혼용이어야 한다.
        int characterCount = 0 ;
        int numberCount = 0 ;

        for(int i = 0; i<user.getPassword().length(); i++) {

            if(user.getPassword().charAt(i) >= 'a' && user.getPassword().charAt(i) <= 'z') {
                characterCount++;
            } else if(user.getPassword().charAt(i) >= 'A' && user.getPassword().charAt(i) <= 'Z') {
                characterCount++;
            } else if(user.getPassword().charAt(i) >= '0' && user.getPassword().charAt(i) <= '9') {
                numberCount++;
            }
        }

        if(characterCount == 0 || numberCount == 0) {
            throw new RuntimeException("비밀번호는 영문자 , 숫자가 혼용되어야 합니다.");
        }

        //연락처에 '-'가 있다면 빼준다 (이경우에는 오류가 아닌 걸로 처리)
        user.setPhone( user.getPhone().replace("-" , ""));

        for(int i = 0 ; i<user.getPhone().length(); i++) {
            if(user.getPhone().charAt(i) >= '0' && user.getPhone().charAt(i) <= '9') {
                //정상케이스
            } else {
                //정상이 아닌 케이스
                throw new RuntimeException("연락처는 숫자와 - 만 가능합니다.");
            }
        }
        //사용자 요구사항 끝

        userRepository.save(user);
    }

    public boolean login(String userId , String password) throws RuntimeException {

        //DB에 아래와 같은 데이터가 있다고 가정~~
        HashMap<String , String> userInfo = new HashMap<>();
        userInfo.put("test001" , "pass001");
        userInfo.put("test002" , "pass002");
        userInfo.put("test003" , "pass003");
        userInfo.put("test004" , "pass004");
        userInfo.put("test005" , "pass005");

        if(userInfo.get(userId) == null) {
            return false;
        }

        if(!userInfo.get(userId).equals(password)) {
            return false ;
        }

        return true;
    }
}
