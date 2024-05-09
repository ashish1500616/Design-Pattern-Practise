### What the Code Would Look Like Without the Bridge:

> Without the bridge pattern, you would typically see a tighter coupling between the web pages and their themes, likely
> through inheritance. Each `WebPage` subclass would need to directly implement its theming logic, leading to duplication
> and a harder-to-maintain codebase. Here’s how the code might be structured without using the bridge pattern:

```java
interface WebPage {
    String getContent();
}

class AboutDarkTheme implements WebPage {
    @Override
    public String getContent() {
        return "About page in Dark Black";
    }
}

class CareersLightTheme implements WebPage {
    @Override
    public String getContent() {
        return "Careers page in Off White";
    }
}
```

// More classes would be needed for each combination of page and theme.

# Bridge Design Pattern

The Bridge design pattern is a structural pattern used to separate the abstraction from its implementation, allowing
both to be developed and extended independently. Here's a deeper look at the two main components of this pattern: the
abstract part and the implementation part.

## Abstract Part (Abstraction)

### Purpose

The abstract part, or the abstraction, provides a high-level interface to the client. It handles the delegation of
client requests to the underlying implementation part. It acts as a bridge between the client code and the
implementation part, abstracting the complexities of the underlying implementations.

### Example

In the context of a web page system:

- **Abstraction**: The `WebPage` interface.
- **Concrete Abstractions**: Classes like `About` and `Careers` implement the `WebPage` interface but delegate the
  theme-related details to the implementation.

## Implementation Part (Implementer)

### Purpose

The implementation part contains the concrete implementations of the abstract interface. It does the actual work behind
the scenes. This component can be extensively modified or extended without any need for changing the abstraction that
uses it.

### Example

In the web page system:

- **Implementer**: The `Theme` interface.
- **Concrete Implementations**: `DarkTheme`, `LightTheme`, and `AquaTheme` are classes that provide specific
  functionalities for the web pages.

## Interaction Between Abstraction and Implementation

- The abstraction contains a reference to the implementer.
- It delegates the work to the implementer, which then performs the actual operation. For example, calling
  the `getContent` method on a `WebPage` will involve fetching the theme color from a `Theme` object.
- This separation allows implementations to be changed or swapped without modifying the abstraction, enhancing
  flexibility and scalability.

## Key Benefits

1. **Decoupling Interface and Implementation**: Changes to the implementation should not affect its clients through the
   abstraction.
2. **Flexibility in Extending**: Implementations can be added or replaced independently of the abstractions they extend.
3. **Adherence to Single Responsibility Principle**: Separates the concerns into different classes.
4. **Following Open/Closed Principle**: The system is open for extension (through new implementations and abstractions)
   but closed for modifications.

