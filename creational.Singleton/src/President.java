public final class President {
    private static President instance;

    // private constructor to prevent instantiation outside of class
    private President() {
    }

    public static President getInstance() {
        if (instance == null) {
            instance = new President();
        }
        return instance;
    }

    // Prevent cloning of the instance -VA
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    // Prevent deserialization of the instance
    private Object readResolve() {
        return instance;
    }
}
