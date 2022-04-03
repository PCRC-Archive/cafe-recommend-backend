package pcrc.caferecommendbackend.cafe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResponse {
    String category;
    int cafeCount;
    List<CafeResponseDto> cafes;
}
