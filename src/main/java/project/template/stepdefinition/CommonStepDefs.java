package project.template.stepdefinition;

import cucumber.api.DataTable;
import cucumber.api.java.ru.И;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.template.elements.AbstractElement;
import project.template.pages.AbstractPage;
import project.template.utils.Init;

import java.util.List;
import java.util.Map;

import static project.template.utils.Evaluator.*;


public class CommonStepDefs {
    private static final Logger LOG = LoggerFactory.getLogger(CommonStepDefs.class);
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
        AbstractElement element = getCurrentPage().getElement(subElName, getElByContainsText(getVariable(findedText),result));
        element.click();
    }

    private AbstractElement getElByContainsText(String text,List<AbstractElement> list){
        return list.stream().filter(e->e.getText().contains(text)).findFirst().get();
    }

    @И("^пользователь устанавливает переменную$")
    public void пользовательУстанавливаетПеременную(DataTable dataTable) {
        LOG.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Map<String,String> map = dataTable.asMap(String.class,String.class);
        for(String key:map.keySet()){
            setVariable(key,map.get(key));
        }
    }
}