package states;

/**
 * Represents the calling state of a phone where:
 * - Cannot pick up (already in use)
 * - Cannot dial (already on a call)
 * - Can hang up to end the call
 * 
 * This state tracks call duration and demonstrates how states can
 * maintain their own state-specific data.
 */
public class PhoneStateCalling implements PhoneState {
    private final long callStartTime;
    private int callDurationSeconds = 0;
    
    public PhoneStateCalling(long pickUpTime) {
        this.callStartTime = System.currentTimeMillis();
        this.callDurationSeconds = (int) ((callStartTime - pickUpTime) / 1000);
    }

    @Override
    public PhoneState pickUp() {
        throw new IllegalStateException("❌ Cannot pick up - already on a call");
    }

    @Override
    public PhoneState hangUp() {
        long callEndTime = System.currentTimeMillis();
        int totalCallDuration = (int) ((callEndTime - callStartTime) / 1000);
        System.out.println("📞 Ending call");
        System.out.println("📊 Call Statistics:");
        System.out.println("   • Time to connect: " + callDurationSeconds + " seconds");
        System.out.println("   • Call duration: " + totalCallDuration + " seconds");
        System.out.println("   • Total time: " + (callDurationSeconds + totalCallDuration) + " seconds");
        
        return PhoneStateIdle.getInstance();
    }

    @Override
    public PhoneState dial() {
        throw new IllegalStateException("❌ Cannot dial - already on a call");
    }
}
