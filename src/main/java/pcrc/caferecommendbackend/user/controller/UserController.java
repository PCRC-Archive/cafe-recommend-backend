package pcrc.caferecommendbackend.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pcrc.caferecommendbackend.user.SessionConst;
import pcrc.caferecommendbackend.user.dto.UserRequestDto;
import pcrc.caferecommendbackend.user.dto.UserResponseDto;
import pcrc.caferecommendbackend.user.kakao_model.AuthorizationKakao;
import pcrc.caferecommendbackend.user.kakao_model.KakaoProfile;
import pcrc.caferecommendbackend.user.service.KakaoUserService;
import pcrc.caferecommendbackend.user.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final KakaoUserService kakaoUserService;
    private final UserService userService;

    @PostMapping(value = "/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserRequestDto requestDto, HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException {
        System.out.println("<login>");
        if (requestDto.getService().equals("true")) {
            // get token and user profile
            AuthorizationKakao authorization = kakaoUserService.callTokenApi(requestDto.getCode());
            KakaoProfile userInfoFromKakao = kakaoUserService.callGetUserByAccessToken(authorization.getAccess_token());
            System.out.println("userInfoFromKakao = " + userInfoFromKakao);
            // join or login
            Boolean service = true;
            UserResponseDto responseDto = userService.join_or_login(userInfoFromKakao, service);
            //make session
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionConst.ACCESS_TOKEN, authorization.getAccess_token());
            session.setAttribute(SessionConst.USER, userInfoFromKakao.getId()); //id
            //세션에 자체 로그인인지 아닌지도 저장?
            session.setAttribute(SessionConst.SERVICE, true);
            System.out.println("id: " + session.getId());
            System.out.println("token: " + session.getAttribute(SessionConst.ACCESS_TOKEN));
            // set Cookie
            Cookie cookie = new Cookie("nickname", responseDto.getNickname());
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return null;
    }

    @PostMapping("/user/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        System.out.println("<logout>");
        // get session
        HttpSession session = request.getSession(false);
        System.out.println("id: " + session.getId());
        System.out.println("access_token: " + session.getAttribute(SessionConst.ACCESS_TOKEN));
        System.out.println("service: " + session.getAttribute(SessionConst.SERVICE));
        // kakao server logout
        kakaoUserService.logout(session.getAttribute(SessionConst.ACCESS_TOKEN).toString());
        UserResponseDto responseDto = UserResponseDto.builder().success(Boolean.TRUE).build();
        // invalidate session
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    // invalidate를 하고나서 브라우저 쿠키에는 세션 아이디가 남는데 우야누?

    @PostMapping(value = "/user/unlink")
    public ResponseEntity<?> unlink(HttpServletRequest request) throws JsonProcessingException {
        System.out.println("<unlink>");
        // get session
        HttpSession session = request.getSession(false);
        System.out.println("id: " + session.getId());
        System.out.println("access_token: " + session.getAttribute(SessionConst.ACCESS_TOKEN));
        System.out.println("user: " + session.getAttribute(SessionConst.USER));
        System.out.println("service: " + session.getAttribute(SessionConst.SERVICE));
        // kakao server unlink
        kakaoUserService.unlink(session.getAttribute(SessionConst.ACCESS_TOKEN).toString());
        // delete user info from DB
        UserResponseDto responseDto = userService.unlink((Long) session.getAttribute(SessionConst.USER), (Boolean) session.getAttribute(SessionConst.SERVICE));
        // invalidate session
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
