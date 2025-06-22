package DocumentsFactory;

import DocumentFactoryPackage.DocumentFactory;
import Documents.Document;
import Documents.worddocument;

public class worddocumentfactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new worddocument();
    }
}
