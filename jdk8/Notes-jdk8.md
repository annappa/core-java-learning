## Info
[JDK8 Tutorial](https://www.youtube.com/watch?v=QRwG9X9l6xI&list=PLsyeobzWxl7qbvNnJKjYbkTLn2w3eRy1Q&index=1)

[ Need of Stream API in Java](https://www.youtube.com/watch?v=B-U-ItdMUVw&ab_channel=Telusko)

[Stream API in Java](https://www.youtube.com/watch?v=tklkyVa7KZo&ab_channel=Telusko)

[Java Full Stack and Spring Boot AI](https://courses.telusko.com/learn/Java-Full-Stack-Developer-Live-Course)

## Docs
The new version of Java, **released in March 2014, called Java 8**, introduced features that will change how you program on a day-to-day basis.

There were two **motivations** that drove the changes in Java 8:

* Better code readability

* Simpler support for multicore

## Functional Programming in Java
[Functional Programming in Java](https://www.baeldung.com/java-functional-programming)

[Functional Programming in Java](https://www.freecodecamp.org/news/functional-programming-in-java/)

[Java Functional Programming | Full Course](https://www.youtube.com/watch?v=VRpHdSFWGPs)

[Functional Programming with Java 8](https://www.youtube.com/watch?v=Ee5t_EGjv0A)

[Java 8 to 18: Most important changes in the Java Platform](https://www.youtube.com/watch?v=P7SI9mLwiqw)

## Optional
Java 8's Optional<T> is a container object that may or may not contain a non-null value. 
It’s a better alternative to returning null and helps avoid NullPointerException. 

#### When to Use Optional
- As return types: Especially for methods that may not return a value.

- To chain operations: Using map/filter/flatMap instead of nested null checks.

- To indicate absence of a value explicitly, rather than returning null.

#### Creating an Optional
```java
Optional<String> empty = Optional.empty(); // No value
Optional<String> value = Optional.of("Hello"); // Non-null value
Optional<String> nullable = Optional.ofNullable(null); // null-safe creation

```

#### Avoid These Common Pitfalls
Don’t use Optional as method parameters or fields (overhead and not idiomatic).

Don’t use Optional.get() without checking isPresent() — defeats the purpose.

Don’t use Optional for every nullable value; use it where it enhances clarity.

#### Examples 
check - jdk8/src/main/java/com/kscm/corejava/jdk8/optional/MainOptionalExample.java