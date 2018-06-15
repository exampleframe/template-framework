package project.template.pages;

import com.codeborne.selenide.SelenideElement;
import project.template.elements.AbstractElement;
import project.template.elements.CompositeElement;
import project.template.factories.FieldFactory;
import java.util.List;

public class AbstractPage {

    public Boolean isOpened() {
        AbstractElement element = FieldFactory.getPageLoadElement(this.getClass());
        return element.isDisplayed();
    }
    public AbstractElement getElement(String name){
        return FieldFactory.getElement(name,this.getClass());
    }

    public List<AbstractElement> getCollection(String arg0) {

        return FieldFactory.getElements(arg0,this.getClass());
    }

    public AbstractElement getElement(String elName, AbstractElement parentEl) {
AbstractElement element = FieldFactory.getSubElement(elName, parentEl,this.getClass());
        if(element!=null)return element;
        else return FieldFactory.generator(elName,parentEl);
    }
}
