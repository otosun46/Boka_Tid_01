/**
 * @Author:Otosun Tarih :09/11/2020
 */
package Runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        tags = {"@SmokeTest"},
        features = {"src/test/java/FeaturesFiles"},
        glue = {"StepDefinations"},
        dryRun = false,
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport/ExtentReportRegression.html"
                // to generate a extend report we need this plugin
        }
)
public class SmokeTest extends AbstractTestNGCucumberTests {

}
