package br.com.rogerio.manageFarm.dto;

import br.com.rogerio.manageFarm.model.Perfil;
import br.com.rogerio.manageFarm.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String phone;
    private boolean isActive;
    private List<Perfil> profiles;
    private LocalDateTime initialDate;
    private LocalDateTime updateDate;


    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.isActive = user.isActive();
        this.profiles = user.getProfiles();
        this.initialDate = user.getInitialDate();
        this.updateDate = user.getUpdateDate();
    }
}
