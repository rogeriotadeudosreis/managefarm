package br.com.rogerio.manageFarm.repository;

import br.com.rogerio.manageFarm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName (String nome);

    List<User> findBylastName (String sobrenome);
}
