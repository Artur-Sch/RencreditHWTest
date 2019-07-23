package insuranceTest.steps;


import insuranceTest.pages.ContributionsPage;
import io.qameta.allure.Step;

import static org.junit.Assert.assertTrue;

public class ContributionsSteps {

    ContributionsPage contributionsPage = new ContributionsPage();


    @Step("Заполнение поля {0} значением {1}")
    public void fillFormTextField(String fieldNAme, String value) {
        contributionsPage.fillTextFields(fieldNAme, value);
    }

    @Step("Выбор элемента - {0}")
    public void clickCheckbox(String checkboxName) {
        contributionsPage.clickElementSpan(checkboxName);
    }

    @Step("поле {0} заполнено значением {1}")
    public void checkFillField(String field, String value){
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String actual = contributionsPage.getFillField(field);
        assertTrue(String.format("Значение поля [%s] равно [%s]. Ожидалось - [%s]", field, actual, value),
                actual.equals(value));


    }
}
