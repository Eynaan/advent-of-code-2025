package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MainPuzzle2 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> inputs = InputLoader.loadInputs();
        List<Rotation> rotations = new java.util.ArrayList<>();
        int position = 50;
        int noOfZeros = 0;
        int noOfRotations = 0;
        
        for (String input : inputs) {
            Rotation rotation = new Rotation(input);
            rotations.add(rotation);
        }

        for (Rotation rotation : rotations) {
            if (rotation.getDirection() == 'R') {
                noOfZeros += countZerosCrossed(position, rotation.getSteps(), true);
                position = add(position, rotation.getSteps());
            } else if (rotation.getDirection() == 'L') {
                noOfZeros += countZerosCrossed(position, rotation.getSteps(), false);
                position = subtract(position, rotation.getSteps());
            }
            noOfRotations++;
        }

        System.out.println("Final Position: " + position);
        System.out.println("Number of times position was zero: " + noOfZeros);
        System.out.println("Total number of rotations processed: " + noOfRotations);
    }

    public static int add(int a, int b) {
        return (a + b) % 100;
    }

    public static int subtract(int a, int b) {
        return (a - b + 100) % 100;
    }

    public static int countZerosCrossed(int start, int steps, boolean clockwise) {
        int count = 0;
        int current = start;
        
        for (int i = 0; i < steps; i++) {
            if (clockwise) {
                current = (current + 1) % 100;
            } else {
                current = (current - 1 + 100) % 100;
            }
            if (current == 0) {
                count++;
            }
        }
        
        return count;
    }
}