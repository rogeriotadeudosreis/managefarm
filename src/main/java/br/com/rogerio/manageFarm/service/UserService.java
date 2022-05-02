package br.com.rogerio.manageFarm.service;

import br.com.rogerio.manageFarm.model.User;
import br.com.rogerio.manageFarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(User user){
        User userSave = repository.save(user);
        return userSave;
    }
}
