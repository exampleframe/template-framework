package project.template.elements;

import com.codeborne.selenide.SelenideElement;

public class Label extends AbstractElement {
    public Label(SelenideElement element, String name) {
        super(element, name);
    }

    @Override
    public String getText() {
        return element.getText();
    }
}
