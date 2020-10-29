package models;

/**
 *Last Edited: 10/29/2020
 *
 *This class is a subclass of User with no login or saving capabilities
 *
 * @author Ryan
 */
public class Guest extends User{

    private final boolean ISGUEST;

    public Guest(){
        super();
        this.ISGUEST = true;
        this.setUsername("Guest");
    }

    @Override
    public void login() {
        LoginSession.currentUser = this;
    }

    @Override
    public Boolean save() {
        return false;
    }

    //========================= GETTERS =========================//
    @Override
    public String getUuid() {
        return null;
    }

    @Override
    public int getId() {
        return -1;
    }
}
