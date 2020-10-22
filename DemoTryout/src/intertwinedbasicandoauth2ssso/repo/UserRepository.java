package com.example.demo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
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
    
    @Async(AppConfiguration.TASK_EXECUTOR_REPOSITORY)
    @Query("select e.firstName as "+ CustomEmployeeRs.USERNAME +"," +
            " e.lastName as " + CustomEmployeeRs.PASSWORD + ", " +
            " d.name as " + CustomEmployeeRs.ORGANIZATION_NAME +
            " from Employee e join e.department d") 
    public CompletableFuture<Page<List<Map<String, Object>>>> findUserDetails(final Pageable pageable); 

}

