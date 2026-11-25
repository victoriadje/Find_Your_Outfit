package com.outfitgen.view;

import com.outfitgen.interface_adapter.logged_in.LoggedInState;
import com.outfitgen.interface_adapter.logged_in.LoggedInViewModel;
import com.outfitgen.interface_adapter.logged_in.LoggedInViewModelListener;
import com.outfitgen.interface_adapter.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements ActionListener, LoggedInViewModelListener {

    private final LoggedInViewModel loggedInViewModel;
    private LogoutController logoutController;

    private final JLabel username;

    private final JButton logOut;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        logOut.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        // Execute the logout use case through the Controller
        if (evt.getSource().equals(logOut)) {
            logoutController.execute();
        }
    }

    public void setLogoutController(LogoutController logoutController) {
        // Save the logout controller in the instance variable
        this.logoutController = logoutController;
    }

    /**
     * The logged-in state was updated.
     */
    @Override
    public void stateChanged() {
        LoggedInState state = loggedInViewModel.getState();
        username.setText(state.getUsername());
    }
}