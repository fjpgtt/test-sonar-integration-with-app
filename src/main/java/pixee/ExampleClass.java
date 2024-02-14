package pixee;
public class ExampleClass {

    // Error: Unused variable
    private int unusedVariable;

    // Error: Empty catch block
    public void exampleMethod() {
        try {
            // Code that might throw an exception
        } catch (Exception e) {
            // Empty catch block
        }
    }

    // Error: Division by zero
//    public void divisionByZero() {
//        int x = 10;
//        int y = 0;
//        int result = x / y; // Division by zero
//    }

    // Error: Unused private method
    private void unusedMethod() {
        // This method is not used anywhere in the code
    }
}
