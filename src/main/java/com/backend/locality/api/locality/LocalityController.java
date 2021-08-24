package com.backend.locality.api.locality;

import com.backend.locality.api.locality.interfaces.IndexLocalityRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/locality")
public class LocalityController {
    private final LocalityService localityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LocalityModel> getAllLocalities(@ModelAttribute IndexLocalityRequest request) {
        return localityService.findAll(request);
    }

    @RequestMapping(method = RequestMethod.POST)
    public LocalityModel saveLocality(@RequestBody LocalityCreationDTO locality) {
        return localityService.saveLocality(locality);
    }

    @RequestMapping(value = "/{localityId}", method = RequestMethod.GET)
    public LocalityModel getLocalityById(@PathVariable("localityId") Long localityId) {
        return localityService.findLocalityById(localityId);
    }
}
