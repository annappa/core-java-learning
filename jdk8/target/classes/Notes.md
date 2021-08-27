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

#### Following are the important points to be considered:

* Lambda expressions are used primarily to define inline implementation of a functional interface, i.e., an interface with a single method only. 
  In the above example, we've used various types of lambda expressions to define the operation method of MathOperation interface. 
  Then we have defined the implementation of sayMessage of GreetingService.

* Lambda expression eliminates the need of anonymous class and gives a very simple yet powerful functional programming capability to Java.


