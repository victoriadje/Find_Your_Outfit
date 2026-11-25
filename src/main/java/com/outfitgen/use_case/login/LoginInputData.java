package com.outfitgen.use_case.login;

public class LoginInputData {
    private final String username;
    private final String password;

    public LoginInputData (String user, String password) {
        this.username = user;
        this.password = password;
    }

    String getUsername() {
        return this.username;
    }

    String getPassword() {
        return password;
    }
}
