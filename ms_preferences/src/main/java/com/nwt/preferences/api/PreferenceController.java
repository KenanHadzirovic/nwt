package com.nwt.preferences.api;

import com.nwt.preferences.entities.Preference;
import com.nwt.preferences.entities.PreferenceType;
import com.nwt.preferences.repository.interfaces.PreferenceRepository;
import com.nwt.preferences.repository.interfaces.PreferenceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/preference")
public class PreferenceController {

    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    PreferenceTypeRepository preferenceTypeRepository;


    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<Preference>> getAll() {
        List<Preference> result = preferenceRepository.findAll();

        return new ResponseEntity<List<Preference>>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Preference> getById(@PathVariable Long id){
        Optional<Preference> result = preferenceRepository.findById(id);

        if(result == null || !result.isPresent())
        {
            new ResponseEntity<>("Preference not found by provided id", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Preference>(result.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{ownerId}")
    public ResponseEntity<List<Preference>> getPreferencesByUserId(@PathVariable Long ownerId) {
        List<Preference> result = preferenceRepository.getPreferencesByOwnerId(ownerId);

        return new ResponseEntity<List<Preference>>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity create(@RequestBody Preference preference) throws ParseException {

        Preference result;
        if(preference.getPreferenceId() != null)
        {
            if(!preferenceRepository.findById(preference.getPreferenceId()).isPresent())
            {
                preference.setPreferenceId(null);
            }
        }
        result = preferenceRepository.save(preference);
        if (result == null) {
            return new ResponseEntity<>("Unable to save the preference.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result.getPreferenceId(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/batch")
    public ResponseEntity create(@RequestBody List<Preference> preferences) throws ParseException {

        List<Preference> result;
        result = preferenceRepository.saveAll(preferences);
        if (result == null) {
            return new ResponseEntity<>("Unable to save the preference.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(preferences, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        preferenceRepository.deleteById(id);


        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value="initialize", method = RequestMethod.GET)
    public boolean initializeData()
    {
        InitializeData();
        return true;
    }

    private void InitializeData()
    {
        System.out.println("Initializing data");

        preferenceTypeRepository.save(new PreferenceType((long)1, "Color"));
        preferenceTypeRepository.save(new PreferenceType((long)2, "Is confirmed"));

        Preference p = new Preference((long)1, null,true,(long)1, (long)2 );
        preferenceRepository.save(p);
        preferenceRepository.save(new Preference((long)2, "#aaaaaa", null, (long)2, (long)1 ));
        preferenceRepository.save(new Preference((long)3, "#aaaaaa", null, (long)1, (long)1 ));
        preferenceRepository.save(new Preference((long)4, null,false, (long)2, (long)2 ));
    }

}
