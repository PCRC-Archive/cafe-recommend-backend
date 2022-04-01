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
    List<RegionStationDto> Seoul;
    List<RegionStationDto> Busan;
    List<RegionStationDto> Daejeon;
    List<RegionStationDto> Daegu;
    List<RegionStationDto> Gwangju;
}
