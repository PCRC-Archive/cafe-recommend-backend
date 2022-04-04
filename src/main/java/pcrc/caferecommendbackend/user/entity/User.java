package pcrc.caferecommendbackend.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name="user")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "service")
    private Boolean service;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_image_url")
    private String profile_image_url;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")
    private String gender;

    @Builder
    public User(Long id, Boolean service, String nickname, String profile_image_url, String email, String birthday, String gender) {
        this.id = id;
        this.service = service;
        this.nickname = nickname;
        this.profile_image_url = profile_image_url;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }
}