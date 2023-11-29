package kawa.lib.kawa;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.SetNamedPart;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.thisRef;

/* compiled from: hashtable.scm */
public class hashtable extends ModuleBody {
    public static final Location $Prvt$do = StaticFieldLocation.make("kawa.lib.std_syntax", "do");
    public static final Class $Prvt$hashnode = HashNode.class;
    public static final Location $Prvt$let$St = StaticFieldLocation.make("kawa.lib.std_syntax", "let$St");
    public static final hashtable $instance;
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("mutable").readResolve());
    static final SimpleSymbol Lit1;
    public static final Class hashtable = HashTable.class;
    public static final ModuleMethod hashtable$Mncheck$Mnmutable;

    public hashtable() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("hashtable-check-mutable").readResolve();
        Lit1 = simpleSymbol;
        hashtable hashtable2 = new hashtable();
        $instance = hashtable2;
        hashtable$Mncheck$Mnmutable = new ModuleMethod(hashtable2, 1, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable2.run();
    }

    /* compiled from: hashtable.scm */
    public class HashTable extends GeneralHashTable {
        public Procedure equivalenceFunction;
        public Procedure hashFunction;
        public boolean mutable;

        private void $finit$() {
            this.mutable = true;
        }

        public HashTable(Procedure procedure, Procedure procedure2, int i) {
            super(i);
            $finit$();
            this.equivalenceFunction = procedure;
            this.hashFunction = procedure2;
        }

        public HashTable(Procedure procedure, Procedure procedure2) {
            $finit$();
            this.equivalenceFunction = procedure;
            this.hashFunction = procedure2;
        }

        public HashTable(HashTable hashTable, boolean z) {
            $finit$();
            Invoke.invokeSpecial.applyN(new Object[]{hashtable.hashtable, this, hashTable.equivalenceFunction.apply0(), hashTable.hashFunction.apply0(), Integer.valueOf(hashTable.size() + 100)});
            putAll(hashTable);
            SetNamedPart.setNamedPart.apply3(thisRef.thisSyntax, hashtable.Lit0, z ? Boolean.TRUE : Boolean.FALSE);
        }

        public int hash(Object obj) {
            return ((Number) this.hashFunction.apply1(obj)).intValue();
        }

        public boolean matches(Object obj, Object obj2) {
            return this.equivalenceFunction.apply2(obj, obj2) != Boolean.FALSE;
        }

        public void walk(Procedure procedure) {
            Entry[] entryArr = this.table;
            try {
                HashNode[] hashNodeArr = (HashNode[]) entryArr;
                for (int length = hashNodeArr.length - 1; length >= 0; length--) {
                    HashNode hashNode = hashNodeArr[length];
                    while (hashNode != null) {
                        procedure.apply2(hashNode.getKey(), hashNode.getValue());
                        hashNode = getEntryNext(hashNode);
                    }
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, (Object) entryArr);
            }
        }

        public Object fold(Procedure procedure, Object obj) {
            Entry[] entryArr = this.table;
            try {
                HashNode[] hashNodeArr = (HashNode[]) entryArr;
                for (int length = hashNodeArr.length - 1; length >= 0; length--) {
                    HashNode hashNode = hashNodeArr[length];
                    while (hashNode != null) {
                        obj = procedure.apply3(hashNode.getKey(), hashNode.getValue(), obj);
                        hashNode = getEntryNext(hashNode);
                    }
                }
                return obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, (Object) entryArr);
            }
        }

        public FVector keysVector() {
            FVector fVector = new FVector();
            Entry[] entryArr = this.table;
            try {
                HashNode[] hashNodeArr = (HashNode[]) entryArr;
                for (int length = hashNodeArr.length - 1; length >= 0; length--) {
                    HashNode hashNode = hashNodeArr[length];
                    while (hashNode != null) {
                        fVector.add(hashNode.getKey());
                        hashNode = getEntryNext(hashNode);
                    }
                }
                return fVector;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, (Object) entryArr);
            }
        }

        public Pair entriesVectorPair() {
            FVector fVector = new FVector();
            FVector fVector2 = new FVector();
            Entry[] entryArr = this.table;
            try {
                HashNode[] hashNodeArr = (HashNode[]) entryArr;
                for (int length = hashNodeArr.length - 1; length >= 0; length--) {
                    HashNode hashNode = hashNodeArr[length];
                    while (hashNode != null) {
                        fVector.add(hashNode.getKey());
                        fVector2.add(hashNode.getValue());
                        hashNode = getEntryNext(hashNode);
                    }
                }
                return lists.cons(fVector, fVector2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, (Object) entryArr);
            }
        }

        public Object toAlist() {
            LList lList = LList.Empty;
            Entry[] entryArr = this.table;
            try {
                HashNode[] hashNodeArr = (HashNode[]) entryArr;
                for (int length = hashNodeArr.length - 1; length >= 0; length--) {
                    HashNode hashNode = hashNodeArr[length];
                    while (hashNode != null) {
                        lList = lists.cons(lists.cons(hashNode.getKey(), hashNode.getValue()), lList);
                        hashNode = getEntryNext(hashNode);
                    }
                }
                return lList;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, (Object) entryArr);
            }
        }

        public LList toNodeList() {
            LList lList = LList.Empty;
            Entry[] entryArr = this.table;
            try {
                HashNode[] hashNodeArr = (HashNode[]) entryArr;
                for (int length = hashNodeArr.length - 1; length >= 0; length--) {
                    HashNode hashNode = hashNodeArr[length];
                    while (hashNode != null) {
                        lList = lists.cons(hashNode, lList);
                        hashNode = getEntryNext(hashNode);
                    }
                }
                return lList;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, (Object) entryArr);
            }
        }

        public HashNode[] toNodeArray() {
            HashNode[] hashNodeArr = new HashNode[size()];
            Entry[] entryArr = this.table;
            try {
                HashNode[] hashNodeArr2 = (HashNode[]) entryArr;
                int i = 0;
                for (int length = hashNodeArr2.length - 1; length >= 0; length--) {
                    HashNode hashNode = hashNodeArr2[length];
                    while (hashNode != null) {
                        hashNodeArr[i] = hashNode;
                        i++;
                        hashNode = getEntryNext(hashNode);
                    }
                }
                return hashNodeArr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, (Object) entryArr);
            }
        }

        public void putAll(HashTable hashTable) {
            Entry[] entryArr = hashTable.table;
            try {
                HashNode[] hashNodeArr = (HashNode[]) entryArr;
                for (int length = hashNodeArr.length - 1; length >= 0; length--) {
                    HashNode hashNode = hashNodeArr[length];
                    while (hashNode != null) {
                        put(hashNode.getKey(), hashNode.getValue());
                        hashNode = hashTable.getEntryNext(hashNode);
                    }
                }
            } catch (ClassCastException e) {
                throw new WrongType(e, "table", -2, (Object) entryArr);
            }
        }

        public Object clone() {
            return new HashTable(this, true);
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        if (moduleMethod.selector != 1) {
            return super.apply1(moduleMethod, obj);
        }
        try {
            hashtableCheckMutable((HashTable) obj);
            return Values.empty;
        } catch (ClassCastException e) {
            throw new WrongType(e, "hashtable-check-mutable", 1, obj);
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.match1(moduleMethod, obj, callContext);
        }
        if (!(obj instanceof HashTable)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static void hashtableCheckMutable(HashTable hashTable) {
        if (!hashTable.mutable) {
            misc.error$V("cannot modify non-mutable hashtable", new Object[0]);
        }
    }
}
