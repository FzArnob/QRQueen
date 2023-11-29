package gnu.lists;

class LListPosition extends ExtPosition {
    Object xpos;

    public LListPosition(LListPosition lListPosition) {
        this.sequence = lListPosition.sequence;
        this.ipos = lListPosition.ipos;
        this.xpos = lListPosition.xpos;
    }

    public SeqPosition copy() {
        return new LListPosition(this);
    }

    public LListPosition(LList lList, int i, boolean z) {
        set(lList, i, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: gnu.lists.LList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: gnu.lists.LList} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void set(gnu.lists.LList r2, int r3, boolean r4) {
        /*
            r1 = this;
            r1.sequence = r2
            int r0 = r3 << 1
            r0 = r0 | r4
            r1.ipos = r0
            if (r4 == 0) goto L_0x000c
            int r3 = r3 + -2
            goto L_0x000e
        L_0x000c:
            int r3 = r3 + -1
        L_0x000e:
            if (r3 < 0) goto L_0x001c
        L_0x0010:
            int r3 = r3 + -1
            if (r3 < 0) goto L_0x0019
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2
            java.lang.Object r2 = r2.cdr
            goto L_0x0010
        L_0x0019:
            r1.xpos = r2
            goto L_0x001f
        L_0x001c:
            r2 = 0
            r1.xpos = r2
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.LListPosition.set(gnu.lists.LList, int, boolean):void");
    }

    public void set(AbstractSequence abstractSequence, int i, boolean z) {
        set((LList) abstractSequence, i, z);
    }

    public boolean hasNext() {
        Object obj = this.xpos;
        if (obj != null) {
            Object obj2 = ((Pair) obj).cdr;
            if ((this.ipos & 1) > 0) {
                obj2 = ((Pair) obj2).cdr;
            }
            if (obj2 != LList.Empty) {
                return true;
            }
            return false;
        } else if ((this.ipos >> 1) == 0) {
            if (this.sequence != LList.Empty) {
                return true;
            }
            return false;
        } else if (((Pair) this.sequence).cdr != LList.Empty) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasPrevious() {
        return (this.ipos >>> 1) != 0;
    }

    public Pair getNextPair() {
        Object obj;
        if ((this.ipos & 1) > 0) {
            Object obj2 = this.xpos;
            if (obj2 == null) {
                obj = this.sequence;
                if ((this.ipos >> 1) != 0) {
                    obj = ((Pair) obj).cdr;
                }
            } else {
                Pair pair = (Pair) ((Pair) obj2).cdr;
                Pair pair2 = pair;
                obj = pair.cdr;
            }
        } else {
            Object obj3 = this.xpos;
            if (obj3 == null) {
                obj = this.sequence;
            } else {
                obj = ((Pair) obj3).cdr;
            }
        }
        if (obj == LList.Empty) {
            return null;
        }
        return (Pair) obj;
    }

    public Object getNext() {
        Pair nextPair = getNextPair();
        return nextPair == null ? LList.eofValue : nextPair.car;
    }

    public void setNext(Object obj) {
        getNextPair().car = obj;
    }

    public Pair getPreviousPair() {
        int i = this.ipos & 1;
        Object obj = this.xpos;
        if (i > 0) {
            obj = obj == null ? this.sequence : ((Pair) obj).cdr;
        } else if (obj == null) {
            return null;
        }
        if (obj == LList.Empty) {
            return null;
        }
        return (Pair) obj;
    }

    public Object getPrevious() {
        Pair previousPair = getPreviousPair();
        return previousPair == null ? LList.eofValue : previousPair.car;
    }

    public void setPrevious(Object obj) {
        getPreviousPair().car = obj;
    }

    public int nextIndex() {
        return this.ipos >> 1;
    }

    public boolean gotoNext() {
        boolean z = (this.ipos & 1) != 0;
        int i = this.ipos;
        Object obj = this.xpos;
        if (obj != null) {
            if (z) {
                obj = ((Pair) obj).cdr;
            }
            if (((Pair) obj).cdr == LList.Empty) {
                return false;
            }
            this.xpos = obj;
            this.ipos = (this.ipos | 1) + 2;
        } else if ((this.ipos >> 1) != 0) {
            AbstractSequence abstractSequence = this.sequence;
            if (((Pair) abstractSequence).cdr == LList.Empty) {
                return false;
            }
            this.ipos = 5;
            this.xpos = abstractSequence;
        } else if (this.sequence == LList.Empty) {
            return false;
        } else {
            this.ipos = 3;
        }
        return true;
    }

    public boolean gotoPrevious() {
        if ((this.ipos >>> 1) == 0) {
            return false;
        }
        if ((this.ipos & 1) != 0) {
            this.ipos -= 3;
            return true;
        }
        set((LList) this.sequence, nextIndex() - 1, false);
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("LListPos[");
        stringBuffer.append("index:");
        stringBuffer.append(this.ipos);
        if (isAfter()) {
            stringBuffer.append(" after");
        }
        if (this.position >= 0) {
            stringBuffer.append(" position:");
            stringBuffer.append(this.position);
        }
        stringBuffer.append(']');
        return stringBuffer.toString();
    }
}
