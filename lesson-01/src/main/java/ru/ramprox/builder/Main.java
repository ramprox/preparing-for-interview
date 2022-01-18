package ru.ramprox.builder;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder()
                .withFirstName("John")
                .withLastName("Smith")
                .withCountry("USA")
                .withAddress("California")
                .withAge(25)
                .withGender("M")
                .getPerson();

        person = new Person.PersonBuilder()
                .withFirstName("Mike")
                .withLastName("Smith")
                .withAddress("Texas")
                .getPerson();        // IllegalStateException: Country must not be null or empty
    }
}
