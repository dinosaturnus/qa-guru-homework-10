package parsing;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ZipTest {

    private ClassLoader cl = JsonFileTest.class.getClassLoader();

    @DisplayName("Парсер PDF-файла из zip-архива")
    @Test
    void pdfParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("PDF_Converter_Pro_Quick_Reference_Guide.RU.pdf")) {
                    PDF pdf = new PDF(zs);
                    assertEquals("Nuance Communications, Inc.", pdf.author);
                    assertEquals("Краткое справочное руководство", pdf.title);
                    assertEquals(59, pdf.numberOfPages);
                }
            }

        }
    }

    @DisplayName("Парсер XLS-файла из zip-архива")
    @Test
    void xlsParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("V_1k_izm_ pr.xlsx")) {
                    XLS xls = new XLS(zs);
                    assertEquals("БИО-А22",
                            xls.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue());
                    assertEquals(" 1 курс ", xls.excel.getSheetName(0));
                }
            }

        }
    }

    @DisplayName("Парсер CSV-файла из zip-архива")
    @Test
    void csvParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("username.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zs));
                    List<String[]> content = csvReader.readAll();
                    assertArrayEquals(new String[]{"Jamie", "Smith"}, content.get(0));
                    assertArrayEquals(new String[]{"Rachel", "Rodjer"}, content.get(1));

                }
            }
        }
    }
}
