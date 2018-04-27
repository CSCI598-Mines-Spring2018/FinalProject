public class Robot {
    private Room loc;
    Robot(Room loc) {
        this.loc = loc;
    }

    public Room getLoc() {
        return loc;
    }

    public void setLoc(Room loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "loc=" + loc.getName() +
                '}';
    }
}
