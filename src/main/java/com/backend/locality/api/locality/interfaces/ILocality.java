package com.backend.locality.api.locality.interfaces;

import com.backend.locality.api.locality.LocalityCreationDTO;
import com.backend.locality.api.locality.LocalityModel;

import java.util.List;

public interface ILocality {
    public List<LocalityModel> findAll(IndexLocalityRequest request);
    public LocalityModel findLocalityById(Long id);
    public LocalityModel saveLocality(LocalityCreationDTO locality);
}
