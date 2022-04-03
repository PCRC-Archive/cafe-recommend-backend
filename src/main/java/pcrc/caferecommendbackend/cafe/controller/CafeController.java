package pcrc.caferecommendbackend.cafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import pcrc.caferecommendbackend.cafe.dto.request.LocationSearchRequest;
import pcrc.caferecommendbackend.cafe.dto.request.NameSearchRequest;
import pcrc.caferecommendbackend.cafe.dto.request.StationSearchRequest;
import pcrc.caferecommendbackend.cafe.dto.response.SearchResponse;
import pcrc.caferecommendbackend.cafe.service.CafeService;

@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    /**
     * ★★★★ 그냥 지금은 @ModelAttribute 떄려박았으나 contentstype 확인 필요. ★★★★
     */
    @GetMapping("/search/region")
    public ResponseEntity<SearchResponse> getCafesByLocation(@ModelAttribute LocationSearchRequest request) {
        SearchResponse response = cafeService.getCafesByLocation(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 역 주변 그리고 카테고리에 의한 카페검색
     */
    @GetMapping("/search/station")
    public ResponseEntity<?> getCafesByStation(@ModelAttribute StationSearchRequest request) {
        SearchResponse response = cafeService.getCafesByStation(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 이름과 카테고리에 의한 카페검색
     */
    @GetMapping("/search/name")
    public ResponseEntity<?> getCafesByName(@ModelAttribute NameSearchRequest request) {
        SearchResponse response = cafeService.getCafesByName(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
