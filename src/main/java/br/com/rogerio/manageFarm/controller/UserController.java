package br.com.rogerio.manageFarm.controller;

import br.com.rogerio.manageFarm.model.User;
import br.com.rogerio.manageFarm.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.swagger.readers.operation.ResponseHeaders;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        Salvar usuário no sistema
     */
    @PostMapping(path = "/criar", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Armazena um registro de usuário.", notes = "Armazena o registro de user na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Recurso criado.", response = User.class),
            @ApiResponse(code = 400, message = "Servidor não entendeu a requisição, pois está com uma sintaxe inválida."),
            @ApiResponse(code = 403, message = "Sem permissão para acessar esse recurso."),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 405, message = "Servidor não suporta método/verbo enviado nesta requisição."),
            @ApiResponse(code = 406, message = "Servidor não identificou o Accept-* enviado."),
            @ApiResponse(code = 415, message = "O formato de média dos dados requisitados não é suportado pelo servidor."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<User> salvar(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        userService.create(user);
        URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    /*
        Alterar um usuário no sistema
     */
    @PutMapping(path = "/atualizar{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Altera um registro de usuário.", notes = "Altera o registro de user na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Recurso atualizado.", response = User.class),
            @ApiResponse(code = 400, message = "Servidor não entendeu a requisição, pois está com uma sintaxe inválida."),
            @ApiResponse(code = 403, message = "Sem permissão para acessar esse recurso."),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 405, message = "Servidor não suporta método/verbo enviado nesta requisição."),
            @ApiResponse(code = 406, message = "Servidor não identificou o Accept-* enviado."),
            @ApiResponse(code = 415, message = "O formato de média dos dados requisitados não é suportado pelo servidor."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<User> atualizar(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        User userExists = userService.findById(user.getId());

        if (userExists != null) {
            userService.update(user);
            URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    /*
        Retornar uma lista de usuário do sistema
     */
    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna uma lista de usuários.", notes = "Retorna uma lista de usuários da base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok.", response = User.class),
            @ApiResponse(code = 204, message = "Sem retorno de dados"),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<List<User>> listar() {
        List<User> lista = userService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    /*
        Excluir um usuário do sistema pelo seu identificador
     */
    @DeleteMapping(path = "/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui um registro de usuário.", notes = "Exclui um registro de usuário na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        User userExists = userService.findById(id);
        if (userExists != null) {
            userService.delete(id);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }

    /*
        Retornar uma lista de usuário do sistema pelo nome completo
     */
    @GetMapping(path = "/listar-por-nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna uma lista de usuários por nome.", notes = "Retorna uma lista de usuários por nome da base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok.", response = User.class),
            @ApiResponse(code = 204, message = "Sem retorno de dados"),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<List<User>> listarPorNome(@PathVariable String nome) {
        List<User> listaNome = userService.findByName(nome);
        if (listaNome.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listaNome);
    }

    /*
        Retornar uma lista de usuário do sistema pelo sobrenome
     */
    @GetMapping(path = "/listar-por-sobrenome/{sobrenome}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna uma lista de usuários pelo sobrenome.", notes = "Retorna uma lista de usuários pelo sobrenome da base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok.", response = User.class),
            @ApiResponse(code = 204, message = "Sem retorno de dados"),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<List<User>> listarPorSobreNome(@PathVariable String sobrenome) {
        List<User> listaSobrenome = userService.findBySobreNome(sobrenome);
        if (listaSobrenome.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listaSobrenome);
    }

    /*
        Consultar um usuário do sistema pelo seu identificador
     */
    @GetMapping(path = "/buscar-por-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna um usuário pelo identificador.", notes = "Retorna um usuário pelo identificador da base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok.", response = User.class),
            @ApiResponse(code = 204, message = "Sem retorno de dados"),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}
