package com.akbank;

import com.basepages.SeleniumBasePage;
import com.driver.DriverManager;
import com.utility.Log;;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AkbankHomePage extends SeleniumBasePage {


    //Sayfanın en altına scroll etmek için gerekli.
    @FindBy(xpath = "//a[@href='/kredi-hesaplama']")
    WebElement btnCredit;

    //price gireceğimiz path
    @FindBy(xpath = "//div[@class='textbox']//input[@id='t-credit-price']")
    WebElement inputCreditPrice;

    //sigortasız butonu.
    @FindBy(xpath = "(//div[@class='custom-checkbox rd-insured'])[1]")
    WebElement chooseInsurance;

    //vade azaltma butonu.
    @FindBy(xpath = "(//div[@class='range period']//a[@class='step-btn decrease'])[1]")
    WebElement setCreditVade;

    //hesap detayları butonu.
    @FindBy(xpath = "(//a[@class='details open-tooltip'])[1]")
    WebElement btnAccountDetails;

    //cost modaldaki maliyet masraf box.
    @FindBy(xpath = "//a[@id='accordion2']")
    WebElement btnCostRate;

    //masraf ve ödeme tablarının aktifliğini kontrol etmek için.
    @FindBy(xpath = "(//li[@class='active'])[1]")
    WebElement controlCostRateAndPaymentPlanStatus;

    //cost modaldaki ödeme planı butonu.
    @FindBy(xpath = "//a[@id='accordion2']")
    WebElement btnPaymentPlan;

    //Scroll down on the “Ödeme Planı” page until 20th is seen button.
    @FindBy(xpath = "//div[@class='jspDrag']")
    WebElement sliderPaymentPlan;

    //hemen basvur butonu ekranı ortalamak için
    @FindBy(xpath = "(//a[@class='btn grey mr10 info'])[1]")
    WebElement btnAplly;



    //Burada constructor oluşturduk ; Web element FindyBy ile oluşturulan webelementleri initialize(başlatmak) oluyor.(eşittir yapmamak gibi.)
    public AkbankHomePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }


    /**
     * @param addUrlPlugin : /payment, /account  gibi sayfalara direkt gitmek için kullanılabilir.
     * @return
     */
    public AkbankHomePage navigateToUrl(String addUrlPlugin) {
        DriverManager.getDriver().get("https://www.akbank.com/" + addUrlPlugin);
        Log.pass("Akbank Web sitesi açıldı.");

        return this;
    }

    public AkbankHomePage creditCalculation() throws InterruptedException {
        Thread.sleep(2000);
        scrollToElement(btnCredit, "hasan");
        Log.pass("Footer alanında ; Kredi kartlarıma scroll edildi .(Sayfanın en altına.) ");
        btnCredit.click();
        Log.pass("Kredi kartlarım butonuna tıklandı.");

        return this;
    }


    public AkbankHomePage selectFilters() {

        //Tutar girildiği kısım.
        scrollToElementBlockCenter(inputCreditPrice, "TUTAR BOX.");
        inputCreditPrice.clear();
        Log.pass("Box içerisindeki tutar temizlendi.");
        inputCreditPrice.sendKeys("50000");
        Log.pass(inputCreditPrice + " TL tutar girildi.");

        //sigortasız secilen kısım.
        scrollToElementBlockCenter(chooseInsurance, "Sigorta butonu.");
        chooseInsurance.click();
        Log.pass("Sigortasız butonu seçildi.");

        //vade azaltma alanı burada her bir click -1 ay vade demek.
        for (int i = 0; i <= 3; i++) {
            setCreditVade.click();
            Log.pass(i + " aylık vade azaltıldı.");
        }
        Log.pass("Vade süresi belirlendi.");
        btnAccountDetails.click();
        Log.pass("Hesap detaylarına tıklandı.");
        return this;
    }

    public AkbankHomePage costRateModalContent() {

        scrollToElementBlockCenter(controlCostRateAndPaymentPlanStatus, "Masraf ve Ödeme planı alanı");
        controlElementText(controlCostRateAndPaymentPlanStatus, "Masraf ve Maliyet Oranları", "Beklenen Text ile şu anki uyuşmuyor.");
        Log.pass("Masraf ve maliyet oranları active mi kontrol edildi.");
        btnPaymentPlan.click();
        Log.pass("Ödeme planı tabına tıklandı.");
        controlElementText(controlCostRateAndPaymentPlanStatus, "Ödeme Planı", "Beklenen Text ile şu anki uyuşmuyor.");


        scrollToElementBlockCenter(sliderPaymentPlan, "Masraf ve Ödeme planındaki slider.");
        Actions action = new Actions(driver);
        action.dragAndDropBy(sliderPaymentPlan, 0, 240).build().perform();
        scrollToElementBlockCenter(btnAplly, "Hemen basvur butonu.");

        return this;
    }

}
