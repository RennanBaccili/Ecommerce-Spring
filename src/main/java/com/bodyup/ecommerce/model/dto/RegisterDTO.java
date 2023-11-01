package com.bodyup.ecommerce.model.dto;

import com.bodyup.ecommerce.model.enums.UserRoles;

public record RegisterDTO(String email, String password, UserRoles role) {

}
