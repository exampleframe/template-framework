package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "project.template.stepdefinition",
        tags = "@TEST",
        snippets = SnippetType.CAMELCASE
)

public class RunnerTest {
}