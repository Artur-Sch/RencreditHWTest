package insuranceTest.pages;

import insuranceTest.core.Init;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    protected WebDriver driver;
    private WebDriverWait webDriverWait;

    public BasePage() {
        this.driver = Init.getDriver();
        PageFactory.initElements(Init.getDriver(), this);
        webDriverWait = new WebDriverWait(driver, 10, 200);
    }

    @Step("Ожидание загрузки элемента - {element}")
    public WebElement waitForReadyElement(WebElement element) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
