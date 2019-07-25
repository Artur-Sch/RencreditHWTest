package insuranceTest.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ContributionsPage extends BasePage {

    @FindBy(xpath = "//div[@class='calculator__slide-section']")
    List<WebElement> calculatorTextFields;

    @FindBy(xpath = "//span[text()]")
    List<WebElement> spanTextElements;

    @FindBy(xpath = "//span[@class='js-calc-rate']")
    WebElement percentElement;

    @FindBy(xpath = " //span[@class='js-calc-result']")
    WebElement resulValueElement;

    @FindBy(xpath = " //span[@class='js-calc-earned']")
    WebElement earnedValueElement;

    @FindBy(xpath = " //span[@class='js-calc-replenish']")
    WebElement replenishValueElement;


    public void fillTextFields(String nameField, String value) {
        for (WebElement element : calculatorTextFields) {
            waitForReadyElement(element);
            if (element.findElement(By.xpath(".//label[contains(text(),'')]")).getText().equalsIgnoreCase(nameField)) {
                if (element.getAttribute("data-property").equalsIgnoreCase("period")) {
                    new Select(element.findElement(By.xpath(".//select[@class=\"calculator__slide-input js-slide-value\"]"))).selectByValue(value.replaceAll("\\D", ""));
                    return;
                } else {
                    element.findElement(By.xpath(".//input")).sendKeys(value);
                    return;
                }
            }
        }
        takeScreenshot();
        Assert.fail("Не найдено поле - " + nameField);
    }

    public void clickElementSpan(String nameField) {
        for (WebElement element : spanTextElements) {
            if (element.getText().equalsIgnoreCase(nameField)) {
                waitForReadyElement(element).click();
                return;
            }
        }
        takeScreenshot();
        Assert.fail("Не найден елемент - " + nameField);
    }

    public String getFillField(String field) {
        switch (field) {
            case "Ставка":
                return  waitForReadyElement(percentElement).getText();
            case "К снятию":
                return waitForReadyElement(resulValueElement).getText();
            case "Начислено %":
                return waitForReadyElement(earnedValueElement).getText();
            case "Пополнение на":
                return waitForReadyElement(replenishValueElement).getText();
        }
        throw new AssertionError("Поле не объявлено на странице");

    }
}
