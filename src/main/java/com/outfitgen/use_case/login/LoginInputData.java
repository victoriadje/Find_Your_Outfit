package com.outfitgen.use_case.login;

public class LoginInputData {
    private final String username;
    private final String password;

    public LoginInputData (String user, String password) {
        this.username = user;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return password;
    }
}
