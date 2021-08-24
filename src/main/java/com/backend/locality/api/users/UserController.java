package com.backend.locality.api.users;

import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String index(@RequestBody PatchUserRequest request) throws BadHttpRequest {
        try {
            userRepository.updateUserLocalityId(request.getLocalityId(), request.getUserId());
            return "SUCCESS";
        } catch (Exception e) {
            throw new BadHttpRequest(e);
        }
    }

}
