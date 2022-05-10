package br.com.rogerio.manageFarm.controller;

import br.com.rogerio.manageFarm.dto.UserDto;
import br.com.rogerio.manageFarm.dto.UserUpdateDto;
import br.com.rogerio.manageFarm.model.User;
import br.com.rogerio.manageFarm.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    private final ModelMapper mapper;

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
    public ResponseEntity<UserDto> salvar(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        User userSalvo = userService.create(user);
        UserDto dto = mapper.map(userSalvo, UserDto.class);
        URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /*
        Alterar um usuário no sistema
     */
    @PutMapping(path = "/atualizar/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<UserDto> atualizar(@PathVariable Long id, @RequestBody @Valid UserUpdateDto dto) {
        dto.setId(id);
        User userAtualizado = userService.update(dto);
        UserDto userDto = mapper.map(userAtualizado, UserDto.class);
        return ResponseEntity.ok(userDto);
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
    public ResponseEntity<Page<UserDto>> listar(
            @RequestParam(required = false) String descricao,
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {

        if (descricao == null) {
            Page<UserDto> lista = userService.findAll(paginacao);
            return ResponseEntity.ok(lista);
        } else {
            Page<UserDto> lista = userService.findByName(descricao, paginacao);
            return ResponseEntity.ok(lista);
        }
    }

    /*
        Excluir um usuário do sistema pelo seu identificador
     */
    @DeleteMapping(path = "/excluir/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Exclui um registro de usuário.", notes = "Exclui um registro de usuário na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        User userExists = userService.findById(id);
        if (userExists != null) {
            userService.delete(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    /*
        Retornar uma lista de usuário do sistema pelo email
     */
    @GetMapping(path = "/consultar-por-email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna um registro de usuário pelo email.", notes = "Retorna um registro de usuário pelo email da base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok.", response = User.class),
            @ApiResponse(code = 204, message = "Sem retorno de dados"),
            @ApiResponse(code = 404, message = "Recurso não encontrado."),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no servidor.")
    })
    public ResponseEntity<User> consultarPorEmail(@PathVariable String email) {
        User userPorEmail = userService.findByEmail(email);
        return ResponseEntity.ok().body(userPorEmail);
    }

    /*
        Consultar um usuário do sistema pelo seu identificador
     */
    @GetMapping(path = "/consultar-por-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
