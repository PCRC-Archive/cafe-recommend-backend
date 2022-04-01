package pcrc.caferecommendbackend.subway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pcrc.caferecommendbackend.subway.dto.SubwayListResponse;
import pcrc.caferecommendbackend.subway.service.SubwayService;

@RestController
@RequiredArgsConstructor
public class SubwayController {

    private final SubwayService subwayService;

    @GetMapping("/station")
    public ResponseEntity<SubwayListResponse> getStations() {
        SubwayListResponse response = subwayService.getSubwayStations();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
