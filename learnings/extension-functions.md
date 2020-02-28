# extension functions

- class extenden met nieuwe functies
- die class noemen we de "receiver"
- ```
  fun String.lastChar() = this.get(this.length - 1)
  "abc".lastChar()
  ```
- lastChar() is zichtbaar in IDE :-)
- usage of `this` to reference the class
  - can be omitted: `fun String.lastChar() = get(length - 1)`
- you need to import it from where you declared it
  - `import com.example.lastChar`
- you can also call it from Java, by doing static import and adding the object as a param:
  - ```
    import static StringExtensionKt.lastChar;
    lastChar("abc")
    ```
  - if the new function takes arguments, these come after the argument that defines the receiver, e.g. `repeat("abc", 5)`
- you can't access private members :-(
  - that means the example with `this.length` does not compile...
- Kotlin standard library = Java standard library + extensions :-)
  - There is no Kotlin SDK you can download instead of a Java SDK
  - You choose your JVM version for execution
  - it's all syntactic sugar and tool support :-)
- infix
  - `infix fun Int.until(to: Int): IntRange`
    - can be called as `3.until(7)`
    - but also as `3 until 7`
  - `to` is an infix fun to create a Pair<A, B> (is like a Tuple)
    - `"hot".to(RED)` or `hot to RED`
    - easy way to inflate a Map: `mapOf(key1 to v1, key2 to v2)`
- triple quote `"""` is also an extension
  - is used to split a string over multiple lines
  - use a margin prefix | or # to cut of your indents
  - or call `.trimIndent()` or `.trimMargin()`
  - good for representing regex string also, because you don't have to escape special characters
- `eq` extension to check `==`
- override
  - Parent and Child class with both foo() extension
  - ```
    Parent p = Child()
    p.foo()
    ```
  - --> Parent.foo() is called
  - Why? because under the hood, it's static methods and that is decided at compile-time
  - Consequence: overriding of extensions is not possible
- overwrite a method
  - what if you overwrite an existing class method by defining an extension with the same signature?
  - the original method always wins; the code compiles, but you get a warning
  - you can however overload the method (use different signature e.g. other arguments)
- importance
  - principle is to keep essentials inside a class (this is your public api) and everything else, like utilities, in extensions
  - other use case: extend an api of a library you don't control