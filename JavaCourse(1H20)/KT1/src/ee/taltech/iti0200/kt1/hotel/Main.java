public class Main {

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

    public static void main(String[] args) {

        // Siin teen loon hotelli, mis saab sisse ainult nime, ja loon toad, mis saavad sisse ruumi numbri,
        // suuruse, korruse ja ruumitüübi.
        Hotel hotel = new Hotel("Viimsi Hotell");
        Room room11 = new Room(ROOMNR11, ROOMSIZE20, 1, RoomType.Regular);
        Room room12 = new Room(ROOMNR12, ROOMSIZE21, 1, RoomType.Suite);
        Room room13 = new Room(ROOMNR13, ROOMSIZE22, 1, RoomType.Regular);
        Room room21 = new Room(ROOMNR21, ROOMSIZE23, 2, RoomType.Suite);
        Room room22 = new Room(ROOMNR22, ROOMSIZE24, 2, RoomType.Regular);
        Room room23 = new Room(ROOMNR23, ROOMSIZE25, 2, RoomType.Suite);

        Room room23Copy = new Room(ROOMNR23, 2, 3, RoomType.Regular);

        // Siin lisan ruumid hotelli, samad ruumid ei lisandu hotelli, sama kehtib sarnase toanumbri kohta.
        // Lisamisel tagastatakse true või false, vastavalt sellele kas tuba sai olema lisatud.
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room12);
        hotel.addRoomToHotel(room13);
        hotel.addRoomToHotel(room21);
        hotel.addRoomToHotel(room22);
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room11);
        hotel.addRoomToHotel(room23);
        hotel.addRoomToHotel(room23Copy);

        // Siin me reserveerime toa. Kui tuba pole hotellis või tuba on juba brooneeritud, tagastatakse false,
        // vastasel juhul broneeritakse ja tagastatakse true.
        System.out.println(hotel.bookRoom(room11)); // true
        System.out.println(hotel.bookRoom(room12)); // true
        System.out.println(hotel.bookRoom(room23Copy)); // false
        System.out.println(hotel.bookRoom(room12)); // false

        // Siin me tühistame broneeringu. Kui tuba on hotellis ja on broneeritud ja tema tüüp pole suite,
        // siis tühistab ja tagastab true, vastasel juhul false.
        System.out.println(hotel.cancelBooking(room11)); // true
        System.out.println(hotel.cancelBooking(room12)); // false
        System.out.println(hotel.cancelBooking(room13)); // false
        System.out.println(hotel.cancelBooking(room23Copy)); // false
        System.out.println(hotel.cancelBooking(room11)); // false

        // Siin otsime suuruse järgi vabad toad. tuba 11 ja 12 ei sobi suuruse järgi ja tuba 21 on reserveeritud.
        // Saame 3 vaba tuba.
        hotel.bookRoom(room21);
        System.out.println(hotel.findRoomsBySize(ROOMSIZE22).size()); // 3

        // Siin saame kõik vabad toad, kõik hõivatud ja kõik ülsde.
        System.out.println(hotel.getRooms()); // List kõikidest tubadest mis on hotellist
        System.out.println(hotel.getAllFreeRooms()); // List kõikidest vabadest tubadest
        System.out.println(hotel.getAllBookedRooms()); // List kõikidest reserveeritud tubadest
    }
}
