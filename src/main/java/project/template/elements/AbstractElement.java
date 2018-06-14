package project.template.elements;

import com.codeborne.selenide.SelenideElement;

public abstract class AbstractElement {

private final SelenideElement element;
private final String name;

    public AbstractElement(SelenideElement element, String name) {
        this.element = element;
        this.name = name;
    }

    public void click(){
        element.click();
    }

    public String getName(){
        return element.innerText();
    }

    public void setValue(){
        System.out.println("Должен быть переопределен");
    }
}
