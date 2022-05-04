package br.com.rogerio.manageFarm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Length(min = 3, message = "O nome do usuário deve ter no mínimo 03 caracteres")
    @Column(name = "NOME")
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3, message = "O sobrenome deve ter no mínimo 03 caracteres")
    @Column(name = "SOBRENOME")
    private String lastName;

    @NotNull
    @NotEmpty
    @Length(min = 3, message = "O userName deve ter no mínimo 03 caracteres")
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @NotEmpty
    @Length(min = 4, message = "A senha deve ter no mínimo 04 caracteres")
    @Length(max = 8, message = "A senha deve ter no máximo 08 caracteres")
    @Column(name = "SENHA")
    private String password;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dateTime;

    @PrePersist
    public void onSave() {
        this.dateTime = LocalDate.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.dateTime = LocalDate.now();
    }
}
