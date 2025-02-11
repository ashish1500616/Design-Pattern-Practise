# Simple Factory Pattern Implementation Guide

## 1. Overview

### What is Simple Factory?
Simple Factory is a creational pattern that provides an interface for creating objects but lets subclasses decide which class to instantiate. It encapsulates object creation logic in a single place.

### Current Implementation
Our implementation demonstrates the Simple Factory pattern using a door manufacturing example:
```java
// Client code
Door door = DoorFactory.makeDoor(100, 200);
```

### Components
1. **Door (Interface)**
   - Defines the contract for all door types
   - Methods: getWidth(), getHeight()

2. **WoodenDoor (Concrete Product)**
   - Implements the Door interface
   - Represents a specific type of door

3. **DoorFactory (Factory)**
   - Static factory method makeDoor()
   - Encapsulates door creation logic

### Why This Implementation?
1. **Separation of Concerns**
   - Creation logic is isolated in the factory
   - Client code doesn't need to know how doors are created

2. **Easy Extension**
   - New door types can be added without changing client code
   - Single point of modification for creation logic

## 2. Design Pattern Decision Framework

### When to Use Simple Factory?

✅ **Use When:**
- Object creation involves complex logic
- Multiple implementations share a common interface
- You want to centralize object creation logic
- Creation process should be independent of client code

❌ **Don't Use When:**
- There's only one implementation
- Object creation is straightforward
- You need runtime flexibility in choosing implementations
- You need to support multiple ways of object creation

### Decision Tree
```
Is object creation complex?
├── Yes → Does it have multiple implementations?
│   ├── Yes → Need runtime flexibility?
│   │   ├── Yes → Use Factory Method/Abstract Factory
│   │   └── No → Use Simple Factory ✅
│   └── No → Use Constructor/Builder
└── No → Use Constructor
```

## 3. Implementation Tips

### 1. Factory Method Naming
```java
// Good
public static Door makeDoor(float width, float height)
public static Door createDoor(float width, float height)

// Avoid
public static Door getDoor(float width, float height) // Misleading
public static Door newDoor(float width, float height) // Too low level
```

### 2. Parameter Validation
```java
public static Door makeDoor(float width, float height) {
    if (width <= 0 || height <= 0) {
        throw new IllegalArgumentException("Invalid dimensions");
    }
    return new WoodenDoor(width, height);
}
```

### 3. Error Handling
```java
public static Door makeDoor(float width, float height) {
    try {
        return new WoodenDoor(width, height);
    } catch (Exception e) {
        // Log error
        throw new DoorCreationException("Failed to create door", e);
    }
}
```

### 4. Documentation
```java
/**
 * Creates a door with specified dimensions.
 * @param width The door width in centimeters
 * @param height The door height in centimeters
 * @return A new Door instance
 * @throws IllegalArgumentException if dimensions are invalid
 */
public static Door makeDoor(float width, float height)
```

## 4. Interview Questions

### Q1: What's the difference between Simple Factory and Factory Method?
**Answer**: 
- Simple Factory uses a single class with static method(s) to create objects
- Factory Method defines an interface for creating objects but lets subclasses decide which class to instantiate
- Simple Factory is less flexible but simpler to implement
- Factory Method supports inheritance and allows runtime decisions

### Q2: How would you extend this implementation to support multiple door types?
**Answer**:
```java
public class DoorFactory {
    public static Door makeDoor(DoorType type, float width, float height) {
        switch (type) {
            case WOODEN: return new WoodenDoor(width, height);
            case IRON: return new IronDoor(width, height);
            case GLASS: return new GlassDoor(width, height);
            default: throw new IllegalArgumentException("Unknown door type");
        }
    }
}
```

### Q3: What are the SOLID principles violated/followed in Simple Factory?
**Answer**:
- ✅ Single Responsibility: Factory handles only object creation
- ✅ Open/Closed: New door types can be added without modifying client code
- ❌ Dependency Inversion: Factory directly depends on concrete classes
- ✅ Interface Segregation: Door interface is minimal and focused
- ✅ Liskov Substitution: All door types can be used interchangeably

### Q4: How would you make this implementation thread-safe?
**Answer**:
```java
public class DoorFactory {
    private static final Object lock = new Object();
    
    public static Door makeDoor(float width, float height) {
        synchronized(lock) {
            return new WoodenDoor(width, height);
        }
    }
}
```

### Q5: What are the performance implications of using Simple Factory?
**Answer**:
- Minimal overhead as it's just a method call
- Static methods are slightly faster than instance methods
- Thread synchronization (if needed) can impact performance
- Object creation is centralized, enabling optimization
- Memory footprint is minimal as factory doesn't maintain state

## 5. Common Pitfalls and Solutions

### 1. Over-Engineering
❌ **Don't:**
```java
public class ComplexDoorFactory {
    private final Map<String, DoorSupplier> suppliers;
    private final DoorValidator validator;
    private final DoorRegistry registry;
    // Too complex for simple cases
}
```

✅ **Do:**
```java
public class DoorFactory {
    public static Door makeDoor(float width, float height) {
        return new WoodenDoor(width, height);
    }
}
```

### 2. Tight Coupling
❌ **Don't:**
```java
public class DoorFactory {
    public static WoodenDoor makeDoor() { // Returns concrete class
        return new WoodenDoor(100, 200);
    }
}
```

✅ **Do:**
```java
public class DoorFactory {
    public static Door makeDoor() { // Returns interface
        return new WoodenDoor(100, 200);
    }
}
```

## 6. Testing Strategy

### Unit Testing
```java
@Test
void shouldCreateDoorWithValidDimensions() {
    Door door = DoorFactory.makeDoor(100, 200);
    assertEquals(100, door.getWidth());
    assertEquals(200, door.getHeight());
}

@Test
void shouldThrowExceptionForInvalidDimensions() {
    assertThrows(IllegalArgumentException.class, 
        () -> DoorFactory.makeDoor(-100, 200));
}
```

Remember: Simple Factory is about finding the right balance between flexibility and simplicity. Don't over-complicate it unless requirements clearly justify the added complexity.