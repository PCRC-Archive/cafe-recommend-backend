package pcrc.caferecommendbackend.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pcrc.caferecommendbackend.user.kakao_model.AuthorizationKakao;
import pcrc.caferecommendbackend.user.kakao_model.KakaoProfile;

@Service
@RequiredArgsConstructor
public class KakaoUserService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ObjectMapper objectMapper2;

    private final String kakaoOauth2ClinetId = 	"";
    private final String frontendRedirectUrl = "";

    /**
     * 카카오 서버로부터 code(인증코드)로 사용자 토큰 가져오기
     */
    public AuthorizationKakao callTokenApi(String code) throws JsonProcessingException {
        String grantType = "authorization_code";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", kakaoOauth2ClinetId);
        params.add("redirect_uri", frontendRedirectUrl + "/callback");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        AuthorizationKakao authorization = objectMapper.readValue(response.getBody(), AuthorizationKakao.class);
        return authorization;
    }

    /**
     * accessToken을 이용한 유저정보 받기
     */
    public KakaoProfile callGetUserByAccessToken(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        KakaoProfile kakaoProfile = objectMapper2.readValue(response.getBody(), KakaoProfile.class);
        return kakaoProfile;
    }

    /**
     * accessToken을 이용하여 kakao 계정 로그아웃
     */
    public void logout(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", kakaoOauth2ClinetId);
        params.add("redirect_uri", frontendRedirectUrl + "/logout");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v1/user/logout";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        //System.out.println("response: " + response.getStatusCode());
    }

    /**
     * accessToken을 이용하여 kakao 계정 탈퇴
     */
    public void unlink(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", kakaoOauth2ClinetId);
        params.add("redirect_uri", frontendRedirectUrl + "/unlink");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v1/user/unlink";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        //System.out.println("response: " + response.getStatusCode());
    }
}