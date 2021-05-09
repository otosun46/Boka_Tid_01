/**
 * @Author:Otosun Tarih :07/05/2021
 */
package StepDefinations;

import Pages.RandevuContent;
import Utilities.Driver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Randevu_Stepdefs {
    WebDriver driver;
    RandevuContent randevuContent= new RandevuContent();

    @Given("^Randevu sayfasina git$")
    public void randevuSayfasinaGit() {
        driver = Driver.getDriver();
        driver.get("https://www.migrationsverket.se/Kontakta-oss/Boka-tid-innan-du-besoker-oss.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Then("^Resedokument almayi sec$")
    public void resedokumentAlmayiSec() {
        randevuContent.findElementAndClickFunction("hamtaResedok");
        randevuContent.beklet(500);
    }

    @And("^Nereden alacagini sec$")
    public void neredenAlacaginiSec() {
        randevuContent.findElementAndClickFunction("enhet");
        randevuContent.findElementAndClickFunction("sundybery");
    }

    @Then("^Kac kisilik randevu alacagini sec$")
    public void kacKisilikRandevuAlacaginiSec() {
        randevuContent.findElementAndClickFunction("sokande");
        randevuContent.findElementAndClickFunction("birKisi");
    }

    @And("^Sartlari kabul et ve devam et$")
    public void sartlariKabulEtVeDevamEt() {
        randevuContent.findElementAndClickFunction("sartKabul");
        randevuContent.findElementAndClickFunction("devamEt");
    }

    @And("^Randevu icin zaman var mi$")
    public void randevuIcinZamanVarMi() {
        String mesaj=randevuContent.findWebElement("errorText").getText();
        String text = "Det finns inga mer lediga tider för närvarande.";
//        randevuContent.findElementAndVerifyContainsText("errorText",text);
       // System.out.println(mesaj);
        if (mesaj.toLowerCase().contains(text.toLowerCase())){
            randevuContent.beklet(3000);
            Driver.quitDriver();
        }
        else System.out.println("Bos zaman var, elini cabuk tut");
    }
}
