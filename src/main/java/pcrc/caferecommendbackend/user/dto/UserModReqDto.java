package pcrc.caferecommendbackend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModReqDto {

    String profile_image_url;

    String nickname;

    @Email
    String email;
}
