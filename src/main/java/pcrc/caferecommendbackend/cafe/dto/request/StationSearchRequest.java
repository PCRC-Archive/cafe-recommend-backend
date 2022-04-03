package pcrc.caferecommendbackend.cafe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationSearchRequest {
    String station;
    String region;
    String category;
}
