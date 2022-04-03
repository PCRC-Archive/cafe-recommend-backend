package pcrc.caferecommendbackend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Boolean success;

    private String nickname;

    private String profile_image_url;

    private String email;

    private String age_range;

    private String gender;

}
