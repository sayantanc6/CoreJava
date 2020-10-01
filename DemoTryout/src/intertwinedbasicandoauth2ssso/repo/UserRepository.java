package intertwinedbasicandoauth2ssso.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEmployee, Long> {
	UserEmployee findByUsername(final String username);
	UserEmployee findByEmail(String email);

    @Transactional
    void removeUserByUsername(String username);
    
    @Query("UPDATE UserEmployee u SET u.lastLogin=:lastLogin WHERE u.username = ?#{ principal?.username }")
    @Modifying
    @Transactional
    public void updateLastLogin(@Param("lastLogin") Date lastLogin);
    
    public boolean existsByEmail(String email);

}
