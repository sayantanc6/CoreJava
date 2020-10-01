package withoutsso.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEmployee, Long> {
	UserEmployee findByUsername(final String username);

    @Transactional
    void removeUserByUsername(String username);

}
