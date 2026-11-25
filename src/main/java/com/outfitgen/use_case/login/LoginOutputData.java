package com.outfitgen.use_case.login;

public class LoginOutputData {
    private final String username;

    public LoginOutputData (String user) {
        this.username = user;
    }

    public String getUsername() {
        return username;
    }
}
