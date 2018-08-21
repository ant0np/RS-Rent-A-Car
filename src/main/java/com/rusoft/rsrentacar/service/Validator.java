package com.rusoft.rsrentacar.service;

import com.rusoft.rsrentacar.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class Validator implements org.springframework.validation.Validator {

    private final ClientService clientService;

    @Autowired
    public Validator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client) o;

        if(clientService.findByNameAndYear(client.getName(),client.getYear()) != null) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
            errors.rejectValue("name", "This person is already exists");

        }
    }

}