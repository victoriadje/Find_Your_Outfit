package com.outfitgen.use_case.logout;

/**
 * DAO interface for the Logout Use Case.
 */
public interface LogoutUserDataAccessInterface {

    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user
     */
    String getCurrentUsername();

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username
     */
    void setCurrentUsername(String username);

    /**
     * Saves everything the user changed, so nothing is lost when they log out.
     * @param username the user who is logging out
     */
    void saveChanges(String username);
}