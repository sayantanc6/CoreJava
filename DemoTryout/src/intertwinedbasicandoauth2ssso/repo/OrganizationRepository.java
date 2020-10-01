package intertwinedbasicandoauth2ssso.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    public Organization findByName(String name);
}
