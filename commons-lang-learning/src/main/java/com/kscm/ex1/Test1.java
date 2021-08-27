package com.kscm.ex1;

public class Test1 {
    public static void main(String[] args) {
        Person person1 = new Person("Annappa", "Rameshappa");
        //Person person1 = new Person("Annappa");
        Address address1 = new Address("sjr", "ktk");
        //Address address1 = new Address("sjr");

        person1.setAddress(address1);
        address1.setPerson(person1);

        System.out.println("Person ==>" + person1);
        System.out.println("Address ==>" + address1);
    }
}
