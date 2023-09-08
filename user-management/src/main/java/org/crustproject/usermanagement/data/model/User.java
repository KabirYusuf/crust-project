package org.crustproject.usermanagement.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.crustproject.usermanagement.data.enums.Role;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private boolean isVerified;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles")
    private Set<Role> roles;
}
