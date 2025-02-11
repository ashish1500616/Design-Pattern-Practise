# Builder Pattern Implementation Guide

## 1. Overview

### What is Builder Pattern?
The Builder pattern is a creational design pattern that lets you construct complex objects step by step. It's particularly useful when you need to create an object with numerous possible configurations.

### Current Implementation
Our implementation demonstrates the Builder pattern using a burger customization system:
```java
Burger burger = new BurgerBuilder(14)
    .addCheese()
    .addLettuce()
    .addTomato()
    .build();
```

### Components
1. **Product (Burger)**
   - The complex object being built
   - Contains final fields for all possible attributes
   - Only accessible through its Builder

2. **Builder (BurgerBuilder)**
   - Provides methods for setting optional parameters
   - Implements fluent interface pattern (method chaining)
   - Contains build() method to create final product

### Why Builder Pattern?
1. **Complex Object Construction**
   - Handles objects with many optional parameters
   - Avoids "telescoping constructor" anti-pattern
   
2. **Immutability**
   - Creates immutable objects without complex constructors
   - Ensures object validity once constructed
   
3. **Readability**
   - Makes object creation code more readable
   - Clear separation between construction and representation

## 2. Design Pattern Decision Framework

### When to Use Builder Pattern?

✅ **Use When:**
- Object has many optional parameters
- Object construction requires multiple steps
- Object must be immutable after creation
- Need to enforce parameter validation during construction
- Want to prevent inconsistent object states

❌ **Don't Use When:**
- Object has few parameters
- All parameters are required
- No complex validation needed
- Object construction is straightforward

### Decision Tree
```
Does object have multiple optional parameters?
├── Yes → Is immutability required?
│   ├── Yes → Need step-by-step construction?
│   │   ├── Yes → Use Builder Pattern ✅
│   │   └── No → Consider Factory Pattern
│   └── No → Consider Prototype Pattern
└── No → Use Constructor/Factory
```

## 3. SOLID Principles Application

### 1. Single Responsibility Principle
✅ Builder handles only object construction
✅ Product class focuses on business logic
✅ Each builder method has a single purpose

### 2. Open/Closed Principle
✅ New attributes can be added without changing existing builder code
✅ Product class remains unchanged when adding new construction options

### 3. Liskov Substitution Principle
✅ Different builders can be used interchangeably
✅ Product validation guarantees consistent behavior

### 4. Interface Segregation Principle
✅ Builder interface is focused on construction
✅ Methods are cohesive and purposeful

### 5. Dependency Inversion Principle
✅ High-level modules depend on builder abstraction
✅ Construction details encapsulated in concrete builders

## 4. Implementation Tips

### 1. Method Naming
```java
// Good
public Builder addTopping(String topping)
public Builder withTopping(String topping)

// Avoid
public Builder setTopping(String topping) // Implies mutation
public Builder topping(String topping)    // Too vague
```

### 2. Parameter Validation
```java
public Builder addTopping(String topping) {
    if (topping == null || topping.isEmpty()) {
        throw new IllegalArgumentException("Topping cannot be null or empty");
    }
    this.toppings.add(topping);
    return this;
}
```

### 3. Immutability
```java
public class Product {
    private final List<String> toppings;
    
    Product(Builder builder) {
        // Create defensive copy for mutable objects
        this.toppings = List.copyOf(builder.toppings);
    }
}
```

## 5. Interview Questions

### Q1: What problem does the Builder pattern solve?
**Answer**: 
- Resolves telescoping constructor problem
- Simplifies creation of complex objects
- Ensures object validity during construction
- Supports immutable objects
- Improves code readability

### Q2: How is Builder different from Factory patterns?
**Answer**:
- Builder: Step-by-step construction of complex objects
- Factory: Single-step creation of complete objects
- Builder focuses on how an object is constructed
- Factory focuses on what type of object to create

### Q3: When would you choose Builder over Factory?
**Answer**:
- When object has many optional parameters
- When construction needs to be done in steps
- When you need immutable objects
- When you need to validate during construction

### Q4: How do you implement a thread-safe Builder?
**Answer**:
```java
public class ThreadSafeBuilder {
    private final Object lock = new Object();
    
    public Builder addTopping(String topping) {
        synchronized(lock) {
            // Add topping
            return this;
        }
    }
}
```

## 6. Common Pitfalls and Solutions

### 1. Mutable State
❌ **Don't:**
```java
public class Builder {
    private List<String> toppings;
    // Direct field access may cause concurrent modification
}
```

✅ **Do:**
```java
public class Builder {
    private final List<String> toppings = new ArrayList<>();
    // Encapsulated, controlled access
}
```

### 2. Missing Validation
❌ **Don't:**
```java
public class Builder {
    public Product build() {
        return new Product(this); // No validation
    }
}
```

✅ **Do:**
```java
public class Builder {
    public Product build() {
        validate();
        return new Product(this);
    }
    
    private void validate() {
        // Validation logic
    }
}
```

## 7. Testing Strategy

### Unit Testing
```java
@Test
void shouldCreateValidProduct() {
    Product product = new Builder()
        .addTopping("Cheese")
        .addTopping("Tomato")
        .build();
    
    assertNotNull(product);
    assertTrue(product.getToppings().contains("Cheese"));
}

@Test
void shouldThrowOnInvalidState() {
    assertThrows(IllegalStateException.class, () -> {
        new Builder().build(); // Missing required parameters
    });
}
```

Remember: The Builder pattern is about providing a clear and flexible way to construct complex objects. Focus on immutability, validation, and clean API design when implementing builders.