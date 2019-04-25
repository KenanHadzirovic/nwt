package com.nwt.preferences.repository;

import com.nwt.preferences.entities.Preference;
import com.nwt.preferences.repository.interfaces.PreferenceRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class PreferenceRepositoryImpl implements PreferenceRepository {

}
