package project.template.stepdefinition;

import cucumber.api.PendingException;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;

public class CommonStepDefs {


    @Когда("^пользователь переходит на страницу авторизации$")
    public void пользовательПереходитНаСтраницуАвторизации() {

    }

    @И("^открывается \"([^\"]*)\"$")
    public void открывается(String arg0){
        System.out.println();

    }
}