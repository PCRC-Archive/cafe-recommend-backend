package pcrc.caferecommendbackend.subway.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="station")
@Getter
@NoArgsConstructor
public class Country {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "역이름")
    private String station;

    @Column(name = "역지역")
    private String region;

    @Column(name = "위도")
    private double latitude;

    @Column(name = "경도")
    private double longitude;

    @Column(name = "호선")
    private String line;

}
