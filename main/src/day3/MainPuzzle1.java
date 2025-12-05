package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPuzzle1 {
    
    public static void main(String[] args) {
        try {
            List<Bank> banks = readBanks("src/main/resources/Input.txt");
            int totalJoltage = 0;
            
            for (Bank bank : banks) {
                int maxJoltage = bank.getMaxJoltageTwoBatteries();
                System.out.println("Bank: " + bank.getBatteryJoltage() + " -> Max: " + maxJoltage);
                totalJoltage += maxJoltage;
            }
            
            System.out.println("\nTotal Output Joltage: " + totalJoltage);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private static List<Bank> readBanks(String filename) throws IOException {
        List<Bank> banks = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    banks.add(new Bank(line.trim()));
                }
            }
        }
        
        return banks;
    }
}