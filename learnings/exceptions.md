# exceptions

- no difference between checked and unchecked
- throw is an expression, yes, finally!
  - `val blah = if (...) x else throw IllegalArgumentException(...)`
  - try is also an expression:
    ```
    val blah = try { 
      Integer.parseInt...
    } catch (...) {
      null
      // or return to jump out of the entire function
    }
    ```
  - in Java you cannot catch a checked exception if it is never thrown
    - so what if you call Kotlin from java and want to catch the IOException?
    - solution: @Throws annotation
    ```
    //Kotlin
    @Throws(IOException::class)
    fun foo() { 
      throw IOException()
    }
    
    //Java
    try {
      MyKotlinClass.foo()
    } catch (IOException e) {
      //...
    }
    ```