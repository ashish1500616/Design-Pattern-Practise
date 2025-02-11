# Design Patterns

This repository contains a collection of design patterns implemented in various programming languages. Design patterns
are proven solutions to recurring design problems that can be applied to software development.

## Categories

The design patterns in this repository are categorized into the following groups:

### Creational Patterns

- **Abstract Factory**: Provides an interface for creating families of related or dependent objects.
- **Builder**: Constructs complex objects step by step, allowing the same construction process to create various representations.
- **Factory Method**: Defines an interface for creating objects but allows subclasses to decide which class to instantiate.
- **Prototype**: Creates new objects by cloning existing ones.
- **Singleton**: Ensures that a class has only one instance and provides global access to it.

### Structural Patterns

- **Adapter**: Allows objects with incompatible interfaces to work together.
- **Bridge**: Decouples an abstraction from its implementation, allowing both to vary independently.
- **Composite**: Composes objects into tree structures to represent part-whole hierarchies.
- **Decorator**: Dynamically adds responsibilities to objects without subclassing.
- **Facade**: Provides a simplified interface to a complex subsystem of classes.
- **Flyweight**: Shares common state between multiple objects to reduce memory usage.
- **Proxy**: Provides a placeholder for another object to control access, add functionality, or defer expensive operations.

### Behavioral Patterns

- **Chain of Responsibility**: Passes requests along a chain of handlers until processed (e.g., Middleware pipelines).
- **Command**: Encapsulates requests as objects for parameterization and queuing.
- **Interpreter**: Implements a grammar or language interpreter.
- **Iterator**: Provides sequential access to collection elements.
- **Mediator**: Centralizes object interactions through a hub.
- **Memento**: Captures and restores object states.
- **Observer**: Implements publish/subscribe mechanism for object state changes.
- **State**: Alters object behavior based on internal state.
- **Strategy**: Enables dynamic algorithm selection at runtime.
- **Template Method**: Defines algorithm skeleton with customizable steps.
- **Visitor**: Separates algorithms from object structures.

## Essential Design Patterns for Interviews

These are the most commonly asked and practically useful design patterns you should focus on:

### Must-Know Creational Patterns
- **Singleton**
  - Use for managing global state/resources
  - Common in logging, caching, connection pools
- **Factory Method** 
  - Enables flexible object creation
  - Great for dependency injection scenarios
- **Builder**
  - Handles complex object construction
  - Useful for objects with many optional parameters

### Key Structural Patterns
- **Adapter**
  - Makes incompatible interfaces work together
  - Essential for integrating legacy code/external APIs
- **Decorator**
  - Adds behavior dynamically at runtime
  - Perfect for feature toggles and extensions
- **Composite**
  - Represents tree-like object hierarchies
  - Used in UI components and file systems

### Critical Behavioral Patterns
- **Observer**
  - Implements publish/subscribe relationships
  - Fundamental for event-driven systems
- **Strategy**
  - Allows switching algorithms at runtime
  - Great for configurable business rules
- **Command**
  - Encapsulates operations as objects
  - Useful for undo/redo and request queuing

## Usage

Each design pattern folder contains implementation examples in various programming languages. Feel free to explore the
folders and choose the language of your preference.


## Factory Pattern Variations

### Simple Factory
- Simplest form using a single class with static methods
- Best for simple object creation with few variations
```java
Door door = DoorFactory.makeDoor(100, 200);
```

### Factory Method
- Uses inheritance for flexible object creation
- Subclasses determine concrete implementation
```java
HiringManager developmentManager = new DevelopmentManager();
developmentManager.takeInterview(); // Creates Developer interviewer
```

### Abstract Factory
- Creates families of related objects
- Ensures object compatibility within a family
```java
DoorFactory woodenFactory = new WoodenDoorFactory();
Door door = woodenFactory.makeDoor();
DoorFittingExpert expert = woodenFactory.makeFittingExpert();
```