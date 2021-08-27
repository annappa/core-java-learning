package com.kscm.heapdump.analysis.mat.ex3;


/**
 * Dog represents a dog which can be uniquely identified by the MicroChip ID and
 * can optionally have a name.
 */
public class Dog {

    /** The Unique MicroChip ID of the dog. */
    private int microChipID;
    /** The name of the dog. */
    private String name;
    /** Extra memory space for each instance to speed up the memory leak. */
    private byte[] toExpediteLeak;

    Dog(int microChipID) {
        this.microChipID = microChipID;
    }

    Dog(int microChipID, String name) {
        /**
         * On Creating a Dog object we allocate a 10Mb storage with each instance to
         * expedite the memory leak.
         */
        this.toExpediteLeak = new byte[10000000];
        this.microChipID = microChipID;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Dog))
            return false;

        Dog dog = (Dog) obj;
        return dog.microChipID == microChipID && dog.name.equals(name);
    }

    @Override
    public int hashCode() {
        return microChipID;
    }

    /**
     * @return MicroChip ID of the dog.
     */

    public int getMicroChipID() {
        return microChipID;
    }

    /**
     * @return name of the dog.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of the dog.
     */
    public void setName(String name) {
        this.name = name;
    }
}
