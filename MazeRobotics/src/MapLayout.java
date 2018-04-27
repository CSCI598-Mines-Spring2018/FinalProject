import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLayout {
    private Scanner scanner;
    private String file;
    private ArrayList<Room> rooms;
    private ArrayList<Robot> robotLocations;
    private long hallwayWidth, robotScan;
    private Room[] goals;

    MapLayout(String fileName) {
        this(fileName, 10);
    }

    MapLayout(String fileName, long width) {
        this(fileName, width, 10);
    }

    MapLayout(String fileName, long width, long scanAmount) {
        {
            file = fileName;
            robotLocations = new ArrayList<>();
            hallwayWidth = width;
            robotScan = scanAmount;
            goals = new Room[2];
            rooms = new ArrayList<>();

            try {
                scanner = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }

            ArrayList<String> names = new ArrayList<>();
            ArrayList<Long> squareFootage = new ArrayList<>();
            ArrayList<String> hallways = new ArrayList<>();
            String line;

            // Get all the rooms
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.equals("") || line.equals(" ") || line.equals(null)) {
                    break;
                } else {
                    String[] room = line.split("\\s+");
                    names.add(room[0]);
                    squareFootage.add(Long.parseLong(room[1]));
                }
            }
            for(int i = 0; i < names.size(); i++) {
                rooms.add(new Room(names.get(i), squareFootage.get(i)));
            }

            // Get all the hallways
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.equals("") || line.equals(" ") || line.equals(null)) {
                    break;
                } else {
                    hallways.add(line);
                }
            }
            for(String hallway : hallways) {
                String[] splitHallway = hallway.split("\\s+");
                for (int i = 0; i < splitHallway.length-1; i = i+2) {
                    if(!splitHallway[i+1].equals("0")) {
                        String hallwayName = "Hallway_" + splitHallway[i] + "_" + splitHallway[i + 2];
                        Long hallwayLength = Long.parseLong(splitHallway[i + 1]);
                        Room newHallway = new Room(hallwayName, hallwayLength * hallwayWidth);
                        rooms.add(newHallway);
                        getRoom(splitHallway[i]).addConnection(newHallway);
                        newHallway.addConnection(getRoom(splitHallway[i + 2]));
                    } else {
                        getRoom(splitHallway[i]).addConnection(getRoom(splitHallway[i+2]));
                    }
                }
            }

            // Get the robot starting locations
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.equals("") || line.equals(" ") || line.equals(null)) {
                    break;
                } else {
                    robotLocations.add(new Robot(getRoom(line)));
                }
            }

            // Get your goal locations
            goals[0] = getRoom(scanner.next());
            goals[1] = getRoom(scanner.next());
            scanner.close();

        /*System.out.println(names);
        System.out.println(squareFootage);
        System.out.println(hallways);
        System.out.println(locations);*/

            /*for(Room room : rooms) {
                System.out.println(room);
            }
            System.out.println(robotLocations);*/
        }
    }

    public boolean step() {
        for(Robot robot : robotLocations) {
            Room currentLoc = robot.getLoc();
            if(currentLoc.isScanned()) {
                checkCompletion();
                ArrayList<Connection> connections = currentLoc.getConnections();
                for(Connection connection : connections) {
                    if(!connection.getDestination().isScanned()) {
                        robot.setLoc(connection.getDestination());
                    }
                }
            } else {
                currentLoc.addScan(robotScan);
            }
        }
        return false;
    }

    public String getSolution() {
        String solution = "";
        for(Room room : rooms) {
            solution += room.toString() + "\n";
        }
        return solution;
    }

    private Room getRoom(String roomName) {
        return rooms.get(rooms.indexOf(new Room(roomName, 0)));
    }

    private boolean checkCompletion() {
        if(!goals[0].isScanned() || !goals[1].isScanned()) return false;
        return true;
        // return dfs(goals[0]);
    }

    private boolean dfs(Room room) {
        return true;
    }
}
