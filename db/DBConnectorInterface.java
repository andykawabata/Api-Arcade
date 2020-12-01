package db;

/*
*Last updated on 10/25/20
*
*An interface that tells Adapters to include CRUD methods
*
*Contributing authors
*@author Andy
*@author Ryan
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DBConnectorInterface {

    public abstract int createObject(Map<String, String> _keyValuePairs, String _table) throws Exception;

    public abstract List<Map<String, String>> readObject(Map<String, String> _keyValuePairs, String _table) throws Exception;

    public abstract Boolean updateObject(Map<String, String> _keyValuePairs, String _uuid, String _table) throws FileNotFoundException, IOException;

    public abstract Boolean deleteObject(String _uuid, String _table) throws FileNotFoundException, IOException;

    public abstract ArrayList<String> getTableRows(String _table) throws FileNotFoundException, IOException;

}
