package project.template.pages;

import org.openqa.selenium.support.FindBy;
import project.template.elements.Label;
import project.template.factories.PageEntry;
import project.template.factories.PageLoadElement;

@PageEntry(title = "Начальная страница",
containsPages = {HeaderPage.class, MenuCatalogPage.class})

public class StartPage extends AbstractPage {

    @PageLoadElement
    @FindBy(name = "Заголовок",
        xpath = "//h2")
    public Label title;
}
