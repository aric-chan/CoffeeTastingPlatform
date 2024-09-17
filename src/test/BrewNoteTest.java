import model.BrewNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BrewNoteTest {
    private BrewNote bnote1;
    private BrewNote bnote2;
    private BrewNote bnote3;

    @BeforeEach
    void runBefore() {
        bnote1 = new BrewNote(1,"Johnny",7,6,5,4,3,8,"");
        bnote2 = new BrewNote(1,"Jessica",6,4,3,4,3,6,"too sour!");
        bnote3 = new BrewNote(2,"Eric",9,6,5,6,3,7,"balanced");
    }

    @Test
    void testgetBeanID() {
        assertEquals(1,bnote1.getBeanID());
    }

    @Test
    void testgetAroma() {
        assertEquals(7,bnote1.getAroma());
    }

    @Test
    void testgetAcidity() {
        assertEquals(6,bnote1.getAcidity());
    }

    @Test
    void testgetSweet() {
        assertEquals(5,bnote1.getSweet());
    }

    @Test
    void testgetBody() {
        assertEquals(4,bnote1.getBody());
    }

    @Test
    void testgetFinish() {
        assertEquals(3,bnote1.getFinish());
    }

    @Test
    void testgetOverall() {
        assertEquals(8,bnote1.getOverall());
    }

    @Test
    void testgetNote() {
        assertEquals("",bnote1.getNote());
        assertEquals("too sour!",bnote2.getNote());
    }

    @Test
    void testSetBeanID() {
        bnote1.setBeanID(9);
        assertEquals(9,bnote1.getBeanID());
    }

    @Test
    void testSetTaster() {
        bnote1.setTaster("Peter");
        assertEquals("Peter",bnote1.getTaster());
    }

    @Test
    void testSetAroma() {
        bnote1.setAroma(10);
        assertEquals(10,bnote1.getAroma());
    }

    @Test
    void testSetAcidity() {
        bnote1.setAcidity(9);
        assertEquals(9,bnote1.getAcidity());
    }

    @Test
    void testSetSweet() {
        bnote1.setSweet(8);
        assertEquals(8,bnote1.getSweet());
    }

    @Test
    void testSetBody() {
        bnote1.setBody(7);
        assertEquals(7,bnote1.getBody());
    }

    @Test
    void testSetFinish() {
        bnote1.setFinish(6);
        assertEquals(6,bnote1.getFinish());
    }

    @Test
    void testSetOverall() {
        bnote1.setOverall(7);
        assertEquals(7,bnote1.getOverall());
    }

    @Test
    void testSetNote() {
        bnote1.setNote("Fruity!");
        bnote2.setNote("");

        assertEquals("Fruity!",bnote1.getNote());
        assertEquals("",bnote2.getNote());
    }





}