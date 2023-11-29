package com.google.appinventor.components.runtime.util;

import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.RunnableClosure;

public class BiggerFuture extends Thread {
    public BiggerFuture(Procedure procedure, InPort inPort, OutPort outPort, OutPort outPort2, String str, long j) {
        super(new ThreadGroup("biggerthreads"), new RunnableClosure(procedure, inPort, outPort, outPort2), str, j);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#<future ");
        stringBuffer.append(getName());
        stringBuffer.append(">");
        return stringBuffer.toString();
    }
}
