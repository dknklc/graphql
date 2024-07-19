package com.dekankilic.graphql.dto;

import com.dekankilic.graphql.model.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
