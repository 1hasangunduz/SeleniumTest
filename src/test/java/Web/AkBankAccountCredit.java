package Web;

import com.akbank.AkbankHomePage;
import com.basepages.BaseTest;
import org.testng.annotations.Test;

public class AkBankAccountCredit extends BaseTest {

    @Test
    public void Control_Account_Function() throws InterruptedException {

        new AkbankHomePage()
                .navigateToUrl("")
                .creditCalculation()
                .selectFilters()
                .costRateModalContent()

        ;

    }


}
