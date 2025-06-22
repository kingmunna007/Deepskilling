import DocumentFactoryPackage.DocumentFactory;
import Documents.Document;
import DocumentsFactory.exceldocumentfactory;
import DocumentsFactory.pdfdocumentfactory;
import DocumentsFactory.worddocumentfactory;

public class test {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new worddocumentfactory();
        Document wordDoc = wordFactory.createDocument();//all created using run conditions
        wordDoc.open();

        DocumentFactory pdfFactory = new pdfdocumentfactory();
        Document pdfDoc = pdfFactory.createDocument();//all created using run conditions
        pdfDoc.open();

        DocumentFactory excelFactory = new exceldocumentfactory();
        Document excelDoc = excelFactory.createDocument();//all created using run conditions
        excelDoc.open();
    }
}