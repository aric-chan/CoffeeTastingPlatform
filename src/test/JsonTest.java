
import model.Beans;
import model.BrewNote;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBean(int beanID, String roaster, String origin, String process, int roastYYyy, int roastMM, int roastDD, Beans beans) {
        assertEquals(beanID, beans.getBeanID());
        assertEquals(roaster, beans.getRoaster());
        assertEquals(origin, beans.getOrigin());
        assertEquals(process, beans.getProcess());
        assertEquals(roastYYyy, beans.getRoastYYyy());
        assertEquals(roastMM, beans.getRoastMM());
        assertEquals(roastDD, beans.getRoastDD());

    }
    protected void checkBrewNote(int id, String taster, int ar, int ac, int sw, int bo, int fin, int overall, String note, BrewNote brewnote) {
        assertEquals(id, brewnote.getBeanID());
        assertEquals(taster, brewnote.getTaster());
        assertEquals(ar, brewnote.getAroma());
        assertEquals(ac, brewnote.getAcidity());
        assertEquals(sw, brewnote.getSweet());
        assertEquals(bo, brewnote.getBody());
        assertEquals(fin, brewnote.getFinish());
        assertEquals(overall, brewnote.getOverall());
        assertEquals(note, brewnote.getNote());
    }
}
