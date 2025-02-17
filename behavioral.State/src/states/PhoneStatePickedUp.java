package states;

/**
 * Represents the picked up state of a phone where:
 * - Cannot pick up again (already picked up)
 * - Can dial to start a call
 * - Can hang up to return to idle
 * 
 * This state serves as a transitional state between idle and calling,
 * enforcing the natural flow of phone operations.
 */
public class PhoneStatePickedUp implements PhoneState {
    // Optional: Store state-specific data
    private final long pickedUpTimestamp;
    
    public PhoneStatePickedUp() {
        this.pickedUpTimestamp = System.currentTimeMillis();
    }

    @Override
    public PhoneState pickUp() {
        throw new IllegalStateException("❌ Phone is already picked up");
    }

    @Override
    public PhoneState hangUp() {
        System.out.println("📞 Hanging up phone without dialing");
        System.out.println("ℹ️ Was picked up for: " + 
            (System.currentTimeMillis() - pickedUpTimestamp) + "ms");
        return PhoneStateIdle.getInstance();
    }

    @Override
    public PhoneState dial() {
        System.out.println("📞 Dialing number...");
        return new PhoneStateCalling(pickedUpTimestamp);
    }
}
