package states;

/**
 * Represents the idle state of a phone where:
 * - The phone can be picked up
 * - Cannot dial (must pick up first)
 * - Cannot hang up (already idle)
 * 
 * This is the initial state of the phone and the state it returns to
 * after hanging up.
 */
public class PhoneStateIdle implements PhoneState {
    // For better performance in high-throughput scenarios, we can use the Flyweight pattern
    private static final PhoneStateIdle INSTANCE = new PhoneStateIdle();
    
    public static PhoneStateIdle getInstance() {
        return INSTANCE;
    }

    @Override
    public PhoneState pickUp() {
        System.out.println("📞 Picking up phone from idle state");
        return new PhoneStatePickedUp();
    }

    @Override
    public PhoneState hangUp() {
        throw new IllegalStateException("❌ Cannot hang up - phone is already idle");
    }

    @Override
    public PhoneState dial() {
        throw new IllegalStateException("❌ Cannot dial - must pick up phone first");
    }
}
