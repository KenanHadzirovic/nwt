package com.nwt.preferences.repository.interfaces;

import com.nwt.preferences.entities.Preference;
import com.nwt.preferences.entities.PreferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceTypeRepository extends JpaRepository<PreferenceType, Long> {

}
