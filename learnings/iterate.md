# iterate

- loops
  - while, do while --> same as in java
  - for
    - different keyword: `for (s in list)`
    - you can omit the element type
    - iterate over a map:
      ```
      for ((key, value) in map) {
        println("$key = $value")
      ```
    - ranges: 
      - `for (i in 1..9)`
      - `for (i in 1 until 9)` 9 is not included
      - more complicated with `downTo` and `step`
    - iterator over chars in string: `for (c in "abc")`
- in
  - used to iterate over a range (see ranges)
  - other use: check if something belongs in a range
    - `c in 'a'..'z'`
    - this returns a boolean
    - you can also use `!in`
    - this in check can be used in a when statement :-)
    - not so easy...
      - `"ball" in "a..k"` results in `true`
      - is written in Java as `"a" <= "ball" && "ball" <= "k"`
      - so this uses alphabetical comparison :-)
  - ClosedRange
    - a Kotlin interface that implements Comparable
    - https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-closed-range/index.html
    - `1..9` is an IntRange
    - `"ab".."az"` is a `ClosedRange<String>`
    - `startDate..endDate` is a `ClosedRange<MyDate>`
    - any Comparable element goes
    - this means you can also use <>= as an operator and write: `if (myDate >= startDate)`
  - use `in` keyword on a list: `if (myElement in myList)` is same as `if (myList.contains(myElement))`
  - note: 
    - `in` in a for loop iterates over all the values
    - `in` as a boolean expression compares against the 2 outer values only