# Strategy Design Pattern Implementation Guide

## 1. Overview

### Functionality and Problem Addressed
The Strategy pattern is a behavioral design pattern that enables defining a family of algorithms, encapsulating each one, and making them interchangeable. In our implementation, we demonstrate this through a sorting application that dynamically selects between Bubble Sort and Quick Sort algorithms based on the dataset size.

### Motivation
- **Algorithm Encapsulation**: Each sorting algorithm is encapsulated in its own class
- **Runtime Flexibility**: Allows switching between sorting strategies without changing client code
- **Separation of Concerns**: Isolates algorithm implementation from code that uses the algorithm

### Ideal Use Cases
- Sorting algorithms (as demonstrated in this example)
- Payment processing with different payment methods
- Compression algorithms
- Route calculation in navigation systems
- Various validation strategies

### Problem Scenarios vs Strategy Pattern Solutions

#### 1. Sorting Algorithm Selection

**Anti-Pattern**:
```java
class BadSorter {
    public void sort(int[] array, String type) {
        if (type.equals("quick")) {
            // Quick sort implementation
            System.out.println("Quick sorting...");
        } else if (type.equals("bubble")) {
            // Bubble sort implementation
            System.out.println("Bubble sorting...");
        } else if (type.equals("merge")) {
            // Merge sort implementation
            System.out.println("Merge sorting...");
        }
        // Adding new sorting algorithm requires modifying this class
    }
}
```

**Strategy Pattern Solution**:
```java
interface SortStrategy {
    void sort(int[] array);
}

class QuickSort implements SortStrategy {
    public void sort(int[] array) {
        System.out.println("Quick sorting...");
    }
}

class BubbleSort implements SortStrategy {
    public void sort(int[] array) {
        System.out.println("Bubble sorting...");
    }
}

class Sorter {
    private SortStrategy strategy;
    
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void sort(int[] array) {
        strategy.sort(array);
    }
}
```

#### 2. Payment Processing

**Anti-Pattern**:
```java
class BadPaymentProcessor {
    public void processPayment(double amount, String method) {
        if (method.equals("credit")) {
            // Process credit card payment
            System.out.println("Processing credit card payment");
        } else if (method.equals("paypal")) {
            // Process PayPal payment
            System.out.println("Processing PayPal payment");
        } else if (method.equals("crypto")) {
            // Process cryptocurrency payment
            System.out.println("Processing crypto payment");
        }
    }
}
```

**Strategy Pattern Solution**:
```java
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Processing credit card payment");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Processing PayPal payment");
    }
}

class PaymentProcessor {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void processPayment(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

#### 3. File Compression

**Anti-Pattern**:
```java
class BadCompressor {
    public void compress(String file, String format) {
        if (format.equals("zip")) {
            // ZIP compression logic
            System.out.println("Compressing using ZIP");
        } else if (format.equals("rar")) {
            // RAR compression logic
            System.out.println("Compressing using RAR");
        } else if (format.equals("7z")) {
            // 7z compression logic
            System.out.println("Compressing using 7Z");
        }
    }
}
```

**Strategy Pattern Solution**:
```java
interface CompressionStrategy {
    void compress(String file);
}

class ZipCompression implements CompressionStrategy {
    public void compress(String file) {
        System.out.println("Compressing using ZIP");
    }
}

class RarCompression implements CompressionStrategy {
    public void compress(String file) {
        System.out.println("Compressing using RAR");
    }
}

class FileCompressor {
    private CompressionStrategy strategy;
    
    public void setCompressionStrategy(CompressionStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void compress(String file) {
        strategy.compress(file);
    }
}
```

#### 4. Navigation Routing

**Anti-Pattern**:
```java
class BadNavigator {
    public void calculateRoute(String from, String to, String preference) {
        if (preference.equals("fastest")) {
            // Calculate fastest route
            System.out.println("Calculating fastest route");
        } else if (preference.equals("shortest")) {
            // Calculate shortest route
            System.out.println("Calculating shortest route");
        } else if (preference.equals("economical")) {
            // Calculate economical route
            System.out.println("Calculating economical route");
        }
    }
}
```

**Strategy Pattern Solution**:
```java
interface RouteStrategy {
    void calculateRoute(String from, String to);
}

class FastestRoute implements RouteStrategy {
    public void calculateRoute(String from, String to) {
        System.out.println("Calculating fastest route");
    }
}

class ShortestRoute implements RouteStrategy {
    public void calculateRoute(String from, String to) {
        System.out.println("Calculating shortest route");
    }
}

class Navigator {
    private RouteStrategy routeStrategy;
    
    public void setRouteStrategy(RouteStrategy strategy) {
        this.routeStrategy = strategy;
    }
    
    public void calculateRoute(String from, String to) {
        routeStrategy.calculateRoute(from, to);
    }
}
```

### Key Benefits of Strategy Pattern Over Anti-Patterns

1. **Elimination of Complex Conditionals**
   - Anti-pattern: Large if-else chains or switch statements
   - Solution: Each strategy encapsulated in its own class

2. **Easy Extension**
   - Anti-pattern: Modifying existing code to add new behavior
   - Solution: Create new strategy classes without touching existing code

3. **Runtime Flexibility**
   - Anti-pattern: Behavior hardcoded and difficult to change
   - Solution: Strategies can be swapped at runtime

4. **Better Testing**
   - Anti-pattern: Testing all conditions in one large class
   - Solution: Each strategy can be tested independently

5. **Improved Maintainability**
   - Anti-pattern: Changes affect entire class, risk of breaking existing code
   - Solution: Changes isolated to specific strategy implementations

## 2. Design Pattern Framework

### Decision Guide
When to use the Strategy pattern:
1. ✅ Multiple algorithms exist for a specific task
2. ✅ Algorithm selection needs to happen at runtime
3. ✅ Algorithms need to be interchangeable
4. ✅ Algorithm implementation details should be hidden from client code

When not to use:
1. ❌ Only one algorithm implementation exists
2. ❌ Algorithms rarely change
3. ❌ Simple conditional logic would suffice

### Pattern Structure
```
┌─────────────┐         ┌───────────────┐
│   Context   │ ───────▶│ <<interface>> │
│   (Sorter)  │         │ SortStrategy  │
└─────────────┘         └───────────────┘
                               ▲
                               │
                 ┌─────────────┴─────────────┐
                 │                           │
        ┌────────────────┐           ┌─────────────────┐
        │ BubbleSort     │           │   QuickSort     │
        │   Strategy     │           │    Strategy     │
        └────────────────┘           └─────────────────┘
```

### Comparison with Similar Patterns
- **Strategy vs State**: Strategy pattern focuses on interchangeable algorithms, while State pattern represents state-dependent behavior
- **Strategy vs Template Method**: Strategy uses composition and is more flexible at runtime, while Template Method uses inheritance
- **Strategy vs Command**: Strategy encapsulates algorithms, Command encapsulates requests

## 3. Implementation Details

### Core Components
1. **SortStrategy Interface**
```java
public interface SortStrategy {
    int[] sort(int[] dataset);
}
```

2. **Concrete Strategies**
- BubbleSortStrategy: Implements sorting for small datasets
- QuickSortStrategy: Implements sorting for large datasets

3. **Context (Sorter)**
- Maintains reference to current strategy
- Delegates sorting to appropriate strategy based on dataset size

### Implementation Highlights
- The Sorter class automatically selects the appropriate strategy based on array size
- Strategies are injected through constructor, following dependency injection principles
- Each strategy implements the same interface, ensuring interchangeability

## 4. SOLID Principles Application

### Single Responsibility Principle (SRP)
- Each strategy class has one responsibility: implementing a specific sorting algorithm
- The Sorter class's sole responsibility is strategy selection and delegation

### Open/Closed Principle (OCP)
- New sorting strategies can be added without modifying existing code
- The Sorter class is open for extension but closed for modification

### Dependency Inversion Principle (DIP)
- High-level Sorter class depends on abstractions (SortStrategy interface)
- Concrete strategies can be easily swapped without affecting the Sorter class

## 5. Helpful Tips

### Best Practices
1. Keep the Strategy interface focused and minimal
2. Use dependency injection to provide strategies
3. Consider using enum or factory for strategy creation
4. Document performance characteristics of each strategy

### Common Pitfalls
1. Over-complicating the strategy interface
2. Creating too many strategy classes
3. Tight coupling between context and strategies
4. Not considering the overhead of strategy switching

### Testing Recommendations
- Unit test each strategy independently
- Test strategy selection logic
- Verify correct delegation in the context class
- Test with edge cases and boundary conditions

## 6. Interview Preparation

### Common Interview Questions

1. **Q: What is the Strategy Pattern, and when should it be used?**
   - A: The Strategy pattern enables defining a family of algorithms, encapsulating each one, and making them interchangeable. Use it when you need to:
     - Switch between algorithms at runtime
     - Isolate algorithm implementation from code that uses the algorithm
     - Avoid complex conditional statements
     - Provide different variants of an algorithm

2. **Q: How does the Strategy pattern support the Open/Closed Principle?**
   - A: By encapsulating algorithms in separate classes that implement a common interface, new strategies can be added without modifying existing code. The context class remains unchanged while supporting new algorithms.

3. **Q: Compare Strategy pattern with State pattern**
   - A: While both patterns might appear similar in structure:
     - Strategy pattern focuses on interchangeable algorithms
     - State pattern represents different behaviors based on internal state
     - Strategy is typically set once and rarely changes
     - State changes frequently based on context

4. **Q: What are the disadvantages of the Strategy pattern?**
   - A: Key considerations include:
     - Increased number of classes in the system
     - Clients must be aware of different strategies
     - Communication overhead between strategy and context
     - Potential memory overhead from strategy objects

5. **Q: How would you implement dynamic strategy selection?**
   - A: Several approaches are possible:
     - Factory pattern to create strategies based on conditions
     - Strategy registry with lookup mechanism
     - Rule engine for complex selection logic
     - Configuration-driven selection

### Key Discussion Points
1. Pattern Recognition: Identify scenarios where Strategy pattern adds value
2. Implementation Trade-offs: Understanding when to use Strategy vs simpler solutions
3. Design Considerations: Strategy creation, lifetime management, and selection mechanism
4. Testing Strategies: Approaches for testing interchangeable algorithms
5. Performance Implications: Understanding the impact of strategy switching

Remember to be prepared to discuss real-world applications and alternatives to the Strategy pattern during interviews.