package project.template.stepdefinition;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.PendingException;
import cucumber.api.java.ru.И;
import project.template.elements.AbstractElement;
import project.template.elements.CompositeElement;
import project.template.pages.AbstractPage;
import project.template.utils.Init;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CommonStepDefs {

    private AbstractPage getCurrentPage() {
        return Init.getPageFactory().getCurrentPage();
    }

    @И("^открывается \"([^\"]*)\"$")
    public void открывается(String arg0) {
        AbstractPage page = Init.getPageFactory().getPage(arg0);

        if (!page.isOpened())
            throw new Error();

    }

    @И("^пользователь заполняет поле \"([^\"]*)\" значением \"([^\"]*)\"$")
    public void пользовательЗаполняетПолеЗначением(String arg0, String arg1) {
        AbstractPage page = getCurrentPage();

        page.getElement(arg0).setValue(arg1);
    }

    @И("^пользователь нажимает \"([^\"]*)\"$")
    public void пользовательНажимает(String arg0) {
        AbstractPage page = getCurrentPage();

        page.getElement(arg0).click();
    }

    @И("^стоп$")
    public void стоп() {
        System.out.println();
    }

    @И("^пользователь видит, что коллекция \"([^\"]*)\" содержит \"([^\"]*)\" элементов$")
    public void пользовательВидитЧтоКоллекцияСодержитЭлементов(String arg0, String arg1) {
        if (getCurrentPage().getCollection(arg0).size() != Integer.parseInt(arg1))
            throw new Error("?????");
    }

    @И("^пользователь нажимает кнопку \"([^\"]*)\" у элемента коллекции \"([^\"]*)\", содержащего \"([^\"]*)\"$")
    public void пользовательНажимаетКнопкуУЭлементаКоллекцииСодержащего(String subElName, String collectionName, String findedText) {
        List<AbstractElement> result = getCurrentPage().getCollection(collectionName);
        AbstractElement element = getCurrentPage().getElement(subElName, getElByContainsText(findedText,result));
        element.click();
    }

    private AbstractElement getElByContainsText(String text,List<AbstractElement> list){
        return list.stream().filter(e->e.getText().contains(text)).findFirst().get();
    }
}