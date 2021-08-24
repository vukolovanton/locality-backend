package com.backend.locality.api.issues;

import com.backend.locality.api.BaseEntity;
import com.backend.locality.api.users.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "issues")
public class IssuesModel extends BaseEntity {

    @Column
    private String title;

    @Lob
    @Column(length = 512, columnDefinition = "TEXT")
    private String description;

    @Column
    @Enumerated(value = EnumType.STRING)
    private IssueStatuses status;

    @Column
    private String imageUrl;

    @Column
    private Long localityId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel user;
}
