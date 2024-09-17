import model.Purchase;
import model.Beans;
import model.BeanReview;
import model.BrewNote;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Purchase> prListReader = new ArrayList<>();
            prListReader = reader.readPurchase();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPurchase() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPurchase.json");
        try {
            List<Purchase> prListReader = new ArrayList<>();
            prListReader = reader.readPurchase();
            assertEquals("Purchaser 1", prListReader.get(0).getBuyer());
            assertEquals(0, prListReader.get(0).getBeanList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyBeanReview() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBeanReview.json");
        try {
            List<BeanReview> brListReader = new ArrayList<>();
            brListReader = reader.readBeanReview();
            assertEquals(1, brListReader.get(0).getBeanID());
            assertEquals(0, brListReader.get(0).getBrewNoteList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPurchase() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPurchase.json");
        try {
            List<Purchase> prListReader = new ArrayList<>();
            prListReader= reader.readPurchase();
            assertEquals("Purchaser 1", prListReader.get(0).getBuyer());
            List<Beans> beans = prListReader.get(0).getBeanList();
            assertEquals(2, beans.size());
            checkBean(1,"Craft","Kenya","Natural",2024,02,10,prListReader.get(0).getBeanList().get(0));
            checkBean(2,"P&H","Ethiopia","Washed",2023,12,19,prListReader.get(0).getBeanList().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBeanReview() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBeanReview.json");
        try {
            List<BeanReview> brListReader = new ArrayList<>();
            brListReader = reader.readBeanReview();
            assertEquals(1, brListReader.get(0).getBeanID());
            List<BrewNote> brewNote = brListReader.get(0).getBrewNoteList();
            assertEquals(2, brewNote.size());
            checkBrewNote(1,"Aric",4,4,6,8,6,5,brListReader.get(0).getBrewNoteList().get(0).getNote(),brListReader.get(0).getBrewNoteList().get(0));
            checkBrewNote(1,"Mary",5,5,7,9,7,6,brListReader.get(0).getBrewNoteList().get(1).getNote(),brListReader.get(0).getBrewNoteList().get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}