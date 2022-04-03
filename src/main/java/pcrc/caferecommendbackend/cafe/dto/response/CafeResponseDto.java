package pcrc.caferecommendbackend.cafe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CafeResponseDto {
    int index;
    String cafeId;
    String name;
    String address;
    Double rating;
    Double latitude;
    Double longitude;
    String category;
}
