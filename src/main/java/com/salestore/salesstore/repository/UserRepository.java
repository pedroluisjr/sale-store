package com.salestore.salesstore.repository;

import com.salestore.salesstore.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {

    @Query(value = "SELECT u FROM UserTable u WHERE u.login = :login OR u.email = :email") //Select é efetuado pelo nome da classe.
    Optional<UserTable> findUser(String login, String email);

    @Query(value = "SELECT u FROM UserTable u WHERE u.login = :login")
    Optional<UserTable> findByLogin(String login);

}
