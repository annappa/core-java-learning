## Info
[Interface in Java 8 Default , Static Methods | New features](https://www.youtube.com/watch?v=4e_RsZWdiSc&list=PLsyeobzWxl7qbvNnJKjYbkTLn2w3eRy1Q&index=2)

## Notes
* Before JDK8, we cannot define a method in interface
* Before JDK8, all methods are public and abstract by default. We can only declare methods before JDK8
* In Java, we don't have a concept of multiple inheritance because it creates a diamon problem thats why we cannot extend multiple classes. That's why we achieve this with implementing multiple interaces
* From JDK8, diamond problem has been introduced because multiple interfaces can have the same default method and we can implement multiple interfaces
* We cannot create default methods in interface which are same as class methods. Compiler will error out
* Class methods always have the highest priority in case of overloading. Class has more power when compared to interface.
* After JDK8, we can define a default method and static methods.
