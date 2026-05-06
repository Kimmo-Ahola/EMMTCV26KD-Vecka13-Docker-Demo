package com.example.playwright_demo;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LocatorsTest extends BasePlaywrightTest {

    @Test
    void locatorShowcase() {
        page.navigate("https://practice.expandtesting.com/locators");

        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add item"))).isVisible();
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact"))).isVisible();

        assertThat(page.getByText("Hot Deal: Buy 1 Get 1 Free")).isVisible();

        assertThat(page.getByLabel("Choose a country")).isVisible();
        assertThat(page.getByLabel("Choose a country")).hasValue("France");

        assertThat(page.getByPlaceholder("Search the site")).isVisible();

        assertThat(page.getByAltText("avatar")).isVisible();

        assertThat(page.getByTitle("Reload")).isVisible();
        assertThat(page.getByTitle("Settings")).isVisible();

        assertThat(page.getByTestId("status")).isVisible();
        assertThat(page.getByTestId("status")).containsText("All systems operational");

        assertThat(page.locator("a").filter(new com.microsoft.playwright.Locator.FilterOptions().setHasText("This is a legacy CSS target"))).isVisible();

        assertThat(page.locator("//li[contains(., 'Task 1')]")).isVisible();

        assertThat(page.locator("table").locator("tr").filter(new com.microsoft.playwright.Locator.FilterOptions().setHasText("Headphones"))).isVisible();
        assertThat(page.locator("table").locator("tr").filter(new com.microsoft.playwright.Locator.FilterOptions().setHasText("Keyboard"))).isVisible();
    }
}