package com.stockmanager.core.user;

import com.stockmanager.core.user.userrole.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Column(name = "user_role", columnDefinition = "VARCHAR(100)")
    private UserRole userRole;
}
