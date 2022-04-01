package pcrc.caferecommendbackend.subway.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcrc.caferecommendbackend.subway.dto.RegionStationDto;
import pcrc.caferecommendbackend.subway.entity.Country;
import pcrc.caferecommendbackend.subway.repository.SubwayRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationFactory {

    private final SubwayRepository subwayRepository;

    public List<RegionStationDto> createStationList(String region) {
        List<String> subwayLines = subwayRepository.findLines(region);
        List<RegionStationDto> regionSubways = new ArrayList<>();
        for(String line : subwayLines) {
            List<Country> stationList = subwayRepository.findByRegionAndLine(region, line);
            List<String> stations = new ArrayList<>();
            stationList.forEach(station -> {
                stations.add(station.getStation());
            });
            RegionStationDto response = new RegionStationDto(line, stations);
            regionSubways.add(response);
        }
        return regionSubways;
    }

}
