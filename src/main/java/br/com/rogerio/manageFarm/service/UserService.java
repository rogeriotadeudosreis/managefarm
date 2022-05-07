package br.com.rogerio.manageFarm.service;

import br.com.rogerio.manageFarm.dto.UserUpdateDto;
import br.com.rogerio.manageFarm.model.User;
import br.com.rogerio.manageFarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private final ModelMapper mapper;

    @Transactional
    public User create(User user) {
        System.out.println(verifyUserExists(user));
        verifyUserExists(user);
        User userSave = repository.save(user);
        return userSave;
    }

    @Transactional
    public User update(UserUpdateDto dto) {
        User userUpdate = findById(dto.getId());

        userUpdate.setId(dto.getId());
        userUpdate.setName(dto.getName());
        userUpdate.setLastName(dto.getLastName());
        userUpdate.setUsername(dto.getUsername());
        userUpdate.setPhone(dto.getPhone());
        userUpdate.setPassword(dto.getPassword());

        return repository.save(userUpdate);
    }

    @Transactional
    public List<User> findAll() {
        List<User> listUsers = repository.findAll();
        if (listUsers.isEmpty()) {
            return null;
        }
        return listUsers;
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Transactional
    public List<User> findByName(String nome) {
        List<User> listUsersByNome = repository.findByNameIgnoreCase(nome);
        if (listUsersByNome.isEmpty()) {
            return null;
        }
        return listUsersByNome;
    }

    @Transactional
    public User findByEmail(String email) {
        Optional<User> userOptional = repository.findByUsernameIgnoreCase(email);
        User user = userOptional.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email informado."));
        return user;
    }

    @Transactional
    public User findById(Long id) {
        Optional<User> optional = repository.findById(id);
        User user = optional.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o identificador informado."));
        return user;
    }

    private boolean verifyUserExists(User user) {
        Optional<User> userOptional = repository.findByUsernameIgnoreCase(user.getUsername());
        if (userOptional.isPresent()) {
            throw new RuntimeException("Já existe um usuário no sistema com o email " + user.getUsername());
        }
        return false;
    }
}
