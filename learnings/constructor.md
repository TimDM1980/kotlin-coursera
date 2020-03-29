# constructor

- no `new` keyword, just call constructor
- Person person = new Person(...) --> `val person = Person(...)`
- primary constructor
    - generated automatically, when declaring variables in your class definition
    - `class Person(name: String, age: Int)`
    - empty arg constructor generated when you don't specify arguments in your class
    - you can assign the constructor params in an init block
        - ```
          class Person(name: String, age: Int) {
            val name: String
            var age: Int
            init {
              this.name = name
              this.age = age
            }
          }
          ```
        - shorthand for this: `class Person(val name: String, var age: Int)`
    - change visibility: 
        - ```
          class Person 
          internal constructor(name: String) {
          }
          ```
- secondary constructors
    - ```
      class Person(val name: String, var age: Int) {
          constructor(age: Int) : this("John Doe", age) {
              // do other stuff
          }
      }
      ```
    - you must call the primary or another secondary constructor
- call parent constructor
    primary: `class Child(name: String) : Parent(name)`
    secondary: `constructor(name: String, foo: Int) : super(name)`