package pixee;

public class CodeWithErrors {

private static final String HELLO_WORLD = "Hello world";

    // Error: Unused variable
    private int unusedVariable;

    // Error: Variable should not be public
    public int variable = 10;

    // Method getVariable should have a return type int
    // Error: It should be public int getVariable()
    public void getVariable() {
        System.out.println(HELLO_WORLD);
        return variable;
    }

    // Method setVariable should not have a return type
    // Error: It should be public void setVariable(int variable)
    public int setVariable(int variable) {
        System.out.println(HELLO_WORLD);
        this.variable = variable;
    }

    // Method sum should not have a return type
    // Error: It should be public int sum(int a, int b)
    public void sum(int a, int b) {
        System.out.println(HELLO_WORLD);
        return a + b;
    }

    // Method one should not be private
    // Error: It should be public int one()
    private int one(){
        System.out.println(HELLO_WORLD);
        return 1;
    }

    // Error: Empty catch block
    public void methodWithEmptyCatchBlock() {
        try {
            // Code that might throw an exception
        } catch (Exception e) {
            // Empty catch block
        }
    }

    // Error: Null pointer dereference
    public void methodWithNullPointerError() {
        String s = null;
        int length = s.length(); // Attempting to access length of null reference
    }

    // Error: Division by zero
    public void methodWithDivisionByZero() {
        int x = 10;
        int y = 0;
        int result = x / y; // Division by zero
    }

    // Error: Unused private method
    private void unusedMethod() {
        // This method is not used anywhere in the code
    }
}
