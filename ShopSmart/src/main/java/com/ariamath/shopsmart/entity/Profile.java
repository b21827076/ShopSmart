package com.ariamath.shopsmart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name="profile",schema = "public")
@Data
@AllArgsConstructor
@Slf4j
@RequiredArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String profilePicture;
    private String banner;
    private String about;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User User;

    public Profile(User user) {
        this.id = user.getId();
        this.profilePicture = "./assets/helmetBro.jpg";
        this.banner = "./assets/bannerpiazza.png";
        this.about = "Hey, this looks empty!";
        this.User = user;
    }

/*
    @OneToMany(mappedBy = "rater")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private final Collection<Rating> ratedProfiles = new ArrayList<>();

    @OneToMany(mappedBy = "ratee")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private final Collection<Rating> profileRatings = new ArrayList<>();
 */
}
