package sn;

public class ContactNotFoundPlainException extends Exception {
    @Override
    public String getMessage() {
        return "Contact inconnu";
    }
}
