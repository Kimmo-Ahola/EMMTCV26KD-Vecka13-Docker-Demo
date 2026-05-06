package com.example.playwright_demo;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class BasePlaywrightTest {
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void beforeAll() {
        boolean ci = System.getenv("CI") != null;

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(ci)
        );
    }

    @BeforeEach
    void beforeEach() {
        context = browser.newContext();
        page = context.newPage();
        page.setDefaultTimeout(10_000);
    }

    @AfterEach
    void afterEach() {
        context.close();
    }

    @AfterAll
    static void afterAll() {
        browser.close();
        playwright.close();
    }
}
