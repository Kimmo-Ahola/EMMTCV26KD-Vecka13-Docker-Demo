package com.example.playwright_demo;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PracticePageTest extends BasePlaywrightTest {
    @BeforeEach
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) baseUrl = "http://localhost:8080";

        page.navigate(baseUrl);
    }

    @Test
    void shouldInteractWithPracticePage() {
        Locator heading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Playwright Test Page")
        );
        assertThat(heading).isVisible();

        Locator email = page.getByPlaceholder("Email");
        email.fill("tester@example.com");
        assertThat(email).hasValue("tester@example.com");

        Locator button = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Click Me")
        );
        button.click();

        Locator checkbox = page.getByRole(
                AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Accept Terms")
        );
        checkbox.check();
        assertThat(checkbox).isChecked();

        Locator radio = page.getByRole(
                AriaRole.RADIO,
                new Page.GetByRoleOptions().setName("Option B")
        );
        radio.check();
        assertThat(radio).isChecked();

        Locator select = page.locator("#color-select");
        select.selectOption("green");
        assertThat(select).hasValue("green");

        Locator logo = page.getByRole(
                AriaRole.IMG,
                new Page.GetByRoleOptions().setName("company logo")
        );
        assertThat(logo).hasAttribute("alt", "Playwright Logo");

        Locator link = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Learn more")
        );
        link.click();

        assertThat(page.locator("#nested-span"))
                .hasText("Nested Text");
    }
}
