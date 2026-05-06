package com.example.playwright_demo;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BasePlaywrightTest {
    @BeforeEach
    void setup() {
        page.navigate("https://practice.expandtesting.com/login");
    }

    @Test
    void successfulLogin() {
        page.getByLabel("Username").fill("practice");
        page.getByLabel("Password").fill("SuperSecretPassword!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        assertThat(page).hasURL("https://practice.expandtesting.com/secure");
        assertThat(page.getByText("You logged into a secure area!")).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"))).isVisible();
    }

    @Test
    void invalidUsernameShowsError() {
        page.getByLabel("Username").fill("wrongUser");
        page.getByLabel("Password").fill("SuperSecretPassword!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        assertThat(page).hasURL("https://practice.expandtesting.com/login");
        assertThat(page.getByText("Invalid username.")).isVisible();
    }

    @Test
    void invalidPasswordShowsError() {
        page.getByLabel("Username").fill("practice");
        page.getByLabel("Password").fill("WrongPassword");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        assertThat(page).hasURL("https://practice.expandtesting.com/login");
        assertThat(page.getByText("Invalid password.")).isVisible();
    }
}
