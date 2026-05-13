package com.example.playwright_demo;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PracticePageTest extends BasePlaywrightTest {
    @BeforeEach
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) baseUrl = "http://localhost:8080";

        page.navigate(baseUrl);
        page.waitForURL(baseUrl);
    }

    @Test
    void shouldInteractWithPracticePage() {
        // Heading
        assertThat(page.locator("#page-title"))
                .hasText("Playwright Test Page");

        // Fill email
        page.getByPlaceholder("Email")
                .fill("tester@example.com");

        // Button click
        page.locator(".btn-green").click();

        // Checkbox
        page.getByLabel("Accept Terms").check();

        // Radio button
        page.getByLabel("Option B").check();

        // Dropdown
        page.locator("#color-select")
                .selectOption("green");

        // List items
        List<String> features =
                page.locator(".feature").allTextContents();

        System.out.println(features);

        // Image alt
        String alt =
                page.locator("#logo").getAttribute("alt");

        System.out.println(alt);

        // Link click
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Learn more")).click();

        // Nested span
        assertThat(page.locator("#nested-span"))
                .hasText("Nested Text");
    }
}
