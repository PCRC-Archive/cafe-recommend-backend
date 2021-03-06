package pcrc.caferecommendbackend.subway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pcrc.caferecommendbackend.subway.dto.SubwayListResponse;
import pcrc.caferecommendbackend.subway.entity.Country;
import pcrc.caferecommendbackend.subway.factory.StationFactory;
import pcrc.caferecommendbackend.subway.repository.SubwayRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayService {

    private final SubwayRepository subwayRepository;
    private final StationFactory stationFactory;

    public SubwayListResponse getSubwayStations() {
        return SubwayListResponse.builder()
                .seoul(stationFactory.createStationList("수도권"))
                .busan(stationFactory.createStationList("부산"))
                .daejeon(stationFactory.createStationList("대전"))
                .daegu(stationFactory.createStationList("대구"))
                .gwangju(stationFactory.createStationList("광주"))
                .build();
    }

    public Pair<Double, Double> getStationCoordinate(String stationName, String region) {
        List<Country> fetchedStations = subwayRepository.findByStationAndRegion(stationName, region);  // 가져 옴
        return Pair.of(fetchedStations.get(0).getLatitude(), fetchedStations.get(0).getLongitude());    // 반환.
    }

}
