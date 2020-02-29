# nullable

- nullability
  - billion dollar mistake
  - modern approach: runtime error --> compile time error
  - you use the `?` character
    - `val s1: String = "always not null"`
    - `val s2: String = null` won't compile
    - but you can do: `val s2: String? = null` 
    - `s2.length` will not compile, because compiler knows that s2 is possibly null
      - so how do you check the length of s2?
      - wrap it in an if statement: `if (s2 != null) { s.length }`
      - use the safe access expression: `s2?.length`
        - the result is an object of type `Int?`
        - the result will contain null when s2 itself was null
  - elvis operator `?:`
    - helps to return an alternative result when something was null
    - `s?.length ?: 0`
    - `foo ?: bar`
    - origin: Groovy language
    - notice operator precedence: `x ?: 0 + y` is like `x ?: (0 + y)` because `+` and `-` has higher precedence than `?:`
  - NullPointerException
    - you can explicitely throw it with `!!`
    - afterwards, the value is smart-cast to a nun-nullable type
    - `foo!!` throws a NPE if foo was null, or changes foo into a non nullable type otherwise
    - after `s!!` you can call `s.length` instead of `s?.length`
    - use case: when the compiler can't know, but you do know it's not null, and you want to get rid of the nullable type
    