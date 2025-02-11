# Decorator Design Pattern Implementation Guide
## 1. Introduction

### What is the Decorator Pattern?

The Decorator pattern allows behavior to be added to individual objects dynamically without affecting other objects of the same class. It provides a flexible alternative to subclassing for extending functionality.

### Real-World Analogy: Pizza Customization

Imagine customizing a pizza. You start with a base (e.g., Wheat Base) and add toppings (e.g., Onion, Olive). Each topping "decorates" the pizza, adding functionality (flavor, cost) without changing the base pizza itself.

## 2. Core Problems & Solutions

### Problem 1: Static Inheritance Limitations
❌ **Problem**:
```java
// Using inheritance leads to class explosion
class Pizza { }
class PizzaWithCheese extends Pizza { }
class PizzaWithOnion extends Pizza { }
class PizzaWithCheeseAndOnion extends Pizza { }
class PizzaWithCheeseAndOnionAndTomato extends Pizza { }
// Combinations grow exponentially!
```

✅ **Solution**:
```java
// Decorator pattern allows dynamic composition
Pizza pizza = new BasePizza();
pizza = new CheeseDecorator(pizza);
pizza = new OnionDecorator(pizza);
// Can add any combination at runtime!
```

### Problem 2: Runtime Behavior Modification
❌ **Problem**:
```java
class Pizza {
    // Need to modify class for each new feature
    boolean hasExtraCheese;
    boolean hasOnion;
    boolean hasTomato;
    
    double getCost() {
        double cost = basePrice;
        if(hasExtraCheese) cost += 20;
        if(hasOnion) cost += 15;
        if(hasTomato) cost += 10;
        return cost;
    }
}
```

✅ **Solution**:
```java
interface Pizza {
    double getCost();
}

class CheeseDecorator extends ToppingDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }
    
    @Override
    public double getCost() {
        return super.getCost() + 20;
    }
}
// Each new topping is a separate decorator
```

### Problem 3: Feature Combination Management
❌ **Problem**:
```java
// Hard to manage different feature combinations
if(wantsCheese && wantsOnion && !wantsTomato) {
    return new PizzaWithCheeseAndOnion();
} else if(wantsCheese && !wantsOnion && wantsTomato) {
    return new PizzaWithCheeseAndTomato();
}
// Complex conditional logic
```

✅ **Solution**:
```java
Pizza pizza = new BasePizza();
if(wantsCheese) pizza = new CheeseDecorator(pizza);
if(wantsOnion) pizza = new OnionDecorator(pizza);
if(wantsTomato) pizza = new TomatoDecorator(pizza);
// Simple, flexible composition
```

## 3. Decision Framework

### When to Use Decorator Pattern?

```
Decision Tree:
┌─────────────────────────┐
│ Need to modify object   │
│ behavior?              │
└──────────────┬──────────┘
               │
               ▼
┌─────────────────────────┐
│ Can inheritance solve   │
│ it?                    │
└──────────────┬──────────┘
               │
        ┌──────┴──────┐
        ▼             ▼
    ┌───────┐    ┌───────┐
    │  Yes   │    │   No   │
    └───┬────┘    └───┬───┘
        │            │
        ▼            ▼
┌──────────────┐  ┌──────────────┐
│Many possible │  │Consider other │
│combinations? │  │patterns      │
└──────┬───────┘  └─────────────┘
       │
       ▼
┌─────────────────┐
│Need runtime     │
│modifications?   │
└────────┬────────┘
         │
    ┌────┴────┐
    ▼         ▼
┌──────┐  ┌──────┐
│ Yes  │  │ No   │
└──┬───┘  └──┬───┘
   │         │
   ▼         ▼
┌──────┐  ┌──────┐
│ Use  │  │ Use  │
│Decor-│  │Inher-│
│ator  │  │itance│
└──────┘  └──────┘
```

## 4. Implementation Details

### Core Structure
```java
// Base Component
public interface Pizza {
    double getCost();
    String getDescription();
}

// Concrete Component
public class WheatBase implements Pizza {
    @Override
    public double getCost() {
        return 100.0;
    }

    @Override
    public String getDescription() {
        return "Wheat Base";
    }
}

// Abstract Decorator
public abstract class ToppingDecorator implements Pizza {
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }
}

// Concrete Decorator
public class OnionTopping extends ToppingDecorator {
    public OnionTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getCost() {
        return super.getCost() + 20.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Onion";
    }
}
```

### Usage Example
```java
Pizza pizza = new WheatBase();
System.out.println(pizza.getDescription());  // "Wheat Base"
System.out.println(pizza.getCost());         // 100.0

pizza = new OnionTopping(pizza);
System.out.println(pizza.getDescription());  // "Wheat Base, Onion"
System.out.println(pizza.getCost());         // 120.0
```

## 5. SOLID Principles Support

### Single Responsibility Principle
- Each decorator class handles one specific additional responsibility
- Base components focus on core functionality

### Open/Closed Principle
- New behaviors can be added through new decorator classes
- Existing code remains unchanged

### Interface Segregation
- Decorators implement only necessary methods
- Clients aren't forced to depend on methods they don't use

## 6. Best Practices & Tips

### ✅ Do's
1. Keep decorators lightweight
2. Use meaningful names for decorators
3. Consider order of decoration
4. Maintain consistent interface

### ❌ Don'ts
1. Don't create deep decorator chains
2. Avoid state in decorators
3. Don't modify core component behavior
4. Don't rely on decorator order

### Testing Strategies
```java
@Test
void shouldCalculateCostCorrectly() {
    Pizza pizza = new WheatBase();
    pizza = new OnionTopping(pizza);
    
    assertEquals(120.0, pizza.getCost());
    assertEquals("Wheat Base, Onion", 
                 pizza.getDescription());
}
```

## 7. Interview Questions

### Basic Level
1. **Q**: What is the Decorator pattern?
   **A**: A structural pattern that lets you attach new behaviors to objects by placing them inside wrapper objects containing the behaviors.

2. **Q**: How is it different from inheritance?
   **A**: Decorator uses composition and provides runtime flexibility, while inheritance is static and can lead to class explosion.

### Intermediate Level
3. **Q**: When would you choose Decorator over Strategy pattern?
   **A**: 
   - Decorator: When adding layers of behavior
   - Strategy: When switching between different algorithms

4. **Q**: How does Decorator support the Open/Closed principle?
   **A**: New behaviors can be added through new decorator classes without modifying existing code.

### Advanced Level
5. **Q**: How would you handle decorator order dependencies?
   **A**: 
   ```java
   // Use builder pattern or factory
   public class PizzaBuilder {
       private Pizza pizza;
       
       public PizzaBuilder(Pizza base) {
           this.pizza = base;
       }
       
       public PizzaBuilder addOnion() {
           pizza = new OnionTopping(pizza);
           return this;
       }
       
       public Pizza build() {
           return pizza;
       }
   }
   ```

6. **Q**: How to avoid excessive decoration?
   **A**: 
   - Use builder pattern
   - Implement decoration limits
   - Consider using composite pattern for groups
   - Monitor performance impact

### Real-World Implementation Example
```java
// Shopping cart with discounts
public interface Order {
    double getTotal();
}

public class BaseOrder implements Order {
    private double amount;
    
    @Override
    public double getTotal() {
        return amount;
    }
}

public class DiscountDecorator implements Order {
    private Order order;
    private double discount;
    
    @Override
    public double getTotal() {
        return order.getTotal() * (1 - discount);
    }
}
```

Remember: The key to successful decorator implementation is maintaining simplicity while providing flexibility for extension.
