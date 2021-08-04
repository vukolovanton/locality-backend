package com.backend.locality.api.locality.interfaces;

import com.backend.locality.api.locality.LocalityModel;

import java.util.List;

public interface ILocalityService {
    public List<LocalityModel> findAll();
    public LocalityModel findLocalityById(Long id);
    public LocalityModel saveLocality(LocalityModel locality);
}
