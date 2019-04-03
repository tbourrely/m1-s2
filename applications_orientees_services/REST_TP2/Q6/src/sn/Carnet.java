package sn;

import java.util.ArrayList;

public class Carnet {
    private ArrayList<Contact> contacts;

    public Carnet() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("Jamy", "0603020103"));
        contacts.add(new Contact("James", "0603920161"));
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
