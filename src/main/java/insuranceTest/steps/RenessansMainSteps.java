package insuranceTest.steps;

import insuranceTest.pages.RenessansMainPage;
import io.qameta.allure.Step;

public class RenessansMainSteps {

    RenessansMainPage renessansMainPage = new RenessansMainPage();

    @Step("Выбор меню - {0}")
    public void selectMenu(String nameMenu) {
        renessansMainPage.selectService(nameMenu);
    }


}
