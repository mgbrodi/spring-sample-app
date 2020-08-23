package com.example.springsampleapp.service;

import com.example.springsampleapp.model.City;

import java.util.List;

public interface ICityService {
    List<City> findAll();
}
