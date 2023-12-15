package com.salestore.salesstore.repository;

import com.salestore.salesstore.model.UserTable;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {

//    @Query(value = "SELECT u FROM UserTable u WHERE u.login = :login OR u.email = :email") //Select é efetuado pelo nome da classe.
    Optional<UserTable> existsByLoginOrEmail(String login, String email);

    Optional<UserTable> findByLogin(String login);

    Optional<UserTable> findByName(String name);

    @Query("SELECT u FROM UserTable u WHERE u.email = :email AND u.id <> :id")
    Optional<UserTable> findByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);

}
