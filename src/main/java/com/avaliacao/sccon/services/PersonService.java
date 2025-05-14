package com.avaliacao.sccon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.sccon.domain.Person;
import com.avaliacao.sccon.dto.PersonDTO;
import com.avaliacao.sccon.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> listPersons() {
        return personRepository.findAll();
    }

    public Person findPersonId(Integer id) {
        Optional<Person> obj = personRepository.findById(id);
        return obj.orElseThrow(
                () -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Objeto não encontrado! Id: " + id + ", Tipo: " + Person.class.getName()
                )
        );
    }

    // problema de inserir registros já existentes mesmo com id diferente.
    public Person insertPerson(Person personObj) {

        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            if (person.getId().equals(personObj.getId())) { // retorna null caso a id ja exista.
                return null;

            } else if (personObj.getId() == null) { // se não existir uma id, cria uma nova.

                Integer index = persons.size() - 1;
                Integer greaterValue = persons.get(index).getId();
                personObj.setId(greaterValue + 1);

                return personRepository.save(personObj);
            }
        }
        return personRepository.save(personObj);
    }

    public void deletePerson(Integer id) {
        findPersonId(id);

        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir o objeto");
        }

    }

    public Person updatePersonAttributes(Integer id, PersonDTO personDTO) {
        Person existingPerson = findPersonId(id);

        if (personDTO.getName() != null) {
            existingPerson.setName(personDTO.getName());
        }
        if (personDTO.getDateOfBirth() != null) {
            existingPerson.setDateOfBirth(personDTO.getDateOfBirth());
        }
        if (personDTO.getDateOfAdmission() != null) {
            existingPerson.setDateOfAdmission(personDTO.getDateOfAdmission());
        }

        return personRepository.save(existingPerson);
    }

    public Person updatePerson(Person personObj) {
        findPersonId(personObj.getId());
        return personRepository.save(personObj);
    }

    public Person fromDTO(PersonDTO personObjDTO) {
        return new Person(
                personObjDTO.getId(),
                personObjDTO.getName(),
                personObjDTO.getDateOfBirth(),
                personObjDTO.getDateOfAdmission()
        );
    }
}
