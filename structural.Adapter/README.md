# Adapter Design Pattern: A Comprehensive Guide

## 1. Introduction

### What is the Adapter Pattern?

The Adapter pattern allows objects with incompatible interfaces to collaborate. It acts as a bridge between two incompatible interfaces, enabling them to work together without modifying their source code.

### Real-World Analogy

Think of a power adapter that allows a US device to work with an EU socket. Similarly, the Adapter pattern makes a class with one interface work where another interface is expected.

## 2. Core Problems & Solutions

### Problem 1: Incompatible Interfaces
❌ **Problem**:
```java
// Client expects Lion interface
public class Hunter {
    public void hunt(Lion lion) {
        lion.roar();
    }
}

// But we have WildDog that can't be used
public class WildDog {
    public void bark() { ... }
}

// Can't use WildDog directly
Hunter hunter = new Hunter();
WildDog dog = new WildDog();
hunter.hunt(dog); // Compilation error!
```

✅ **Solution**:
```java
public class WildDogAdapter implements Lion {
    private WildDog dog;
    
    public void roar() {
        dog.bark(); // Adapts bark to roar
    }
}

// Now works perfectly
Hunter hunter = new Hunter();
WildDog dog = new WildDog();
Lion adapter = new WildDogAdapter(dog);
hunter.hunt(adapter); // Works!
```

### Problem 2: Legacy System Integration
❌ **Problem**:
```java
// Modern payment system
interface ModernPayment {
    void processPayment(BigDecimal amount);
}

// Legacy payment system
class LegacyPaymentSystem {
    public void oldPayment(double amount, String currency) {
        // Old implementation
    }
}

// Can't use legacy system with modern interface
ModernPayment payment = new LegacyPaymentSystem(); // Won't work!
```

✅ **Solution**:
```java
class PaymentAdapter implements ModernPayment {
    private LegacyPaymentSystem legacySystem;
    
    public void processPayment(BigDecimal amount) {
        double oldAmount = amount.doubleValue();
        legacySystem.oldPayment(oldAmount, "USD");
    }
}
```

### Problem 3: Third-Party Library Integration
❌ **Problem**:
```java
// Your application's interface
interface DataAnalyzer {
    Report analyze(Data data);
}

// Third-party library
class ExternalAnalytics {
    public JSONObject performAnalysis(String rawData) {
        // Different input/output types
    }
}
```

✅ **Solution**:
```java
class AnalyticsAdapter implements DataAnalyzer {
    private ExternalAnalytics externalTool;
    
    public Report analyze(Data data) {
        String rawData = convertToString(data);
        JSONObject result = externalTool.performAnalysis(rawData);
        return convertToReport(result);
    }
}
```

## 3. Key Benefits With Examples

### 1. Reusability of Existing Code
❌ **Without Adapter**:
```java
// Need to rewrite WildDog to implement Lion
class WildDog implements Lion { // Modifying existing code
    public void roar() { // Forced to change implementation
        System.out.println("Barking like roar...");
    }
}
```

✅ **With Adapter**:
```java
// WildDog remains unchanged
class WildDog {
    public void bark() { ... }
}

// Adapter provides compatibility
class WildDogAdapter implements Lion {
    private WildDog dog;
    public void roar() {
        dog.bark();
    }
}
```

### 2. Separation of Concerns
❌ **Without Adapter**:
```java
// Mixing adaptation logic with business logic
class WildDog {
    public void bark() { ... }
    public void actLikeLion() { ... }
    public void convertToLionBehavior() { ... }
    // Class becomes bloated with multiple responsibilities
}
```

✅ **With Adapter**:
```java
// Clean separation of concerns
class WildDog {
    public void bark() { ... } // Original behavior
}

class WildDogAdapter implements Lion {
    private WildDog dog;
    public void roar() { // Adaptation logic isolated
        dog.bark();
    }
}
```

### 3. Easy Maintenance
❌ **Without Adapter**:
```java
// Changes require modifying multiple classes
class Hunter {
    public void hunt(Lion lion) { ... }
    public void huntDog(WildDog dog) { ... }
    public void huntWolf(Wolf wolf) { ... }
    // Adding new animals requires modifying Hunter
}
```

✅ **With Adapter**:
```java
// Single interface for all animals
class Hunter {
    public void hunt(Lion lion) { ... }
}

// New adapters can be added without changing Hunter
class WolfAdapter implements Lion {
    private Wolf wolf;
    public void roar() {
        wolf.howl();
    }
}
```

## 4. Real-World Scenarios

### 1. Database Migration
❌ **Problem**:
```java
// Application using old database
class UserService {
    OldDatabase db;
    
    void saveUser(User user) {
        db.insertRecord("users", user.toMap());
    }
}
```

✅ **Solution**:
```java
// Adapter making new database work with old code
class NewDatabaseAdapter implements OldDatabase {
    private NewDatabase newDb;
    
    public void insertRecord(String table, Map data) {
        Document doc = convertToDocument(data);
        newDb.insert(doc);
    }
}
```

### 2. Payment Gateway Integration
❌ **Problem**:
```java
// Application expects PaymentProcessor
interface PaymentProcessor {
    void processPayment(Payment payment);
}

// PayPal has different interface
class PayPal {
    void sendPayment(double amount, String currency) { ... }
}
```

✅ **Solution**:
```java
class PayPalAdapter implements PaymentProcessor {
    private PayPal paypal;
    
    public void processPayment(Payment payment) {
        paypal.sendPayment(
            payment.getAmount(),
            payment.getCurrency()
        );
    }
}
```

## 5. Implementation Guide

### Core Components
```java
// 1. Target Interface (What client expects)
public interface Lion {
    void roar();
}

// 2. Adapter (Bridges the gap)
public class WildDogAdapter implements Lion {
    private WildDog wildDog; // Adaptee
    
    @Override
    public void roar() {
        wildDog.bark(); // Convert bark to roar
    }
}

// 3. Client (Uses the Target Interface)
public class Hunter {
    public void hunt(Lion lion) {
        lion.roar();
    }
}
```

### Implementation Types

1. **Object Adapter (Composition)**
```java
// Preferred approach - more flexible
public class ObjectAdapter implements Target {
    private Adaptee adaptee;
    // Uses composition
}
```

2. **Class Adapter (Inheritance)**
```java
// Less flexible but sometimes useful
public class ClassAdapter extends Adaptee implements Target {
    // Uses inheritance
}
```

## 6. SOLID Principles Analysis

### Supporting SOLID
1. **Single Responsibility Principle**
   - Adapter only handles interface conversion
   - Original classes maintain their core responsibilities

2. **Open/Closed Principle**
   - New adapters can be added without modifying existing code
   - Original classes remain unchanged

3. **Interface Segregation Principle**
   - Adapter implements only required interface methods
   - Clients depend only on methods they use

4. **Dependency Inversion Principle**
   - High-level modules depend on abstractions (Lion interface)
   - Low-level modules provide concrete implementations

## 7. Best Practices and Anti-patterns

### ✅ Best Practices

1. **Keep Adapters Simple**
```java
// Good: Simple, focused adapter
public class WildDogAdapter implements Lion {
    private WildDog dog;
    
    public void roar() {
        dog.bark(); // Simple conversion
    }
}
```

2. **Use Interface Segregation**
```java
// Good: Implement only what's needed
public interface Lion {
    void roar(); // Only essential method
}
```

### ❌ Anti-patterns

1. **Complex Adaptations**
```java
// Bad: Adapter doing too much
public class ComplexAdapter implements Target {
    private Adaptee adaptee;
    private OtherSystem other;
    
    public void method() {
        // Complex logic
        // Data transformations
        // Business rules
    }
}
```

2. **Chaining Adapters**
```java
// Bad: Multiple adapters
Adaptee adaptee = new Adaptee();
FirstAdapter first = new FirstAdapter(adaptee);
SecondAdapter second = new SecondAdapter(first);
ThirdAdapter third = new ThirdAdapter(second);
```

## 8. Interview Questions

### Basic Level
1. **Q**: What is the Adapter pattern and when should you use it?
   **A**: The Adapter pattern converts one interface into another that clients expect. Use it when you need to make existing classes work with others without modifying their source code.

2. **Q**: What are the two types of Adapter pattern implementations?
   **A**: 
   - Object Adapter: Uses composition
   - Class Adapter: Uses inheritance
   - Object Adapter is generally preferred as it's more flexible

### Intermediate Level
3. **Q**: How does the Adapter pattern support the Open/Closed Principle?
   **A**: 
   - New adapters can be added without modifying existing code
   - Original classes remain unchanged
   - Interface adaptations are encapsulated in separate classes

4. **Q**: What's the difference between Adapter and Facade patterns?
   **A**:
   - Adapter: Makes incompatible interfaces work together
   - Facade: Simplifies complex subsystem interface
   - Adapter changes interface, Facade simplifies it

### Advanced Level
5. **Q**: How would you implement a bidirectional adapter?
   **A**:
```java
public class BidirectionalAdapter implements FirstInterface, SecondInterface {
    private First first;
    private Second second;
    
    @Override
    public void firstMethod() {
        second.secondMethod();
    }
    
    @Override
    public void secondMethod() {
        first.firstMethod();
    }
}
```

6. **Q**: How do you handle complex data transformations in adapters?
   **A**: 
   - Keep transformation logic in separate classes
   - Use Builder pattern for complex objects
   - Consider using mapping libraries
   - Write thorough unit tests for transformations

## 9. Testing Strategy

### Unit Testing
```java
class WildDogAdapterTest {
    @Test
    void shouldAdaptBarkToRoar() {
        WildDog dog = mock(WildDog.class);
        WildDogAdapter adapter = new WildDogAdapter(dog);
        
        adapter.roar();
        
        verify(dog).bark();
    }
}
```

### Integration Testing
```java
class HunterIntegrationTest {
    @Test
    void shouldHuntWithAdapter() {
        WildDog dog = new WildDog();
        WildDogAdapter adapter = new WildDogAdapter(dog);
        Hunter hunter = new Hunter();
        
        hunter.hunt(adapter); // Should work seamlessly
    }
}
```

Remember: The Adapter pattern is about making incompatible interfaces work together while maintaining clean code principles and ensuring testability.
