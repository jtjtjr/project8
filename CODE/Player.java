public class Player {
    private Ship ship;
    private int crewNum;
    private int morale;
    private int resources;

    // Constructor
    public Player(int crewNum, int morale, int resources, String shipName) {
        this.crewNum = crewNum;
        this.morale = morale;
        this.resources = resources;
        this.ship = new Ship(shipName);
    }

    // Getters and Setters
    public Ship getShip() {
        return ship;
    }

    public int getCrewNum() {
        return crewNum;
    }

    public void setCrewNum(int crewNum) {
        this.crewNum = crewNum;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    // Example methods
    public void updateMorale(int change) {
        morale += change;
    }

    public void useResources(int amount) {
        if (resources >= amount) {
            resources -= amount;
        } else {
            System.out.println("Not enough resources!");
        }
    }

    public void addCrewMember() {
        crewNum++;
    }

    public void removeCrewMember() {
        if (crewNum > 0) {
            crewNum--;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "ship=" + ship +
                ", crewNum=" + crewNum +
                ", morale=" + morale +
                ", resources=" + resources +
                '}';
    }

    // Inner class for Ship
    public class Ship {
        private String name;

        public Ship(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ship{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
