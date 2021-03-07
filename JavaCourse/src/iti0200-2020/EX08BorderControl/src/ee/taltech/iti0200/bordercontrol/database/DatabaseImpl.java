package ee.taltech.iti0200.bordercontrol.database;

import java.util.LinkedList;
import java.util.List;

public class DatabaseImpl implements Database {
    List<String> stolenVehicles = new LinkedList<>();
    List<String> missingPersons = new LinkedList<>();
    List<String> terrorists = new LinkedList<>();
    List<Long> illegalGoods = new LinkedList<>();

    @Override
    public List<String> getStolenVehicles() {
        return stolenVehicles;
    }

    @Override
    public List<String> getMissingPersons() {
        return missingPersons;
    }

    @Override
    public List<String> getTerrorists() {
        return terrorists;
    }

    @Override
    public List<Long> getIllegalGoods() {
        return illegalGoods;
    }

    @Override
    public void setStolenVehicles(List<String> stolenVehicles) {
        this.stolenVehicles = stolenVehicles;
    }

    @Override
    public void setMissingPersons(List<String> missingPersons) {
        this.missingPersons = missingPersons;
    }

    @Override
    public void setTerrorists(List<String> terrorists) {
        this.terrorists = terrorists;
    }

    @Override
    public void setIllegalGoods(List<Long> illegalGoods) {
        this.illegalGoods = illegalGoods;
    }
}
