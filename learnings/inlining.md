# inlining

- Some functions in the Kotlin library are marked as `inline`
- These are: run, let, takeIf, takeUnless, repeat, withLock, use, ...
    - run: run a block of code that is passed as a lambda
    - let:
        - runs the passed lambda only when the receiver (= object it is called upon), is not null
        - e.g. foo?.let { it -> ... }
        - Don't forget to use together with ?.
    - takeIf: returns the receiver if it satisfies the predicate that passed as a lambda
    - withLock: like synchronised for Kotlin
    - use: like try with resources for Kotlin
- inlining means that the Kotlin compiler, instead of calling the function, substitutes the function code in the caller body
    - this is done for performance reasons, it has less overhead
    - be careful when you use inline yourself (premature optimization)
    - calling inlined functions from Java doesn't to a substitute, because the Java compiler doesn't know the inline concept
    - inline functions annotated with @InlineOnly can't be called from Java