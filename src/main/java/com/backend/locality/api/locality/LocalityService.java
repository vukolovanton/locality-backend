package com.backend.locality.api.locality;

import com.backend.locality.api.locality.interfaces.ILocality;
import com.backend.locality.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocalityService implements ILocality {
    private final LocalityRepository localityRepository;
    private final UserRepository userRepository;

    @Override
    public List<LocalityModel> findAll() {
        return localityRepository.findAll();
    }

    @Override
    public LocalityModel findLocalityById(Long id) {
        return localityRepository.findLocalityById(id);
    }

    @Override
    public LocalityModel saveLocality(LocalityCreationDTO locality) {
        LocalityModel l = localityRepository.saveLocality(locality);
        userRepository.updateUserLocalityId(l.getId(), locality.getUserId());
        return l;
    }
}
