package models;
/*
*Last updated on 10/28/20
*
*Tracks current user
*
*Contributing authors
*@author Francisco
*@author Ryan
*@author Andy
*/
public class LoginSession {

    public static User currentUser;


    public static boolean isGuest(){
        return (currentUser.getUsername().equals("guest"));
    }

}
