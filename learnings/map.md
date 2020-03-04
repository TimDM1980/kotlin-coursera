# map

- get a value from a map
    - `map["key"]`
        - returns null when key is not present
        - can be followed by safe access: `map["key"]?.property`
    - `map.getValue("key")`
        - throws a NoSuchElementException when key is not present
        - no need to follow with safe access
    - `map.getOrElse("key") { lambda }`
        - invokes the lambda if the key was not present
    