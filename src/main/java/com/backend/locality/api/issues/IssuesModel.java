package com.backend.locality.api.issues;

import com.backend.locality.api.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "issues")
public class IssuesModel extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String status;

    @Column
    private String reportedBy;

    @Column
    private String imageUrl;

    @Column
    private Long userId;

    @Column
    private Long localityId;
}
