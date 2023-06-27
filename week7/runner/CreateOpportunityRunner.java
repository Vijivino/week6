package week7.runner;

import io.cucumber.testng.CucumberOptions;
import week7.steps.Baseclass;


@CucumberOptions(features="src/test/java/week7/feature/CreateOpportunity.feature",
                           glue="week7/steps",
                           monochrome=true)
public class CreateOpportunityRunner extends Baseclass{

}
