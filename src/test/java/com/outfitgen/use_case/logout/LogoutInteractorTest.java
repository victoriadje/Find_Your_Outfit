package com.outfitgen.use_case.logout;

import com.outfitgen.data_access.InMemoryUserDataAccessObject;
import com.outfitgen.entity.UserFactory;
import com.outfitgen.entity.User;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    @Test
    void successTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new UserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);
        userRepository.setCurrentUsername("Paul");

        // This creates a successPresenter that tests whether the test case is as we expect.
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData user) {
                assertEquals("Paul", user.getUsername());
                assertNull(userRepository.getCurrentUsername());
            }
        };

        LogoutInputBoundary interactor = new LogoutInteractor(userRepository, successPresenter);
        interactor.execute();
        assertNull(userRepository.getCurrentUsername());
    }

    // Logging out should save the user's changes.
    @Test
    void savesChangesTest() {
        final List<String> savedFor = new ArrayList<>();
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject() {
            @Override
            public void saveChanges(String username) {
                savedFor.add(username);
            }
        };
        userRepository.setCurrentUsername("Paul");

        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData user) {
                // By the time the user sees they are logged out, their changes are already saved.
                assertEquals(List.of("Paul"), savedFor);
            }
        };

        LogoutInputBoundary interactor = new LogoutInteractor(userRepository, successPresenter);
        interactor.execute();
        assertEquals(List.of("Paul"), savedFor);
    }

}