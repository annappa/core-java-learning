package com.kscm.ex1;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Address {
    private String streetName;
    private String state;
    private Person person;


    public Address(String streetName, String state) {
        this.streetName = streetName;
        this.state = state;
    }

    /*public Address(String streetName) {
        this.streetName = streetName;
    }*/

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        /*return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("address1",
                this.address1).append("address2", this.address2).append("address3", this.address3)
                .append("city", this.city).append("state", this.state).append("country",
                        this.country).append("postcode", this.postcode).toString();*/
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("streetName",
                this.streetName).append("state",
                this.state).toString();
    }
}
