package pcrc.caferecommendbackend.cafe.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cafe")
@Getter
@NoArgsConstructor
public class Cafe {
    @Id
    @Column(name="cafe_id")
    private String cafeId;

    @Column(name="상호명")
    private String cafeName;

    @Column(name="지점명")
    private String branchName;

    @Column(name="시도명")
    private String cityName;

    @Column(name="시군구명")
    private String townName;

    @Column(name="도로명주소")
    private String cafeAddress;

    @Column(name="위도")
    private double latitude;

    @Column(name="경도")
    private double longitude;

    @Column(name="rating")
    private double rating;      // 자바에서 double은 null을 가질 수 없다**/

    @Column(name="큰규모")
    private int largeCategory;

    @Column(name="테이크아웃")
    private int takeoutCategory;

    @Column(name="'소개팅'")
    private int sogayCategory;

    @Column(name="커피맛집")
    private int coffeeCategory;

    @Column(name="디저트맛집")
    private int desertCategory;

    @Column(name="인스타감성")
    private int instaCategory;

    @Column(name="조용한")
    private int quietCategory;

    @Column(name="데이트코스")
    private int dateCategory;

    @Column(name="드라이브")
    private int driveCategory;

    @Column(name="경치좋은")
    private int sightseeCategory;

    @Column(name="테마있는")
    private int temesCategory;

    @Column(name="공부맛집")
    private int learningCategory;
}
