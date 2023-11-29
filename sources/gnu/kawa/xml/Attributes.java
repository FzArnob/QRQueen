package gnu.kawa.xml;

import androidx.fragment.app.FragmentTransaction;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.TreePosition;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.io.PrintStream;

public class Attributes extends MethodProc {
    public static final Attributes attributes = new Attributes();

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public static void attributes(TreeList treeList, int i, Consumer consumer) {
        int gotoAttributesStart = treeList.gotoAttributesStart(i);
        PrintStream printStream = System.out;
        printStream.print("Attributes called, at:" + gotoAttributesStart + " ");
        treeList.dump();
        while (gotoAttributesStart >= 0) {
            int i2 = gotoAttributesStart << 1;
            if (treeList.getNextKind(i2) == 35) {
                int nextDataIndex = treeList.nextDataIndex(gotoAttributesStart);
                if (consumer instanceof PositionConsumer) {
                    ((PositionConsumer) consumer).writePosition(treeList, i2);
                } else {
                    treeList.consumeIRange(gotoAttributesStart, nextDataIndex, consumer);
                }
                gotoAttributesStart = nextDataIndex;
            } else {
                return;
            }
        }
    }

    public static void attributes(Object obj, Consumer consumer) {
        if (obj instanceof TreeList) {
            attributes((TreeList) obj, 0, consumer);
        } else if ((obj instanceof SeqPosition) && !(obj instanceof TreePosition)) {
            SeqPosition seqPosition = (SeqPosition) obj;
            if (seqPosition.sequence instanceof TreeList) {
                attributes((TreeList) seqPosition.sequence, seqPosition.ipos >> 1, consumer);
            }
        }
    }

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        Object nextArg = callContext.getNextArg();
        callContext.lastArg();
        if (nextArg instanceof Values) {
            TreeList treeList = (TreeList) nextArg;
            int i = 0;
            while (true) {
                int i2 = i << 1;
                int nextKind = treeList.getNextKind(i2);
                if (nextKind != 0) {
                    if (nextKind == 32) {
                        attributes(treeList.getPosNext(i2), consumer);
                    } else {
                        attributes(treeList, i, consumer);
                    }
                    i = treeList.nextDataIndex(i);
                } else {
                    return;
                }
            }
        } else {
            attributes(nextArg, consumer);
        }
    }
}
