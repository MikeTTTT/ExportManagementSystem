package items;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Items {
    private final StringProperty ID;
    private final StringProperty UNIT;
    private final StringProperty UNIT_PACKING;
    private final StringProperty DESCRIPTION;

    public Items(String ID, String UNIT, String UNIT_PACKING, String DESCRIPTION) {
        this.ID = new SimpleStringProperty(ID);
        this.UNIT = new SimpleStringProperty(UNIT);
        this.UNIT_PACKING = new SimpleStringProperty(UNIT_PACKING);
        this.DESCRIPTION = new SimpleStringProperty(DESCRIPTION);
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getUNIT() {
        return UNIT.get();
    }

    public StringProperty UNITProperty() {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT.set(UNIT);
    }

    public String getUNIT_PACKING() {
        return UNIT_PACKING.get();
    }

    public StringProperty UNIT_PACKINGProperty() {
        return UNIT_PACKING;
    }

    public void setUNIT_PACKING(String UNIT_PACKING) {
        this.UNIT_PACKING.set(UNIT_PACKING);
    }

    public String getDESCRIPTION() {
        return DESCRIPTION.get();
    }

    public StringProperty DESCRIPTIONProperty() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION.set(DESCRIPTION);
    }
}
