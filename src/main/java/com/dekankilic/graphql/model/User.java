package com.dekankilic.graphql.model;

import com.dekankilic.graphql.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    private String username;
    private String mail;

    @Enumerated(EnumType.STRING)
    private Role role;
}
