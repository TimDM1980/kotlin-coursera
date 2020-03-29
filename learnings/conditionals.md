# conditionals
  
- `if`
  - is an expression
  - this means it returns a value
  - `val max = if (a > b) a else b`
  - no ternary operator in kotlin
- `when`
  - acts as a switch, but you can pass any expression
    ```
    when (color) {
      BLUE -> "cold"
      RED -> "warm"
      else -> "meh" // default case
    }
    ```
  - very good for pattern matching needs, but no a full-fledged pattern matching feature 
  - check one of the values: `"y", "yes" -> "this means yes"`
  - checks on equality
    ```
    when (setOf(c1, c2)) {
      setOf(RED, BLUE) -> ...
    ```
  - check type: 
    ```
    when (pet) {
      is Cat -> pet.meow()
      is Dog -> pet.woof()
    }
    ```
    - notice you don't need to cast when calling meow() or woof() :-)
  - handy val assignment: `when (val pet = getPet())`
  - when without arguments
    - to check boolean expressions
    ```
    when {
      myVar > 5 -> "result"
      myVar <= 5 -> ...
    }
    ```
  - assign the result of when: `val x = when {`
  - no breaks necessary