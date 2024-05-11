package builder;

public class IosBuilder extends Builder {
    @Override
    public void test() {
        System.out.println("Running IOS test.");
    }

    @Override
    public void lint() {
        System.out.println("Running IOS lint.");
    }

    @Override
    public void assemble() {
        System.out.println("Running IOS assemble.");
    }

    @Override
    public void deploy() {
        System.out.println("Running IOS deploy.");
    }
}
