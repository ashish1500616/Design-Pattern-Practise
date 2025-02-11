# Composite Design Pattern Implementation

## Overview
The Composite pattern allows you to compose objects into tree structures to represent part-whole hierarchies. In our example, we'll demonstrate this using a company's organizational structure, where departments can contain both employees (leaf nodes) and other departments (composite nodes).

### Why Composite Pattern?
- Enables treating individual employees and entire departments through the same interface
- Perfect for representing organizational hierarchies where:
  - Employees are individual workers (leaf nodes)
  - Departments can contain employees and other departments (composite nodes)
  - Cost calculations and structure display work uniformly for both

## Design Pattern Framework

### When to Use Composite Pattern
1. When you need to represent part-whole hierarchies
2. When clients should treat all objects in the composed structure uniformly
3. When the structure can be recursively composed

```
Decision Flow:
┌─────────────────┐
│ Do you need to  │
│ represent a     │──No──┐
│ tree structure? │      │
└────────┬────────┘      │
         │Yes            │
         ▼              │
┌─────────────────┐     │
│ Do clients need │     │
│ uniform access? │──No─┤
└────────┬────────┘     │
         │Yes           │
         ▼              │
┌─────────────────┐     │
│ Use Composite   │     │
│ Pattern         │     │
└─────────────────┘     │
                        ▼
                 ┌──────────────┐
                 │Consider other│
                 │patterns     │
                 └──────────────┘
```

## Implementation Details

### Component Hierarchy
```
Tech Corp (Department)
├── Engineering (Department)
│   ├── John Dev (Employee)
│   └── Alice Code (Employee)
└── Design (Department)
    └── Bob Art (Employee)
```

### Core Components
1. **Component**: Base interface/abstract class
2. **Leaf**: Individual objects with no children
3. **Composite**: Container objects that can hold other components

### Example Implementation
```java
// Component interface - represents both employees and departments
interface OrganizationComponent {
    void showDetails(String indent);
    double getAnnualCost();
}

// Leaf node - represents individual employees
class Employee implements OrganizationComponent {
    private String name;
    private String position;
    private double salary;
    
    // Implementation details...
}

// Composite node - represents departments
class Department implements OrganizationComponent {
    private String name;
    private List<OrganizationComponent> employees;
    
    // Implementation details...
}
```

## SOLID Principles Applied

1. **Single Responsibility Principle**
   - Each component has a single, well-defined role
   - Leaf nodes handle individual elements
   - Composite nodes manage child components

2. **Open/Closed Principle**
   - New components can be added without modifying existing code
   - Structure can be extended without changing client code

3. **Interface Segregation**
   - Component interface defines only essential operations
   - No forced implementation of unnecessary methods

## Best Practices & Tips

### Do's
- Keep the OrganizationComponent interface simple
- Use meaningful names for components
- Implement proper error handling
- Consider caching expensive calculations

### Don'ts
- Don't add methods that only make sense for one type
- Avoid deep nesting that impacts performance
- Don't break the component interface contract
- Don't mix different types of hierarchies

### Testing Strategies
1. Test individual components in isolation
2. Verify composite operations work recursively
3. Test edge cases (empty composites, deep nesting)
4. Validate proper cleanup of resources

## Interview Questions

### Basic Concepts
1. **Q**: What is the main purpose of the Composite pattern?
   **A**: To compose objects into tree structures and treat individual objects and compositions uniformly.

2. **Q**: When would you choose Composite over other patterns?
   **A**: When you need to represent hierarchical structures and want clients to treat individual and composite objects the same way.

### Advanced Topics
1. **Q**: How does the Composite pattern support the Open/Closed Principle?
   **A**: New components can be added without changing existing code, as long as they implement the component interface.

2. **Q**: What are the potential performance implications of the Composite pattern?
   **A**: Deep nesting can impact performance due to recursive operations. Memory usage increases with tree depth.

### Real-World Applications
1. **Q**: Describe a real-world scenario where you've used or encountered the Composite pattern.
   **A**: GUI frameworks use Composite pattern for nested components (windows containing panels containing buttons).

2. **Q**: How would you implement caching in a Composite structure?
   **A**: Cache composite operations results at each level, invalidate cache when structure changes.

## Conclusion
The Composite pattern is a powerful tool for handling hierarchical structures. Its strength lies in providing a uniform interface while maintaining flexibility and extensibility. Understanding when and how to apply it effectively is crucial for clean, maintainable code.

# Composite Design Pattern: Problems & Solutions

## Anti-Patterns vs. Pattern Solutions

### 1. Cost Calculation

#### Problem (Without Composite)
```java
double calculateCost(Organization org) {
    double total = 0;
    // Calculate employee costs
    for (Employee emp : org.getEmployees()) {
        total += emp.getSalary();
    }
    // Calculate department costs separately
    for (Department dept : org.getDepartments()) {
        total += calculateDepartmentCost(dept);
    }
    return total;
}
```

#### Solution (With Composite)
```java
// Works for both Employee and Department
double cost = component.getAnnualCost();
```

### 2. Structure Display

#### Problem (Without Composite)
```java
void displayOrganization(Organization org) {
    System.out.println("Employees:");
    for (Employee emp : org.getEmployees()) {
        displayEmployee(emp);
    }
    System.out.println("Departments:");
    for (Department dept : org.getDepartments()) {
        displayDepartment(dept);
    }
}
```

#### Solution (With Composite)
```java
// Works uniformly for any organizational component
component.showDetails("");
```

## Usage Example
```java
public static void main(String[] args) {
    // Create individual employees
    Employee dev1 = new Employee("John Dev", "Developer", 75000);
    Employee dev2 = new Employee("Alice Code", "Developer", 70000);
    
    // Create departments
    Department engineering = new Department("Engineering");
    engineering.addEmployee(dev1);
    engineering.addEmployee(dev2);
    
    // Get total cost and show structure
    System.out.println("Total Cost: $" + engineering.getAnnualCost());
    engineering.showDetails("");
}
```

## Benefits of the Pattern Solution

1. **Unified Interface**
   - Before: Different methods for different types
   - After: Single interface for all components

2. **Simplified Client Code**
   - Before: Type checking and casting
   - After: Work with abstract component type

3. **Easy Extension**
   - Before: Modify existing code for new types
   - After: Add new components implementing interface

4. **Recursive Operations**
   - Before: Complex external recursive logic
   - After: Natural recursion through composition

## Best Practices Based on Anti-Patterns

1. **Avoid Type Checking**
   ```java
   // Don't do this:
   if (component instanceof Department) {
       // special handling
   }
   
   // Do this:
   component.operation(); // polymorphic call
   ```

2. **Prevent Interface Bloat**
   ```java
   // Don't do this:
   interface FileSystemComponent {
       void display();
       long getSize();
       void compress(); // Not all components need this
       void encrypt();  // Not all components need this
   }
   
   // Do this:
   interface FileSystemComponent {
       void display();
       long getSize();
   }
   ```

3. **Maintain Consistency**
   ```java
   // Don't do this:
   class Directory {
       public int getSize() { ... }  // Returns int
   }
   class File {
       public long getSize() { ... } // Returns long
   }
   
   // Do this:
   interface FileSystemComponent {
       long getSize();  // Consistent return type
   }
   ```

## Common Anti-Pattern Scenarios to Avoid

1. **Breaking Uniform Access**
   ```java
   // Anti-pattern
   if (isDirectory) {
       ((Directory)component).listContents();
   } else {
       component.display();
   }
   
   // Pattern solution
   component.display(); // Works for all types
   ```

2. **Mixing Responsibilities**
   ```java
   // Anti-pattern
   class Directory {
       void display();
       void compress();
       void backup();
       void sync();
   }
   
   // Pattern solution
   class Directory implements FileSystemComponent {
       void display(); // Core functionality only
   }
   ```

These examples demonstrate how the Composite pattern solves common design problems and helps avoid anti-patterns in hierarchical structure management.
