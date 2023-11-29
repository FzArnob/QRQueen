package gnu.kawa.xml;

import androidx.fragment.app.FragmentTransaction;
import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class TreeScanner extends MethodProc implements Externalizable {
    public NodePredicate type;

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public abstract void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer);

    TreeScanner() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.xml.CompileXmlFunctions:validateApplyTreeScanner");
    }

    public NodePredicate getNodePredicate() {
        return this.type;
    }

    public void apply(CallContext callContext) throws Throwable {
        PositionConsumer positionConsumer = (PositionConsumer) callContext.consumer;
        Object nextArg = callContext.getNextArg();
        callContext.lastArg();
        try {
            KNode kNode = (KNode) nextArg;
            scan(kNode.sequence, kNode.getPos(), positionConsumer);
        } catch (ClassCastException unused) {
            throw new WrongType(getDesc(), -4, nextArg, "node()");
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.type);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.type = (NodePredicate) objectInput.readObject();
    }

    public String getDesc() {
        String name = getClass().getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf > 0) {
            name = name.substring(lastIndexOf + 1);
        }
        return name + "::" + this.type;
    }

    public String toString() {
        return "#<" + getClass().getName() + ' ' + this.type + '>';
    }
}
