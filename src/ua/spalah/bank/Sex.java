package ua.spalah.bank;

/**
 * Created by root on 03.01.2017.
 */
public enum Sex {
    MALE("Mr."),
    FEMALE("Mrs.");

    private final String salutation;

    Sex(String salutation) {
        this.salutation = salutation;
    }

    public String getSalutation() {
        return salutation;
    }
}
