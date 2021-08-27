package com.kscm.ex1;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Person {
    private String firstName;
    private String lastName;
    private Address address;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /*public Person(String firstName) {
        this.firstName = firstName;
    }*/


    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("firstName",
                this.firstName).append("lastName",
                this.lastName).toString();
    }
}
