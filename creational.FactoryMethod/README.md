# Factory Method Pattern Implementation Guide

## 1. Overview

### What is Factory Method?
The Factory Method pattern is a creational design pattern that provides an interface for creating objects but allows subclasses to alter the type of objects that will be created. In our implementation, we demonstrate this through a hiring system where different types of managers can create their specific type of interviewer.

### Current Implementation
Our implementation shows how different hiring managers conduct interviews:
```java
DevelopmentManager developmentManager = new DevelopmentManager();
developmentManager.takeInterview();  // Creates a Developer interviewer

MarketingManager marketingManager = new MarketingManager();
marketingManager.takeInterview();    // Creates a CommunityExecutive interviewer
```

### Components
1. **Creator (HiringManager)**
   - Abstract class defining the factory method
   - Contains the core business logic
   - Methods: makeInterviewer(), takeInterview()

2. **Concrete Creators**
   - DevelopmentManager
   - MarketingManager
   - Each creates specific type of interviewer

3. **Product Interface (Interviewer)**
   - Defines the interface for objects the factory method creates
   - Methods: askQuestions()

4. **Concrete Products**
   - Developer
   - CommunityExecutive

### Why Factory Method?
1. **Flexibility in Object Creation**
   - Decouples object creation from business logic
   - Allows adding new interviewer types easily
   
2. **Encapsulation**
   - Hides interviewer creation logic
   - Centralizes object creation code

3. **Single Responsibility**
   - Separates creation logic from business logic
   - Each class handles one specific type of interviewer

## 2. Design Pattern Decision Framework

### When to Use Factory Method?

✅ **Use When:**
- Creation logic should be separate from core business logic
- Class cannot anticipate the type of objects it needs to create
- Class wants its subclasses to specify the objects it creates
- You want to delegate responsibility to helper subclasses

❌ **Don't Use When:**
- Object creation is simple
- Type of object is fixed and won't change
- Creation logic doesn't vary by condition or type

### Decision Tree
```
Is object creation complex or varies by type?
├── Yes → Do you need subclasses to decide object type?
│   ├── Yes → Does creation logic need to be separate?
│   │   ├── Yes → Use Factory Method ✅
│   │   └── No → Use Simple Factory
│   └── No → Consider Builder Pattern
└── No → Use direct construction
```

## 3. SOLID Principles Application

### 1. Single Responsibility Principle
✅ HiringManager focuses only on interview process
✅ Each concrete manager creates only its specific interviewer type
✅ Interviewers focus only on their interview questions

### 2. Open/Closed Principle
✅ New interviewer types can be added without modifying existing code
✅ New manager types can be added by extending HiringManager

### 3. Liskov Substitution Principle
✅ All concrete interviewers can substitute Interviewer interface
✅ All managers can substitute HiringManager

### 4. Interface Segregation Principle
✅ Interviewer interface is focused and minimal
✅ Managers have clear, specific responsibilities

### 5. Dependency Inversion Principle
✅ High-level modules depend on abstractions
✅ HiringManager depends on Interviewer interface, not concrete classes

## 4. Implementation Tips

### 1. Factory Method Naming
```java
// Good
protected Interviewer makeInterviewer()

// Avoid
protected Interviewer createInterviewer() // Less conventional
protected Interviewer getInterviewer() // Implies retrieval
```

### 2. Error Handling
```java
protected Interviewer makeInterviewer() {
    try {
        return new Developer();
    } catch (Exception e) {
        throw new InterviewerCreationException("Failed to create interviewer", e);
    }
}
```

### 3. Initialization
```java
// Good - Lazy initialization
public void takeInterview() {
    interviewer = this.makeInterviewer();  // Created only when needed
    interviewer.askQuestions();
}

// Avoid - Eager initialization
private Interviewer interviewer = makeInterviewer();  // Created at instantiation
```

## 5. Interview Questions

### Q1: What's the difference between Factory Method and Abstract Factory?
**Answer**: 
- Factory Method creates a single object through inheritance
- Abstract Factory creates families of related objects
- Factory Method uses a method to create objects
- Abstract Factory uses an object to create objects

### Q2: How would you add a new interviewer type?
**Answer**:
1. Create new interviewer:
   ```java
   public class TechnicalWriter implements Interviewer {
       @Override
       public void askQuestions() {
           System.out.println("Asking about documentation skills.");
       }
   }
   ```
2. Create new manager:
   ```java
   public class DocumentationManager extends HiringManager {
       @Override
       protected Interviewer makeInterviewer() {
           return new TechnicalWriter();
       }
   }
   ```

### Q3: How does Factory Method help with the Open/Closed Principle?
**Answer**:
- New interviewer types can be added without changing existing code
- Existing manager classes remain unchanged
- Only requires adding new classes, not modifying existing ones

### Q4: What are the trade-offs of using Factory Method?
**Answer**:
- ➕ Flexible object creation
- ➕ Follows Single Responsibility Principle
- ➕ Easy to extend with new types
- ➖ Can lead to many subclasses
- ➖ May be overkill for simple creation logic
- ➖ Requires inheritance hierarchy

## 6. Common Pitfalls and Solutions

### 1. Complex Hierarchies
❌ **Don't:**
```java
// Too many levels of inheritance
public class SeniorDevelopmentManager extends DevelopmentManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new SeniorDeveloper();
    }
}
```

✅ **Do:**
```java
// Keep hierarchy flat
public class SeniorDevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new SeniorDeveloper();
    }
}
```

### 2. Mixing Concerns
❌ **Don't:**
```java
public class DevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        if (isJuniorPosition()) {
            return new JuniorDeveloper();
        }
        return new SeniorDeveloper();
    }
}
```

✅ **Do:**
```java
// Separate managers for different concerns
public class JuniorDevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new JuniorDeveloper();
    }
}
```

## 7. Testing Strategy

### Unit Testing
```java
@Test
void shouldCreateCorrectInterviewerType() {
    HiringManager manager = new DevelopmentManager();
    Interviewer interviewer = manager.makeInterviewer();
    
    assertTrue(interviewer instanceof Developer);
}

@Test
void shouldAskCorrectQuestions() {
    HiringManager manager = new MarketingManager();
    manager.takeInterview();
    
    // Verify output contains "Asking about community building"
}
```

Remember: Factory Method is about delegating the instantiation logic to subclasses while maintaining a common interface. Focus on separation of concerns and keeping the creation logic flexible and extensible.