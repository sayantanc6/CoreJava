package intertwinedbasicandoauth2ssso.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORGANIZATION")
public class Organization {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id" )
    private Collection<UserEmployee> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<UserEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<UserEmployee> employees) {
		this.employees = employees;
	}
}