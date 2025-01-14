package PDF_Config;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

public class pdf {
    public static void main (String[] args) throws IOException {

        //Creating PDF document object
        PDDocument document = new PDDocument();

        PDPage blankPage = new PDPage();

        //Adding the blank page to the document
        document.addPage( blankPage );


        //Retrieving the pages of the document
        PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);

        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        PDFont font=  new PDType1Font(HELVETICA_BOLD);
        contentStream.setFont( font , 12);
        contentStream.setLeading(14.5f);
        //Setting the position for the line
        contentStream.newLineAtOffset(45, 100);

        String text1 = "This is the sample document and we are adding content to it.";
        String text2 = "as we want like this using the ShowText()  method of the ContentStream class";

        //Adding text in the form of string
        contentStream.showText(text1);
        contentStream.newLine();
        contentStream.showText(text2);

        //Ending the content stream
        contentStream.endText();

        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();

        //Saving the document
        document.save("C:\\Users\\Lasse\\Downloads\\Zertifikat.pdf");

        System.out.println("PDF created");

        //Closing the document
        document.close();

    }
}
