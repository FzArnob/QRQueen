package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.kawa.hashtable;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.hashtables;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;

/* compiled from: srfi69.scm */
public class srfi69 extends ModuleBody {
    public static final srfi69 $instance;
    static final IntNum Lit0 = IntNum.make(64);
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
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod alist$Mn$Grhash$Mntable;
    public static final ModuleMethod hash;
    public static final ModuleMethod hash$Mnby$Mnidentity;
    public static final ModuleMethod hash$Mntable$Mn$Gralist;
    public static final ModuleMethod hash$Mntable$Mncopy;
    public static final Location hash$Mntable$Mndelete$Ex = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mndelete$Ex");
    public static final ModuleMethod hash$Mntable$Mnequivalence$Mnfunction;
    public static final Location hash$Mntable$Mnexists$Qu = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mncontains$Qu");
    public static final ModuleMethod hash$Mntable$Mnfold;
    public static final ModuleMethod hash$Mntable$Mnhash$Mnfunction;
    public static final ModuleMethod hash$Mntable$Mnkeys;
    public static final ModuleMethod hash$Mntable$Mnmerge$Ex;
    public static final ModuleMethod hash$Mntable$Mnref;
    public static final ModuleMethod hash$Mntable$Mnref$Sldefault;
    public static final Location hash$Mntable$Mnset$Ex = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mnset$Ex");
    public static final Location hash$Mntable$Mnsize = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mnsize");
    public static final ModuleMethod hash$Mntable$Mnupdate$Ex;
    public static final ModuleMethod hash$Mntable$Mnupdate$Ex$Sldefault;
    public static final ModuleMethod hash$Mntable$Mnvalues;
    public static final ModuleMethod hash$Mntable$Mnwalk;
    public static final Location hash$Mntable$Qu = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Qu");
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod make$Mnhash$Mntable;
    public static final ModuleMethod string$Mnci$Mnhash;
    public static final ModuleMethod string$Mnhash;

    public srfi69() {
        ModuleInfo.register(this);
    }

    public static hashtable.HashTable alist$To$HashTable(Object obj) {
        return alist$To$HashTable(obj, Scheme.isEqual);
    }

    public static hashtable.HashTable alist$To$HashTable(Object obj, Object obj2) {
        return alist$To$HashTable(obj, obj2, appropriateHashFunctionFor(obj2));
    }

    public static Object hash(Object obj) {
        return hash(obj, (IntNum) null);
    }

    public static Object hashByIdentity(Object obj) {
        return hashByIdentity(obj, (IntNum) null);
    }

    public static Object hashTableRef(hashtable.HashTable hashTable, Object obj) {
        return hashTableRef(hashTable, obj, Boolean.FALSE);
    }

    public static void hashTableUpdate$Ex(hashtable.HashTable hashTable, Object obj, Object obj2) {
        hashTableUpdate$Ex(hashTable, obj, obj2, Boolean.FALSE);
    }

    static Object lambda1(Object obj) {
        return obj;
    }

    public static hashtable.HashTable makeHashTable() {
        return makeHashTable(Scheme.isEqual);
    }

    public static hashtable.HashTable makeHashTable(Procedure procedure) {
        return makeHashTable(procedure, appropriateHashFunctionFor(procedure), 64);
    }

    public static hashtable.HashTable makeHashTable(Procedure procedure, Procedure procedure2) {
        return makeHashTable(procedure, procedure2, 64);
    }

    public static Object stringCiHash(Object obj) {
        return stringCiHash(obj, (IntNum) null);
    }

    public static Object stringHash(CharSequence charSequence) {
        return stringHash(charSequence, (IntNum) null);
    }

    static Object symbolHash(Symbol symbol) {
        return symbolHash(symbol, (IntNum) null);
    }

    static Object vectorHash(Object obj) {
        return vectorHash(obj, (IntNum) null);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("hash-table-values").readResolve();
        Lit19 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("hash-table-keys").readResolve();
        Lit18 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("hash-table-merge!").readResolve();
        Lit17 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("hash-table-copy").readResolve();
        Lit16 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("hash-table->alist").readResolve();
        Lit15 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("alist->hash-table").readResolve();
        Lit14 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("hash-table-fold").readResolve();
        Lit13 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("hash-table-walk").readResolve();
        Lit12 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("hash-table-update!/default").readResolve();
        Lit11 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("hash-table-update!").readResolve();
        Lit10 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("hash-table-ref/default").readResolve();
        Lit9 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("hash-table-ref").readResolve();
        Lit8 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("make-hash-table").readResolve();
        Lit7 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("hash-table-hash-function").readResolve();
        Lit6 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("hash-table-equivalence-function").readResolve();
        Lit5 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("hash-by-identity").readResolve();
        Lit4 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("hash").readResolve();
        Lit3 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("string-ci-hash").readResolve();
        Lit2 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("string-hash").readResolve();
        Lit1 = simpleSymbol23;
        srfi69 srfi69 = new srfi69();
        $instance = srfi69;
        string$Mnhash = new ModuleMethod(srfi69, 1, simpleSymbol23, 8193);
        string$Mnci$Mnhash = new ModuleMethod(srfi69, 3, simpleSymbol21, 8193);
        hash = new ModuleMethod(srfi69, 5, simpleSymbol19, 8193);
        hash$Mnby$Mnidentity = new ModuleMethod(srfi69, 7, simpleSymbol17, 8193);
        hash$Mntable$Mnequivalence$Mnfunction = new ModuleMethod(srfi69, 9, simpleSymbol15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mnhash$Mnfunction = new ModuleMethod(srfi69, 10, simpleSymbol14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnhash$Mntable = new ModuleMethod(srfi69, 11, simpleSymbol13, 12288);
        hash$Mntable$Mnref = new ModuleMethod(srfi69, 15, simpleSymbol12, 12290);
        hash$Mntable$Mnref$Sldefault = new ModuleMethod(srfi69, 17, simpleSymbol11, 12291);
        hash$Mntable$Mnupdate$Ex = new ModuleMethod(srfi69, 18, simpleSymbol10, 16387);
        hash$Mntable$Mnupdate$Ex$Sldefault = new ModuleMethod(srfi69, 20, simpleSymbol9, 16388);
        hash$Mntable$Mnwalk = new ModuleMethod(srfi69, 21, simpleSymbol8, 8194);
        hash$Mntable$Mnfold = new ModuleMethod(srfi69, 22, simpleSymbol7, 12291);
        ModuleMethod moduleMethod = new ModuleMethod(srfi69, 23, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:166");
        lambda$Fn1 = moduleMethod;
        alist$Mn$Grhash$Mntable = new ModuleMethod(srfi69, 24, simpleSymbol6, 16385);
        hash$Mntable$Mn$Gralist = new ModuleMethod(srfi69, 28, simpleSymbol5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mncopy = new ModuleMethod(srfi69, 29, simpleSymbol22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        hash$Mntable$Mnmerge$Ex = new ModuleMethod(srfi69, 30, simpleSymbol20, 8194);
        ModuleMethod moduleMethod2 = new ModuleMethod(srfi69, 31, (Object) null, 12291);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:183");
        lambda$Fn2 = moduleMethod2;
        hash$Mntable$Mnkeys = new ModuleMethod(srfi69, 32, simpleSymbol18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod3 = new ModuleMethod(srfi69, 33, (Object) null, 12291);
        moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:186");
        lambda$Fn3 = moduleMethod3;
        hash$Mntable$Mnvalues = new ModuleMethod(srfi69, 34, simpleSymbol16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        srfi69.run();
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 1) {
            if (i == 3) {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            } else if (i == 5) {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            } else if (i == 7) {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            } else if (i != 32) {
                if (i != 34) {
                    if (i == 23) {
                        callContext.value1 = obj;
                        callContext.proc = moduleMethod;
                        callContext.pc = 1;
                        return 0;
                    } else if (i == 24) {
                        callContext.value1 = obj;
                        callContext.proc = moduleMethod;
                        callContext.pc = 1;
                        return 0;
                    } else if (i != 28) {
                        if (i != 29) {
                            switch (i) {
                                case 9:
                                    if (!(obj instanceof hashtable.HashTable)) {
                                        return -786431;
                                    }
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                case 10:
                                    if (!(obj instanceof hashtable.HashTable)) {
                                        return -786431;
                                    }
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                case 11:
                                    if (!(obj instanceof Procedure)) {
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
            } else if (!(obj instanceof hashtable.HashTable)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
        } else if (!(obj instanceof CharSequence)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 1) {
            if (i == 3) {
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 5) {
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 7) {
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i != 11) {
                if (i != 15) {
                    if (i != 21) {
                        if (i == 24) {
                            callContext.value1 = obj;
                            callContext.value2 = obj2;
                            callContext.proc = moduleMethod;
                            callContext.pc = 2;
                            return 0;
                        } else if (i != 30) {
                            return super.match2(moduleMethod, obj, obj2, callContext);
                        } else {
                            if (!(obj instanceof hashtable.HashTable)) {
                                return -786431;
                            }
                            callContext.value1 = obj;
                            if (!(obj2 instanceof hashtable.HashTable)) {
                                return -786430;
                            }
                            callContext.value2 = obj2;
                            callContext.proc = moduleMethod;
                            callContext.pc = 2;
                            return 0;
                        }
                    } else if (!(obj instanceof hashtable.HashTable)) {
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
        } else if (!(obj instanceof CharSequence)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj2) == null) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object stringHash(CharSequence charSequence, IntNum intNum) {
        int hashCode = charSequence.hashCode();
        return intNum == null ? Integer.valueOf(hashCode) : IntNum.modulo(IntNum.make(hashCode), intNum);
    }

    public static Object stringCiHash(Object obj, IntNum intNum) {
        int hashCode = obj.toString().toLowerCase().hashCode();
        return intNum == null ? Integer.valueOf(hashCode) : IntNum.modulo(IntNum.make(hashCode), intNum);
    }

    static Object symbolHash(Symbol symbol, IntNum intNum) {
        int hashCode = symbol.hashCode();
        return intNum == null ? Integer.valueOf(hashCode) : IntNum.modulo(IntNum.make(hashCode), intNum);
    }

    public static Object hash(Object obj, IntNum intNum) {
        int hashCode = obj == null ? 0 : obj.hashCode();
        return intNum == null ? Integer.valueOf(hashCode) : IntNum.modulo(IntNum.make(hashCode), intNum);
    }

    public static Object hashByIdentity(Object obj, IntNum intNum) {
        int identityHashCode = System.identityHashCode(obj);
        return intNum == null ? Integer.valueOf(identityHashCode) : IntNum.modulo(IntNum.make(identityHashCode), intNum);
    }

    static Object vectorHash(Object obj, IntNum intNum) {
        int hashCode = obj.hashCode();
        return intNum == null ? Integer.valueOf(hashCode) : IntNum.modulo(IntNum.make(hashCode), intNum);
    }

    public static Procedure hashTableEquivalenceFunction(hashtable.HashTable hashTable) {
        return hashTable.equivalenceFunction;
    }

    public static Procedure hashTableHashFunction(hashtable.HashTable hashTable) {
        return hashTable.hashFunction;
    }

    static Procedure appropriateHashFunctionFor(Object obj) {
        boolean z = true;
        boolean z2 = obj == Scheme.isEq;
        Object obj2 = z2 ? hash$Mnby$Mnidentity : z2 ? Boolean.TRUE : Boolean.FALSE;
        if (obj2 == Boolean.FALSE) {
            boolean z3 = obj == strings.string$Eq$Qu;
            obj2 = z3 ? string$Mnhash : z3 ? Boolean.TRUE : Boolean.FALSE;
            if (obj2 == Boolean.FALSE) {
                if (obj != unicode.string$Mnci$Eq$Qu) {
                    z = false;
                }
                Object obj3 = z ? string$Mnci$Mnhash : z ? Boolean.TRUE : Boolean.FALSE;
                return obj3 != Boolean.FALSE ? (Procedure) obj3 : hash;
            }
        }
        return (Procedure) obj2;
    }

    public Object apply0(ModuleMethod moduleMethod) {
        return moduleMethod.selector == 11 ? makeHashTable() : super.apply0(moduleMethod);
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        if (moduleMethod.selector != 11) {
            return super.match0(moduleMethod, callContext);
        }
        callContext.proc = moduleMethod;
        callContext.pc = 0;
        return 0;
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 11) {
            if (i != 15) {
                if (i != 22) {
                    if (i == 24) {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.value3 = obj3;
                        callContext.proc = moduleMethod;
                        callContext.pc = 3;
                        return 0;
                    } else if (i == 31) {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.value3 = obj3;
                        callContext.proc = moduleMethod;
                        callContext.pc = 3;
                        return 0;
                    } else if (i == 33) {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.value3 = obj3;
                        callContext.proc = moduleMethod;
                        callContext.pc = 3;
                        return 0;
                    } else if (i != 17) {
                        if (i != 18) {
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
                } else if (!(obj instanceof hashtable.HashTable)) {
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

    public static hashtable.HashTable makeHashTable(Procedure procedure, Procedure procedure2, int i) {
        return new hashtable.HashTable(procedure, procedure2, i);
    }

    public static Object hashTableRef(hashtable.HashTable hashTable, Object obj, Object obj2) {
        HashNode node = hashTable.getNode(obj);
        if (node != null) {
            return node.getValue();
        }
        if (obj2 != Boolean.FALSE) {
            return Scheme.applyToArgs.apply1(obj2);
        }
        return misc.error$V("hash-table-ref: no value associated with", new Object[]{obj});
    }

    public static Object hashTableRef$SlDefault(hashtable.HashTable hashTable, Object obj, Object obj2) {
        return hashTable.get(obj, obj2);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 18) {
            if (i != 20) {
                if (i != 24) {
                    return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            } else if (!(obj instanceof hashtable.HashTable)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.value4 = obj4;
                callContext.proc = moduleMethod;
                callContext.pc = 4;
                return 0;
            }
        } else if (!(obj instanceof hashtable.HashTable)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    public static void hashTableUpdate$Ex(hashtable.HashTable hashTable, Object obj, Object obj2, Object obj3) {
        hashtable.hashtableCheckMutable(hashTable);
        HashNode node = hashTable.getNode(obj);
        if (node != null) {
            node.setValue(Scheme.applyToArgs.apply2(obj2, node.getValue()));
        } else if (obj3 != Boolean.FALSE) {
            hashtables.hashtableSet$Ex(hashTable, obj, Scheme.applyToArgs.apply2(obj2, Scheme.applyToArgs.apply1(obj3)));
        } else {
            misc.error$V("hash-table-update!: no value exists for key", new Object[]{obj});
        }
    }

    public static void hashTableUpdate$Ex$SlDefault(hashtable.HashTable hashTable, Object obj, Object obj2, Object obj3) {
        hashtable.hashtableCheckMutable(hashTable);
        HashNode node = hashTable.getNode(obj);
        if (node == null) {
            hashtables.hashtableSet$Ex(hashTable, obj, Scheme.applyToArgs.apply2(obj2, obj3));
        } else {
            node.setValue(Scheme.applyToArgs.apply2(obj2, node.getValue()));
        }
    }

    public static void hashTableWalk(hashtable.HashTable hashTable, Procedure procedure) {
        hashTable.walk(procedure);
    }

    public static Object hashTableFold(hashtable.HashTable hashTable, Procedure procedure, Object obj) {
        return hashTable.fold(procedure, obj);
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        int i = moduleMethod.selector;
        if (i == 18) {
            try {
                hashTableUpdate$Ex((hashtable.HashTable) obj, obj2, obj3, obj4);
                return Values.empty;
            } catch (ClassCastException e) {
                throw new WrongType(e, "hash-table-update!", 1, obj);
            }
        } else if (i == 20) {
            try {
                hashTableUpdate$Ex$SlDefault((hashtable.HashTable) obj, obj2, obj3, obj4);
                return Values.empty;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "hash-table-update!/default", 1, obj);
            }
        } else if (i != 24) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        } else {
            return alist$To$HashTable(obj, obj2, obj3, obj4);
        }
    }

    public static hashtable.HashTable alist$To$HashTable(Object obj, Object obj2, Object obj3) {
        Object[] objArr = new Object[2];
        objArr[0] = Lit0;
        try {
            objArr[1] = Integer.valueOf(lists.length((LList) obj) * 2);
            return alist$To$HashTable(obj, obj2, obj3, numbers.max(objArr));
        } catch (ClassCastException e) {
            throw new WrongType(e, "length", 1, obj);
        }
    }

    public static hashtable.HashTable alist$To$HashTable(Object obj, Object obj2, Object obj3, Object obj4) {
        try {
            try {
                try {
                    hashtable.HashTable makeHashTable = makeHashTable((Procedure) obj2, (Procedure) obj3, ((Number) obj4).intValue());
                    while (obj != LList.Empty) {
                        try {
                            Pair pair = (Pair) obj;
                            Object car = pair.getCar();
                            hashTableUpdate$Ex$SlDefault(makeHashTable, lists.car.apply1(car), lambda$Fn1, lists.cdr.apply1(car));
                            obj = pair.getCdr();
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "arg0", -2, obj);
                        }
                    }
                    return makeHashTable;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "make-hash-table", 2, obj4);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "make-hash-table", 1, obj3);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "make-hash-table", 0, obj2);
        }
    }

    public static Object hashTable$To$Alist(hashtable.HashTable hashTable) {
        return hashTable.toAlist();
    }

    public static hashtable.HashTable hashTableCopy(hashtable.HashTable hashTable) {
        return new hashtable.HashTable(hashTable, true);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 1) {
            try {
                try {
                    return stringHash((CharSequence) obj, LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-hash", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-hash", 1, obj);
            }
        } else if (i == 3) {
            try {
                return stringCiHash(obj, LangObjType.coerceIntNum(obj2));
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "string-ci-hash", 2, obj2);
            }
        } else if (i == 5) {
            try {
                return hash(obj, LangObjType.coerceIntNum(obj2));
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "hash", 2, obj2);
            }
        } else if (i == 7) {
            try {
                return hashByIdentity(obj, LangObjType.coerceIntNum(obj2));
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "hash-by-identity", 2, obj2);
            }
        } else if (i == 11) {
            try {
                try {
                    return makeHashTable((Procedure) obj, (Procedure) obj2);
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "make-hash-table", 2, obj2);
                }
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "make-hash-table", 1, obj);
            }
        } else if (i == 15) {
            try {
                return hashTableRef((hashtable.HashTable) obj, obj2);
            } catch (ClassCastException e8) {
                throw new WrongType(e8, "hash-table-ref", 1, obj);
            }
        } else if (i == 21) {
            try {
                try {
                    hashTableWalk((hashtable.HashTable) obj, (Procedure) obj2);
                    return Values.empty;
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "hash-table-walk", 2, obj2);
                }
            } catch (ClassCastException e10) {
                throw new WrongType(e10, "hash-table-walk", 1, obj);
            }
        } else if (i == 24) {
            return alist$To$HashTable(obj, obj2);
        } else {
            if (i != 30) {
                return super.apply2(moduleMethod, obj, obj2);
            }
            try {
                try {
                    hashTableMerge$Ex((hashtable.HashTable) obj, (hashtable.HashTable) obj2);
                    return Values.empty;
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "hash-table-merge!", 2, obj2);
                }
            } catch (ClassCastException e12) {
                throw new WrongType(e12, "hash-table-merge!", 1, obj);
            }
        }
    }

    public static void hashTableMerge$Ex(hashtable.HashTable hashTable, hashtable.HashTable hashTable2) {
        hashTable.putAll(hashTable2);
    }

    public static Object hashTableKeys(hashtable.HashTable hashTable) {
        return hashTableFold(hashTable, lambda$Fn2, LList.Empty);
    }

    static Pair lambda2(Object obj, Object obj2, Object obj3) {
        return lists.cons(obj, obj3);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            try {
                return stringHash((CharSequence) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-hash", 1, obj);
            }
        } else if (i == 3) {
            return stringCiHash(obj);
        } else {
            if (i == 5) {
                return hash(obj);
            }
            if (i == 7) {
                return hashByIdentity(obj);
            }
            if (i == 32) {
                try {
                    return hashTableKeys((hashtable.HashTable) obj);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "hash-table-keys", 1, obj);
                }
            } else if (i == 34) {
                try {
                    return hashTableValues((hashtable.HashTable) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "hash-table-values", 1, obj);
                }
            } else if (i == 23) {
                return lambda1(obj);
            } else {
                if (i == 24) {
                    return alist$To$HashTable(obj);
                }
                if (i == 28) {
                    try {
                        return hashTable$To$Alist((hashtable.HashTable) obj);
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "hash-table->alist", 1, obj);
                    }
                } else if (i != 29) {
                    switch (i) {
                        case 9:
                            try {
                                return hashTableEquivalenceFunction((hashtable.HashTable) obj);
                            } catch (ClassCastException e5) {
                                throw new WrongType(e5, "hash-table-equivalence-function", 1, obj);
                            }
                        case 10:
                            try {
                                return hashTableHashFunction((hashtable.HashTable) obj);
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "hash-table-hash-function", 1, obj);
                            }
                        case 11:
                            try {
                                return makeHashTable((Procedure) obj);
                            } catch (ClassCastException e7) {
                                throw new WrongType(e7, "make-hash-table", 1, obj);
                            }
                        default:
                            return super.apply1(moduleMethod, obj);
                    }
                } else {
                    try {
                        return hashTableCopy((hashtable.HashTable) obj);
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "hash-table-copy", 1, obj);
                    }
                }
            }
        }
    }

    public static Object hashTableValues(hashtable.HashTable hashTable) {
        return hashTableFold(hashTable, lambda$Fn3, LList.Empty);
    }

    static Pair lambda3(Object obj, Object obj2, Object obj3) {
        return lists.cons(obj2, obj3);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        int i = moduleMethod.selector;
        if (i == 11) {
            try {
                try {
                    try {
                        return makeHashTable((Procedure) obj, (Procedure) obj2, ((Number) obj3).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "make-hash-table", 3, obj3);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "make-hash-table", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "make-hash-table", 1, obj);
            }
        } else if (i == 15) {
            try {
                return hashTableRef((hashtable.HashTable) obj, obj2, obj3);
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "hash-table-ref", 1, obj);
            }
        } else if (i == 22) {
            try {
                try {
                    return hashTableFold((hashtable.HashTable) obj, (Procedure) obj2, obj3);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "hash-table-fold", 2, obj2);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "hash-table-fold", 1, obj);
            }
        } else if (i == 24) {
            return alist$To$HashTable(obj, obj2, obj3);
        } else {
            if (i == 31) {
                return lambda2(obj, obj2, obj3);
            }
            if (i == 33) {
                return lambda3(obj, obj2, obj3);
            }
            if (i == 17) {
                try {
                    return hashTableRef$SlDefault((hashtable.HashTable) obj, obj2, obj3);
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "hash-table-ref/default", 1, obj);
                }
            } else if (i != 18) {
                return super.apply3(moduleMethod, obj, obj2, obj3);
            } else {
                try {
                    hashTableUpdate$Ex((hashtable.HashTable) obj, obj2, obj3);
                    return Values.empty;
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "hash-table-update!", 1, obj);
                }
            }
        }
    }
}
