package com.backend.locality.api.locality;

import com.backend.locality.api.BaseEntity;
import com.backend.locality.api.users.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "locality",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        })
public class LocalityModel extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String city;

    @Column
    private String street;
}
