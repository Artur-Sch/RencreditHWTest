package insuranceTest.pages;

import insuranceTest.core.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RenessansMainPage extends BasePage {

    @FindBy(xpath = "//div[@class='services services_main']//div[text()]")
    List<WebElement> mainMenuServices;

    public void selectService(String nameService) {
        for (WebElement service : mainMenuServices) {
            if (service.getText().equalsIgnoreCase(nameService)) {
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.elementToBeClickable(service))
                        .click();
                return;
            }
        }
        Assert.fail("Не найден пункт меню - " + nameService);
    }


}

