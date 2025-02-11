# Command Design Pattern

## Overview

The Command pattern is a behavioral design pattern that encapsulates a request as an object, allowing for:

- Parameterizing objects with different requests
- Queuing or logging requests
- Supporting undoable operations
- Implementing request handling at different times

## Intent

The intent of the Command pattern is to decouple the object that invokes the operation from the one that knows how to perform it.

## Motivation

Imagine developing a GUI application where you want to implement functionalities like "Open," "Save," and "Print." Without the Command pattern, you might directly bind each menu item or button to the corresponding action. This approach leads to tight coupling and makes it difficult to add new functionalities or modify existing ones.

The Command pattern solves this by encapsulating each action as a command object. The GUI components (invokers) trigger the commands, and the command objects delegate the actual execution to the appropriate receiver.

## Structure

The key components of the Command pattern are:

-   **Command:** Declares an interface for executing an operation.
-   **ConcreteCommand:** Defines a binding between a receiver object and an action.
-   **Client:** Creates concrete command objects.
-   **Invoker:** Asks the command to carry out the request.
-   **Receiver:** Knows how to perform the operations associated with carrying out a request.

## Implementation

### Example: Simple Remote Control

Let's illustrate with a simple remote control example:

```java
// Anti-pattern: Direct method calls
class RemoteControl {
    public void turnOnLight() {
        light.switchOn();
    }
    
    public void turnOffLight() {
        light.switchOff();
    }
    
    public void turnOnAC() {
        ac.powerOn();
    }
    // More methods for each device and action...
}
```

This approach leads to:
- Tight coupling between the remote control and devices
- Difficulty in adding new devices or commands
- No way to undo operations
- Impossible to queue or log operations

## Problem and Anti-Pattern Analysis

### 1. Tight Coupling Problem

**Anti-pattern:**
```java
class Button {
    private Light light;
    
    public void click() {
        light.turnOn(); // Direct dependency on Light
    }
}
```

**Issues:**
- Button class is tightly coupled to Light
- Cannot reuse the button for other devices
- Testing becomes difficult
- Violates Open/Closed Principle

### 2. Lack of Operation History

**Anti-pattern:**
```java
class Editor {
    private String content = "";
    
    public void type(String text) {
        content += text;
    }
    
    public void delete(int chars) {
        content = content.substring(0, content.length() - chars);
    }
    // No way to implement undo/redo
}
```

## Solution with Command Pattern

### 1. Decoupling Through Commands

```java
// Command interface
interface Command {
    void execute();
    void undo();
}

// Concrete command
class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    public void execute() {
        light.turnOn();
    }
    
    public void undo() {
        light.turnOff();
    }
}

// Button doesn't know about Light
class Button {
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void click() {
        command.execute();
    }
}
```

### 2. Implementing Undo/Redo

```java
class CommandHistory {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();
    
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }
    
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }
    
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }
}
```

### 3. Macro Commands

```java
class MacroCommand implements Command {
    private List<Command> commands;
    
    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }
    
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
    
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
```

## Implementation Details

### Core Components

1. **Command Interface**
   - Declares execute() method
   - Optionally declares undo() method

2. **Concrete Commands**
   - Implement Command interface
   - Hold reference to receiver
   - Delegate work to receiver

3. **Invoker**
   - Holds command(s)
   - Triggers command execution

4. **Receiver**
   - Contains business logic
   - Knows how to perform the operations

## SOLID Principles Support

1. **Single Responsibility Principle**
   - Each command class has one purpose
   - Separation of command execution from command creation

2. **Open/Closed Principle**
   - New commands can be added without modifying existing code
   - Invoker remains unchanged when adding commands

3. **Interface Segregation Principle**
   - Commands implement only methods they need
   - Different command interfaces for different needs

4. **Dependency Inversion Principle**
   - High-level modules depend on Command abstraction
   - Not on concrete commands

## Best Practices and Tips

### 1. Command Creation
- Use Factory pattern for command creation
- Consider Command pool for frequently used commands
- Implement command validation

### 2. Command Execution
- Handle exceptions within commands
- Log command execution when needed
- Consider command timeout mechanisms

### 3. Testing
- Test commands in isolation
- Mock receivers in unit tests
- Test command history management

## Interview Questions

### Basic Understanding
Q: What is the Command pattern and when should you use it?
A: The Command pattern encapsulates a request as an object, letting you:
- Parameterize clients with different requests
- Queue or log requests
- Support undoable operations
Perfect for GUI actions, multi-level undo/redo, and job queuing systems.

### Advanced Concepts
Q: How does Command pattern support undo operations?
A: By:
1. Including undo() in Command interface
2. Storing command history
3. Maintaining state for undoable operations
4. Using memento pattern for complex states

### Real-World Applications
Q: Provide an example of Command pattern in real systems
A: Examples include:
- Text editors (undo/redo)
- Transaction management in databases
- Job queuing systems
- GUI button actions
- Remote control systems

### Performance Considerations
Q: What are the memory implications of Command pattern?
A: Consider:
- Memory usage for command objects
- History stack size limits
- Command object pooling
- Cleanup of completed commands

## Practical Example

Here's a complete example of a text editor using Command pattern:

```java
interface Command {
    void execute();
    void undo();
}

class InsertTextCommand implements Command {
    private TextEditor editor;
    private int position;
    private String text;
    
    public InsertTextCommand(TextEditor editor, int position, String text) {
        this.editor = editor;
        this.position = position;
        this.text = text;
    }
    
    public void execute() {
        editor.insertText(position, text);
    }
    
    public void undo() {
        editor.deleteText(position, text.length());
    }
}

class TextEditor {
    private StringBuilder content = new StringBuilder();
    private CommandHistory history = new CommandHistory();
    
    public void executeCommand(Command command) {
        history.executeCommand(command);
    }
    
    public void undo() {
        history.undo();
    }
    
    // Other editor methods...
}
```

This implementation shows how Command pattern:
- Separates concerns
- Supports undo/redo
- Makes testing easier
- Allows for command logging and validation



# Command Design Pattern

## Overview

The Command pattern is a behavioral design pattern that encapsulates a request as an object. This approach enables you to parameterize objects with operations, delay execution, queue requests, and support undoable actions.

## Feature-wise Problems and Solutions

### 1. Request Encapsulation

#### Problem (Anti-Pattern)
```java
class RemoteControl {
    private Light light;
    private TV tv;
    private Stereo stereo;
    
    public void turnOnLight() {
        light.on();  // Direct coupling
    }
    
    public void turnOnTV() {
        tv.powerOn();  // Different method names
    }
    
    public void turnOnStereo() {
        stereo.enable();  // Inconsistent naming
    }
}
```

**Issues:**
- Direct coupling to multiple device types
- Inconsistent method names across devices
- Not extensible - new devices require code changes
- Hard to test and maintain

#### Solution with Command Pattern
```java
interface Command {
    void execute();
}

class TurnOnCommand implements Command {
    private Device device;
    
    public TurnOnCommand(Device device) {
        this.device = device;
    }
    
    public void execute() {
        device.turnOn();
    }
}

class RemoteControl {
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void pressButton() {
        command.execute();
    }
}
```

**Benefits:**
- Decoupled from specific devices
- Consistent interface
- Easy to add new commands
- Testable with mock commands

### 2. Undo Operations

#### Problem (Anti-Pattern)
```java
class TextEditor {
    private String content = "";
    
    public void type(String text) {
        content += text;  // No way to undo
    }
    
    public void delete(int chars) {
        content = content.substring(0, content.length() - chars);  // Cannot restore
    }
}
```

**Issues:**
- No history of operations
- Cannot undo changes
- No way to track state changes
- Complex to implement undo/redo

#### Solution with Command Pattern
```java
interface Command {
    void execute();
    void undo();
}

class TypeCommand implements Command {
    private TextEditor editor;
    private String text;
    private int position;
    
    public TypeCommand(TextEditor editor, String text, int position) {
        this.editor = editor;
        this.text = text;
        this.position = position;
    }
    
    public void execute() {
        editor.insertAt(position, text);
    }
    
    public void undo() {
        editor.deleteAt(position, text.length());
    }
}

class CommandHistory {
    private Stack<Command> undoStack = new Stack<>();
    
    public void execute(Command cmd) {
        cmd.execute();
        undoStack.push(cmd);
    }
    
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
        }
    }
}
```

### 3. Request Queuing and Scheduling

#### Problem (Anti-Pattern)
```java
class PrintSystem {
    public void printDocument(Document doc) {
        // Direct printing - no queue
        printer.print(doc);
    }
    
    public void processAllJobs() {
        // Cannot handle jobs in order
        // No way to prioritize
        // Cannot delay execution
    }
}
```

**Issues:**
- No job queuing
- Cannot prioritize jobs
- No delayed execution
- System overload possible

#### Solution with Command Pattern
```java
class PrintCommand implements Command {
    private Printer printer;
    private Document doc;
    private Priority priority;
    
    public void execute() {
        printer.print(doc);
    }
}

class PrintQueue {
    private PriorityQueue<PrintCommand> queue;
    
    public void addJob(PrintCommand cmd) {
        queue.add(cmd);
    }
    
    public void processJobs() {
        while (!queue.isEmpty()) {
            PrintCommand cmd = queue.poll();
            cmd.execute();
        }
    }
}
```

### 4. Composite Commands (Macros)

#### Problem (Anti-Pattern)
```java
class HomeAutomation {
    public void movieMode() {
        lights.dim();
        tv.turnOn();
        soundSystem.turnOn();
        soundSystem.setVolume(30);
        // Direct calls, cannot reuse combinations
    }
    
    public void gameMode() {
        // Duplicate logic
        lights.dim();
        tv.turnOn();
        soundSystem.turnOn();
        soundSystem.setVolume(50);
    }
}
```

**Issues:**
- Duplicate code for command sequences
- Cannot reuse command combinations
- Hard to maintain consistent sequences
- No atomic execution guarantee

#### Solution with Command Pattern
```java
class MacroCommand implements Command {
    private List<Command> commands;
    
    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }
    
    public void execute() {
        commands.forEach(Command::execute);
    }
    
    public void undo() {
        Collections.reverse(commands);
        commands.forEach(Command::undo);
        Collections.reverse(commands);
    }
}

// Usage
MacroCommand movieMode = new MacroCommand(Arrays.asList(
    new DimLightsCommand(lights),
    new TurnOnCommand(tv),
    new SetVolumeCommand(soundSystem, 30)
));
```

### 5. Logging and Debugging

#### Problem (Anti-Pattern)
```java
class TransactionSystem {
    public void transfer(Account from, Account to, double amount) {
        from.withdraw(amount);
        to.deposit(amount);
        // No operation logging
        // Cannot replay operations
        // No audit trail
    }
}
```

**Issues:**
- No operation history
- Cannot debug issues
- No audit trail
- Cannot replay operations

#### Solution with Command Pattern
```java
class TransferCommand implements Command {
    private final Account from;
    private final Account to;
    private final double amount;
    private final Logger logger;
    
    public void execute() {
        logger.log("Starting transfer of " + amount);
        from.withdraw(amount);
        to.deposit(amount);
        logger.log("Transfer completed");
    }
    
    public void undo() {
        logger.log("Reversing transfer of " + amount);
        to.withdraw(amount);
        from.deposit(amount);
        logger.log("Transfer reversed");
    }
}

// Command history maintains a log of all operations
class CommandHistory {
    private List<Command> history = new ArrayList<>();
    
    public void execute(Command cmd) {
        cmd.execute();
        history.add(cmd);
    }
    
    public List<Command> getHistory() {
        return new ArrayList<>(history);
    }
}
```

## Best Practices

1. **Command Validation**
   - Validate parameters in command constructor
   - Check preconditions before execution
   - Handle execution failures gracefully

2. **Command State**
   - Keep commands immutable when possible
   - Store only required state
   - Consider using Memento pattern for complex state

3. **Performance Considerations**
   - Use command pooling for frequent operations
   - Implement cleanup for completed commands
   - Set reasonable limits on history size

4. **Testing**
   - Unit test each command independently
   - Mock receivers in tests
   - Test undo/redo operations
   - Verify command history management

## Real-World Applications

1. **GUI Applications**
   - Menu items and buttons
   - Keyboard shortcuts
   - Multi-level undo/redo

2. **Database Systems**
   - Transaction management
   - Rollback operations
   - Query execution

3. **Game Development**
   - Player actions
   - Game state management
   - Replay systems

4. **Task Schedulers**
   - Job queuing
   - Batch processing
   - Delayed execution