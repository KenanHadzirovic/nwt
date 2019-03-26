package com.nwt.preferences.api;

import com.nwt.preferences.entities.Preference;
import com.nwt.preferences.repository.interfaces.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/preference")
public class PreferenceController {

    @Autowired
    PreferenceRepository preferenceRepository;


    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<Preference>> getAll() {
        List<Preference> result = preferenceRepository.findAll();

        return new ResponseEntity<List<Preference>>(result, HttpStatus.OK);
    }

    @RequestMapping("initialize")
    public boolean initializeData()
    {
        InitializeData();
        return true;
    }

    private void InitializeData()
    {
        System.out.println("Initializing data");

        Preference p = new Preference((long)1, "true", (long)1, (long)2 );
        preferenceRepository.save(p);
        preferenceRepository.save(new Preference((long)2, "false", (long)2, (long)1 ));
        preferenceRepository.save(new Preference((long)3, "true", (long)1, (long)3 ));
        preferenceRepository.save(new Preference((long)4, "false", (long)2, (long)2 ));
    }

}
