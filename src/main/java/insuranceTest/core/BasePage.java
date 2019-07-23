package insuranceTest.core;

import insuranceTest.annotations.ElementTitle;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;

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


    @Attachment("Screenshot")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @Step("Поиск элемента по аннотации - {0}")
    public WebElement getElementByTitle(String title) {
        Field field = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(f -> f.getType().equals(WebElement.class))
                .filter(f -> f.getAnnotation(ElementTitle.class) != null)
                .filter(f -> f.getAnnotation(ElementTitle.class).value().equalsIgnoreCase(title))
                .findFirst().orElseThrow(()->new RuntimeException("Не найден элемент с названием "+ title));
        Assert.assertEquals(field.getType(), WebElement.class);
        field.setAccessible(true);
        WebElement element= null;
        try {
            element = (WebElement) field.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return waitForReadyElement(element);
    }
}
