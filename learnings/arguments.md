# arguments

- string templates
  - `println("Hello $variable!")`
  - `println("Hello ${foo()}!")`
- named arguments: `foo(arg1 = "bar")`
- arguments can have defaults
- when using named arguments:
  - arguments with defaults can be omitted `foo(arg3 = "bar")`
  - order can be swapped
- less need to overload methods as in Java
  - when calling from Java, only signature with all args is available
  - adding @JvmOverloads annotation generates extra overloaded signatures