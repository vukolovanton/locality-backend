package com.backend.locality.api.documents;

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
@Table(name = "documents",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        })
public class DocumentsModel extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private boolean isPinned;

    @Column
    private String link;

    @Column
    private int localityId;

    @Column
    private int userId;
}
