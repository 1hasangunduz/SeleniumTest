package com.basepages;

import com.driver.DriverManager;
import com.utility.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumBasePage {

    //Scroll'un calısması için gerekli.
    public WebDriver driver = DriverManager.getDriver();


    //burası aslında kullanılmalı js direkt tıklıyor çünkü.
    public void setSlider(WebElement element) {
        Actions at = new Actions(driver);
        at.dragAndDropBy(element, -80, 120);

    }


    public void switchToWindow() {
        for (String handle : DriverManager.getDriver().getWindowHandles()) {
            DriverManager.getDriver().switchTo().window(handle);
        }
    }

    public void scrollToElementBlockCenter(WebElement element, String whereToScroll) {

        //Elementi merkez alarak scroll eder.
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            Log.pass("Scroll işleminin başarıyla gerçekleştiği obje :  " + whereToScroll);
        } catch (Exception e) {
            Log.fail("Error while scrolling to the element : ", e);
        }

    }

    public String getTextOfElement(WebElement elem) {

        String text = null;
        try {
            text = elem.getText();
            Log.pass(text);
        } catch (Exception exception) {
            Log.fail("Error while getting text of element: ", exception);
        }
        return text;
    }

    public void controlElementText(WebElement elem, String onTrue, String onFalse) {

        try {
            if (!getTextOfElement(elem).contains(onTrue)) {
                Log.fail(onFalse);
            }
        } catch (Exception e) {
            Log.fail(onFalse, e);
        }
    }

    public void scrollToElement(WebElement element, String whereToScroll) {

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            Log.pass("Objeye başarıyla scroll edildi : " + whereToScroll);
        } catch (Exception e) {
            Log.fail("Error while scrolling to the element : ", e);
        }

    }

    public void scrollToElement(WebElement element) {
        scrollToElement(element, element.getText());
    }

}
