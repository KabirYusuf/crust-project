package org.crustproject.usermanagement.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationRequest {
    private String email;
    private String password;
}
