package ru.ramprox.builder;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private static final String NULL_OR_EMPTY = "must not be null or empty";
    private static final String POSITIVE_OR_ZERO = "must be positive or zero";

    private Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public static class PersonBuilder {

        private Person person;

        public PersonBuilder() {
            person = new Person();
        }

        public PersonBuilder withFirstName(String firstName) {
            person.firstName = firstName;
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            person.lastName = lastName;
            return this;
        }

        public PersonBuilder withMiddleName(String middleName) {
            person.middleName = middleName;
            return this;
        }

        public PersonBuilder withCountry(String country) {
            person.country = country;
            return this;
        }

        public PersonBuilder withAddress(String address) {
            person.address = address;
            return this;
        }

        public PersonBuilder withPhone(String phone) {
            person.phone = phone;
            return this;
        }

        public PersonBuilder withAge(int age) {
            person.age = age;
            return this;
        }

        public PersonBuilder withGender(String gender) {
            person.gender = gender;
            return this;
        }

        // Принял, что обязательными для объекта Person являются поля:
        // firstName, lastName, country, address, gender и проверка age >=0
        public Person getPerson() {
            if(person.firstName == null || person.firstName.isEmpty()) {
                throw new IllegalStateException("First name " + NULL_OR_EMPTY);
            }
            if(person.lastName == null || person.lastName.isEmpty()) {
                throw new IllegalStateException("Last name " + NULL_OR_EMPTY);
            }
            if(person.country == null || person.country.isEmpty()) {
                throw new IllegalStateException("Country " + NULL_OR_EMPTY);
            }
            if(person.address == null || person.address.isEmpty()) {
                throw new IllegalStateException("Address " + NULL_OR_EMPTY);
            }
            if(person.age < 0) {
                throw new IllegalStateException("Age " + POSITIVE_OR_ZERO);
            }
            if(person.gender == null || person.gender.isEmpty()) {
                throw new IllegalStateException("Address " + NULL_OR_EMPTY);
            }
            return person;
        }
    }
}
