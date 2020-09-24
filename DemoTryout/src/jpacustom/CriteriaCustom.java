package jpacustom;

import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

public class CriteriaCustom {
	
	@Autowired
	@PersistenceContext      // to tell EntityManager use transaction-scoped  PersistenceContext by default.
	EntityManager em;
	
	@Autowired
	Projection prj;
	
	public void name() {
		// plain a single table
		TypedQuery<DummyEntity> query = em.createQuery("SELECT e FROM DUMMY_ENTITY e", DummyEntity.class);
		  List<DummyEntity> results = query.getResultList();
				
		// with named parameters..
		TypedQuery<DummyEntity> query2 = em.createQuery("SELECT e FROM DUMMY_ENTITY d WHERE d.A1 = :name" , DummyEntity.class);
		DummyEntity employee = query.setParameter("name", "b1").getSingleResult();
		
		/* use CriteriaQuery,CriteriaBuilder and Predicate */
		
		CriteriaBuilder cb = em.getCriteriaBuilder();         // to build criteria query
		CriteriaQuery<DummyEntity> cq = cb.createQuery(DummyEntity.class);     // to create a criteria query
		Root<DummyEntity> entity = cq.from(DummyEntity.class);                // to fetch DummyEntity which is equivalent FROM clause
		cq.select(entity); 													// to select DummyEntity 
		cq.select(entity.get("A1")); 											// to select DummyEntity from A1 column
		cq.multiselect(entity.get("A1"),entity.get("A2")); 						// to select DummyEntity from A1,A2 column
		cq.select(entity).distinct(true); 										// to select distinct from DummyEntity from A1 column
		
		// setting expressions (used in select clause)
		Expression<Number> select = entity.get("A1");
		Expression<Number> maximum = cb.max(select);
		Expression<Number> minimum = cb.min(select);
		Expression<Date> fetchDate = entity.get("fetchDate").as(Date.class);
		
		// setting predicates like conditions (used in where clause)
		Predicate pStation = cb.equal(entity.get("name"), "");
		Predicate pError = cb.equal(entity.get("errorState"), 0);
		Predicate pTime = cb.between(entity.get("fetchDate").as(Date.class), Date.valueOf("a"), Date.valueOf("a"));  
		Predicate pNotNull = cb.notEqual(entity.get("A1"), entity.get("A1")); 
		
		cq.multiselect(fetchDate, select, maximum, minimum); 					// to SELECT   
		cq.where(pStation,pError,pTime,pNotNull); 							// setting conditions WHERE clause
		cq.groupBy(fetchDate);  					// grouping should be done by the year of the fetchDate
		cq.orderBy(cb.asc(fetchDate));                 // setting sort with ascending order using fetchDate
		
		TypedQuery<DummyEntity> tq = em.createQuery(cq);          // to create TypedQuery
		List<DummyEntity> list = em.createQuery(cq).getResultList();      // to get list of results
		Stream<DummyEntity> stream = em.createQuery(cq).getResultStream();  // to gt streams of results
		
		/* using joins*/
		DummyEntity de = new DummyEntity();
		ListJoin<DummyEntity, DemoEntity> dummyJoin = entity.join(de.demos);
	}
	
	public  Specification<DummyEntity> nameA(String name) {
		return new Specification<DummyEntity>() {
			@Override
			public Predicate toPredicate(Root<DummyEntity> root, CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				prj.se
				ListJoin<DummyEntity, DemoEntity> dummyJoin = root.join(root.demos);
				return criteriaBuilder.equal(dummyJoin.get(root.demos), name);
			}
		};
	}
}
