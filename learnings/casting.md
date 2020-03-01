# casting

- kotlin equivalent for instanceof and casting:
  ```
  if (any is String) {
    val s = any as String
    s.toUpperCase() 
  }
  ```
- can be improved, you don't need to cast explicitely after doing the check:
  ```
  if (any is String) {
    any.toUpperCase() 
  }
  ```
- `as vs as?`
  - as throws an exception when object can't be cast
  - as?, which is called safe cast, simply return null if it can't be cast
    - is the same as `if (a is String) a else null`
    - this does not mean the object-to-cast is null, but means that is is a different type (e.g. an Int)
  - so now you can write: `(any as? String)?.toUpperCase()`
    - the second ? takes care of the possible null value 
