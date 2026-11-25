package com.outfitgen.app;

import com.outfitgen.data_access.CSVClothingRepository;
import com.outfitgen.data_access.FileUserDataAccessObject;
import com.outfitgen.data_access.WeatherDataAccessObject;
import com.outfitgen.entity.UserFactory;
import com.outfitgen.interface_adapter.ViewManagerModel;
import com.outfitgen.interface_adapter.logged_in.LoggedInViewModel;
import com.outfitgen.interface_adapter.login.LoginController;
import com.outfitgen.interface_adapter.login.LoginPresenter;
import com.outfitgen.interface_adapter.login.LoginViewModel;
import com.outfitgen.interface_adapter.logout.LogoutController;
import com.outfitgen.interface_adapter.logout.LogoutPresenter;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitController;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitPresenter;
import com.outfitgen.interface_adapter.recommend_outfit.RecommendOutfitViewModel;
import com.outfitgen.interface_adapter.signup.SignupController;
import com.outfitgen.interface_adapter.signup.SignupPresenter;
import com.outfitgen.interface_adapter.signup.SignupViewModel;
import com.outfitgen.use_case.login.LoginInputBoundary;
import com.outfitgen.use_case.login.LoginInteractor;
import com.outfitgen.use_case.login.LoginOutputBoundary;
import com.outfitgen.use_case.logout.LogoutInputBoundary;
import com.outfitgen.use_case.logout.LogoutInteractor;
import com.outfitgen.use_case.logout.LogoutOutputBoundary;
import com.outfitgen.use_case.recommend_outfit.*;
import com.outfitgen.use_case.signup.SignupInputBoundary;
import com.outfitgen.use_case.signup.SignupInteractor;
import com.outfitgen.use_case.signup.SignupOutputBoundary;
import com.outfitgen.view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new UserFactory();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);


    // Data access objects
    private final FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("users.csv", userFactory);
    private final RecommendOutfitDataAccessInterface clothingDAO;
    private final WeatherDataAccessInterface weatherDAO;

    //SignUp
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    //Login
    private LoginView loginView;
    private LoginViewModel loginViewModel;
    //Logged in
    private LoggedInView loggedInView;
    private LoggedInViewModel loggedInViewModel;
    //Recommend Outfit
    private RecommendOutfitView recommendOutfitView;
    private RecommendOutfitViewModel recommendOutfitViewModel;
    private RecommendOutfitController recommendController;
    //Logout
    private LogoutController logoutController;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);

        clothingDAO = new CSVClothingRepository("src/main/java/com/outfitgen/data_access/SavedRepository");

        weatherDAO = new WeatherDataAccessObject();
    }

    public AppBuilder addSignupView() {

        signupViewModel = new SignupViewModel();

        signupView = new SignupView(signupViewModel);

        cardPanel.add(signupView, SignupViewModel.VIEW_NAME);

        return this;
    }

    public AppBuilder addLoginView() {

        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);

        cardPanel.add(loginView, LoginViewModel.VIEW_NAME);

        return this;
    }

    public AppBuilder addLoggedInView() {

        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);

        cardPanel.add(loggedInView, LoggedInViewModel.VIEW_NAME);

        return this;
    }

    public AppBuilder addRecommendOutfitView() {

        recommendOutfitViewModel = new RecommendOutfitViewModel();

        return this;
    }

    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);

        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        SignupController controller = new SignupController(userSignupInteractor);

        signupView.setSignupController(controller);

        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary =
                new LoginPresenter(
                        viewManagerModel, loggedInViewModel, loginViewModel
                );
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        LoginController loginController = new LoginController(loginInteractor);

        loginView.setLoginController(loginController);

        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);

        return this;
    }

    // Victoria's: connect the recommend outfit use case
    public AppBuilder addRecommendOutfitUseCase() {
        final RecommendOutfitOutputBoundary presenter = new RecommendOutfitPresenter(recommendOutfitViewModel);

        final RecommendOutfitInputBoundary recommendOutfitInteractor = new RecommendOutfitInteractor(clothingDAO, weatherDAO, presenter);

        recommendController = new RecommendOutfitController(recommendOutfitInteractor);

        recommendOutfitView = new RecommendOutfitView(recommendController, recommendOutfitViewModel);

        // Log Out button on the Recommend Outfit screen (users land here after logging in).
        recommendOutfitView.setLogoutController(logoutController);
        cardPanel.add(recommendOutfitView, RecommendOutfitViewModel.VIEW_NAME);

        return this;
    }

    public JFrame build() {

        final JFrame application = new JFrame("Find Your Outfit");

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(SignupViewModel.VIEW_NAME);
        viewManagerModel.notifyViewChanged();

        return application;
    }
}