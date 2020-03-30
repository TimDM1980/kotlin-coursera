# generics

- `fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T>`
    - this is an extension function added to List, using generics
- nullability
    - you don't know if T is nullable or not
        - you could use this function with Int or with Int?
    - you can make stuff explicit e.g.
        - `fun <T> List<T>.firstOrNull(): T?`
        - this indicates that a null can be returned
    - you can add upper bounds
        - `fun <T : Any> foo(list: List<T>)`
        - you can't call it on `listOf(1, null)`
- upper bounds
    - like extends in Java
    - `<T : Number>`
    - can be nullable itself: `<T : Number?>`
    - multiple upper bounds: fun <T> foo(bar: T) where T : CharSequence, T : Appendable        
- JVM clash
    - ```
      fun List<Int>.average(): Double
      fun List<Double>.average(): Double
      ```
    - this won't compile, since generics are erased and signature of the 2 methods becomes the same
    - add @JvmName annotation to fix this
    
       