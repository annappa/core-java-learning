package com.kscm.guava.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/*
Guava provides a very powerful memory based caching mechanism by an interface LoadingCache<K,V>. Values are automatically loaded in the cache and it provides many utility methods useful for caching needs.
 */

public class GuavaTester {
    public static void main(String args[]) {

        //create a cache for employees based on their employee id
        LoadingCache<String, Employee> employeeCache =
                CacheBuilder.newBuilder()
                        .maximumSize(100)                             // maximum 100 records can be cached
                        .expireAfterAccess(30, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
                        .build(new CacheLoader<String, Employee>() {  // build the cacheloader

                            @Override
                            public Employee load(String empId) throws Exception {
                                //make the expensive call
                                return getFromDatabase(empId);
                            }
                        });

        try {
            //on first invocation, cache will be populated with corresponding
            //employee record
            System.out.println("Invocation #1");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

            //second invocation, data will be returned from cache
            System.out.println("Invocation #2");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Employee getFromDatabase(String empId) {

        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map<String, Employee> database = new HashMap<String, Employee>();

        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);

        System.out.println("Database hit for" + empId);

        return database.get(empId);
    }
}

class Employee {
    String name;
    String dept;
    String emplD;

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.emplD = empID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmplD() {
        return emplD;
    }

    public void setEmplD(String emplD) {
        this.emplD = emplD;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Employee.class)
                .add("Name", name)
                .add("Department", dept)
                .add("Emp Id", emplD).toString();
    }
}
