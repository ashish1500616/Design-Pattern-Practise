import states.PhoneState;
import states.PhoneStateIdle;

/**
 * The Phone class acts as the context in the State pattern.
 * It delegates all state-specific behavior to the current state object.
 * 
 * Key aspects of this implementation:
 * 1. Simple interface - clients don't need to know about states
 * 2. Delegation - all behavior is delegated to state objects
 * 3. State transitions - handled internally by state objects
 * 4. Immutable from outside - states can only be changed through defined actions
 */
public class Phone {
    private PhoneState state;
    private String lastNumber = null;
    
    /**
     * Creates a new Phone in the idle state.
     */
    public Phone() {
        // Always start in idle state
        this.state = PhoneStateIdle.getInstance();
        System.out.println("📱 Phone initialized in " + state.getStateName() + " state");
    }

    /**
     * Picks up the phone.
     * @throws IllegalStateException if the action is not allowed in current state
     */
    public void pickUp() {
        System.out.println("\n🔄 Action: Pick up phone");
        try {
            PhoneState newState = this.state.pickUp();
            this.state = newState;
            System.out.println("✅ New state: " + state.getStateName());
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Hangs up the phone.
     * @throws IllegalStateException if the action is not allowed in current state
     */
    public void hangUp() {
        System.out.println("\n🔄 Action: Hang up phone");
        try {
            PhoneState newState = this.state.hangUp();
            this.state = newState;
            System.out.println("✅ New state: " + state.getStateName());
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Dials a number on the phone.
     * @throws IllegalStateException if the action is not allowed in current state
     */
    public void dial(String number) {
        System.out.println("\n🔄 Action: Dial number " + number);
        try {
            this.lastNumber = number;
            PhoneState newState = this.state.dial();
            this.state = newState;
            System.out.println("✅ New state: " + state.getStateName());
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Gets the current state name for display purposes.
     */
    public String getCurrentState() {
        return state.getStateName();
    }

    /**
     * Gets the last dialed number.
     */
    public String getLastNumber() {
        return lastNumber;
    }
}
