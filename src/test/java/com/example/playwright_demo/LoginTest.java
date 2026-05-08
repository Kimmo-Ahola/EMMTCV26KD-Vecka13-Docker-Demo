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
    void failingLogin() {
        assertThat(page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Main navigation"))).isVisible();

        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("practice");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("SuperSecretPassWord!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        assertThat(page.getByText("Your password is invalid!")).isVisible();
        assertThat(page.locator("#flash")).containsText("Your password is invalid!");

        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("practice");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username"))).hasValue("practice");
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
