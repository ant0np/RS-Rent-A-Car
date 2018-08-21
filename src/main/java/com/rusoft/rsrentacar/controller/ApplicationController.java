package com.rusoft.rsrentacar.controller;


import com.rusoft.rsrentacar.domain.Car;
import com.rusoft.rsrentacar.domain.Client;
import com.rusoft.rsrentacar.service.CarService;
import com.rusoft.rsrentacar.service.ClientService;
import com.rusoft.rsrentacar.service.Validator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class ApplicationController {

    private ClientService clientService;
    private CarService carService;
    private Validator validator;

    @Autowired
    public ApplicationController(ClientService clientService, CarService carService, Validator validator) {
        this.clientService = clientService;
        this.carService = carService;
        this.validator = validator;
    }


    @ApiOperation(value = "View all clients")
    @GetMapping("clients")
    public ResponseEntity<Iterable<Client>> getAllClients() {
        Iterable<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @ApiOperation(value = "Add client")
    @PostMapping("clients")
    public ResponseEntity<Void> addClient(@RequestBody String name,
                                          @RequestBody String year,
                                          @RequestBody String model,
                                          @RequestBody String carYear,
                                          UriComponentsBuilder builder,
                                          BindingResult bindingResult) {
        Client client = new Client();
        client.setName(name);
        client.setYear(year);

        validator.validate(client, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Car car = carService.findCarByModelAndYear(model, carYear);

        if (car == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (car.getClient() != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        client.setCar(car);
        clientService.add(client);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/clients/{id}").buildAndExpand(client.getClientId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete client")
    @DeleteMapping("clients/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable("clientId") Long clientId,
                                             @RequestBody String model) {
        Client client = clientService.findById(clientId);
        if (client != null) {
            if (client.getCar().getModel().equals(model)) {
                clientService.deleteById(clientId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
