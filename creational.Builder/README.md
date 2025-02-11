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

## 4. Anti-patterns and Solutions

### 1. Telescoping Constructors
❌ **Problem**:
```java
public class Pizza {
    public Pizza(String size) { ... }
    public Pizza(String size, boolean cheese) { ... }
    public Pizza(String size, boolean cheese, boolean pepperoni) { ... }
    public Pizza(String size, boolean cheese, boolean pepperoni, boolean mushrooms) { ... }
}

// Usage - Unclear what the booleans represent
Pizza pizza = new Pizza("large", true, false, true);
```

✅ **Solution**:
```java
public class Pizza {
    private Pizza(PizzaBuilder builder) { ... }
    
    public static class PizzaBuilder {
        public PizzaBuilder size(String size) { ... }
        public PizzaBuilder addCheese() { ... }
        public PizzaBuilder addPepperoni() { ... }
        public PizzaBuilder addMushrooms() { ... }
    }
}

// Usage - Clear and readable
Pizza pizza = new PizzaBuilder()
    .size("large")
    .addCheese()
    .addMushrooms()
    .build();
```

### 2. Object State Validation
❌ **Problem**:
```java
public class User {
    private String email;
    private String password;
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        // No validation at construction time
    }
}

// Invalid state possible
User user = new User("invalid-email", "");
```

✅ **Solution**:
```java
public class User {
    private final String email;
    private final String password;
    
    private User(UserBuilder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }
    
    public static class UserBuilder {
        public User build() {
            validateEmail(email);
            validatePassword(password);
            return new User(this);
        }
        
        private void validateEmail(String email) {
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Invalid email");
            }
        }
    }
}

// Validation enforced at build time
User user = new UserBuilder()
    .email("valid@email.com")
    .password("secure123")
    .build();
```

### 3. Immutability
❌ **Problem**:
```java
public class Configuration {
    private List<String> settings;
    
    public Configuration(List<String> settings) {
        this.settings = settings; // Direct reference
    }
    
    // State can be modified after creation
    public List<String> getSettings() {
        return settings;
    }
}
```

✅ **Solution**:
```java
public class Configuration {
    private final List<String> settings;
    
    private Configuration(ConfigBuilder builder) {
        this.settings = List.copyOf(builder.settings); // Immutable copy
    }
    
    public List<String> getSettings() {
        return List.copyOf(settings); // Defensive copy
    }
    
    public static class ConfigBuilder {
        private List<String> settings = new ArrayList<>();
        
        public ConfigBuilder addSetting(String setting) {
            settings.add(setting);
            return this;
        }
    }
}
```

### 4. Step-by-Step Construction
❌ **Problem**:
```java
public class Document {
    public Document(String title, String content, String author, 
                   String format, boolean encrypted) {
        // All parameters must be provided at once
        // Hard to understand the construction process
    }
}
```

✅ **Solution**:
```java
public class Document {
    public static class DocumentBuilder {
        public DocumentBuilder beginDocument(String title) {
            validateTitle(title);
            return this;
        }
        
        public DocumentBuilder addContent(String content) {
            validateContent(content);
            return this;
        }
        
        public DocumentBuilder signBy(String author) {
            validateAuthor(author);
            return this;
        }
        
        public DocumentBuilder formatAs(String format) {
            validateFormat(format);
            return this;
        }
        
        public Document build() {
            return encrypted ? 
                encryptAndBuild() : 
                buildPlaintext();
        }
    }
}

// Clear construction steps
Document doc = new DocumentBuilder()
    .beginDocument("Title")
    .addContent("Content")
    .signBy("Author")
    .formatAs("PDF")
    .encrypt()
    .build();
```

### 5. Optional Parameters
❌ **Problem**:
```java
public class Report {
    public Report(String title, String data) {
        // Required parameters
    }
    
    public void setFooter(String footer) { ... }
    public void setHeader(String header) { ... }
    public void setPageNumbers(boolean show) { ... }
    // Object in potentially incomplete state
}
```

✅ **Solution**:
```java
public class Report {
    private Report(ReportBuilder builder) {
        // All fields set at once
    }
    
    public static class ReportBuilder {
        private final String title;  // Required
        private final String data;   // Required
        private String footer;       // Optional
        private String header;       // Optional
        private boolean showPageNumbers; // Optional
        
        public ReportBuilder(String title, String data) {
            this.title = Objects.requireNonNull(title);
            this.data = Objects.requireNonNull(data);
        }
        
        public ReportBuilder withFooter(String footer) {
            this.footer = footer;
            return this;
        }
        
        public ReportBuilder withHeader(String header) {
            this.header = header;
            return this;
        }
        
        public ReportBuilder showPageNumbers() {
            this.showPageNumbers = true;
            return this;
        }
    }
}

// Clear which parameters are optional
Report report = new ReportBuilder("Q1 Report", "Data")
    .withHeader("Company Name")
    .showPageNumbers()
    .build();
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