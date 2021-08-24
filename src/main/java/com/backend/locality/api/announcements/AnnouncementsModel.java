package com.backend.locality.api.announcements;

import com.backend.locality.api.BaseEntity;
import com.backend.locality.api.announcements.interfaces.AnnouncementsStatus;
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
@Table(name = "announcements")
public class AnnouncementsModel extends BaseEntity {

    @Column
    private String title;

    @Lob
    @Column(length = 512, columnDefinition = "TEXT")
    private String description;

    @Column
    private String imageUrl;

    @Column
    private boolean isPinned;

    @Column
    @Enumerated(value = EnumType.STRING)
    private AnnouncementsStatus status;

    @Column
    private Long localityId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel user;
}
