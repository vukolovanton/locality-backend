package com.backend.locality.api.users;

import com.backend.locality.api.BaseEntity;
import com.backend.locality.api.role.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserModel extends BaseEntity {

    public UserModel(String username, String firstName, String lastName, String password, String email, Role role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private Long localityId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
