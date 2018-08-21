package com.rusoft.rsrentacar.domain;


import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    //TODO:
    // index for name and year
    // same name different year

    @ApiModelProperty(notes = "The client specific id")
    private Long clientId;
    @ApiModelProperty(notes = "The client name")
    private String name;
    @ApiModelProperty(notes = "The client year of birth")
    private String year;

    private Car car;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "client")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
