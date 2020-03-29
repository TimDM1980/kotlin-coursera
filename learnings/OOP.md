# Object Oriented Programming

- visibility modifiers in kotlin
    - default is `public`
    - others are: `protected`, `private` and `internal`
    - `protected` is no longer visible inside the package, is only for subclasses
    - package private doesn't exist
    - `internal` is scoped to a module
        - module = set of files compiled together
        - under the hood, converted to public and some name mangling happens
- override modifiers    
    - default is `final`
    - want non-final? use `open`
    - using `override` when overriding is mandatory
    - `abstract` also still exists
- you can place several classes in one file
- inheritance
    - `:` replaces extends and implements
- class modifiers
    - enum class
        - e.g. `enum class Color`
        - in java, this was `enum Color`
        - use `;` to separate the end of the enum list with the members
        - ```
          BLUE, RED, GREEN;
          
          fun rgb() = ...
          ```
    - data class
        - adds equals, hashcode, copy, toString
            - __only the properties declared in the primary constructor are used!!!__
            - properties from secondary constructors or the properties used in the primary constructor body are skipped
        - copy makes a copy
            - you can specify the args you want to change
            - e.g. `address.copy(street = "new street")`
    - sealed class
        - restricts class hierarchy
        - all subclasses must be located in the same file
        - ```
          sealed class Expr
          class Number(val value: Int) : Expr()
          class Sum(val left: Expr, val right: Expr) : Expr()
          
          fun eval(e: Expr): Int = when (e) {
            is Number -> e.value
            is Sum -> eval(e.left) + eval (e.right)
          }
          ```
        - you would need `else -> throw IllegalArg...` if you don't add sealed
        - otherwise the code wouldn't compile  
- object equality
    - `==` calls java `equals` 
    = `===` calls java `==` (reference equality)
- parent class reference
    - ```
      class A {
        class B
        inner class C {
          this@A...
        }
      }
      ```
    - an instance of class C will have a reference to an instance of class A
    - is like using a static inner class in Java
- class delegation
    - `class Controller(repo: Repository, logger: Logger) : Repository by repo, Logger by loggger`
    - same as writing boiler plate delegation code for log(), getById(), ...
- `object` instead of `class`
    - is a singleton
    - singleton pattern under the hood
    - call method on singleton: `MySingletonClass.foo()`
    - call from java: `MySingletonClass.INSTANCE.foo()`
- object expressions
    - replace Java anonymous classes (think old Swing eventlisteners)
    - ```
      object : MouseAdapter {
          override fun mouseClicked...
      }
      ```
    - you can use lambda if only 1 method to be implemented
    - object expression is not a singleton
        - each call creates a new instance
- companion object
    - replacement for static methods in java
    - ```
      class A {
        companion object {
            fun foo()
        }
      }
      
      A.foo()
      ```
    - why no static methods?
        - a companion object can implement an interface
            - `companion object : Factory {`
        - a companion object can be receiver for extension functions
            - use `Companion keyword`: e.g. `Person.Companion.extFunction()`
            - call via class e.g. `Person.extFunction()`
    - to make the function callable from java:
        - add `@JvmStatic` so static is added in the bytecode
        - or go via `Companion keyword
- there is even no static keyword in kotlin
    - but there are 3 alternatives:
        - top-level (not inside a class block)
        - inside objects (singleton)
        - inside companion object
            
                    
            
              
        