package com.project.springbootstudy.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.service.user.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void 회원가입_성공() {

    }

    @Test
    public void 회원가입_실패_사용자ID가_10자리를_초과하는_경우() {

        String userId = "aslknfslkjfdskljsdf";

        User user = new User();
        user.setUserId(userId); //나머지 필드는 별도로 세팅 안했습니다.

        UserService userService = new UserService();
        //UserService객체를 직접 생성한 이유?
        //현재 서버를 띄운게 아니에요.
        //그래서 Spring이 컨테이너에 객체를 모으는 활동도 하지 않습니다.

        //값 검증 (오류일 경우)
        //userService.join을 실행할 때 오류가 나는지 확인하는 문법입니다.

        //Assertions 기능 중에 하나로
        //userService.join 메서드를 실행했을때 예외가 발생한다면 이를 exception에 저장해줍니다.
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.join(user);
        });

        // 예외 메시지 또는 다른 속성 검증
        // assertEquals는 앞, 뒤 값이 동일한지 확인하는 검증용 데이터입니다.
        Assertions.assertEquals("사용자ID는 최대10자리여야 합니다.", exception.getMessage());

    }

    @Test //여긴 좀 더 깔끔하고 실무에서 사용하는 방식으로 작성해보겠습니다.
    public void 회원가입_실패_사용자_비밀번호가_8_10_자리_사이가_아닌경우() {

        //given
        String userId = "taewoo";
        String password = "1111122222321321132132123";
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        UserService userService = new UserService();

        //when
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.join(user);
        });

        //then
        Assertions.assertEquals(exception.getMessage() , "비밀번호는 최소 8자리 , 최대 10자리입니다.");
    }


}
