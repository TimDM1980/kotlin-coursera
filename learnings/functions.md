# functions

- `fun` for a function
- listOf() gives you a readonly list (as opposed to mutableListOf())
- `fun max(a: Int, b: Int): Int {`
  - lijkt dus meer op typescript dan op Java
  - if you omit the return type, than it is `:Unit`, which is `void` for Kotlin
- Kotlin does not only have class functions, but also:
  - top level functions
    - at package level, no class wrapper needed
    - can be called from java as static function (`MyClass.foo()`)
    - you can change the JVM name of the class :-) `@file:JvmName("Foo")` now you can call `Foo.myfunc()`
  - local functions: a function within a function

