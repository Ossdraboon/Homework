package homework.benny.calculator;

public class Application {

    /* You asked me the other day what this static keyword does, and I mentioned that it is hard to explain without going into object-orient programming
    This is why I took your static implementation and made the methods none-static, I hope this will help to explain the concept
    Java is an object-oriented language. An object in java refers to an instance of a class. Well, what is an instance?
    As you can see in the main function below, I have written this weird Syntax with 'new Calculator()'. This very statement creates an instance of
    class Calculator. We call this an object. So, an object is an instance of a class.
    Now you might ask yourself: "What is the point of doing this? My code worked just fine! I don't need instances of a class!". What you do with object-
    oriented programming in essence is, you group business logic together that belongs together. So for instance, you can see that I created a class calculator.
    This class should only be responsible for doing what a calculator does. It should not have logic on how to wash the dishes.That is what a Dishwasher class
    should handle. Now, imagine you would like to create a list of people in your code. A person has different attributes: firstName, lastName, age, gender, height,
    weight. To "persist" these values, you create a class Person with the attributes that I just mentioned. In order to create a new Person, you will instantiate an
    object of that class via the new keyword (Person peter = new Person()). This person right now has no values set to their attributes, because we use the default constructor
    which each class has (no paramaters). This is a detail you do not need to understand for now. But now we would like to set values for peter's attributes. Suppose
    we wrote a static method setAge which takes a number and writes that value to peter's age variable. It would look like this Person.setAge(42); What you will notice here,
    is that we invoked the method on the Class Person (because it is static), not on the object peter. Doing peter.setAge(42) won't work, because setAge is static! This means,
    that all instances of class Peter will share this method (AND THE UNDERLYING STATIC VARIABLE). So, if you were to create a second person (Person Anna = new Person()), Anna would
    also be 42 years old! And if you do Person.setAge(20), you would change the age of Peter and Anna. Well, that does not seem right. In order to fix this, we make both the method
    and the variable age none-static. After doing that, the calls will look as follows: peter.setAge(42), anna.setAge(20). Peter and Anna now have their own instance variable age (because
    it is not static), they no longer share their age. The key takeway here is, when something is static, it is shared by ALL instances / objects of a class. So, you do not want to put
    anything in there that should be different from object to object. This is also why in a static method you cannot modify an instance variable of a class (none static variable).
    Because they belong to different scopes. Static -> same for every object of class; none static -> every object of a class has its own.
     */
    public static void main(String[] args) {
        // Just a little example of what the none static approach looks like
        Calculator calculator = new Calculator();
        calculator.start();
    }

    // I do not actually use any of the following, just for explanation purposes
    private static void staticVsNoneStatic() {
        Calculator calculatorOne = new Calculator();
        Calculator calculatorTwo = new Calculator();

        // as you can see here, we don't refer to the instances of calculator (calculatorOne, calculatorTwo)
        // the variable is static, so it is the same for all instances, as such we go via the class itself
        Calculator.staticVariable = 234.f;

        // none static / member-variables are different per instance of a class. By updating calculatorTwo.memberVariable,
        // I do not change the value stored in calculatorOne.memberVariable
        // this is why I cannot change an instance variable from a static method
        calculatorOne.memberVariable = 234f;
        calculatorTwo.memberVariable = 555f;

        // Calculator.memberVariable -> error, there is no such static field
        // calculatorOne.staticVariable -> warning, static variable accessed via instance
    }
}
