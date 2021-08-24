package com.backend.locality.api.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchUserRequest {
    private Long userId;
    private Long localityId;
}
