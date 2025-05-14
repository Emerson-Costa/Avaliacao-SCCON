package com.avaliacao.sccon;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.avaliacao.sccon.domain.Person;
import com.avaliacao.sccon.repositories.PersonRepository;

@SpringBootApplication
public class ScconApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(ScconApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Person> people = List.of(
                new Person(1, "Emerson José da Silva Costa", sdf.parse("27/09/1985"), sdf.parse("01/02/2023")),
                new Person(2, "Karina Fedrizzi", sdf.parse("10/04/1991"), sdf.parse("01/04/2023")),
                new Person(3, "José da Silva", sdf.parse("06/04/2000"), sdf.parse("10/05/2020"))
        );
        personRepository.saveAll(people);
    }
}
