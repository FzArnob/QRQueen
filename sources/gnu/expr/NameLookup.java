package gnu.expr;

import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Symbol;

public class NameLookup extends GeneralHashTable<Object, Declaration> {
    static final Symbol KEY = Symbol.makeUninterned("<current-NameLookup>");
    Language language;

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language2) {
        this.language = language2;
    }

    public NameLookup(Language language2) {
        this.language = language2;
    }

    public static NameLookup getInstance(Environment environment, Language language2) {
        Location location = environment.getLocation(KEY);
        NameLookup nameLookup = (NameLookup) location.get((Object) null);
        if (nameLookup == null) {
            NameLookup nameLookup2 = new NameLookup(language2);
            location.set(nameLookup2);
            return nameLookup2;
        }
        nameLookup.setLanguage(language2);
        return nameLookup;
    }

    public static void setInstance(Environment environment, NameLookup nameLookup) {
        if (nameLookup == null) {
            environment.remove(KEY);
        } else {
            environment.put(KEY, (Object) null, nameLookup);
        }
    }

    public void push(Declaration declaration) {
        Object symbol = declaration.getSymbol();
        if (symbol != null) {
            int i = this.num_bindings + 1;
            this.num_bindings = i;
            if (i >= ((HashNode[]) this.table).length) {
                rehash();
            }
            int hash = hash(symbol);
            HashNode makeEntry = makeEntry(symbol, hash, declaration);
            int hashToIndex = hashToIndex(hash);
            makeEntry.next = ((HashNode[]) this.table)[hashToIndex];
            ((HashNode[]) this.table)[hashToIndex] = makeEntry;
        }
    }

    public boolean pop(Declaration declaration) {
        Object symbol = declaration.getSymbol();
        if (symbol == null) {
            return false;
        }
        int hashToIndex = hashToIndex(hash(symbol));
        HashNode<K, V> hashNode = null;
        HashNode<K, V> hashNode2 = ((HashNode[]) this.table)[hashToIndex];
        while (hashNode2 != null) {
            HashNode<K, V> hashNode3 = hashNode2.next;
            if (hashNode2.getValue() == declaration) {
                if (hashNode == null) {
                    ((HashNode[]) this.table)[hashToIndex] = hashNode3;
                } else {
                    hashNode.next = hashNode3;
                }
                this.num_bindings--;
                return true;
            }
            hashNode = hashNode2;
            hashNode2 = hashNode3;
        }
        return false;
    }

    public void push(ScopeExp scopeExp) {
        for (Declaration firstDecl = scopeExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            push(firstDecl);
        }
    }

    public void pop(ScopeExp scopeExp) {
        for (Declaration firstDecl = scopeExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            pop(firstDecl);
        }
    }

    public void removeSubsumed(Declaration declaration) {
        int hashToIndex = hashToIndex(hash(declaration.getSymbol()));
        HashNode<K, V> hashNode = ((HashNode[]) this.table)[hashToIndex];
        HashNode<K, V> hashNode2 = null;
        while (hashNode != null) {
            HashNode<K, V> hashNode3 = hashNode.next;
            Declaration declaration2 = (Declaration) hashNode.getValue();
            if (declaration2 == declaration || !subsumedBy(declaration, declaration2)) {
                hashNode2 = hashNode;
            } else if (hashNode2 == null) {
                ((HashNode[]) this.table)[hashToIndex] = hashNode3;
            } else {
                hashNode2.next = hashNode3;
            }
            hashNode = hashNode3;
        }
    }

    /* access modifiers changed from: protected */
    public boolean subsumedBy(Declaration declaration, Declaration declaration2) {
        return declaration.getSymbol() == declaration2.getSymbol() && (this.language.getNamespaceOf(declaration) & this.language.getNamespaceOf(declaration2)) != 0;
    }

    public Declaration lookup(Object obj, int i) {
        for (HashNode<K, V> hashNode = ((HashNode[]) this.table)[hashToIndex(hash(obj))]; hashNode != null; hashNode = hashNode.next) {
            Declaration declaration = (Declaration) hashNode.getValue();
            if (obj.equals(declaration.getSymbol()) && this.language.hasNamespace(declaration, i)) {
                return declaration;
            }
        }
        return null;
    }

    public Declaration lookup(Object obj, boolean z) {
        return lookup(obj, z ? 2 : 1);
    }
}
