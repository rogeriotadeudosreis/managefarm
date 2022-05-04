package br.com.rogerio.manageFarm.service;

import br.com.rogerio.manageFarm.model.User;
import br.com.rogerio.manageFarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User create(User user){
        if (user.getId() == null){
            User userSave = repository.save(user);
            return userSave;
        }
        return user;
    }

    @Transactional
    public User update(User user){
        findById(user.getId());
        User userUpdate = repository.save(user);
        return userUpdate;
    }

    @Transactional
    public List<User> findAll(){
        List<User> listUsers = repository.findAll();
        if (listUsers.isEmpty()){
            return null;
        }
        return listUsers;
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        repository.deleteById(id);
    }

    @Transactional
    public List<User> findByName(String nome){
        List<User> listUsersByNome = repository.findByName(nome);
        if (listUsersByNome.isEmpty()){
            return null;
        }
        return listUsersByNome;
    }

    @Transactional
    public List<User> findBySobreNome (String nome){
        List<User> listUsersBySobreNome = repository.findBylastName(nome);
        if (listUsersBySobreNome.isEmpty()){
            return null;
        }
        return listUsersBySobreNome;
    }

    @Transactional
    public User findById(Long id){
        Optional<User> optional = repository.findById(id);
            User user = optional.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            return user;
    }
}
