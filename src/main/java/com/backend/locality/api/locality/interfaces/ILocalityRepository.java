package com.backend.locality.api.locality.interfaces;

import com.backend.locality.api.locality.LocalityModel;

import java.util.List;

public interface ILocalityRepository {
    public List<LocalityModel> findAll();
    public LocalityModel findLocalityById(int id);
    public LocalityModel saveLocality(LocalityModel locality);
}
