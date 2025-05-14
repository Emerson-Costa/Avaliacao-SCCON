package com.avaliacao.sccon.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avaliacao.sccon.domain.Person;
import com.avaliacao.sccon.dto.PersonDTO;
import com.avaliacao.sccon.services.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Person>> findAllPersons() {

        List<Person> persons = personService.listPersons();
        persons.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        return ResponseEntity.ok().body(persons);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> findPersonById(@PathVariable Integer id) {

        Person personObj = personService.findPersonId(id);
        return ResponseEntity.ok().body(personObj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> insertPerson(@Valid @RequestBody PersonDTO personObjDTO) {

        Person personObj = personService.fromDTO(personObjDTO);

        personObj = personService.insertPerson(personObj);
        if (personObj == null) {
            return ResponseEntity.status(409).build(); //Status para casos de conflitos (409)
        }
        return ResponseEntity.ok().body(personObj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {

        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @Valid @RequestBody PersonDTO personObjDTO) {

        Person personObj = personService.fromDTO(personObjDTO);
        personObj.setId(id);
        personObj = personService.updatePerson(personObj);
        return ResponseEntity.ok().body(personObj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Person> updadePersonByAttribute(@PathVariable Integer id, @RequestBody PersonDTO personObjDTO) {
        Person updatedPerson = personService.updatePersonAttributes(id, personObjDTO);
        if (updatedPerson == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedPerson);
    }

    @RequestMapping(value = "/{id}/age", method = RequestMethod.GET)
    public ResponseEntity<?> getAge(
            @PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestParam(name = "output", required = true) String output) {

        Person person = personService.findPersonId(id);
        if (person == null || person.getDateOfBirth() == null) {
            return ResponseEntity.status(404).body("Pessoa não encontrada.");
        }

        Date birthDate = person.getDateOfBirth();
        java.time.LocalDate birthLocalDate = birthDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        java.time.LocalDate today = java.time.LocalDate.of(2023, 3, 1);
        long result;
        switch (output.toLowerCase()) {
            case "days" ->
                result = java.time.temporal.ChronoUnit.DAYS.between(birthLocalDate, today);
            case "months" ->
                result = java.time.temporal.ChronoUnit.MONTHS.between(birthLocalDate, today);
            case "years" ->
                result = java.time.temporal.ChronoUnit.YEARS.between(birthLocalDate, today);
            default -> {
                return ResponseEntity.badRequest().body("parametro de saida inválido. Use 'days', 'months', ou 'years'.");
            }
        }

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}/salary", method = RequestMethod.GET)
    public ResponseEntity<?> getSalary(
            @PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestParam(name = "output", required = true) String output) {

        Person person = personService.findPersonId(id);
        if (person == null || person.getDateOfAdmission() == null) {
            return ResponseEntity.status(404).body("Pessoa não encontrada.");
        }

        final double INITIAL_SALARY = 1558.00;
        final double MINIMUM_WAGE = 1302.00;
        final double ANNUAL_PERCENT = 0.18;
        final double ANNUAL_BONUS = 500.00;

        java.time.LocalDate admissionDate = person.getDateOfAdmission().toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        java.time.LocalDate today = java.time.LocalDate.of(2023, 2, 1);
        long years = java.time.temporal.ChronoUnit.YEARS.between(admissionDate, today);

        double salary = INITIAL_SALARY;
        for (int i = 0; i < years; i++) {
            salary = salary + (salary * ANNUAL_PERCENT) + ANNUAL_BONUS;
        }

        java.math.BigDecimal result;
        switch (output.toLowerCase()) {
            case "full" -> {
                result = new java.math.BigDecimal(salary).setScale(2, java.math.RoundingMode.UP);
                return ResponseEntity.ok("R$ " + result);
            }
            case "min" -> {
                double minCount = salary / MINIMUM_WAGE;
                result = new java.math.BigDecimal(minCount).setScale(2, java.math.RoundingMode.UP);
                return ResponseEntity.ok(result);
            }
            default -> {
                return ResponseEntity.badRequest().body("Parametro de saída inválido. Use 'min' ou 'full'.");
            }
        }
    }
}
