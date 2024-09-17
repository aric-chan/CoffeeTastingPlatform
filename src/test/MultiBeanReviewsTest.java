import model.BeanReview;
import model.BrewNote;
import model.MultiBeanReviews;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiBeanReviewsTest {
    private MultiBeanReviews multiBRTest;
    private MultiBeanReviews multiBRTestEmpty;
    private BeanReview br1;
    private BeanReview br2;
    private BrewNote bnote1;
    private BrewNote bnote2;
    private BrewNote bnote3;

    @BeforeEach
    void runBefore() {
        multiBRTest = new MultiBeanReviews();
        multiBRTestEmpty = new MultiBeanReviews();
        br1 = new BeanReview(1);
        br2 = new BeanReview(2);

        bnote1 = new BrewNote(1,"Johnny",7,6,5,4,3,8,"");
        bnote2 = new BrewNote(1,"Jessica",6,4,3,4,3,6,"too sour!");
        bnote3 = new BrewNote(2,"Eric",9,6,5,6,3,7,"balanced");

        br1.addBrewNote(bnote1);
        br1.addBrewNote(bnote2);
        br2.addBrewNote(bnote3);

        multiBRTest.addBeanReview(br1);
        multiBRTest.addBeanReview(br2);
    }

    @Test
    void testremoveBeanReview() {
        assertEquals(2,multiBRTest.getBeanReviewList().size());
        assertEquals(br1,multiBRTest.getBeanReviewList().get(0));
        assertEquals(br2,multiBRTest.getBeanReviewList().get(1));

        multiBRTest.removeBeanReview(multiBRTest.getBeanReviewList());
        assertEquals(1,multiBRTest.getBeanReviewList().size());
        assertEquals(br1,multiBRTest.getBeanReviewList().get(0));
    }

    @Test
    void testprintBeanReviewAllEmpty() {
        String output = "";
        output = multiBRTest.printBeanReviewAll(multiBRTestEmpty.getBeanReviewList());
        assertEquals(output,"<html>No bean review found!</html>");
    }

    @Test
    void testprintBeanReviewAll() {
        String output = "";
        output = multiBRTest.printBeanReviewAll(multiBRTest.getBeanReviewList());
        System.out.println(output);
        assertEquals(output,"<html><br/>Bean Review for Bean ID : 1<br/>Taster : Johnny<br/>Aroma : 7<br/>Acidity : 6<br/>Sweet : 5<br/>Body : 4<br/>Finish : 3<br/>Overall : 8<br/>Special Notes :<br/>Taster : Jessica<br/>Aroma : 6<br/>Acidity : 4<br/>Sweet : 3<br/>Body : 4<br/>Finish : 3<br/>Overall : 6<br/>Special Notes :too sour!<br/><br/>Bean Review for Bean ID : 2<br/>Taster : Eric<br/>Aroma : 9<br/>Acidity : 6<br/>Sweet : 5<br/>Body : 6<br/>Finish : 3<br/>Overall : 7<br/>Special Notes :balanced<br/></html>");
    }

    @Test
    void testprintBeanReviewAverageEmpty() {
        String output = "";
        output = multiBRTest.printBeanReviewAverage(multiBRTestEmpty.getBeanReviewList());
        assertEquals(output,"<html>No bean review found!</html>");
    }

    @Test
    void testprintBeanReviewAverage() {
        String output = "";
        output = multiBRTest.printBeanReviewAverage(multiBRTest.getBeanReviewList());
        assertEquals(output,"<html><br/>Bean Review for Bean ID : 1<br/>Overall score : {Overall=7.0, Sweet=4.0, Acidity=5.0, Aroma=6.5, Finish=3.0, Body=4.0}<br/>Special Notes by Johnny : <br/>Special Notes by Jessica : too sour!<br/><br/>Bean Review for Bean ID : 2<br/>Overall score : {Overall=7.0, Sweet=5.0, Acidity=6.0, Aroma=9.0, Finish=3.0, Body=6.0}<br/>Special Notes by Eric : balanced<br/></html>");
    }
}
