package com.kscm.heapdump.analysis.mat.ex3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * DogShelter class represents a Dog Shelter. Each instance maintains the list
 * of dogs that are currently present in the shelter. This class is designed to
 * demonstrate memory leak in Java due to design flaws and human errors.
 */
public class DogShelter {

    /** In Memory Store containing the dogs present in the shelter. */
    private Set<Dog> shelterDogs = new HashSet<Dog>();

    public static void main(String[] args) {
        DogShelter shelter = new DogShelter();
        shelter.addAndRemoveRandomEntries(1000);
    }

    /**
     * Adds the <code>Dog</code> object entry to the HashSet.
     *
     * @param microChipID An ID used to uniquely identify a dog.
     * @param name        Name of the dog.
     */
    public void addEntry(int microChipID, String name) {
        Dog dog = new Dog(microChipID, name);
        shelterDogs.add(dog);
    }

    /**
     * Removes the <code>Dog</code> object from the HashSet
     *
     * @param microChipID An ID used to uniquely identify a dog.
     */
    public void removeEntry(int microChipID) {
        Dog dog = new Dog(microChipID);
        shelterDogs.remove(dog);
    }

    /**
     * Generates random Dog Objects and add and remove the dog object serially.
     *
     * @param entriesCount Number of random entries to be added and removed from
     *                     HashSet
     */
    public void addAndRemoveRandomEntries(int entriesCount) {
        Random rand = new Random();
        String[] commonDogNames = { "Buddy", "Coco", "Charlie", "Cooper", "Maggie" };

        for (int i = 0; i <= entriesCount; i++) {
            /** Generate a random dog name from the list of common dog names. */
            String randomDogName = commonDogNames[rand.nextInt(commonDogNames.length)];
            /** First add and then remove the entry from the HashSet. */
            addEntry(i, randomDogName);
            removeEntry(i);
            System.out.printf("Successfuly removed entry for %s with unique id %d.\n", randomDogName, i);
            try {
                /**
                 * Sleep before adding & removing new entry so that we can see the memory grow
                 * in JConsole.
                 */
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
