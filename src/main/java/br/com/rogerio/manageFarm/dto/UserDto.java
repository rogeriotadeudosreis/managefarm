package br.com.rogerio.manageFarm.dto;

import br.com.rogerio.manageFarm.enuns.EnumProfile;
import br.com.rogerio.manageFarm.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
    private EnumProfile profile;

}
