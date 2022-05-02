package br.com.rogerio.manageFarm.controller;

import br.com.rogerio.manageFarm.model.User;
import br.com.rogerio.manageFarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<User> save(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        userRepository.save(user);

        URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }
}
