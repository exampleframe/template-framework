package project.template.pages;

import org.openqa.selenium.support.FindBy;
import project.template.elements.Button;
import project.template.elements.CompositeElement;
import project.template.elements.Label;
import project.template.factories.PageEntry;
import project.template.factories.PageLoadElement;

@PageEntry(title = "Результаты поиска",
        containsPages = {HeaderPage.class, MenuCatalogPage.class})
public class ResultSearchPage extends AbstractPage {

    @PageLoadElement
    @FindBy(name = "Заголовок",
            xpath = "//h1")
    public Label label1;

    @FindBy(name = "Результаты",
            xpath = "//div[@class='product has-avails']")
    public CompositeElement compositeElement1;

    @FindBy(name = "Купить",
            xpath = ".//div[@class='price-buttons-catalog']")
    public Button button1;

}
