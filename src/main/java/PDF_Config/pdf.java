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
        String outputPath = "src/main/resources/zertifikat.pdf";    // Pfad für die Ausgabe-PDF
        String name = "Max Mustermann";           // Dynamischer Name
        String vereinsname = "Sportverein Musterstadt"; // Dynamischer Vereinsname

        try {
            // HTML-Datei aus dem Ressourcen-Ordner laden
            InputStream inputStream = pdf.class.getClassLoader().getResourceAsStream("PDF_Config/zertifikat.html");
            if (inputStream == null) {
                throw new IOException("Die Datei zertifikat.html wurde nicht gefunden!");
            }

            // HTML-Inhalt als String einlesen
            String htmlContent;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                htmlContent = reader.lines().collect(Collectors.joining("\n"));
            }

            // Platzhalter ersetzen
            htmlContent = htmlContent.replace("{{NAME}}", name);
            htmlContent = htmlContent.replace("{{VEREINSNAME}}", vereinsname);

            // PDF aus HTML generieren
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputPath));

            System.out.println("✅ Urkunde für " + name + " im Verein " + vereinsname + " wurde erfolgreich erstellt!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
