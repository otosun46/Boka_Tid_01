package StepDefinations;

import Utilities.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Hooks {
String simdi= LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss"));

    @Before
    public void before(Scenario scenario){
//        System.out.println("Seneryo basladi");
//        System.out.println("Seneryonun id si: "+scenario.getId());
        System.out.println("Senaryo: "+scenario.getName());
    }

    @After
    public void after(Scenario scenario){
//        System.out.println("Seneryo bitti");
//        System.out.println("Seneryonun id si: "+scenario.getId());
        System.out.println("Sonuc  : "+scenario.getStatus());
        System.out.println("============================================================");
        String schenarioName=scenario.getName();
        if (scenario.getStatus()=="failed"){
            TakesScreenshot ts=(TakesScreenshot) Driver.getDriver();
            File ekranDosyasi=ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(ekranDosyasi,new File("target/FailedScrenShots/"+Driver.threadBrowserName.get()+schenarioName+simdi+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       // ExcelUtility.exceleYaz(scenario,simdi, Driver.threadBrowserName.get());
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Driver.quitDriver();
    }
}
