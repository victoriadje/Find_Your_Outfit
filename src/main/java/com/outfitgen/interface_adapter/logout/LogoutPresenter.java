package com.outfitgen.interface_adapter.logout;

import com.outfitgen.interface_adapter.ViewManagerModel;
import com.outfitgen.interface_adapter.logged_in.LoggedInState;
import com.outfitgen.interface_adapter.logged_in.LoggedInViewModel;
import com.outfitgen.interface_adapter.login.LoginState;
import com.outfitgen.interface_adapter.login.LoginViewModel;
import com.outfitgen.use_case.logout.LogoutOutputBoundary;
import com.outfitgen.use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // User is logged out, "logged in" screen shouldn't show their name anymore.
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername("");
        loggedInViewModel.notifyStateChanged();

        // Put username back in the login box to log in again quickly
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        loginState.setPassword("");
        loginViewModel.notifyStateChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(LoginViewModel.VIEW_NAME);
        this.viewManagerModel.notifyViewChanged();
    }
}