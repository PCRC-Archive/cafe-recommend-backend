package pcrc.caferecommendbackend.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcrc.caferecommendbackend.user.dto.UserResponseDto;
import pcrc.caferecommendbackend.user.entity.User;
import pcrc.caferecommendbackend.user.kakao_model.KakaoProfile;
import pcrc.caferecommendbackend.user.repository.UserRepository;

import java.util.Optional;

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
        //System.out.println("user-join");
        String user_id = userInfoFromKakao.getProperties().nickname + '_' + userInfoFromKakao.getId().toString();
        User user = new User(userInfoFromKakao.getId(),
                service,
                userInfoFromKakao.getProperties().getNickname(),
                userInfoFromKakao.getProperties().profile_image,
                userInfoFromKakao.getKakao_account().email,
                userInfoFromKakao.getKakao_account().age_range,
                userInfoFromKakao.getKakao_account().gender);
        userRepository.save(user);
        return UserResponseDto.builder()
                .success(Boolean.TRUE)
                .nickname(user.getNickname())
                .profile_image_url(user.getProfile_image_url())
                .email(user.getEmail())
                .age_range(user.getAge_range())
                .gender(user.getGender()).build();
    }

    /**
     * Login
     */
    public UserResponseDto login(User user) {
        //System.out.println("user-login");
        return UserResponseDto.builder()
                .success(Boolean.TRUE)
                .nickname(user.getNickname())
                .profile_image_url(user.getProfile_image_url())
                .email(user.getEmail())
                .age_range(user.getAge_range())
                .gender(user.getGender()).build();
    }

    /**
     * Unlink(탈퇴)
     */
    public UserResponseDto unlink(Long id, Boolean service) {
        userRepository.deleteById(id);//수정
        return UserResponseDto.builder()
                .success(Boolean.TRUE).build();
    }
}
