
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.junit.*;

public class Test {
    private ArrayList<String> reqAttr;

    @Before
    public void init() {
        reqAttr = new ArrayList<String>();
        reqAttr.add("Author");
        reqAttr.add("Date");
        reqAttr.add("Review");
        reqAttr.add("Document");
    }


    @org.junit.Test
    public void CheckDatum() throws Exception {
        boolean right = true;
        StringBuilder sb = new StringBuilder();
        // Es ist besser, Ehre zu verdienen, sie aber nicht erwiesen zu bekommen, als
        // Ehre erwiesen zu bekommen, sie jedoch nicht zu verdienen.
        Date datum = new Date(1, 1, 1970);
        Date today = new Date(8, 11, 2013);

        int tage = datum.getAgeInDaysAt(today);
        if (tage != 16017) {
            sb.append("\n>>> Date.getAgeInDaysAt berechnet die Tage falsch." + tage);
            right = false;
        }

        int jahre = datum.getAgeInYearsAt(today);
        if (jahre != 43) {
            sb.append("\n>>> Date.getAgeInYearsAt berechnet die Jahre falsch." + jahre);
            right = false;
        }

        assertTrue(sb.toString(), right);
    }

}
