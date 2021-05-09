
package Pages;

import Utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class _Parent {

    WebDriver driver;
    WebDriverWait wait;
    WebElement myElement;
    List<WebElement> myElementList = new ArrayList<>();


    public _Parent() {
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void clickFunction(WebElement element) {
        waitUntilClickable(element);// eleman clikable olana kadar bekle
//        scrollToElement(element); // eleman kadar scroll yap
        element.click();// click yap
    }

    public void sendKeysFunction(WebElement element, String value) {
        waitUntilVisible(element);// elelman görünüt olana kadar bekle
        scrollToElement(element);// elemana kadar scroll yap
        element.clear();// eleman clear yap
        element.sendKeys(value);// value yi gönder
    }

    public void countrySelect(WebElement element, String country) {

        Select menu = new Select(element);
        menu.selectByVisibleText(country);
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilToBeSelected(WebElement element) {
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void verifyElementContainsText(WebElement element, String text) {
        // waitUntilVisible(element);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
          // System.out.println(element.getText());
           Assert.assertTrue(element.getText().toLowerCase().contains(text.toLowerCase()));
    }

    public List<WebElement> waitVisibleListAllElement(List<WebElement> elementList) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
        return elementList;
    }

    public void beklet(int milisaniye) {
        try {
            Thread.sleep(milisaniye);
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    public boolean girdiSayiMi(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isValueColor(String str) {
        str = str.replaceAll(" ", "");
        return str.matches("rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)");
    }
    //color: white; background-color: rgb(255, 23, 68);

    public void selectOptionByString(List<WebElement> elementList, String str) {
        str = str.trim();
        if (girdiSayiMi(str)) {
            if (Integer.parseInt(str) < elementList.size()) {
                clickFunction(selectOptions(elementList, Integer.parseInt(str)));
            }
        } else if (isValueColor(str)) {
            clickFunction(selectOptionColor(elementList, str));
        } else {
            clickFunction(selectOptions(elementList, str));
        }
    }

    public WebElement selectOptions(List<WebElement> elementList, String value) {
        WebElement elementSelected = null;
        for (WebElement element : elementList) {
            if (element.getText().toLowerCase().contains(value.toLowerCase())) {
                elementSelected = element;
                break;
            }
        }
        return elementSelected;
    }

    public WebElement selectOptionColor(List<WebElement> elementList, String value) {
        value = value.replaceAll(" ", "");
        WebElement elementSelected = null;
        for (WebElement element : elementList) {
            String style = element.getAttribute("style");
            System.out.println(style);
            style = style.replaceAll(" ", "");
            if (style.contains(value.toLowerCase())) {
                elementSelected = element;
                break;
            }
        }
        return elementSelected;
    }

    public WebElement selectOptions(List<WebElement> elementList, int index) {
        return elementList.get(index);
    }

    public void verifyMyElementIsDisplayed(WebElement element) {
        Set<String> sayfaidleri = driver.getWindowHandles();
        String anasayfaidsi = driver.getWindowHandle();
        for (String s : sayfaidleri) {
            if (!s.equals(anasayfaidsi))
                driver.switchTo().window(s);
        }
        Assert.assertTrue(element.isDisplayed(), "WebElement bulunamadi.");
        driver.switchTo().window(anasayfaidsi);
    }

    public void isMyTextDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "WebElement bulunamadi.");
    }

    public void verifyTheNumberOfItemsOnTheList(List<WebElement> elementList, int numberOfItems) {
        System.out.println("Number of items= " + elementList.size());
        Assert.assertTrue(elementList.size() == numberOfItems);
    }

    public void printToList(List<WebElement> webElmList) {
        for (WebElement e : webElmList) {
            System.out.println(e.getText());
            System.out.println("------------------------------------------------------------");
        }
    }

    public void pagesClosed() {

        Set<String> sayfaidleri = driver.getWindowHandles();
        String anasayfaidsi = driver.getWindowHandle();
        for (String s : sayfaidleri) {
            System.out.println(s);
            if (!s.equals(anasayfaidsi))
                driver.switchTo().window(s);
        }
        driver.switchTo().window(anasayfaidsi);
    }
}
