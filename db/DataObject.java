package db;
/*
*Last updated on 10/24/20
*
*Defines a DataObject object, which means a User or a Score, who both extend this.
*
*Contributing authors
*@author Andy
*/
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataObject {

    protected final String dataTable = "";
    protected int id = 0;
    protected String uuid;


    public DataObject() {
        this.setUuid(generateUuid());
    }

    //Builds map of instance's current properties
    public Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(this.id));
        if(uuid != null)
            map.put("uuid", uuid);
        return map;
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

    // ================================ GETTERS ====================================

    public String getUuid() {
        return this.uuid;
    }

    public int getId() {
        return this.id;
    }

// ================================ SETTERS ====================================

    private void setUuid(String _uuid) {
        this.uuid = _uuid;
    }

    public void setId(int _id) {
        this.id = _id;
    }

}
