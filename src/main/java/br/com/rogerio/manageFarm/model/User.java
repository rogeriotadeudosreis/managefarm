package br.com.rogerio.manageFarm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Length(min = 3, message = "O nome do usuário deve ter no mínimo 03(três) caracteres")
    @ApiModelProperty(value = "Nome do usuário")
    @Column(name = "NOME")
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3, message = "O sobrenome do usuário deve ter no mínimo 03(três) caracteres")
    @ApiModelProperty(value = "Sobrenome do usuário")
    @Column(name = "SOBRENOME")
    private String lastName;

    @NotNull
    @NotEmpty
    @Email(regexp = EMAIL_PATTERN, message = "O email informado está no formato incorreto.")
    @Length(min = 3, message = "O userName do usuário deve ter no mínimo 03(três) caracteres")
    @Column(name = "USERNAME")
    @ApiModelProperty(value = "Email do usuário")
    private String username;

    @Length(min = 10, message = "O número de telefone deve ter no mínimo 10(dez) dígitos.")
    @Length(max = 13, message = "O número de telefone deve ter no máximo 13(treze) dígitos.")
    @ApiModelProperty(value = "Telefone do usuário")
    @Column(name = "TELEFONE")
    private String phone;

    @ApiModelProperty(value = "Se o usuário está ativo ou inativo")
    @Column(name = "ATIVO")
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @ApiModelProperty(value = "Informa o perfil do usuário")
    private List<Perfil> profiles = new java.util.ArrayList<>();

    @NotNull
    @NotEmpty
    @Length(min = 4, message = "A senha deve ter no mínimo 04(quatro) caracteres")
    @Length(max = 8, message = "A senha deve ter no máximo 08(oito) caracteres")
    @ApiModelProperty(value = "Senha do usuário")
    @Column(name = "SENHA")
    private String password;

    @ApiModelProperty(value = "Data inicial do cadastro do usuário")
    @Column(name = "DATA_INICIAL")
    private LocalDateTime initialDate;

    @ApiModelProperty(value = "Data de cadastro atualizada")
    @Column(name = "DATA_ATUALIZADA")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "Dados salvos automaticamente")
    @PrePersist
    public void onSave() {
        this.isActive = true;
        this.initialDate = LocalDateTime.now();
    }

    @ApiModelProperty(value = "Dados atualizados automaticamente")
    @PreUpdate
    public void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
