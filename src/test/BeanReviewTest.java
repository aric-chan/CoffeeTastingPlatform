import model.BeanReview;
import model.BrewNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BeanReviewTest {
    private BeanReview br1;
    private BeanReview br2;
    private BrewNote bnote1;
    private BrewNote bnote2;
    private BrewNote bnote3;
    private Map<String,Double> testaverageReviewMap;

    @BeforeEach
    void runBefore() {
        br1 = new BeanReview(1);

        bnote1 = new BrewNote(1,"Johnny",7,6,5,4,3,8,"");
        bnote2 = new BrewNote(1,"Jessica",6,4,3,4,3,6,"too sour!");
        bnote3 = new BrewNote(2,"Eric",9,6,5,6,3,7,"balanced");
    }

    @Test
    void testConstructor() {
        assertEquals(1,br1.getBeanID());
    }

    @Test
    void testEmptyAddBrewNote() {
        assertEquals(0,br1.getBrewNoteList().size());
    }

    @Test
    void testAddBrewNote() {
        br1.addBrewNote(bnote1);
        assertEquals(1,br1.getBrewNoteList().size());
        br1.addBrewNote(bnote2);
        assertEquals(2,br1.getBrewNoteList().size());
    }

    @Test
    void testGetOverallBrewNote() {
        br1.addBrewNote(bnote1);
        br1.addBrewNote(bnote2);
        testaverageReviewMap = br1.getAverageReview(br1.getBrewNoteList());
        assertEquals(7,testaverageReviewMap.get("Overall"));
    }

    @Test
    void testSingleGetOverallBrewNote() {
        br1.addBrewNote(bnote1);
        testaverageReviewMap = br1.getAverageReview(br1.getBrewNoteList());
        assertEquals(8,testaverageReviewMap.get("Overall"));
    }

    @Test
    void testRemoveLastBrewNote() {
        br1.addBrewNote(bnote1);
        assertEquals(1,br1.getBrewNoteList().size());
        br1.removeLastBrewNote();
        assertEquals(0,br1.getBrewNoteList().size());
    }

    @Test
    void testRemoveLastBrewNote2() {
        br1.addBrewNote(bnote1);
        br1.addBrewNote(bnote2);
        assertEquals(2,br1.getBrewNoteList().size());
        br1.removeLastBrewNote();
        assertEquals(bnote1,br1.getBrewNoteList().get(0));
    }

    @Test
    void testSetBeanID() {
        br1.setBeanID(10);
        assertEquals(10,br1.getBeanID());

    }


}
