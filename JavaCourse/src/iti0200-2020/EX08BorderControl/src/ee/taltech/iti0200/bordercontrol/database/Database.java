package ee.taltech.iti0200.bordercontrol.database;

import java.util.List;

public interface Database {
    List<String> getStolenVehicles(); // tagastab andmebaasis olevad varastatud sõidukite VIN-koodid

    List<String> getMissingPersons(); // tagastab andmebaasis olevad kadunuks kuulutatud inimesed.

    List<String> getTerrorists(); // tagastab andmebaasis olevad terroristideks kuulutatud inimesed.

    List<Long> getIllegalGoods(); // tagastab andmebaasis olevad toodete ID-d, mida ei tohi transportida.

    void setStolenVehicles(List<String> stolenVehicles); // seab andmebaasi varastatud sõidukite andmed.

    void setMissingPersons(List<String> missingPersons); // seab andmebaasi kadunuks kuulutatud inimeste andmed.

    void setTerrorists(List<String> terrorists); // seab andmebaasi terroristideks kuulutatud inimeste andmed.

    void setIllegalGoods(List<Long> illegalGoods); // seab andmebaasi keelatud toodete andmed.
}
