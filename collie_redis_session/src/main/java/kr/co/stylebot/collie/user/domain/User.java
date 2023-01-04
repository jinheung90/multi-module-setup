package kr.co.stylebot.collie.user.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true, name = "user_login_id")
    private String username = "";

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String phoneNum;

    @Column
    private String password = "";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authorities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id",
                    foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)
            )},
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_name", referencedColumnName = "authority_name",
                            foreignKey =  @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))})
    private List<Authority> authorities;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime disabledAt;

    @Column(name = "is_deleted", columnDefinition = "boolean DEFAULT 0")
    private Boolean deleted = false;

    public void disableUser() {
        this.deleted = true;
        this.disabledAt = LocalDateTime.now();
    }
}
