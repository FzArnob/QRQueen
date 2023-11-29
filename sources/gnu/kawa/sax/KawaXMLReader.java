package gnu.kawa.sax;

import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.xml.XMLFilter;
import gnu.xml.XMLParser;
import java.io.IOException;
import java.io.Reader;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class KawaXMLReader extends ContentConsumer implements XMLReader {
    ErrorHandler errorHandler;

    public DTDHandler getDTDHandler() {
        return null;
    }

    public EntityResolver getEntityResolver() {
        return null;
    }

    public boolean getFeature(String str) {
        return false;
    }

    public Object getProperty(String str) {
        return null;
    }

    public void parse(String str) {
    }

    public void setDTDHandler(DTDHandler dTDHandler) {
    }

    public void setEntityResolver(EntityResolver entityResolver) {
    }

    public void setFeature(String str, boolean z) {
    }

    public void setProperty(String str, Object obj) {
    }

    public void setErrorHandler(ErrorHandler errorHandler2) {
        this.errorHandler = errorHandler2;
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public void parse(InputSource inputSource) throws IOException, SAXException {
        Reader characterStream = inputSource.getCharacterStream();
        if (characterStream == null) {
            characterStream = XMLParser.XMLStreamReader(inputSource.getByteStream());
        }
        SourceMessages sourceMessages = new SourceMessages();
        XMLFilter xMLFilter = new XMLFilter(this);
        LineBufferedReader lineBufferedReader = new LineBufferedReader(characterStream);
        xMLFilter.setSourceLocator(lineBufferedReader);
        getContentHandler().setDocumentLocator(xMLFilter);
        XMLParser.parse(lineBufferedReader, sourceMessages, xMLFilter);
        String sourceMessages2 = sourceMessages.toString(20);
        if (sourceMessages2 != null) {
            throw new SAXParseException(sourceMessages2, xMLFilter);
        }
    }
}
