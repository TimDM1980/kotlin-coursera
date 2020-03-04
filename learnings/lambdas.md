# lambdas

- syntax: curly braces
    - `{ parameters -> body }`
    - curly braces are highlighted in red in IntelliJ
    - `{ x: Int, y: Int -> x + y }`
- pass lambda as argument to a function
    - `list.any({i: Int -> i > 0 })`
    - if the lambda is the last or only argument, you can move it out of the `()`:
        - `list.any() {i: Int -> i > 0 }`
    - if the lambda is the only argument, you can omit the `()`:
        - `list.any {i: Int -> i > 0 }`
    - if the type can be inferred:
        - `list.any { i -> i > 0 }`
    - if the lambda has only one argument:
        - `list.any { it > 0 }`
- multi-line lambda:
    - ```
      list.any {
        println("processing $it")
        it > 0
      }
      ```
    - last expression in that block is the result
- destructuring
    - e.g. when lambda argument is a Pair:
    - `map.mapValues { entry -> ...entry.key... }` can be written as `map.mapValues { (key, value) -> ...key... }`
    - `map.mapValues { (_, value) -> ... }` is an argument is not used, so you don't have to think of a name
- collections
    - filter, map, groupBy, associate, zip, ...
    - result is again a Collection, no need for `stream()` or `Collectors`
    - partition returns 2 collections! Is this a Pair under the hood?
- don't use `it` in complicated or multi-line lambda's

    