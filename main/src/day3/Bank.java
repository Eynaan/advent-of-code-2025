package day3;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    String batteryJoltage;
    List<Battery> batteries;
    
    public Bank(String batteryJoltage) {
        this.batteryJoltage = batteryJoltage;
        this.batteries = new ArrayList<>();
        parseBatteries();
    }
    
    private void parseBatteries() {
        for (int i = 0; i < batteryJoltage.length(); i++) {
            int joltage = Character.getNumericValue(batteryJoltage.charAt(i));
            batteries.add(new Battery(joltage));
        }
    }
    
    public int getMaxJoltageTwoBatteries() {
        int maxJoltage = 0;
        
        for (int i = 0; i < batteries.size() - 1; i++) {
            for (int j = i + 1; j < batteries.size(); j++) {
                int joltage = batteries.get(i).getJoltage() * 10 + batteries.get(j).getJoltage();
                maxJoltage = Math.max(maxJoltage, joltage);
            }
        }
        
        return maxJoltage;
    }
    
    public String getBatteryJoltage() {
        return batteryJoltage;
    }
    
    public long getMaxJoltageWithNBatteries(int n) {
        if (n >= batteries.size()) {
            return Long.parseLong(batteryJoltage);
        }
        
        int batteriesNeeded = n;
        int batteriesRemaining = batteries.size();
        StringBuilder result = new StringBuilder();
        
        int currentIndex = 0;
        while (batteriesNeeded > 0) {
            // Find the maximum digit in the range where we can still pick enough batteries
            int maxDigit = -1;
            int maxIndex = -1;
            
            // We can look ahead up to (batteriesRemaining - batteriesNeeded) positions
            int searchEnd = currentIndex + (batteriesRemaining - batteriesNeeded) + 1;
            
            for (int i = currentIndex; i < searchEnd && i < batteries.size(); i++) {
                int digit = batteries.get(i).getJoltage();
                if (digit > maxDigit) {
                    maxDigit = digit;
                    maxIndex = i;
                }
            }
            
            // Add the maximum digit found
            result.append(maxDigit);
            
            // Move past the selected battery
            currentIndex = maxIndex + 1;
            batteriesNeeded--;
            batteriesRemaining = batteries.size() - currentIndex;
        }
        
        return Long.parseLong(result.toString());
    }
}
