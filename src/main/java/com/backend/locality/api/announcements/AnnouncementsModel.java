package com.backend.locality.api.announcements;

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
@Table(name = "announcements")
public class AnnouncementsModel extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private String status;

    @Column
    private int userId;

    @Column
    private int localityId;
}
