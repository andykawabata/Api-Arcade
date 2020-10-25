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
import java.util.List;
import java.util.Map;

public interface DBConnectorInterface {

    public abstract int createObject(Map<String,String> _keyValuePairs, String _table) throws Exception;

    public abstract List<Map<String, String>> readObject(Map<String,String> _keyValuePairs, String _table) throws Exception;

    public abstract Boolean updateObject(Map<String,String> _keyValuePairs, String _uuid, String _table);

    public abstract Boolean deleteObject(String uuid);

    public boolean findObjectByUsername(Map<String, String> _keyValue, String _table)throws Exception;
}
