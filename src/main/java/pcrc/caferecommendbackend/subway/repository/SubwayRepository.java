package pcrc.caferecommendbackend.subway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pcrc.caferecommendbackend.subway.entity.Country;

import java.util.List;

public interface SubwayRepository extends JpaRepository<Country, Long> {

    List<Country> findByStationAndRegion(String station, String region);

    List<Country> findByRegionAndLine(String region,String line);

    @Query(value = "SELECT DISTINCT s.호선 FROM station s where s.역지역 = :region", nativeQuery = true)
    List<String> findLines(@Param("region") String region);
}
