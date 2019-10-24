package com.pointcontrol.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.pointcontrol.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, Long> {
    
    @Query(value = "SELECT * FROM users WHERE login = :login AND active = 1", nativeQuery = true)
    Optional<Users> findByLogin(
        @Param("login") String login
    );
    
    @Query(value = "SELECT active FROM users WHERE login = :login", nativeQuery = true)
    Boolean existsByLogin(
        @Param("login") String login
    );

    @Query(value = "SELECT MAX(codUser) FROM users", nativeQuery = true)
    Integer getLastCodUser();
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET active =:active WHERE codUser = :codUser", nativeQuery = true)
    int visibility(
        @Param("codUser") Integer codUser,
        @Param("active") Boolean active
    );
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET password =:password WHERE codUser = :codUser", nativeQuery = true)
    int updatePassword(
        @Param("codUser") Integer codUser, 
        @Param("password") String password
    );
    
    @Override
    Page<Users> findAll(
        Pageable pageable
    );
    
    Page<Users> findByNameIgnoreCaseContaining(
        String name, 
        Pageable pageable
    );
}