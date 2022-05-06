package br.com.rogerio.manageFarm.repository;

import br.com.rogerio.manageFarm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name like %:name%")
    List<User> findByNameIgnoreCase(@Param("name") String string);

//    @Query("SELECT u FROM User u WHERE u.username like :username")
    Optional<User> findByUsernameIgnoreCase(@Param("username") String email);
}
