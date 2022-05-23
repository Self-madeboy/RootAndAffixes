package com.root.affixes.service.service.Impl;

import com.root.affixes.service.entity.Developer;
import com.root.affixes.service.entity.Person;
import com.root.affixes.service.repository.DeveloperRepository;
import com.root.affixes.service.repository.PersonRepository;
import com.root.affixes.service.service.GraphDataService;
import org.springframework.stereotype.Service;

/**
 * @author jc
 */
@Service
public class GraphDataServiceImpl implements GraphDataService {

    private final PersonRepository personRepository;
    private final DeveloperRepository developerRepository;

    public GraphDataServiceImpl(PersonRepository personRepository, DeveloperRepository developerRepository) {
        this.personRepository = personRepository;
        this.developerRepository = developerRepository;
    }

    @Override
    public Person createData() {
        Person person = new Person(null, "jc");
        return this.personRepository.save(person);
    }

    @Override
    public Developer createDev() {
        Developer developer = new Developer(null, "研发");
        return this.developerRepository.save(developer);
    }
}
