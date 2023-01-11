package kr.co.stylebot.collie.user.repository;


import kr.co.stylebot.collie.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Long countByDeleted(Integer deleted);
    Optional<User> findByUsername(String username);

    int countBy();


}
