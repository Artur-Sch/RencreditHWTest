package insuranceTest.steps;


import insuranceTest.core.Init;
import insuranceTest.pages.ContributionsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContributionsSteps {

    ContributionsPage contributionsPage = new ContributionsPage();

    @Step("Заполнение поля {0} значением {1}")
    public void fillFormTextField(String fieldNAme, String value) {
        contributionsPage.fillTextFields(fieldNAme, value);
    }

    @Step("Выбор элемента - {0}")
    public void clickCheckbox(String checkboxName) {
        if (!checkboxName.equalsIgnoreCase("none")) {
            contributionsPage.clickElementSpan(checkboxName);
        }
    }

    @Step("поле {0} заполнено значением {1}")
    public void checkFillField(String field, String value) {
        new WebDriverWait(Init.getDriver(), 30)
                .withMessage(String.format("Значение поля [%s] равно [%s]. Ожидалось - [%s]", field, contributionsPage.getFillField(field), value))
                .until((ExpectedCondition<Boolean>) driver -> contributionsPage.getFillField(field).equals(value));
    }
}
