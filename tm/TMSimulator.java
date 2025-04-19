package tm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.System.exit;

public class TMSimulator {

    public static void main(String[] args) {
        if (args.length != 2) {
            usageMsg();
        }
        String filepath = args[0];
        File file = new File(filepath);
        if (!file.exists()) {
            System.out.println("File does not exist: " + filepath);
            usageMsg();
        }
        String inputString = args[1];
        if (inputString.isEmpty()) {
            System.out.println("Input string is empty");
            usageMsg();
        }

        // Go to a new method to read the TM file
        TM turingMachine = readTMFile(file, inputString);
        turingMachine.run();
        System.out.println(turingMachine);
    }

    private static void usageMsg() {
        System.out.println("Usage: java TMSimulator <tm-file> <input-string>");
        exit(1);
    }

    private static TM readTMFile(File file, String inputString) {
        TM turingMachine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Step 1: Read the number of states, 0 to n-1
            String line = reader.readLine();
            int numStates = Integer.parseInt(line);

            // Step 2: Read the number of symbols, 0 to m
            line = reader.readLine();
            int numSymbols = Integer.parseInt(line);

            turingMachine = new TM(numStates, numSymbols, inputString);

            // Step 3: Read the transition table, which will have n*m rows and 3 columns
            // Column 1: next state
            // Column 2: symbol to write
            // Column 3: direction to move (L or R)
            // The transitions will be in order of the alphabet, the first one for each state will be 0, then 1, 2, etc.
            for (int i = 0; i < numStates-1; i++) {
                for (int j = 0; j <= numSymbols; j++) {
                    line = reader.readLine();
                    String[] parts = line.split(",");
                    int nextState = Integer.parseInt(parts[0]);
                    int writeSymbol = Integer.parseInt(parts[1]);
                    int direction = parts[2].equals("L") ? -1 : 1;
                    turingMachine.addTransition(i, j, nextState, writeSymbol, direction);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            exit(1);
        }
        return turingMachine;
    }
}
