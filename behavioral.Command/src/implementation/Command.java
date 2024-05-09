package implementation;

public interface Command {
    void execute();

    void undo();

    void redo();
}
