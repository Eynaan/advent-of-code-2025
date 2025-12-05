package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MainPuzzle1 {
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
                position = add(position, rotation.getSteps());
                if (position == 0) {
                    noOfZeros++;
                }
            } else if (rotation.getDirection() == 'L') {
                position = subtract(position, rotation.getSteps());
                if (position == 0) {
                    noOfZeros++;
                }
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
}