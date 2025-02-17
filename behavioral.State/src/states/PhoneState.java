package states;

/**
 * The PhoneState interface defines all possible actions that can be performed on a phone.
 * Each concrete state will implement this interface and define how the phone behaves
 * in that particular state.
 *
 * This is a key component of the State Pattern where:
 * 1. Each method represents a possible action
 * 2. The return type is the next state after the action
 * 3. States are responsible for state transitions
 */
public interface PhoneState {
    /**
     * Handles the action of picking up the phone.
     * @return The next state after picking up the phone
     * @throws IllegalStateException if picking up is not allowed in current state
     */
    PhoneState pickUp();

    /**
     * Handles the action of hanging up the phone.
     * @return The next state after hanging up the phone
     * @throws IllegalStateException if hanging up is not allowed in current state
     */
    PhoneState hangUp();

    /**
     * Handles the action of dialing a number.
     * @return The next state after dialing
     * @throws IllegalStateException if dialing is not allowed in current state
     */
    PhoneState dial();

    /**
     * Gets a description of the current state.
     * This is useful for debugging and logging purposes.
     * @return String representation of the current state
     */
    default String getStateName() {
        return this.getClass().getSimpleName();
    }
}
