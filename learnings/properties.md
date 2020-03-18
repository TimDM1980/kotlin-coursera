# properties

- like java bean property (field with getter and setter)
- separate concept in Kotlin
- works automatically by declaring a variable in a class
- val vs var
    - if you use `val`, the property is read-only (no setter)
    - if you use `var`, the property is read & write
- read: `contact.address` (getter is called under the hood)
- write: `contact.address` = "..."  (setter is called under the hood)
- how to use kotlin properties from java: call getters and setters
- how to use java bean properties from within kotlin: call them kotlin-style
- you can have a property which is not backed by a field:
    - ```
      val foo2: Int 
        get() {
          return 42 
        }
      ```
- access the "field" inside the setter
    - ```
      var state = false
        set(value) {
          println("changing state from $field to $value")
          field = value
        }
      ```
- if you don't mention `field` inside the custom getter or setter, a field will not be generated by kotlin to back the property
- default generated methods:
    - ```
      get() = field
      set(value) {
        field = value
      }
      ``` 
- you always use property access, never getters or setters, even outside the class!
    - under the hood, getters and setters are called of course
    - inside the class, compiler could optimize and call field directly
- make setter private
    - ```
      var counter: Int = 0
        private set
      ```