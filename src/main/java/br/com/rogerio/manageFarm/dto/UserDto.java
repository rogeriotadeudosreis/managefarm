package br.com.rogerio.manageFarm.dto;

import br.com.rogerio.manageFarm.enuns.EnumProfile;
import br.com.rogerio.manageFarm.model.Perfil;
import br.com.rogerio.manageFarm.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String username;
    private String phone;
    private boolean isActive;
    private List<Perfil> profiles;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.isActive = user.isActive();
        this.profiles = user.getProfiles();
    }
}
