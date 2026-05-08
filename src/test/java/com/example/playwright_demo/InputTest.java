package com.example.playwright_demo;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTest extends BasePlaywrightTest {
    @Test
    void fillInputsAndVerifyValues() {
        page.navigate("https://practice.expandtesting.com/inputs");

        var number = page.locator("input[type='number']");
        var text = page.locator("input[type='text']");
        var password = page.locator("input[type='password']");
        var date = page.locator("input[type='date']");

        number.fill("1337");
        text.fill("hello playwright");
        password.fill("SuperSecret!");
        date.fill("2026-01-21");

        assertThat(number).hasValue("1337");
        assertEquals("1337", number.inputValue());
        assertEquals("hello playwright", text.inputValue());
        assertEquals("SuperSecret!", password.inputValue());
        assertEquals("2026-01-21", date.inputValue());

        page.getByRole(com.microsoft.playwright.options.AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Display Inputs")).click();
        assertThat(page).hasURL("https://practice.expandtesting.com/inputs");
    }
}