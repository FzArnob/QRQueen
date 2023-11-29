package gnu.kawa.lispexpr;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;

public class LispPackage extends Namespace {
    Namespace exported;
    NamespaceUse imported;
    NamespaceUse importing;
    LList shadowingSymbols = LList.Empty;

    public Symbol lookup(String str, int i, boolean z) {
        Symbol lookup = this.exported.lookup(str, i, false);
        if (lookup != null) {
            return lookup;
        }
        Symbol lookupInternal = lookupInternal(str, i);
        if (lookupInternal != null) {
            return lookupInternal;
        }
        for (NamespaceUse namespaceUse = this.imported; namespaceUse != null; namespaceUse = namespaceUse.nextImported) {
            Symbol lookup2 = lookup(str, i, false);
            if (lookup2 != null) {
                return lookup2;
            }
        }
        if (z) {
            return add(new Symbol(this, str), i);
        }
        return null;
    }

    public Symbol lookupPresent(String str, int i, boolean z) {
        Symbol lookup = this.exported.lookup(str, i, false);
        return lookup == null ? super.lookup(str, i, z) : lookup;
    }

    public boolean isPresent(String str) {
        return lookupPresent(str, str.hashCode(), false) != null;
    }

    public boolean unintern(Symbol symbol) {
        String name = symbol.getName();
        int hashCode = name.hashCode();
        if (this.exported.lookup(name, hashCode, false) == symbol) {
            this.exported.remove(symbol);
        } else if (super.lookup(name, hashCode, false) != symbol) {
            return false;
        } else {
            super.remove(symbol);
        }
        symbol.setNamespace((Namespace) null);
        removeFromShadowingSymbols(symbol);
        return true;
    }

    private void addToShadowingSymbols(Symbol symbol) {
        Object obj = this.shadowingSymbols;
        while (obj != LList.Empty) {
            Pair pair = (Pair) obj;
            if (pair.getCar() != symbol) {
                obj = pair.getCdr();
            } else {
                return;
            }
        }
        this.shadowingSymbols = new Pair(symbol, this.shadowingSymbols);
    }

    private boolean removeFromShadowingSymbols(Symbol symbol) {
        Object obj = this.shadowingSymbols;
        Pair pair = null;
        while (obj != LList.Empty) {
            Pair pair2 = (Pair) obj;
            Object cdr = pair2.getCdr();
            if (pair2.getCar() != symbol) {
                pair = pair2;
                obj = cdr;
            } else if (pair == null) {
                this.shadowingSymbols = (LList) cdr;
                return true;
            } else {
                pair.setCdr(cdr);
                return true;
            }
        }
        return false;
    }

    public void shadow(String str) {
        addToShadowingSymbols(lookupPresent(str, str.hashCode(), true));
    }

    public void shadowingImport(Symbol symbol) {
        String name = symbol.getName();
        name.hashCode();
        Symbol lookupPresent = lookupPresent(name, name.hashCode(), false);
        if (!(lookupPresent == null || lookupPresent == symbol)) {
            unintern(lookupPresent);
        }
        addToShadowingSymbols(symbol);
    }
}
