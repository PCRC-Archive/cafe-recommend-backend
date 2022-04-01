package pcrc.caferecommendbackend.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pcrc.caferecommendbackend.cafe.entity.Cafe;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, String> {
    List<Cafe> findByCityNameAndTownName(String cityName, String townName);

    List<Cafe> findByCafeNameAndCityNameAndTownName(String cafeName, String cityName, String townName);

    @Query(value = "SELECT *, (6371*ACOS(COS(RADIANS(:latitude))*COS(RADIANS(위도))*COS(RADIANS(경도)-RADIANS(:longitude))\n" +
            "+SIN(RADIANS(:latitude))*SIN(RADIANS(위도)))) AS distance\n" +
            "FROM cafe\n" +
            "HAVING distance < 0.5 \n" +
            "ORDER BY distance DESC", nativeQuery = true)
    List<Cafe> findCafesByStaion(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
}
