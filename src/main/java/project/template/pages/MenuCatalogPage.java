package project.template.pages;

import org.openqa.selenium.support.FindBy;
import project.template.elements.Link;
import project.template.factories.PageEntry;

@PageEntry(title = "Боковое меню")
public class MenuCatalogPage extends AbstractPage {

    @FindBy(name = "Ноутбуки и планшеты",
        xpath = "//span[text() = 'Ноутбуки и планшеты']/ancestor::li[@style]")
    public Link link1;
}
