## Info and Docs
[learning-java-lambda-expressions in linkedin learning](https://www.linkedin.com/learning/learning-java-lambda-expressions/methods-as-lambdas?u=42751868)

## Functional Interfaces
Java 8has introduced 2 new packages
* java.util.function
* java.util.stream

A functional interface is an interface that contains at most one abstract function.

Functional interface represents the abstract concepts such as functions, actions or predicates.

java.util.function contains functional interfaces.

Examples:

java.util.function.Predicate - takes one argument, returns a Boolean

java.util.function.Consumer - accepts single argument with no return value

java.util.function.Function - accepts one argument and produces a result

java.util.function.Supplier - represents a supplier of results

Example --> [Lambdas_01_01.java](./src/main/java/com/kscm/lambda/ex1/Lambdas_01_01.java)

## Lambda Expressions
Lambda expression facilitates functional programming, and simplifies the development a lot.

#### Syntax:
A lambda expression is characterized by the following syntax.

`parameter -> expression body`

#### Following are the important characteristics of a lambda expression
* **Optional type declaration** − No need to declare the type of a parameter. The compiler can inference the same from the value of the parameter.

* **Optional parenthesis around parameter** − No need to declare a single parameter in parenthesis. For multiple parameters, parentheses are required.

* **Optional curly braces** − No need to use curly braces in expression body if the body contains a single statement.

* **Optional return keyword** − The compiler automatically returns the value if the body has a single expression to return the value. Curly braces are required to indicate that expression returns a value.

Example --> [Java8Tester1.java](./src/main/java/com/kscm/lambda/ex1/Java8Tester1.java)

Example --> [Lambdas_01_02.java](./src/main/java/com/kscm/lambda/ex1/Lambdas_01_02.java)

#### Following are the important points to be considered:

* Lambda expressions are used primarily to define inline implementation of a functional interface, i.e., an interface with a single method only. 
  In the above example, we've used various types of lambda expressions to define the operation method of MathOperation interface. 
  Then we have defined the implementation of sayMessage of GreetingService.

* Lambda expression eliminates the need of anonymous class and gives a very simple yet powerful functional programming capability to Java.

Example --> com.kscm.lambda.ex2.Java8Tester2

## Methods as lambdas
Example -> [Lambdas_01_03.java](./src/main/java/com/kscm/lambda/ex1/Lambdas_01_03.java)

## Custom functional interface
Example -> [Lambdas_01_04.java](./src/main/java/com/kscm/lambda/ex1/Lambdas_01_04.java)
