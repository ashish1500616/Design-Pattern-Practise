package builder;

public class AndroidBuilder extends Builder {
    @Override
    public void test() {
        System.out.println("Running Android Test.");
    }

    @Override
    public void lint() {
        System.out.println("Running Android Lint.");
    }

    @Override
    public void assemble() {
        System.out.println("Running Android Assemble.");
    }

    @Override
    public void deploy() {
        System.out.println("Running Android Deploy.");
    }
}
