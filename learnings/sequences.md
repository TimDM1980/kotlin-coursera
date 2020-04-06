# sequences

- calling map, filter, ... creates a new collection every time
    - these functions are eager and immediate
    - this can cause a performance overhead
- use sequences to avoid this
    - is like Java 8 streams
    - the evaluation is postponed until the result is required
- call `.asSequence()` on the list (like `stream()` in Java)
- horizontal vs vertical evaluation
    - ```
      fun m(i: Int): Int { 
          print("m$i ")
          return i 
      }
        
      fun f(i: Int): Boolean { 
          print("f$i ")
          return i % 2 == 0
      }
      
      val list = listOf(1, 2, 3, 4) 
      list.map(::m).filter(::f)  //m1 m2 m3 m4 f1 f2 f3 f4
      list.asSequence().map(::m).filter(::f).toList() //m1 f1 m2 f2 m3 f3 m4 f4
      ```
    - see output in this code block
    - each element is evaluated through each step of the sequence, before starting with the next element
    - nothing even happens until the "terminal operation" toList is called
- Sequence interface in the Kotlin standard library is seperate from Iterable/Collection
    - this is to always know the difference between eager/lazy evaluation
    - but the signature of all their functions mostly match
    - intermediate operations (= operations that return another sequence) are not marked inline
    - terminal operations are marked as inline
- `generateSequence { lambda }`
    - generates an endless sequence, until the lambda return a null
    - e.g. `generateSequence { readLine().takeIf { it != "exit" } }`
    - infinite is not a problem, because the next element in the stream is only evaluated when it is needed
        - e.g. ```
               val numbers = generateSequence(0) { it + 1 }
               numbers.take(5).toList()
               ``` 
        - it + 1 is only evaluated 4 (5-1) times
    - here, the lambda is called each time a next value is needed
- yield
    - ```
      val numbers = sequence {
        var x = 0
        while (true) {
            yield(x++)
        }
      }
      ```
    - yield adds a number to the sequence each time it is called
    - the entire sequence lambda block is executed only once I think
    - ```
      fun mySequence() = buildSequence { 
        yield(1)
        yieldAll(3..5)
        yieldAll(listOf(7, 9)) 
      }
      
      mySequence() 
        .map { it * it }
        .filter { it > 3 } 
        .first()
      ```
    - here, the last yieldAll is never even called
    - it might even be that 5 is not added to the sequence (not sure...)
- groupingBy
    - is like groupBy but lazy
    - uses a special Grouping object
    
    
       