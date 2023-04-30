package parsing;

import jsonFileClass.RingInGoldWithADiamondJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.InputStreamReader;


public class JsonFileTest {

    private ClassLoader cl = JsonFileTest.class.getClassLoader();

    @DisplayName("Парсер JSON-файла")
    @Test
    void jsonParseTest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream is = cl.getResourceAsStream("ring_in_gold_with_a_diamond.json");
             InputStreamReader isr = new InputStreamReader(is)) {

            RingInGoldWithADiamondJson ring = mapper.readValue(isr, RingInGoldWithADiamondJson.class);

            Assertions.assertEquals("Кольцо из золота с бриллиантом", ring.name);
            Assertions.assertEquals(1012564, ring.productArticle);
            Assertions.assertEquals("Red gold 585", ring.material);
            Assertions.assertEquals("0.76", ring.weightInGrams);
            Assertions.assertEquals("16", ring.sizeInMillimeters.get(1));
            Assertions.assertTrue(ring.available);
            Assertions.assertEquals("SOKOLOV", ring.fullDescription.brand);
            Assertions.assertEquals("Картой онлайн", ring.fullDescription.paymentTypes.get(0));
            Assertions.assertEquals("Гарантия 6 месяцев", ring.fullDescription.assurance);
        }
    }
}
