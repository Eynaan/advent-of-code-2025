package day1;
public class Rotation {
    String input;
    char direction;
    int steps;

    public Rotation(String input) {
        this.input = input;
        this.direction = input.charAt(0);
        this.steps = Integer.parseInt(input.substring(1));
    }

    public String getInput() {
        return input;
    }

    public char getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }
}
