package com.backend.locality.api.locality.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IndexLocalityRequest {
    private String searchText;
}
