package pl.sternik.pb.bv;

import pl.sternik.pb.bv.grupy.Address;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Person {

    @NotNull
    String name;

    @NotNull
    @Valid
    Address address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}