package models;
/*
*Last updated on 10/28/20
*
*Inherits the DataOject class to create a User for later use.
*
*Contributing authors
*@author Francisco
*@author Ryan
*@author Andy
*/
import db.DataObject;
import db.DataStoreAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Get Rid of hardcoded password
public class User extends DataObject {

    private String username;
    private String password;
    public static String TABLE = "src/storage/users.csv";


    public User() {
    }

    public String getUsername() {
        return username;
    }
    /**
     * uses "readObject()" method to load a user into a User object, give the username
     * @param _username: String
     * @param _password: String
     * @return User object with all properties filled. Null if username was not found.
     * @throws Exception
     */
    public static User loadByUsername(String _username) throws Exception {

       Map<String, String> keyValue = new HashMap<>();
       keyValue.put("username", _username);
       List<Map<String, String>> response = DataStoreAdapter.readObject(keyValue, User.TABLE);
       // IF USER DIDN'T EXIST IN DB
       if(response == null)
           return null;
       User user = new User();
       user.id = Integer.valueOf(response.get(0).get("id"));
       user.uuid = response.get(0).get("uuid");
       user.username = response.get(0).get("username");
       user.password = response.get(0).get("password");

       return user;
    }

    public Boolean passwordMatches(String _password) {
         return this.password.equals(_password);
    }

    /**
     * Updates LoginSession to reflect new user
     */
    public void login() {
        LoginSession.currentUser = this;
    }

    /**
     *uses User properties to either create new User or update current user,
     * depending of if the user is in the table already (id != 0)
     * @return true if user was saved, false if error
     */
    public Boolean save() {
        Map<String, String> userProperties = new HashMap<>();
        Map<String, String> parentProperties = new HashMap<>();
        if(this.username != null)
            userProperties.put("username", this.username);
        if(this.password != null)
            userProperties.put("password", this.password);
        parentProperties = super.createMap();
        userProperties.putAll(parentProperties);

        if (this.id == 0) {
            try {
                return DataStoreAdapter.createObject(userProperties, User.TABLE);
            } catch (Exception e) {
            }
        } else {/*
            try {
                // This is an exisitng object in the database, just update the object.
                return DataStoreAdapter.updateObject(this);
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchFieldException ex) {
            }*/
        }
        return false;
    }

    public static boolean usernameExists(String _givenUsername) throws Exception {
       Map<String, String> keyValue = new HashMap<>();
       keyValue.put("username", _givenUsername);
       return DataStoreAdapter.findObjectByUsername(keyValue, User.TABLE);
    }

    //=================  GETTERS ===============
    public String getPassword() {
        return password;
    }

    //=================  SETTERS ===============
    public void setUsername(String _username) {
        this.username = _username;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

}
