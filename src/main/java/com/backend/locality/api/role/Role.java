package com.backend.locality.api.role;

import com.backend.locality.api.common.BaseEntity;
import com.backend.locality.api.users.UserModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private RolesEnum name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserModel> users;
}
