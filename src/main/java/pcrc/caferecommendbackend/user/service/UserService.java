package pcrc.caferecommendbackend.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcrc.caferecommendbackend.user.dto.UserModReqDto;
import pcrc.caferecommendbackend.user.dto.UserResponseDto;
import pcrc.caferecommendbackend.user.entity.User;
import pcrc.caferecommendbackend.user.kakao_model.KakaoProfile;
import pcrc.caferecommendbackend.user.repository.UserRepository;

import java.util.Optional;

/**
 * kakao 계정만 고려
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * have to join or login
     */
    public UserResponseDto join_or_login(KakaoProfile userInfoFromKakao, Boolean service) {
        Long id = userInfoFromKakao.getId();
        Optional<User> result = userRepository.findById(id); //수정
        if (result.isEmpty())
            return join(userInfoFromKakao, service);
        else
            return login(result.get());
    }

    /**
     * Join
     */
    public UserResponseDto join(KakaoProfile userInfoFromKakao, Boolean service) {
        Long user_id = userInfoFromKakao.getId();
        String nickname = userInfoFromKakao.getProperties().getNickname();
        String image = userInfoFromKakao.getProperties().profile_image;
        String email = userInfoFromKakao.getKakao_account().email;
        String birthday = userInfoFromKakao.getKakao_account().birthday;
        String gender = userInfoFromKakao.getKakao_account().gender;
        StringBuffer tmp = new StringBuffer();
        if (birthday != null)
            birthday = tmp.append(birthday).insert(2, '/').toString();
        userRepository.save(User.builder()
                .id(user_id)
                .service(true)
                .nickname(nickname)
                .profile_image_url(image)
                .email(email)
                .birthday(birthday)
                .gender(gender)
                .build());
        return UserResponseDto.builder()
                .success(Boolean.TRUE)
                .user_id(user_id)
                .nickname(nickname)
                .profile_image_url(image)
                .email(email)
                .birthday(birthday)
                .gender(gender).build();
    }

    /**
     * Login
     */
    public UserResponseDto login(User user) {
        return UserResponseDto.builder()
                .success(Boolean.TRUE)
                .user_id(user.getId())
                .nickname(user.getNickname())
                .profile_image_url(user.getProfile_image_url())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .gender(user.getGender()).build();
    }

    /**
     * Unlink(탈퇴)
     */
    public UserResponseDto unlink(Long id, Boolean service) {
        userRepository.deleteById(id);
        return UserResponseDto.builder()
                .success(Boolean.TRUE).build();
    }

    /**
     * Modify (회원 개인 정보 수정)
     */
    public UserResponseDto modify(UserModReqDto requestDto, Long id, Boolean service) {
        userRepository.save(User.builder()
                .id(id)
                .email(requestDto.getEmail())
                .nickname(requestDto.getNickname())
                .profile_image_url(requestDto.getProfile_image_url())
                .build());
        return UserResponseDto.builder()
                .success(Boolean.TRUE).build();
    }
}
