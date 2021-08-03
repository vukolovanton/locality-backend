package com.backend.locality.api.locality;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/locality")
public class LocalityController {
    private final LocalityService localityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LocalityModel> getAllLocalities() {
        return localityService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public LocalityModel saveLocality(LocalityModel locality) {
        return localityService.saveLocality(locality);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public LocalityModel getLocalityById(int localityId) {
        return localityService.findLocalityById(localityId);
    }
}
