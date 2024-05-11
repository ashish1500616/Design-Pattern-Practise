public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        // Type some stuff
        editor.type("This is the first sentence.");
        editor.type("This is the second.");
        // Save the state to restore to: This is the first sentence. This is the second.
        EditorMemento saved = editor.save();
        // Type some more
        editor.type("And this is the third.");
        // Output: Content before Saving
        System.out.println(editor.getContent());
        // Restoring to last saved state
        editor.restore(saved);
        System.out.println(editor.getContent());
    }
}