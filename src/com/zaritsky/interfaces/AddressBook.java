package com.zaritsky.interfaces;

import com.zaritsky.models.Person;

public interface AddressBook {
    void add(Person person);

    void update(Person person);

    void delete(Person person);

}
