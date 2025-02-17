/**
 * Demonstrates the State Pattern in action with various scenarios.
 * This class shows:
 * 1. Normal flow of phone operations
 * 2. Invalid state transitions and error handling
 * 3. Multiple sequential operations
 * 4. State-specific behaviors and constraints
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 State Pattern Demo - Phone System\n");
        demoNormalPhoneUsage();
        demoInvalidOperations();
        demoComplexScenario();
    }

    /**
     * Demonstrates the normal flow of phone operations:
     * Idle -> Pick Up -> Dial -> Talk -> Hang Up
     */
    private static void demoNormalPhoneUsage() {
        System.out.println("📱 Demo 1: Normal Phone Usage");
        System.out.println("-----------------------------");
        
        Phone phone = new Phone();
        phone.pickUp();
        phone.dial("123-456-7890");
        
        // Simulate a call duration
        try {
            System.out.println("💬 Call in progress...");
            Thread.sleep(2000); // Simulate 2-second call
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        phone.hangUp();
        System.out.println("-----------------------------\n");
    }

    /**
     * Demonstrates invalid operations and error handling:
     * - Trying to dial before picking up
     * - Trying to pick up when already picked up
     * - Trying to hang up when idle
     */
    private static void demoInvalidOperations() {
        System.out.println("📱 Demo 2: Invalid Operations");
        System.out.println("-----------------------------");
        
        Phone phone = new Phone();
        
        // Try to dial without picking up
        phone.dial("123-456-7890");
        
        // Pick up the phone
        phone.pickUp();
        
        // Try to pick up again
        phone.pickUp();
        
        // Hang up
        phone.hangUp();
        
        // Try to hang up when already idle
        phone.hangUp();
        System.out.println("-----------------------------\n");
    }

    /**
     * Demonstrates a complex scenario with multiple operations:
     * - Multiple calls
     * - State transitions
     * - Error recovery
     */
    private static void demoComplexScenario() {
        System.out.println("📱 Demo 3: Complex Scenario");
        System.out.println("-----------------------------");
        
        Phone phone = new Phone();
        
        // First call
        phone.pickUp();
        phone.dial("111-222-3333");
        phone.hangUp();
        
        // Second call - with error
        phone.dial("444-555-6666"); // Should fail - phone is idle
        phone.pickUp();
        phone.dial("444-555-6666"); // Should succeed
        
        // Simulate some call duration
        try {
            System.out.println("💬 Complex call in progress...");
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        phone.hangUp();
        System.out.println("-----------------------------\n");
    }
}