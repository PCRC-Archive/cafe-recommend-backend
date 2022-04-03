package pcrc.caferecommendbackend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pcrc.caferecommendbackend.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);

    Optional<User> findById(Long id);

    @Transactional
    void deleteById(Long id);

}
