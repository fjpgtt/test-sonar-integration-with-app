package pixee;

public class CodeWithoutErrors {

private static final String HELLO_WORLD = "Hello world";

    private int variable = 10;

    public int getVariable() {
        System.out.println(HELLO_WORLD);
        return variable;
    }

    public void setVariable(int variable) {
        System.out.println(HELLO_WORLD);
        this.variable = variable;
    }

    public int sum(int a, int b) {
        System.out.println(HELLO_WORLD);
        return a + b;
    }

    private int one(){
        System.out.println(HELLO_WORLD);
        return 1;
    }
}
