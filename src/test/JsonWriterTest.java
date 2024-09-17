import model.Purchase;
import model.Beans;
import model.BrewNote;
import model.BeanReview;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFilePurchase() {
        try {
            Purchase pr = new Purchase("Purchaser 1");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvalidFileBeanReview() {
        try {
            BeanReview br = new BeanReview(1);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPurchase() {
        try {
            Purchase pr = new Purchase("Purchaser 1");
            List<Purchase> prListWriter = new ArrayList<>();
            prListWriter.add(pr);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPurchase.json");
            writer.open();
            writer.writePurchase(prListWriter);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPurchase.json");
            List<Purchase> prListReader = new ArrayList<>();
            prListReader = reader.readPurchase();
            assertEquals("Purchaser 1", prListReader.get(0).getBuyer());
            assertEquals(0, prListReader.get(0).getBeanList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyBeanReview() {
        try {
            BeanReview br = new BeanReview(1);
            List<BeanReview> brListWriter = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBeanReview.json");
            List<BeanReview> brList = new ArrayList<>();
            brListWriter.add(br);
            writer.open();
            writer.writeBeanReview(brListWriter);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBeanReview.json");
            List<BeanReview> brListReader = new ArrayList<>();
            brListReader = reader.readBeanReview();
            assertEquals(1, brListReader.get(0).getBeanID());
            assertEquals(0, brListReader.get(0).getBrewNoteList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralPurchase() {
        try {
            Purchase pr1 = new Purchase("Purchaser 1");
            pr1.addBean(new Beans(1,"Craft","Kenya","Natural",2024,02,10));
            pr1.addBean(new Beans(2,"P&H","Ethiopia","Washed",2023,12,19));

            Purchase pr2 = new Purchase("Purchaser 2");
            pr2.addBean(new Beans(3,"Hatch","Columbia","Anaerobic",2024,03,05));

            List<Purchase> prList = new ArrayList<>();
            prList.add(pr1);
            prList.add(pr2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPurchase.json");
            writer.open();
            writer.writePurchase(prList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPurchase.json");
            List<Purchase> prListReader = new ArrayList<>();
            prListReader = reader.readPurchase();
            assertEquals("Purchaser 1", prListReader.get(0).getBuyer());
            assertEquals("Purchaser 2", prListReader.get(1).getBuyer());

            List<Beans> beansList0 = prListReader.get(0).getBeanList();
            List<Beans> beansList1 = prListReader.get(1).getBeanList();
            assertEquals(2, beansList0.size());
            assertEquals(1, beansList1.size());

            checkBean(1,"Craft","Kenya","Natural",2024,02,10,beansList0.get(0));
            checkBean(2,"P&H","Ethiopia","Washed",2023,12,19,beansList0.get(1));
            checkBean(3,"Hatch","Columbia","Anaerobic",2024,03,05,beansList1.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBeanReview() {
        try {
            BeanReview br1 = new BeanReview(1);
            br1.addBrewNote(new BrewNote(1,"Aric",4,4,6,8,6,5,"Floral"));
            br1.addBrewNote(new BrewNote(1,"Mary",5,5,7,9,7,6,"Nutty"));

            BeanReview br2 = new BeanReview(2);
            br2.addBrewNote(new BrewNote(2,"Sam",8,8,8,6,5,7,"Fruity"));

            List<BeanReview> brList = new ArrayList<>();
            brList.add(br1);
            brList.add(br2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBeanReview.json");
            writer.open();
            writer.writeBeanReview(brList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBeanReview.json");
            List<BeanReview> brListReader = new ArrayList<>();
            brListReader = reader.readBeanReview();
            assertEquals(1, brListReader.get(0).getBeanID());
            assertEquals(2, brListReader.get(1).getBeanID());

            List<BrewNote> brewnoteList0 = brListReader.get(0).getBrewNoteList();
            List<BrewNote> brewnoteList1 = brListReader.get(1).getBrewNoteList();

            assertEquals(2, brewnoteList0.size());
            assertEquals(1, brewnoteList1.size());
            checkBrewNote(1,"Aric",4,4,6,8,6,5,brewnoteList0.get(0).getNote(),brewnoteList0.get(0));
            checkBrewNote(1,"Mary",5,5,7,9,7,6,brewnoteList0.get(1).getNote(),brewnoteList0.get(1));
            checkBrewNote(2,"Sam",8,8,8,6,5,7,brewnoteList1.get(0).getNote(),brewnoteList1.get(0));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}