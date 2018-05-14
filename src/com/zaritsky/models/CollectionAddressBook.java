package com.zaritsky.models;

import com.zaritsky.interfaces.AddressBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AddressBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void update(Person person) {
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public void print() {
        int number = 0;
        System.out.println();
        for (Person person : personList) {
            number++;
            System.out.println(number + ") fio = " + person.getFio() + "; phone = " + person.getPhone());
        }
    }

    public void fillTestData() {
        personList.add(new Person("Иван Печкин", "23948723948"));
        personList.add(new Person("Роман Романов", "345345345"));
        personList.add(new Person("Антон Иванов", "345345345"));
        personList.add(new Person("Ирина Меклева", "23423423"));
        personList.add(new Person("Алиса Ивановна", "456456"));
    }

}

