# Factory Method Pattern Implementation Guide

## 1. Overview

### What is Factory Method?
The Factory Method pattern is a creational design pattern that provides an interface for creating objects but allows subclasses to alter the type of objects that will be created. In our implementation, we demonstrate this through a hiring system where different types of managers can create their specific type of interviewer.

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

### Correct Implementation vs. Violations

#### ✅ Correct Implementation
```java
// Abstract creator
abstract class HiringManager {
    // Factory method
    protected abstract Interviewer makeInterviewer();
    
    // Business logic
    public void takeInterview() {
        Interviewer interviewer = makeInterviewer();
        interviewer.askQuestions();
    }
}

// Concrete creator
class DevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new Developer();
    }
}

// Usage
DevelopmentManager developmentManager = new DevelopmentManager();
developmentManager.takeInterview();  // Creates a Developer interviewer
```

#### ❌ Common Violations

1. **Direct Object Creation (Violation of Factory Pattern)**
```java
// Bad: Tightly coupled, no abstraction
class HiringManager {
    public void takeInterview(String type) {
        if (type.equals("developer")) {
            Developer dev = new Developer();
            dev.askQuestions();
        } else if (type.equals("marketing")) {
            CommunityExecutive exec = new CommunityExecutive();
            exec.askQuestions();
        }
    }
}
```

2. **Mixed Business and Creation Logic (Violation of Single Responsibility)**
```java
// Bad: Mixing object creation with business logic
class DevelopmentManager {
    public void takeInterview() {
        Developer developer = new Developer();
        checkDeveloperAvailability();
        setupInterviewRoom();
        developer.askQuestions();
        recordInterviewFeedback();
    }
}
```

3. **Hard-coded Dependencies (Violation of Dependency Inversion)**
```java
// Bad: Concrete class dependency
class InterviewService {
    private Developer developer = new Developer();  // Hard-coded dependency
    
    public void conductInterview() {
        developer.askQuestions();
    }
}
```

### Key Features Demonstrated

1. **Decoupling Object Creation from Business Logic**

✅ **Correct Implementation:**
```java
abstract class HiringManager {
    // Business logic separate from creation
    public void takeInterview() {
        Interviewer interviewer = makeInterviewer(); // Creation delegated
        interviewer.askQuestions();                  // Business logic
    }
    
    protected abstract Interviewer makeInterviewer();
}
```

❌ **Violation:**
```java
class HiringManager {
    // Business logic mixed with creation
    public void takeInterview() {
        Developer developer = new Developer();  // Direct creation
        developer.askQuestions();
    }
}
```

2. **Flexibility in Object Creation**

✅ **Correct Implementation:**
```java
// Easy to add new types
class TechnicalWritingManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new TechnicalWriter();
    }
}

class QAManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new QAEngineer();
    }
}
```

❌ **Violation:**
```java
// Need to modify existing code to add new types
class InterviewFactory {
    public static Interviewer createInterviewer(String type) {
        switch(type) {
            case "dev": return new Developer();
            case "marketing": return new CommunityExecutive();
            // Need to modify this method for each new type
        }
    }
}
```

3. **Encapsulation of Creation Logic**

✅ **Correct Implementation:**
```java
class DevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        // Creation logic encapsulated in factory method
        Developer developer = new Developer();
        developer.setExperienceLevel("Senior");
        developer.setTechnicalStack(Arrays.asList("Java", "Spring"));
        return developer;
    }
}
```

❌ **Violation:**
```java
// Creation logic exposed and duplicated
class Interview {
    public void startDevInterview() {
        Developer developer = new Developer();
        developer.setExperienceLevel("Senior");
        developer.setTechnicalStack(Arrays.asList("Java", "Spring"));
        developer.askQuestions();
    }
    
    public void startMarketingInterview() {
        CommunityExecutive executive = new CommunityExecutive();
        executive.setExperienceLevel("Senior");
        executive.askQuestions();
    }
}
```

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

## Advanced Testing Scenarios

### 1. Mocking and Test Doubles

✅ **With Factory Method:**
```java
// Easy to mock for testing
class MockInterviewer implements Interviewer {
    private List<String> askedQuestions = new ArrayList<>();
    
    @Override
    public void askQuestions() {
        askedQuestions.add("Mock question asked");
    }
    
    public List<String> getAskedQuestions() {
        return askedQuestions;
    }
}

class TestableHiringManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new MockInterviewer();
    }
}

@Test
void shouldTrackInterviewQuestions() {
    TestableHiringManager manager = new TestableHiringManager();
    manager.takeInterview();
    
    MockInterviewer interviewer = (MockInterviewer) manager.makeInterviewer();
    assertFalse(interviewer.getAskedQuestions().isEmpty());
}
```

### 2. Error Handling Patterns

✅ **Robust Error Handling:**
```java
class InterviewerCreationException extends RuntimeException {
    public InterviewerCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}

class RemoteDevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        try {
            RemoteInterviewer interviewer = new RemoteInterviewer();
            if (!interviewer.checkInternetConnection()) {
                throw new ConnectionException("No internet connection available");
            }
            if (!interviewer.checkAvailability()) {
                throw new SchedulingException("No remote interviewer available");
            }
            return interviewer;
        } catch (ConnectionException | SchedulingException e) {
            throw new InterviewerCreationException("Failed to create remote interviewer", e);
        }
    }
}

// Usage with error handling
try {
    HiringManager manager = new RemoteDevelopmentManager();
    manager.takeInterview();
} catch (InterviewerCreationException e) {
    // Handle gracefully - maybe try another interviewer type
    log.error("Interview creation failed", e);
    fallbackToLocalInterviewer();
}
```

### 3. Performance Optimization

✅ **Lazy Loading Example:**
```java
class OptimizedHiringManager extends HiringManager {
    private Interviewer cachedInterviewer;
    
    @Override
    protected Interviewer makeInterviewer() {
        if (cachedInterviewer == null) {
            cachedInterviewer = createAndInitializeInterviewer();
        }
        return cachedInterviewer;
    }
    
    private Interviewer createAndInitializeInterviewer() {
        Developer developer = new Developer();
        developer.loadQuestionBank();  // Expensive operation
        return developer;
    }
}
```

## Factory Method Anti-Patterns to Avoid

### 1. Over-Abstraction
❌ **Don't:**
```java
// Too many layers of abstraction
interface InterviewerFactory {
    Interviewer createInterviewer();
}

class DeveloperFactory implements InterviewerFactory {
    @Override
    public Interviewer createInterviewer() {
        return new Developer();
    }
}

class HiringManager {
    private InterviewerFactory factory;
    // Unnecessary complexity
}
```

### 2. Mixed Responsibilities
❌ **Don't:**
```java
class DevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        Developer dev = new Developer();
        dev.setSkills(getSkillsFromDatabase());  // Database access in factory method
        dev.scheduleInterview(Calendar.getInstance());  // Scheduling logic
        notifyHR();  // Notification logic
        return dev;
    }
}
```

### 3. Breaking Encapsulation
❌ **Don't:**
```java
class PublicFactoryMethod extends HiringManager {
    public Interviewer makeInterviewer() {  // Made public
        return new Developer();
    }
}
```

These examples demonstrate how Factory Method pattern can be implemented properly while avoiding common pitfalls and maintaining good design principles.

## Implementation Best Practices

### 1. Proper Factory Method Naming and Structure

✅ **Best Practice:**
```java
abstract class HiringManager {
    // Clear, consistent naming
    protected abstract Interviewer makeInterviewer();
    
    // Template method pattern with factory method
    public final void conductInterview() {
        Interviewer interviewer = makeInterviewer();
        preInterview();
        interviewer.askQuestions();
        postInterview();
    }
    
    protected void preInterview() { }
    protected void postInterview() { }
}
```

### 2. Error Handling and Validation

✅ **Best Practice:**
```java
class DevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        try {
            Developer developer = new Developer();
            if (!developer.isAvailable()) {
                throw new InterviewerUnavailableException("No developer available");
            }
            return developer;
        } catch (Exception e) {
            throw new InterviewerCreationException("Failed to create developer interviewer", e);
        }
    }
}
```

### 3. Testing Strategy with Examples

```java
class HiringManagerTest {
    @Test
    void shouldCreateCorrectInterviewer() {
        // Given
        HiringManager manager = new DevelopmentManager();
        
        // When
        Interviewer interviewer = manager.makeInterviewer();
        
        // Then
        assertThat(interviewer).isInstanceOf(Developer.class);
    }
    
    @Test
    void shouldExecuteInterviewProcess() {
        // Given
        HiringManager manager = spy(new DevelopmentManager());
        
        // When
        manager.conductInterview();
        
        // Then
        verify(manager).preInterview();
        verify(manager).makeInterviewer();
        verify(manager).postInterview();
    }
}
```

## Real-World Impact

### 1. Scalability Example

Consider a notification system that needs to support multiple channels:

✅ **With Factory Method:**
```java
abstract class NotificationManager {
    protected abstract NotificationSender makeNotificationSender();
    
    public void notify(String message) {
        NotificationSender sender = makeNotificationSender();
        sender.send(message);
    }
}

class EmailNotificationManager extends NotificationManager {
    @Override
    protected NotificationSender makeNotificationSender() {
        return new EmailSender(configureEmailSettings());
    }
}

class SMSNotificationManager extends NotificationManager {
    @Override
    protected NotificationSender makeNotificationSender() {
        return new SMSSender(configureSMSGateway());
    }
}
```

❌ **Without Factory Method:**
```java
class NotificationService {
    public void sendNotification(String type, String message) {
        if (type.equals("email")) {
            EmailSender sender = new EmailSender();
            sender.configureSettings();
            sender.send(message);
        } else if (type.equals("sms")) {
            SMSSender sender = new SMSSender();
            sender.setupGateway();
            sender.send(message);
        }
        // Adding a new channel requires modifying this method
    }
}
```

### 2. Maintenance Benefits

Consider adding a new interview type for Data Scientists:

✅ **With Factory Method (Easy Addition):**
```java
// Just add these new classes without touching existing code
class DataScienceInterviewer implements Interviewer {
    @Override
    public void askQuestions() {
        System.out.println("Asking about machine learning algorithms...");
    }
}

class DataScienceManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new DataScienceInterviewer();
    }
}
```

❌ **Without Factory Method (Requires Changes):**
```java
class InterviewService {
    // Need to modify existing code
    public void conductInterview(String department) {
        switch(department) {
            case "dev": /* existing code */
            case "marketing": /* existing code */
            case "dataScience": // New modification required here
                DataScienceInterviewer interviewer = new DataScienceInterviewer();
                interviewer.askQuestions();
                break;
        }
    }
}
```

### 3. Configuration Flexibility

✅ **With Factory Method:**
```java
class CloudDevelopmentManager extends HiringManager {
    private final String[] requiredTechnologies;
    
    public CloudDevelopmentManager(String[] technologies) {
        this.requiredTechnologies = technologies;
    }
    
    @Override
    protected Interviewer makeInterviewer() {
        return new CloudDeveloper(requiredTechnologies);
    }
}

// Usage
HiringManager awsManager = new CloudDevelopmentManager(new String[]{"AWS", "Docker"});
HiringManager azureManager = new CloudDevelopmentManager(new String[]{"Azure", "Kubernetes"});
```

## Impact on System Evolution

### 1. Adding New Features
When adding new interview types or changing interview processes:

✅ **With Factory Method:**
- Add new classes
- No modification to existing code
- Independent deployment possible
- Easy to test in isolation

❌ **Without Factory Method:**
- Modify existing if-else chains or switch statements
- Risk of breaking existing functionality
- Need to regression test everything
- Harder to maintain

### 2. System Integration

✅ **With Factory Method:**
```java
// Easy integration with other systems
class ExternalRecruiterManager extends HiringManager {
    private final ExternalRecruitmentAPI api;
    
    @Override
    protected Interviewer makeInterviewer() {
        return new ExternalInterviewer(api);
    }
}

// Can easily integrate with different APIs without changing core logic
ExternalRecruiterManager linkedInManager = new ExternalRecruiterManager(new LinkedInAPI());
ExternalRecruiterManager indeedManager = new ExternalRecruiterManager(new IndeedAPI());
```

This pattern becomes especially valuable in microservices architectures where different services might need different interviewer implementations but share the same interview process.