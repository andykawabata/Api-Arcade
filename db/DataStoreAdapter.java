package db;

/*
*Last updated on 10/24/20
*
*Implements the DBConnector interface using a CSV connector
*Can CRUD a DataObject
*
*Contributing authors
*@author Andy
 */
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class DataStoreAdapter {

    private static final DBConnectorInterface connector = new CSVConnector();

    public static Boolean createObject(Map<String, String> map, String _table) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Exception {
        // Send name-value pairs to the connector class. This class should
        // return a generated id number.
        int id = connector.createObject(map, _table);
        String idString = String.valueOf(id);
        map.put("id", idString);
        return (id != 0);
    }

    public static List<Map<String, String>> readObject(Map<String, String> _map, String _table) throws Exception {
        return connector.readObject(_map, _table);
    }

    // ================================ STUBS ==================================== //
    public static Boolean updateObject(Map<String, String> _map, String uuid, String _table) throws IOException {
        return connector.updateObject(_map, uuid, _table);
    }

    public Boolean deleteObject(String uuid) {
        return false;
    }

    public static boolean findObjectByUsername(Map<String, String> _keyValue, String _table) throws Exception {
        return connector.findObjectByUsername(_keyValue, _table);
    }

}
