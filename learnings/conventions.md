# compiler conventions

- operator overloading
    - many operators like +, -, !, ... work on classes like List, String, ...
        - e.g. `listOf(1, 2, 3) + 4`
    - you can overload them yourself on your own classes
        - use the operator keyword
            - `operator fun Point.plus(other: Point): Point`
        - there is a fixed list of operators and functionnames to override
            - plus, minus, times, div, mod
            - unaryPlus, unaryMinus, not, inc, dec
- other conventions
    - <, > translates to Comparable methods
    - == translates to .equals()
    - === translates to ==
    - accessing elements via []
        - map[key] translates to get()
        - map[key] = newValue translates to set()
        - you can use more than one arg e.g. board[i, j]
    - in (e.g. element in list)
    - .. for ranges
    - iteration
    - destructuring
        - pair, map
        - component() functions under the hood
        - works for any data class (order of the constructor args)
        
            