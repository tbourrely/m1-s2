package sn;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class Contact {
    private String nom;
    private String numero;

    public Contact() {
        this.nom = "";
        this.numero = "";
    }

    public Contact(String nom, String numero) {
        this.nom = nom;
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
