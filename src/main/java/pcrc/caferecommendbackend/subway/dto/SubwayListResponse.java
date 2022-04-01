package pcrc.caferecommendbackend.subway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubwayListResponse {
    List<RegionStationDto> seoul;
    List<RegionStationDto> busan;
    List<RegionStationDto> daejeon;
    List<RegionStationDto> daegu;
    List<RegionStationDto> gwangju;
}
