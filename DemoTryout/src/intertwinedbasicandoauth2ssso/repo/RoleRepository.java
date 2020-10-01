package intertwinedbasicandoauth2ssso.repo;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Id> {
	
	Role findByName(String name);

    void delete(Role role);
 
}
