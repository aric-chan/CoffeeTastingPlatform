import model.Beans;
import model.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {
    private Purchase p1;
    private Purchase p2;
    private Beans bean1;
    private Beans bean2;

    @BeforeEach
    void runBefore() {
        p1 = new Purchase("Samuel");
        p2 = new Purchase("Tom");
        bean1 = new Beans(1, "Kaffa","Ethiopia", "Washed", 2023,02,06);
        bean2 = new Beans(2, "April Coffee Roasters","Costa Rica", "Honey", 2023,11,27);

    }

    @Test
    void testConstructor() {
        assertEquals("Samuel", p1.getBuyer());
    }

    @Test
    void testEmptyGetBeanList() {
        assertEquals(0,p1.getBeanList().size());
    }

    @Test
    void testAddBean() {
        p1.addBean(bean1);
        assertEquals(bean1,p1.getBeanList().get(0));
    }

    @Test
    void testAddMultiBean() {
        p1.addBean(bean1);
        p1.addBean(bean2);
        assertEquals(bean1,p1.getBeanList().get(0));
        assertEquals(bean2,p1.getBeanList().get(1));
    }

    @Test
    void testRemoveLastBean() {
        p1.addBean(bean1);
        assertEquals(1,p1.getBeanList().size());
        p1.removeLastBean();
        assertEquals(0,p1.getBeanList().size());

    }

    @Test
    void testRemoveLastBean2() {
        p1.addBean(bean1);
        p1.addBean(bean2);
        assertEquals(2,p1.getBeanList().size());
        p1.removeLastBean();
        assertEquals(1,p1.getBeanList().size());
        assertEquals(bean1,p1.getBeanList().get(0));
    }

    @Test
    void testSetBuyer() {
        p1.setBuyer("Mary");
        assertEquals("Mary", p1.getBuyer());
    }

}
