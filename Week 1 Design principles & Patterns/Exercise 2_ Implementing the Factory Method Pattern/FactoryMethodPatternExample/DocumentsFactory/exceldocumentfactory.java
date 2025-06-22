package DocumentsFactory;

import DocumentFactoryPackage.DocumentFactory;
import Documents.Document;
import Documents.exceldocument;

public class exceldocumentfactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new exceldocument();
    }
}
