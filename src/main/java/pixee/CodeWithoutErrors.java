package pixee;

public class CodeWithoutErrors {

    private int variable = 10;

    public int getVariable() {
        System.out.println("Hello world");
        return variable;
    }

    public void setVariable(int variable) {
        System.out.println("Hello world");
        this.variable = variable;
    }

    public int sum(int a, int b) {
        System.out.println("Hello world");
        return a + b;
    }

    private int one(){
        System.out.println("Hello world");
        return 1;
    }
}
