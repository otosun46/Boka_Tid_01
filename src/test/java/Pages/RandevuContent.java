/*
 * @Author:Group_2 Tarih :03/12/2020
 */
package Pages;

import cucumber.api.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RandevuContent extends _Parent {
    /********************** WEBELEMENT *************************/

    @FindBy(xpath = "//input[@id='hamta_frampass_resedok']")
    private WebElement hamtaResedok;

    @FindBy(xpath = "//select[@id='enhet']")
    private WebElement enhet;

    @FindBy(xpath = "//select[@id='enhet']//option[@value='14']")
    private WebElement sundybery;

    @FindBy(xpath = "//select[@id='sokande']")
    private WebElement sokande;

    @FindBy(xpath = "//select[@id='sokande']//option[@value='0']")
    private WebElement birKisi;

    @FindBy(xpath = "//input[@id='godkannId1']")
    private WebElement sartKabul;

    @FindBy(xpath = "//input[@id='bokaSubmit']")
    private WebElement devamEt;

    @FindBy(xpath = "//div[@role='status']//span")
    private WebElement errorText;

    @FindBy(css = "mat-select[formcontrolname='type']")
    private WebElement type;




    /********************** WEBELEMENTLIST *************************/
    @FindAll({
            @FindBy(xpath = "//div[@id='toast-container']")
    })
    private List<WebElement> msjContainers;

    @FindAll({
            @FindBy(xpath = "//span[@class='mat-option-text']")
    })
    private List<WebElement> userTypeAllOptions;

    @FindAll({
            @FindBy(xpath = "//ms-delete-button/button")
    })
    private List<WebElement> deleteButtonList;

    @FindAll({
            @FindBy(xpath = "//ms-edit-button/button")
    })
    private List<WebElement> editButtonList;

    @FindAll({
            @FindBy(linkText = "Got it!")
    })
    public List<WebElement> gotItBtns;

    @FindAll({
            @FindBy(css = "mat-option[role='option']")
    })
    private List<WebElement> option;

    @FindAll({
            @FindBy(xpath = "//table/tbody/tr/td[2]")
    })
    private List<WebElement> nameList;

    @FindAll({
            @FindBy(xpath = "//mat-month-view//tr[@role='row']//td[@role='gridcell']")
    })
    private List<WebElement> dayOfMonth;

    /********************** METHODS *************************/
    /**
     * Finds and returns the WebElement whose name is entered as a parameter.
     *
     * @param webElementName
     * @return
     */
    public WebElement findWebElement(String webElementName) {
        switch (webElementName) {
            case "hamtaResedok":
                myElement = hamtaResedok;
                break;
            case "enhet":
                myElement = enhet;
                break;
            case "sundybery":
                myElement = sundybery;
                break;
            case "sokande":
                myElement = sokande;
                break;
            case "birKisi":
                myElement = birKisi;
                break;
            case "sartKabul":
                myElement = sartKabul;
                break;
            case "devamEt":
                myElement = devamEt;
                break;
            case "errorText":
                myElement = errorText;
                break;
        }
        return myElement;
    }

    /**
     * Returns the WebElementList whose name is entered as a parameter.
     *
     * @param webElementListName
     * @return
     */
    public List<WebElement> findWebElementList(String webElementListName) {
        switch (webElementListName) {
            case "userTypeAllOptions":
                myElementList = userTypeAllOptions;
                break;
            case "deleteButtonList":
                myElementList = deleteButtonList;
                break;
            case "editButtonList":
                myElementList = editButtonList;
                break;
            case "nameList":
                myElementList = nameList;
                break;
            case "option":
                myElementList = option;
                break;
            case "dayOfMonth":
                myElementList = dayOfMonth;
                break;
        }
        return myElementList;
    }

    /**
     * This method takes the name of the WebElement to be executed from the steps class,
     * whose parameter will be clicked as a string.
     *
     * @param element
     */
    public void findElementAndClickFunction(String element) {
        clickFunction(findWebElement(element));
    }

    /**
     * Finds the WebElement named as parameter and sends the value parameter to the selected WebElement.
     *
     * @param element
     * @param value
     */
    public void findElementAndSendKeysFunction(String element, String value) {
        scrollToElement(findWebElement(element));
        sendKeysFunction(findWebElement(element), value);
    }

    /**
     * This method is called from the steps class.
     * It takes the name of the WebElementList with options as parameters and the selection criteria as a string.
     * If the selection parameter is a number, it is evaluated as an index and the WebElement with that index is selected.
     * If the parameter is not a number, it is compared with the text of the WebElement and the enclosing WebElement is selected.
     *
     * @param listName
     * @param secenek
     */
    public void findElementAndSelectOption(String listName, String secenek) {
        selectOptionByString(findWebElementList(listName), secenek);
    }

    /**
     * Finds the WebElement named as parameter and verifies that it appears.
     *
     * @param elementName
     */
    public void findElementAndVerifyDisplayed(String elementName) {
        verifyMyElementIsDisplayed(findWebElement(elementName));
    }

    /**
     * Finds the WebElement whose name is sent as parameter and
     * verifies that msg entered as parameter exists in the text of the WebElement.
     *
     * @param elementName
     * @param msg
     */
    public void findElementAndVerifyContainsText(String elementName, String msg) {
        verifyElementContainsText(findWebElement(elementName), msg);
    }

    public void editAndDeleteFunction(String countryName, String editOrDelete) {
       // waitUntilClickable(searchButton);
        List<WebElement> btnList;
        if (editOrDelete.equalsIgnoreCase("delete")) {
            btnList = deleteButtonList;
        } else btnList = editButtonList;

        for (int i = 0; i < nameList.size(); i++) {
            System.out.println(nameList.get(i).getText());
            if (nameList.get(i).getText().equalsIgnoreCase(countryName)) {
                clickFunction(btnList.get(i));
            }
        }
    }

    public void switchToFrame() {
        driver.switchTo().parentFrame();
    }
    //metin
    public void switchToFrame(String frame) {
        driver.switchTo().frame(findWebElement(frame));
    }


    public void invisibleElementClick(String element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", findWebElement(element));
    }

    public void usingElementsInTheDataTableAndSendKeys(DataTable elements) {
        List<List<String>> elementsNameAndValue = elements.asLists(String.class);
        for (int i = 0; i < elementsNameAndValue.size(); i++) {
            findElementAndSendKeysFunction(elementsNameAndValue.get(i).get(0), elementsNameAndValue.get(i).get(1));
        }
    }

    public void usingElementsInTheDataTableAndSelectOptions(DataTable elements, String listName) {
        List<String> elementsName = elements.asList(String.class);
        for (int i = 0; i < elementsName.size(); i++) {
            findElementAndClickFunction(elementsName.get(i));
            i++;
            findElementAndSelectOption(listName, elementsName.get(i));
        }
    }
    public void writeInPElements( ){
        scrollToElement(findWebElement("emailBody"));

        System.out.println("meto");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].textContent = arguments[1];", findWebElement("emailBody"), "This is a test");
    }

    public void writeInPElements(DataTable elements){
        List<List<String>> elementsNameAndValue = elements.asLists(String.class);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        for (int i = 0; i < elementsNameAndValue.size(); i++) {
            scrollToElement(findWebElement(elementsNameAndValue.get(i).get(0)));
            executor.executeScript("arguments[0].textContent = arguments[1];", findWebElement(elementsNameAndValue.get(i).get(0)), elementsNameAndValue.get(i).get(1));
        }}
}
