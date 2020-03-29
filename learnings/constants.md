# constants

- const keyword
    - `const val answer = 42`
    - only for primitives and strings
- add @JvmField
    - eliminates getters and setters and makes the property accessible as a field
- for other classes
    - ```
      @JvmField
      val prop = MyClass()
      ``` 
    - is the same as `public static final MyClass prop = new MyClass` in java
- simulate static in java
    - by using const
    - by adding @JvmField

    