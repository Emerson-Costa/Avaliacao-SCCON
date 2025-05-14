package com.avaliacao.sccon.dto;

import java.io.Serializable;
import java.util.Date;

import com.avaliacao.sccon.domain.Person;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "O campo NOME é obrigatório")
    @Size(min = 3, max = 80, message = "O campo NOME deve ter entre 3 e 80 caracteres")
    private String name;

    @NotNull(message = "O campo DATA DE NASCIMENTO é obrigatório")
    Date dateOfBirth;

    @NotNull(message = "O campo DATA DE ADMISSÃO é obrigatório")
    Date dateOfAdmission;

    public PersonDTO() {

    }

    public PersonDTO(Person obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.dateOfBirth = obj.getDateOfBirth();
        this.dateOfAdmission = obj.getDateOfAdmission();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

}
