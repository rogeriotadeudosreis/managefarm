package br.com.rogerio.manageFarm.controller;

import br.com.rogerio.manageFarm.model.User;
import br.com.rogerio.manageFarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/criar")
    public ResponseEntity<User> salvar(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        userService.create(user);
        URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/atualizar{id}")
    public ResponseEntity<User> atualizar (@RequestBody @ Valid User user, UriComponentsBuilder uriBuilder){
        User userExists =  userService.findById(user.getId());

        if (userExists != null){
            userService.update(user);
            URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<User>> listar() {
        List<User> lista = userService.findAll();
        if (lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir (@PathVariable Long id){
        User userExists = userService.findById(id);
        if (userExists != null){
            userService.delete(id);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista-por-nome/{nome}")
    public ResponseEntity<List<User>> listarPorNome (@PathVariable String nome){
        List<User> lista = userService.findByNome(nome);
        if (lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/lista-por-sobrenome/{sobrenome}")
    public ResponseEntity<List<User>> listarPorSobreNome (@PathVariable String sobrenome)  {
        List<User> lista = userService.findBySobreNome(sobrenome);
        if (lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<User> buscarPorId (@PathVariable Long id){
        User user =  userService.findById(id);
        return ResponseEntity.ok(user);
    }
}
