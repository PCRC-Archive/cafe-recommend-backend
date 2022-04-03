package pcrc.caferecommendbackend.user.entity;

//import com.pcrc.caferecommendbackend.mypage.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    //@Id
    @Column(name = "service")
    private Boolean service;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_image_url")
    private String profile_image_url;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "age_range")
    private String age_range;

    @Column(name = "gender")
    private String gender;

    //@OneToMany(mappedBy = "user")
    //private List<Review> reviews = new ArrayList<>();

    public User(Long id, Boolean service, String nickname, String profile_image_url, String email, String age_range, String gender) {
        this.id = id;
        this.service = service;
        this.nickname = nickname;
        this.profile_image_url = profile_image_url;
        this.email = email;
        this.age_range = age_range;
        this.gender = gender;
    }
}



/*public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_image_url")
    private String profile_image_url;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "age_range")
    private String age_range;

    @Column(name = "gender")
    private String gender;

    public User(Long id, String user_id, String nickname, String profile_image_url, String email, String age_range, String gender) {
        this.id = id;
        this.userId = user_id;
        this.nickname = nickname;
        this.profile_image_url = profile_image_url;
        this.email = email;
        this.age_range = age_range;
        this.gender = gender;
    }
}*/
