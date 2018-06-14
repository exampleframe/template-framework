package project.template.pages;

import org.openqa.selenium.support.FindBy;
import project.template.elements.Button;
import project.template.elements.TextField;
import project.template.factories.PageEntry;

@PageEntry(title = "Главная страница")
public class MainPage extends AbstractPage {

    @FindBy(name = "Строка поиска",
            xpath = "//nav[@id='header-search']//input/parent::div[@class='input-group']")
    public TextField field1;

    @FindBy(name = "Кнопка поиска",
            xpath = "//nav[@id=\"header-search\"]//button//ancestor::div[@class='input-group']")
    public Button button1;
}
