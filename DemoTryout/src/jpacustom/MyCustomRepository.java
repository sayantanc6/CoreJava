package jpacustom;

import java.io.Serializable;
import java.util.List;
// import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
// import org.springframework.scheduling.annotation.Async;

@NoRepositoryBean
public interface MyCustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

//	@Async
//    Future<List<T>> findByAttributeContainsText(String attributeName, String text); 
	
    List<T> findByAttributeContainsText(String attributeName, String text); 
}
