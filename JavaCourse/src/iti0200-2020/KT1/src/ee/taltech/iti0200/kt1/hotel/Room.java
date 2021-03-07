public class Room {

    private int roomNr;
    private int floor;
    private RoomType roomType;
    private int roomSize;
    private boolean roomBooked = false;

    public Room(int roomNr, int roomSize, int floor, RoomType roomType) {
        this.floor = floor;
        this.roomNr = roomNr;
        this.roomType = roomType;
        this.roomSize = roomSize;
    }

    public int getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(int roomNr) {
        this.roomNr = roomNr;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public boolean isRoomBooked() {
        return roomBooked;
    }

    public void setRoomBooked(boolean roomBooked) {
        this.roomBooked = roomBooked;
    }
}
