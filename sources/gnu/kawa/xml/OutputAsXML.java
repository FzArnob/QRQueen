package gnu.kawa.xml;

import androidx.fragment.app.FragmentTransaction;
import gnu.lists.FString;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Procedure1;
import gnu.xml.XMLPrinter;
import java.io.Writer;

public class OutputAsXML extends Procedure1 {
    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Object apply1(Object obj) {
        CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
        XMLPrinter xMLPrinter = new XMLPrinter((Writer) charArrayOutPort);
        xMLPrinter.writeObject(obj);
        xMLPrinter.flush();
        return new FString(charArrayOutPort.toCharArray());
    }
}
