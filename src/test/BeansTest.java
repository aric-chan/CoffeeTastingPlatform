import model.Beans;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BeansTest {
    private Beans bean1;
    private Beans bean2;

    @BeforeEach
    void runBefore() {
        bean1 = new Beans(1,"Kaffa","Ethiopia", "Washed", 2023,02,06);
        bean2 = new Beans(2,"April Coffee Roasters","Costa Rica", "Honey", 2023,11,27);
    }

    @Test
    void testConstructor() {
        assertEquals("Kaffa", bean1.getRoaster());
        assertEquals("Ethiopia", bean1.getOrigin());
        assertEquals("Washed", bean1.getProcess());
        assertEquals(2023, bean1.getRoastYYyy());
        assertEquals(02, bean1.getRoastMM());
        assertEquals(06, bean1.getRoastDD());
    }

    @Test
    void testGetBeanID() {
        assertEquals(1, bean1.getBeanID());
        assertEquals(2, bean2.getBeanID());
    }

    @Test
    void testSetRoaster() {
        bean1.setRoaster("Craft");
        assertEquals("Craft", bean1.getRoaster());
    }

    @Test
    void testSetOrigin() {
        bean1.setOrigin("Brazil");
        assertEquals("Brazil", bean1.getOrigin());
    }

    @Test
    void testSetProcess() {
        bean1.setProcess("Natural");
        assertEquals("Natural", bean1.getProcess());
    }

    @Test
    void testSetRoastYYyy() {
        bean1.setRoastYYyy(2024);
        assertEquals(2024, bean1.getRoastYYyy());
    }

    @Test
    void testSetRoastMM() {
        bean1.setRoastMM(12);
        assertEquals(12, bean1.getRoastMM());
    }

    @Test
    void testSetRoastDD() {
        bean1.setRoastDD(19);
        assertEquals(19, bean1.getRoastDD());
    }

}
