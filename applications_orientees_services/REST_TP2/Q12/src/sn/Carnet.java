package sn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "carnet")
public class Carnet {
    private ArrayList<Contact> contacts;

    public Carnet() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("Jamy", "0603020103"));
        contacts.add(new Contact("James", "0603920161"));
    }

    @XmlElement(name = "contactsList")
    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
