import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RoboticsSim {
    long steps;
    Robot[] robots;
    MapLayout layout;

    RoboticsSim(String[] args) {
        try {
            layout = new MapLayout(args[0]);
            if(args.length >= 2) {
                steps = Long.parseLong(args[1]);
            } else {
                steps = -1;
            }
        } catch(NumberFormatException e) {
            System.out.println("Please input the step count as a number.");
            System.exit(1);
        }
    }

    public String run() {
        System.out.println("Running robotics simulation\n");
        while(layout.step()) {
            steps--;
            if(steps == 0) break;
        }
        System.out.println("Finished simulation\n");
        return(layout.getSolution());
    }
}
