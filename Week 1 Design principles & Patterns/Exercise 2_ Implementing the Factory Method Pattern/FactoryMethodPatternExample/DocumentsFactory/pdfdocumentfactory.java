package DocumentsFactory;

import DocumentFactoryPackage.DocumentFactory;
import Documents.Document;
import Documents.pdfdocument;

public class pdfdocumentfactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new pdfdocument();
    }
}
