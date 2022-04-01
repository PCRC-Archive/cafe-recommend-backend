package pcrc.caferecommendbackend.cafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pcrc.caferecommendbackend.cafe.dto.request.LocationSearchRequest;
import pcrc.caferecommendbackend.cafe.dto.request.NameSearchRequest;
import pcrc.caferecommendbackend.cafe.dto.request.StationSearchRequest;
import pcrc.caferecommendbackend.cafe.dto.response.CafeResponseDto;
import pcrc.caferecommendbackend.cafe.dto.response.SearchResponse;
import pcrc.caferecommendbackend.cafe.entity.Cafe;
import pcrc.caferecommendbackend.cafe.repository.CafeRepository;
import pcrc.caferecommendbackend.category.service.CategoryService;
import pcrc.caferecommendbackend.subway.service.SubwayService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;
    private final CategoryService categoryService;
    private final SubwayService subwayService;

    /** 지역에 의한 검색 메인 **/
    public SearchResponse getCafesByLocation(LocationSearchRequest request) {
        List<Cafe> fetchedCafes = cafeRepository.findByCityNameAndTownName(request.getCity(), request.getRegion()); // DB에서 가져와
        String category = request.getCategory();            // 요청 정보에서 사용자가 얻고자 하는 카테고리는 무엇인가?
        List<Cafe> resolvedCafes = new ArrayList<>();       // 2가지 조건에 부합하는 cafe를 담을 리스트 선언
        fetchedCafes.forEach( cafe -> {
            List<String> categoryListOfCafe = categoryService.detectCategory(cafe);      // 카테고리가 무엇인가
            if(categoryListOfCafe.contains(category)) {                                  // 해당 카테고리는 카페가 들어있는가?
                resolvedCafes.add(cafe);
            }
        });
        List<CafeResponseDto> cafes = new ArrayList<>();
        resolvedCafes.forEach( cafe -> {
            cafes.add(getOneResDtoByLocation(cafe, request.getCategory(), resolvedCafes.indexOf(cafe)));
        });
        return SearchResponse.builder()
                .category(request.getCategory())
                .cafeCount(resolvedCafes.size())
                .cafes(cafes)
                .build();
    }

    public CafeResponseDto getOneResDtoByLocation(Cafe cafe, String category, int index) {
        String cafeName = cafe.getBranchName().isEmpty() ?
                cafe.getCafeName() : cafe.getCafeName().concat(" ").concat(cafe.getBranchName());
        return CafeResponseDto.builder()
                .index(index)
                .cafeId(cafe.getCafeId())
                .name(cafeName)
                .address(cafe.getCafeAddress())
                .rating(cafe.getRating())
                .latitude(cafe.getLatitude())
                .longitude(cafe.getLongitude())
                .category(category)
                .build();
    }

    /** 이름에 의한 검색 메인 **/
    public SearchResponse getCafesByName(NameSearchRequest request) {
        List<Cafe> fetchedCafes = cafeRepository.findByCafeNameAndCityNameAndTownName(
                request.getSearchword(),
                request.getCity(),
                request.getRegion()
        ); // DB에서 가져와
        String category = request.getCategory();            // 요청 정보에서 사용자가 얻고자 하는 카테고리는 무엇인가?
        List<Cafe> resolvedCafes = new ArrayList<>();       // 3가지 조건에 부합하는 cafe를 담을 리스트 선언
        fetchedCafes.forEach( cafe -> {
            List<String> categoryListOfCafe = categoryService.detectCategory(cafe);      // 카테고리가 무엇인가
            if(categoryListOfCafe.contains(category)) {                                  // 해당 카테고리는 카페가 들어있는가?
                resolvedCafes.add(cafe);
            }
        });
        List<CafeResponseDto> cafes = new ArrayList<>();
        resolvedCafes.forEach( cafe -> {
            cafes.add(getOneResDtoByLocation(cafe, request.getCategory(), resolvedCafes.indexOf(cafe)));
        });
        return SearchResponse.builder()
                .category(request.getCategory())
                .cafeCount(resolvedCafes.size())
                .cafes(cafes)
                .build();
    }

    /** 지하철 역에 의한 검색 메인 **/
    public SearchResponse getCafesByStation(StationSearchRequest request) {

        // 요청 정보에 들어있는 지하철 역의 좌표를 알아낸다.
        Pair<Double, Double> stationCoordinate
                = subwayService.getStationCoordinate(request.getStation(), request.getRegion());

        // 위에서 얻어낸 좌표정보를 바탕으로 카페정보들을 받아온다.
        List<Cafe> fetchedCafes
                = cafeRepository.findCafesByStaion(stationCoordinate.getFirst(), stationCoordinate.getSecond());

        // 이제 카테고리
        List<Cafe> resolvedCafes = new ArrayList<>();
        fetchedCafes.forEach( cafe -> {
            List<String> categoryListOfCafe = categoryService.detectCategory(cafe);
            if(categoryListOfCafe.contains(request.getCategory())) {
                resolvedCafes.add(cafe);
            }
        });
        List<CafeResponseDto> cafes = new ArrayList<>();
        resolvedCafes.forEach( cafe -> {
            cafes.add(getOneResDtoByLocation(cafe, request.getCategory(), resolvedCafes.indexOf(cafe)));
        });
        return SearchResponse.builder()
                .category(request.getCategory())
                .cafeCount(resolvedCafes.size())
                .cafes(cafes)
                .build();
    }
}
