package br.com.rogerio.manageFarm.dto;

import br.com.rogerio.manageFarm.enuns.EnumProfile;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 3, message = "O nome deve ter no mínimo 03(três) caracteres.")
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 3, message = "O sobrenome deve ter no mínimo 03(três) caracteres.")
    private String lastName;

    @NotNull
    @NotBlank
    @Email(regexp = EMAIL_PATTERN, message = "O email informado está no formato incorreto.")
    @Length(min = 3, message = "O sobrenome deve ter no mínimo 03(três) caracteres.")
    private String username;

    @NotNull
    @NotBlank
    @Length(min = 10, message = "O telefone deve ter no mínimo 10 (dez) dígitos.")
    @Length(max = 13, message = "O telefone deve ter no máximo 13 (treze) dígitos.")
    private String phone;

    @NotNull
    @NotBlank
    @Length(min = 4, message = "A senha deve ter no mínimo 04(quatro) caracteres.")
    @Length(max = 8, message = "A senha deve ter no máximo 04(quatro) caracteres.")
    private String password;
}
