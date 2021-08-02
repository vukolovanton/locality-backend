package com.backend.locality.api.locality;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locality")
@AllArgsConstructor
public class LocalityController {
    private final LocalityService localityService;

    @GetMapping
    public List<LocalityModel> getAllLocalities() {
        return localityService.findAll();
    }

    @PostMapping
    public LocalityModel saveLocality(LocalityModel locality) {
        return localityService.saveLocality(locality);
    }

    @GetMapping("/index")
    public LocalityModel getLocalityById(int localityId) {
        return localityService.findLocalityById(localityId);
    }
}
