import model.Beans;
import model.MultiPurchases;
import model.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiPurchasesTest {
    private MultiPurchases multiPurchases;
    private MultiPurchases multiPurchasesEmpty;
    private Purchase p1;
    private Purchase p2;
    private Beans bean1;
    private Beans bean2;

    @BeforeEach
    void runBefore() {
        multiPurchases = new MultiPurchases();
        multiPurchasesEmpty = new MultiPurchases();
        p1 = new Purchase("Samuel");
        p2 = new Purchase("Tom");
        bean1 = new Beans(1, "Kaffa","Ethiopia", "Washed", 2023,02,06);
        bean2 = new Beans(2, "April Coffee Roasters","Costa Rica", "Honey", 2023,11,27);
        p1.addBean(bean1);
        p2.addBean(bean2);
        multiPurchases.addPurchase(p1);
        multiPurchases.addPurchase(p2);

    }

    @Test
    void testprintPurchase() {
        String output = "";
        output = multiPurchases.printPurchase(multiPurchases.getPurchaseList());
        assertEquals(output,"<html><br/>Purchase log for Samuel<br/>ID:1<br/>roaster:1<br/>origin:Ethiopia<br/>process:Washed<br/>origin:Ethiopia<br/>Roast date:2023-2-6<br/><br/>Purchase log for Tom<br/>ID:2<br/>roaster:2<br/>origin:Costa Rica<br/>process:Honey<br/>origin:Costa Rica<br/>Roast date:2023-11-27<br/></html>");
    }

    @Test
    void testprintPurchaseEmpty() {
        String output = "";
        output = multiPurchasesEmpty.printPurchase(multiPurchasesEmpty.getPurchaseList());
        assertEquals(output,"<html>No purchase record found!</html>");
    }


}
