import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HotelTest {

    public static final int SIZE6 = 6;
    public static final int ROOMNR11 = 11;
    public static final int ROOMNR12 = 12;
    public static final int ROOMNR13 = 13;
    public static final int ROOMNR21 = 21;
    public static final int ROOMNR22 = 22;
    public static final int ROOMNR23 = 23;
    public static final int ROOMSIZE20 = 20;
    public static final int ROOMSIZE21 = 21;
    public static final int ROOMSIZE22 = 22;
    public static final int ROOMSIZE23 = 23;
    public static final int ROOMSIZE24 = 24;
    public static final int ROOMSIZE25 = 25;

    Hotel hotel = new Hotel("Viimsi Hotell");
    Room room11 = new Room(ROOMNR11, ROOMSIZE20, 1, RoomType.Regular);
    Room room12 = new Room(ROOMNR12, ROOMSIZE21, 1, RoomType.Suite);
    Room room13 = new Room(ROOMNR13, ROOMSIZE22, 1, RoomType.Regular);
    Room room21 = new Room(ROOMNR21, ROOMSIZE23, 2, RoomType.Suite);
    Room room22 = new Room(ROOMNR22, ROOMSIZE24, 2, RoomType.Regular);
    Room room23 = new Room(ROOMNR23, ROOMSIZE25, 2, RoomType.Suite);
    Room room23Copy = new Room(ROOMNR23, 2, 3, RoomType.Regular);

    @org.junit.jupiter.api.Test
    void checkRoomsAdded() {
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room12);
        hotel.addRoomToHotel(room13);
        hotel.addRoomToHotel(room21);
        hotel.addRoomToHotel(room22);
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room23);
        hotel.addRoomToHotel(room23Copy);
        assertEquals(SIZE6, hotel.getRooms().size());
    }

    @org.junit.jupiter.api.Test
    void checkRoomsBooking() {
        new Main();
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room12);
        hotel.addRoomToHotel(room13);
        hotel.addRoomToHotel(room21);
        hotel.addRoomToHotel(room22);
        hotel.addRoomToHotel(room23);
        assertTrue(hotel.bookRoom(room11));
        assertTrue(hotel.bookRoom(room12));
        assertFalse(hotel.bookRoom(room23Copy));
        assertFalse(hotel.bookRoom(room12));
    }

    @org.junit.jupiter.api.Test
    void checkRoomsCanceling() {
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room12);
        hotel.addRoomToHotel(room13);
        hotel.addRoomToHotel(room21);
        hotel.addRoomToHotel(room22);
        hotel.addRoomToHotel(room23);
        hotel.bookRoom(room11);
        hotel.bookRoom(room12);
        hotel.bookRoom(room23Copy);
        hotel.bookRoom(room12);
        assertTrue(hotel.cancelBooking(room11));
        assertFalse(hotel.cancelBooking(room12));
        assertFalse(hotel.cancelBooking(room13));
        assertFalse(hotel.cancelBooking(room23Copy));
        assertFalse(hotel.cancelBooking(room11));
    }

    @org.junit.jupiter.api.Test
    void checkRoomsBySize() {
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room12);
        hotel.addRoomToHotel(room13);
        hotel.addRoomToHotel(room21);
        hotel.addRoomToHotel(room22);
        hotel.addRoomToHotel(room23);
        hotel.bookRoom(room11);
        hotel.bookRoom(room12);
        hotel.bookRoom(room23Copy);
        hotel.bookRoom(room12);
        hotel.cancelBooking(room11);
        hotel.cancelBooking(room12);
        hotel.cancelBooking(room13);
        hotel.cancelBooking(room23Copy);
        hotel.cancelBooking(room11);
        hotel.bookRoom(room21);
        assertEquals(3, hotel.findRoomsBySize(ROOMSIZE22).size());
    }

    @org.junit.jupiter.api.Test
    void getRoomsInHotels() {
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room12);
        hotel.addRoomToHotel(room13);
        hotel.addRoomToHotel(room21);
        hotel.addRoomToHotel(room22);
        hotel.addRoomToHotel(room23);
        hotel.bookRoom(room11);
        hotel.bookRoom(room12);
        hotel.bookRoom(room23Copy);
        hotel.bookRoom(room12);
        hotel.cancelBooking(room11);
        hotel.cancelBooking(room12);
        hotel.cancelBooking(room13);
        hotel.cancelBooking(room23Copy);
        hotel.cancelBooking(room11);
        hotel.bookRoom(room21);
        hotel.findRoomsBySize(ROOMSIZE22).size();
        assertEquals(SIZE6, hotel.getRooms().size());
        assertEquals(4, hotel.getAllFreeRooms().size());
        assertEquals(2, hotel.getAllBookedRooms().size());
    }

    @org.junit.jupiter.api.Test
    void main() {
        String[] args = {""};
        Main.main(args);
    }
}
