package com.nwt.preferences.repository.interfaces;

import com.nwt.preferences.entities.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    @Query("SELECT p FROM Preference p where p.userId= ?1")
    List<Preference> getPreferencesByOwnerId(Long ownerId);

}
