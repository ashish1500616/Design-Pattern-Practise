# Design Patterns

This repository contains a collection of design patterns implemented in various programming languages. Design patterns
are proven solutions to recurring design problems that can be applied to software development.

## Categories

The design patterns in this repository are categorized into the following groups:

### Creational Patterns

- **Abstract Factory**: Provides an interface for creating families of related or dependent objects.
- **Builder**: Constructs complex objects step by step, allowing the same construction process to create various
  representations.
- **Factory Method**: Defines an interface for creating objects but allows subclasses to decide which class to
  instantiate.
- **Prototype**: Creates new objects by cloning existing ones.
- **Singleton**: Ensures that a class has only one instance and provides global access to it.

### Structural Patterns

- **Adapter**: Allows objects with incompatible interfaces to work together.
- **Bridge**: Decouples an abstraction from its implementation, allowing both to vary independently.
- **Composite**: Composes objects into tree structures to represent part-whole hierarchies.
- **Decorator**: Dynamically adds responsibilities to objects without subclassing.
- **Facade**: Provides a simplified interface to a complex subsystem of classes.
- **Flyweight**: Shares common state between multiple objects to reduce memory usage.
- **Proxy**: Provides a placeholder for another object to control access, add functionality, or defer expensive
  operations.

### Behavioral Patterns

- **Chain of Responsibility**: Handles requests hierarchically, with each object either processing the request or
  passing it to the next handler. Eg. Middlewares. Authentication -> Authorization -> Resource.
- **Command**: Encapsulates a request as an object, allowing parameterization, queuing, or logging of requests.
- **Interpreter**: Defines a representation of a grammar or language and interprets sentences in the language.
- **Iterator**: Provides a way to access elements of an aggregate object sequentially without exposing its underlying
  representation.
- **Mediator**: Defines how objects interact, acting as a central hub for communication between objects.
- **Memento**: Captures and restores an object's internal state without violating encapsulation.
- **Observer**: Notifies observers when the state of a subject object changes.
- **State**: Allows an object to alter its behavior when its internal state changes.
- **Strategy**: Enables selecting an algorithm dynamically at runtime.
- **Template Method**: Defines the skeleton of an algorithm, allowing subclasses to provide specific implementations for
  certain steps.
- **Visitor**: Separates an algorithm from an object structure on which it operates, allowing new operations to be added
  without modifying the objects.

## Usage

Each design pattern folder contains implementation examples in various programming languages. Feel free to explore the
folders and choose the language of your preference.

