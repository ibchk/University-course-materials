import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String hotelName;
    private List<Room> rooms = new ArrayList<>();

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
    }

    public boolean addRoomToHotel(Room room) {
        for (Room hotelRoom : rooms) {
            if (room.getRoomNr() == hotelRoom.getRoomNr()) {
                return false;
            }
        }
        rooms.add(room);
        return true;
    }

    public List<Room> findRoomsBySize(int size) {
        List<Room> suitableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomSize() >= size && !room.isRoomBooked()) {
                suitableRooms.add(room);
            }
        }
        return suitableRooms;
    }

    public List<Room> getAllFreeRooms() {
        List<Room> suitableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isRoomBooked()) {
                suitableRooms.add(room);
            }
        }
        return suitableRooms;
    }

    public List<Room> getAllBookedRooms() {
        List<Room> suitableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isRoomBooked()) {
                suitableRooms.add(room);
            }
        }
        return suitableRooms;
    }

    public boolean bookRoom(Room room) {
        if (rooms.contains(room) && !room.isRoomBooked()) {
            room.setRoomBooked(true);
            return true;
        }
        return false;
    }

    public boolean cancelBooking(Room room) {
        if (rooms.contains(room) && room.isRoomBooked() && room.getRoomType() != RoomType.Suite) {
            room.setRoomBooked(false);
            return true;
        }
        return false;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
