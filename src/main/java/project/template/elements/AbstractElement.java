package project.template.elements;

import com.codeborne.selenide.SelenideElement;
import project.template.factories.Elements;

@Elements
public abstract class AbstractElement {

public final SelenideElement element;
private final String name;

    AbstractElement(SelenideElement element, String name) {
        this.element = element;
        this.name = name;
    }

    public Boolean isDisplayed() {
        return element.isDisplayed();
    }

    public void click(){
        element.click();
    }

    public String getName(){
        return element.innerText();
    }

    public void setValue(String value){
        System.out.println("Должен быть переопределен");
    }

    public String getText(){
        return element.innerText();
    }
}
