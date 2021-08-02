package com.backend.locality.api.locality;

import com.backend.locality.api.locality.interfaces.ILocalityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocalityService implements ILocalityService {
    private final LocalityRepository localityRepository;

    @Override
    public List<LocalityModel> findAll() {
        return localityRepository.findAll();
    }

    @Override
    public LocalityModel findLocalityById(int id) {
        return localityRepository.findLocalityById(id);
    }

    @Override
    public LocalityModel saveLocality(LocalityModel locality) {
        return localityRepository.saveLocality(locality);
    }
}
