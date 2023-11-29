package kawa.lib.rnrs;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.kawa.hashtable;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: hashtables.scm */
public class hashtables extends ModuleBody {
    public static final hashtables $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod equal$Mnhash;
    static final ModuleMethod hash$Mnby$Mnidentity;
    static final ModuleMethod hash$Mnfor$Mneqv;
    public static final ModuleMethod hashtable$Mnclear$Ex;
    public static final ModuleMethod hashtable$Mncontains$Qu;
    public static final ModuleMethod hashtable$Mncopy;
    public static final ModuleMethod hashtable$Mndelete$Ex;
    public static final ModuleMethod hashtable$Mnentries;
    public static final ModuleMethod hashtable$Mnequivalence$Mnfunction;
    public static final ModuleMethod hashtable$Mnhash$Mnfunction;
    public static final ModuleMethod hashtable$Mnkeys;
    public static final ModuleMethod hashtable$Mnmutable$Qu;
    public static final ModuleMethod hashtable$Mnref;
    public static final ModuleMethod hashtable$Mnset$Ex;
    public static final ModuleMethod hashtable$Mnsize;
    public static final ModuleMethod hashtable$Mnupdate$Ex;
    public static final ModuleMethod hashtable$Qu;
    public static final ModuleMethod make$Mneq$Mnhashtable;
    public static final ModuleMethod make$Mneqv$Mnhashtable;
    public static final ModuleMethod make$Mnhashtable;
    public static final ModuleMethod string$Mnci$Mnhash;
    public static final ModuleMethod string$Mnhash;
    public static final ModuleMethod symbol$Mnhash;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("symbol-hash").readResolve();
        Lit22 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("string-ci-hash").readResolve();
        Lit21 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("string-hash").readResolve();
        Lit20 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("equal-hash").readResolve();
        Lit19 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("hashtable-mutable?").readResolve();
        Lit18 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("hashtable-hash-function").readResolve();
        Lit17 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("hashtable-equivalence-function").readResolve();
        Lit16 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("hashtable-entries").readResolve();
        Lit15 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("hashtable-keys").readResolve();
        Lit14 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("hashtable-clear!").readResolve();
        Lit13 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("hashtable-copy").readResolve();
        Lit12 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("hashtable-update!").readResolve();
        Lit11 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("hashtable-contains?").readResolve();
        Lit10 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("hashtable-delete!").readResolve();
        Lit9 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("hashtable-set!").readResolve();
        Lit8 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("hashtable-ref").readResolve();
        Lit7 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("hashtable-size").readResolve();
        Lit6 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("hashtable?").readResolve();
        Lit5 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("make-hashtable").readResolve();
        Lit4 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol5;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("make-eqv-hashtable").readResolve();
        Lit3 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = simpleSymbol6;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("make-eq-hashtable").readResolve();
        Lit2 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol7;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("hash-for-eqv").readResolve();
        Lit1 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol8;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("hash-by-identity").readResolve();
        Lit0 = simpleSymbol31;
        hashtables hashtables = new hashtables();
        $instance = hashtables;
        hash$Mnby$Mnidentity = new ModuleMethod(hashtables, 1, simpleSymbol31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mnfor$Mneqv = new ModuleMethod(hashtables, 2, simpleSymbol29, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mneq$Mnhashtable = new ModuleMethod(hashtables, 3, simpleSymbol27, 4096);
        make$Mneqv$Mnhashtable = new ModuleMethod(hashtables, 5, simpleSymbol25, 4096);
        make$Mnhashtable = new ModuleMethod(hashtables, 7, simpleSymbol23, 12290);
        hashtable$Qu = new ModuleMethod(hashtables, 9, simpleSymbol21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnsize = new ModuleMethod(hashtables, 10, simpleSymbol19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnref = new ModuleMethod(hashtables, 11, simpleSymbol17, 12291);
        hashtable$Mnset$Ex = new ModuleMethod(hashtables, 12, simpleSymbol15, 12291);
        hashtable$Mndelete$Ex = new ModuleMethod(hashtables, 13, simpleSymbol14, 8194);
        hashtable$Mncontains$Qu = new ModuleMethod(hashtables, 14, simpleSymbol13, 8194);
        hashtable$Mnupdate$Ex = new ModuleMethod(hashtables, 15, simpleSymbol12, 16388);
        hashtable$Mncopy = new ModuleMethod(hashtables, 16, simpleSymbol11, 8193);
        hashtable$Mnclear$Ex = new ModuleMethod(hashtables, 18, simpleSymbol10, 8193);
        hashtable$Mnkeys = new ModuleMethod(hashtables, 20, simpleSymbol9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnentries = new ModuleMethod(hashtables, 21, simpleSymbol30, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnequivalence$Mnfunction = new ModuleMethod(hashtables, 22, simpleSymbol28, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnhash$Mnfunction = new ModuleMethod(hashtables, 23, simpleSymbol26, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtable$Mnmutable$Qu = new ModuleMethod(hashtables, 24, simpleSymbol24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        equal$Mnhash = new ModuleMethod(hashtables, 25, simpleSymbol22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnhash = new ModuleMethod(hashtables, 26, simpleSymbol20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnci$Mnhash = new ModuleMethod(hashtables, 27, simpleSymbol18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        symbol$Mnhash = new ModuleMethod(hashtables, 28, simpleSymbol16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hashtables.run();
    }

    public hashtables() {
        ModuleInfo.register(this);
    }

    public static void hashtableClear$Ex(hashtable.HashTable hashTable) {
        hashtableClear$Ex(hashTable, 64);
    }

    public static hashtable.HashTable hashtableCopy(hashtable.HashTable hashTable) {
        return hashtableCopy(hashTable, false);
    }

    public static hashtable.HashTable makeEqHashtable() {
        return makeEqHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public static hashtable.HashTable makeEqvHashtable() {
        return makeEqvHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public static hashtable.HashTable makeHashtable(Procedure procedure, Procedure procedure2) {
        return makeHashtable(procedure, procedure2, AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i == 2) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i == 3) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i == 5) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 16) {
            if (i != 18) {
                if (i == 9) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                } else if (i != 10) {
                    switch (i) {
                        case 20:
                            if (!(obj instanceof hashtable.HashTable)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 21:
                            if (!(obj instanceof hashtable.HashTable)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 22:
                            if (!(obj instanceof hashtable.HashTable)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 23:
                            if (!(obj instanceof hashtable.HashTable)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 24:
                            if (!(obj instanceof hashtable.HashTable)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 25:
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 26:
                            if (!(obj instanceof CharSequence)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 27:
                            if (!(obj instanceof CharSequence)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 28:
                            if (!(obj instanceof Symbol)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        default:
                            return super.match1(moduleMethod, obj, callContext);
                    }
                } else if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
            } else if (!(obj instanceof hashtable.HashTable)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
        } else if (!(obj instanceof hashtable.HashTable)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    static int hashByIdentity(Object obj) {
        return System.identityHashCode(obj);
    }

    static int hashForEqv(Object obj) {
        return obj.hashCode();
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 3) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i != 5) {
            return super.match0(moduleMethod, callContext);
        } else {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }
    }

    public static hashtable.HashTable makeEqHashtable(int i) {
        return new hashtable.HashTable(Scheme.isEq, hash$Mnby$Mnidentity, AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public Object apply0(ModuleMethod moduleMethod) {
        int i = moduleMethod.selector;
        if (i == 3) {
            return makeEqHashtable();
        }
        if (i != 5) {
            return super.apply0(moduleMethod);
        }
        return makeEqvHashtable();
    }

    public static hashtable.HashTable makeEqvHashtable(int i) {
        return new hashtable.HashTable(Scheme.isEqv, hash$Mnfor$Mneqv, AbstractHashTable.DEFAULT_INITIAL_SIZE);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 7) {
            if (i != 16) {
                if (i != 18) {
                    if (i != 13) {
                        if (i != 14) {
                            return super.match2(moduleMethod, obj, obj2, callContext);
                        }
                        if (!(obj instanceof hashtable.HashTable)) {
                            return -786431;
                        }
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    } else if (!(obj instanceof hashtable.HashTable)) {
                        return -786431;
                    } else {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                } else if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
            } else if (!(obj instanceof hashtable.HashTable)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        } else if (!(obj instanceof Procedure)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (!(obj2 instanceof Procedure)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 7) {
            if (i != 11) {
                if (i != 12) {
                    return super.match3(moduleMethod, obj, obj2, obj3, callContext);
                }
                if (!(obj instanceof hashtable.HashTable)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (!(obj instanceof hashtable.HashTable)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            }
        } else if (!(obj instanceof Procedure)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (!(obj2 instanceof Procedure)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    public static hashtable.HashTable makeHashtable(Procedure procedure, Procedure procedure2, int i) {
        return new hashtable.HashTable(procedure, procedure2, i);
    }

    public static boolean isHashtable(Object obj) {
        return obj instanceof hashtable.HashTable;
    }

    public static int hashtableSize(hashtable.HashTable hashTable) {
        return hashTable.size();
    }

    public static Object hashtableRef(hashtable.HashTable hashTable, Object obj, Object obj2) {
        HashNode node = hashTable.getNode(obj);
        return node == null ? obj2 : node.getValue();
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        int i = moduleMethod.selector;
        if (i == 7) {
            try {
                try {
                    try {
                        return makeHashtable((Procedure) obj, (Procedure) obj2, ((Number) obj3).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "make-hashtable", 3, obj3);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "make-hashtable", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "make-hashtable", 1, obj);
            }
        } else if (i == 11) {
            try {
                return hashtableRef((hashtable.HashTable) obj, obj2, obj3);
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "hashtable-ref", 1, obj);
            }
        } else if (i != 12) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        } else {
            try {
                hashtableSet$Ex((hashtable.HashTable) obj, obj2, obj3);
                return Values.empty;
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "hashtable-set!", 1, obj);
            }
        }
    }

    public static void hashtableSet$Ex(hashtable.HashTable hashTable, Object obj, Object obj2) {
        hashtable.hashtableCheckMutable(hashTable);
        hashTable.put(obj, obj2);
    }

    public static void hashtableDelete$Ex(hashtable.HashTable hashTable, Object obj) {
        hashtable.hashtableCheckMutable(hashTable);
        hashTable.remove(obj);
    }

    public static boolean isHashtableContains(hashtable.HashTable hashTable, Object obj) {
        return ((hashTable.getNode(obj) == null ? 1 : 0) + 1) & true;
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        if (moduleMethod.selector != 15) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
        try {
            try {
                return hashtableUpdate$Ex((hashtable.HashTable) obj, obj2, (Procedure) obj3, obj4);
            } catch (ClassCastException e) {
                throw new WrongType(e, "hashtable-update!", 3, obj3);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "hashtable-update!", 1, obj);
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (moduleMethod.selector != 15) {
            return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
        if (!(obj instanceof hashtable.HashTable)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        if (!(obj3 instanceof Procedure)) {
            return -786429;
        }
        callContext.value3 = obj3;
        callContext.value4 = obj4;
        callContext.proc = moduleMethod;
        callContext.pc = 4;
        return 0;
    }

    public static Object hashtableUpdate$Ex(hashtable.HashTable hashTable, Object obj, Procedure procedure, Object obj2) {
        hashtable.hashtableCheckMutable(hashTable);
        HashNode node = hashTable.getNode(obj);
        if (node != null) {
            return node.setValue(procedure.apply1(node.getValue()));
        }
        hashtableSet$Ex(hashTable, obj, procedure.apply1(obj2));
        return Values.empty;
    }

    public static hashtable.HashTable hashtableCopy(hashtable.HashTable hashTable, boolean z) {
        return new hashtable.HashTable(hashTable, z);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        boolean z = true;
        if (i == 7) {
            try {
                try {
                    return makeHashtable((Procedure) obj, (Procedure) obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-hashtable", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "make-hashtable", 1, obj);
            }
        } else if (i == 16) {
            try {
                hashtable.HashTable hashTable = (hashtable.HashTable) obj;
                try {
                    if (obj2 == Boolean.FALSE) {
                        z = false;
                    }
                    return hashtableCopy(hashTable, z);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "hashtable-copy", 2, obj2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "hashtable-copy", 1, obj);
            }
        } else if (i == 18) {
            try {
                try {
                    hashtableClear$Ex((hashtable.HashTable) obj, ((Number) obj2).intValue());
                    return Values.empty;
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "hashtable-clear!", 2, obj2);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "hashtable-clear!", 1, obj);
            }
        } else if (i == 13) {
            try {
                hashtableDelete$Ex((hashtable.HashTable) obj, obj2);
                return Values.empty;
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "hashtable-delete!", 1, obj);
            }
        } else if (i != 14) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                return isHashtableContains((hashtable.HashTable) obj, obj2) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e8) {
                throw new WrongType(e8, "hashtable-contains?", 1, obj);
            }
        }
    }

    public static void hashtableClear$Ex(hashtable.HashTable hashTable, int i) {
        hashtable.hashtableCheckMutable(hashTable);
        hashTable.clear();
    }

    public static FVector hashtableKeys(hashtable.HashTable hashTable) {
        return hashTable.keysVector();
    }

    public static Object hashtableEntries(hashtable.HashTable hashTable) {
        Pair entriesVectorPair = hashTable.entriesVectorPair();
        return misc.values(lists.car.apply1(entriesVectorPair), lists.cdr.apply1(entriesVectorPair));
    }

    public static Procedure hashtableEquivalenceFunction(hashtable.HashTable hashTable) {
        return (Procedure) hashTable.equivalenceFunction.apply1(hashTable);
    }

    public static Object hashtableHashFunction(hashtable.HashTable hashTable) {
        Object apply1 = hashTable.hashFunction.apply1(hashTable);
        Object apply2 = Scheme.isEqv.apply2(apply1, hash$Mnby$Mnidentity);
        if (apply2 != Boolean.FALSE) {
            if (apply2 == Boolean.FALSE) {
                return apply1;
            }
        } else if (Scheme.isEqv.apply2(apply1, hash$Mnfor$Mneqv) == Boolean.FALSE) {
            return apply1;
        }
        return Boolean.FALSE;
    }

    public static Object isHashtableMutable(hashtable.HashTable hashTable) {
        return Scheme.applyToArgs.apply1(hashTable.mutable ? Boolean.TRUE : Boolean.FALSE);
    }

    public static int equalHash(Object obj) {
        return obj.hashCode();
    }

    public static int stringHash(CharSequence charSequence) {
        return charSequence.hashCode();
    }

    public static int stringCiHash(CharSequence charSequence) {
        return charSequence.toString().toLowerCase().hashCode();
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return Integer.valueOf(hashByIdentity(obj));
        }
        if (i == 2) {
            return Integer.valueOf(hashForEqv(obj));
        }
        if (i == 3) {
            try {
                return makeEqHashtable(((Number) obj).intValue());
            } catch (ClassCastException e) {
                throw new WrongType(e, "make-eq-hashtable", 1, obj);
            }
        } else if (i == 5) {
            try {
                return makeEqvHashtable(((Number) obj).intValue());
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "make-eqv-hashtable", 1, obj);
            }
        } else if (i == 16) {
            try {
                return hashtableCopy((hashtable.HashTable) obj);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "hashtable-copy", 1, obj);
            }
        } else if (i == 18) {
            try {
                hashtableClear$Ex((hashtable.HashTable) obj);
                return Values.empty;
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "hashtable-clear!", 1, obj);
            }
        } else if (i == 9) {
            return isHashtable(obj) ? Boolean.TRUE : Boolean.FALSE;
        } else {
            if (i != 10) {
                switch (i) {
                    case 20:
                        try {
                            return hashtableKeys((hashtable.HashTable) obj);
                        } catch (ClassCastException e5) {
                            throw new WrongType(e5, "hashtable-keys", 1, obj);
                        }
                    case 21:
                        try {
                            return hashtableEntries((hashtable.HashTable) obj);
                        } catch (ClassCastException e6) {
                            throw new WrongType(e6, "hashtable-entries", 1, obj);
                        }
                    case 22:
                        try {
                            return hashtableEquivalenceFunction((hashtable.HashTable) obj);
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "hashtable-equivalence-function", 1, obj);
                        }
                    case 23:
                        try {
                            return hashtableHashFunction((hashtable.HashTable) obj);
                        } catch (ClassCastException e8) {
                            throw new WrongType(e8, "hashtable-hash-function", 1, obj);
                        }
                    case 24:
                        try {
                            return isHashtableMutable((hashtable.HashTable) obj);
                        } catch (ClassCastException e9) {
                            throw new WrongType(e9, "hashtable-mutable?", 1, obj);
                        }
                    case 25:
                        return Integer.valueOf(equalHash(obj));
                    case 26:
                        try {
                            return Integer.valueOf(stringHash((CharSequence) obj));
                        } catch (ClassCastException e10) {
                            throw new WrongType(e10, "string-hash", 1, obj);
                        }
                    case 27:
                        try {
                            return Integer.valueOf(stringCiHash((CharSequence) obj));
                        } catch (ClassCastException e11) {
                            throw new WrongType(e11, "string-ci-hash", 1, obj);
                        }
                    case 28:
                        try {
                            return Integer.valueOf(symbolHash((Symbol) obj));
                        } catch (ClassCastException e12) {
                            throw new WrongType(e12, "symbol-hash", 1, obj);
                        }
                    default:
                        return super.apply1(moduleMethod, obj);
                }
            } else {
                try {
                    return Integer.valueOf(hashtableSize((hashtable.HashTable) obj));
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "hashtable-size", 1, obj);
                }
            }
        }
    }

    public static int symbolHash(Symbol symbol) {
        return symbol.hashCode();
    }
}
