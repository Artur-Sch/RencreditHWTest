package insuranceTest.steps;

import cucumber.api.java.After;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;
import insuranceTest.core.Init;
import io.cucumber.datatable.DataTable;

public class ScenarioRenessans {

    ContributionsSteps contributionsSteps;
    RenessansMainSteps renessansMainSteps;

    @Когда("открывается страница Ренессанс Кредит")
    public void openPage() {
        Init.setUp();
        contributionsSteps = new ContributionsSteps();
        renessansMainSteps = new RenessansMainSteps();
    }

    @Когда("Перейти в меню – \"(.+)\"$")
    public void openMenu(String nameMenu) {
        renessansMainSteps.selectMenu(nameMenu);
    }

    @Когда("(?:Выбрать валюту|Отметить) – \"(.+)\"$")
    public void currencySelection(String value) {
        contributionsSteps.clickCheckbox(value);
    }

    @Когда("Ввести в поле \"(.+)\" – \"(.+)\"$")
    public void fillAmount(String nameField, String value) {
        contributionsSteps.fillFormTextField(nameField,value);
    }

    @Когда("Выбрать – \"(.+)\" и \"(.+)\"")
    public void clickTwoCheckbox(String nameFirstBox, String nameSecondBox) {
        contributionsSteps.clickCheckbox(nameFirstBox);
        contributionsSteps.clickCheckbox(nameSecondBox);
    }

    @То("Проверить расчеты по вкладу:$")
    public void checkCalculations(DataTable fields) {
        fields.asMap(String.class, String.class)
                .forEach((field, value) -> contributionsSteps.checkFillField(String.valueOf(field),String.valueOf(value)));
    }

    @After
    public void exit() {
        Init.tearDown();
    }


}
