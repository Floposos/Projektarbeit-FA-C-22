package PDF_Config;

import com.itextpdf.html2pdf.HtmlConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;
import java.util.stream.Collectors;

public class pdf {
    public static void main(String[] args) {
        String outputPath = "src/main/resources/zertifikat.pdf";

        String firstname = "Max";
        String lastname = "Mustermann";
        String vereinsname = "Sportverein Musterstadt";
        String eventname = "Sommerturnier";
        String startdatum = "01.06.2024";
        String enddatum = "03.06.2024";
        String sportart1 = "Fußball";
        String ergebnis1 = "1. Platz";
        String sportart2 = "Basketball";
        String ergebnis2 = "3. Platz";
        String sportart3 = "Schwimmen";
        String ergebnis3 = "2. Platz";
        String dateEventEnd = "03.06.2024";

        try {
            InputStream inputStream = pdf.class.getClassLoader().getResourceAsStream("PDF_Config/zertifikat.html");
            if (inputStream == null) {
                throw new IOException("Die Datei zertifikat.html wurde nicht gefunden!");
            }

            String htmlContent;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                htmlContent = reader.lines().collect(Collectors.joining("\n"));
            }

            htmlContent = htmlContent.replace("{{VORNAME}}", firstname)
                    .replace("{{NACHNAME}}", lastname)
                    .replace("{{VEREINSNAME}}", vereinsname)
                    .replace("{{EVENTNAME}}", eventname)
                    .replace("{{STARTDATUM}}", startdatum)
                    .replace("{{ENDDATUM}}", enddatum)
                    .replace("{{SPORTART1}}", sportart1)
                    .replace("{{ERGEBNIS1}}", ergebnis1)
                    .replace("{{SPORTART2}}", sportart2)
                    .replace("{{ERGEBNIS2}}", ergebnis2)
                    .replace("{{SPORTART3}}", sportart3)
                    .replace("{{ERGEBNIS3}}", ergebnis3)
                    .replace("{{DATE_EVENT_END}}", dateEventEnd);

            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputPath));

            System.out.println("Urkunde für " + firstname + " " + lastname + " wurde erfolgreich erstellt!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
