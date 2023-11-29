package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;

public class RecordConstructor extends ProcedureN {
    Field[] fields;
    ClassType type;

    public RecordConstructor(ClassType classType, Field[] fieldArr) {
        this.type = classType;
        this.fields = fieldArr;
    }

    public RecordConstructor(Class cls, Field[] fieldArr) {
        this((ClassType) Type.make(cls), fieldArr);
    }

    public RecordConstructor(Class cls) {
        init((ClassType) Type.make(cls));
    }

    public RecordConstructor(ClassType classType) {
        init(classType);
    }

    private void init(ClassType classType) {
        this.type = classType;
        Field fields2 = classType.getFields();
        int i = 0;
        int i2 = 0;
        for (Field field = fields2; field != null; field = field.getNext()) {
            if ((field.getModifiers() & 9) == 1) {
                i2++;
            }
        }
        this.fields = new Field[i2];
        while (fields2 != null) {
            if ((fields2.getModifiers() & 9) == 1) {
                this.fields[i] = fields2;
                i++;
            }
            fields2 = fields2.getNext();
        }
    }

    public RecordConstructor(Class cls, Object obj) {
        this((ClassType) Type.make(cls), obj);
    }

    public RecordConstructor(ClassType classType, Object obj) {
        this.type = classType;
        if (obj == null) {
            init(classType);
            return;
        }
        int i = 0;
        int listLength = LList.listLength(obj, false);
        this.fields = new Field[listLength];
        Field fields2 = classType.getFields();
        while (i < listLength) {
            Pair pair = (Pair) obj;
            String obj2 = pair.getCar().toString();
            Field field = fields2;
            while (field != null) {
                if (field.getSourceName() == obj2) {
                    this.fields[i] = field;
                    obj = pair.getCdr();
                    i++;
                } else {
                    field = field.getNext();
                }
            }
            throw new RuntimeException("no such field " + obj2 + " in " + classType.getName());
        }
    }

    public int numArgs() {
        int length = this.fields.length;
        return length | (length << 12);
    }

    public String getName() {
        return this.type.getName() + " constructor";
    }

    public Object applyN(Object[] objArr) {
        try {
            Object newInstance = this.type.getReflectClass().newInstance();
            if (objArr.length == this.fields.length) {
                int i = 0;
                while (i < objArr.length) {
                    Field field = this.fields[i];
                    try {
                        field.getReflectField().set(newInstance, objArr[i]);
                        i++;
                    } catch (Exception e) {
                        throw new WrappedException("illegal access for field " + field.getName(), e);
                    }
                }
                return newInstance;
            }
            throw new WrongArguments(this, objArr.length);
        } catch (InstantiationException e2) {
            throw new GenericError(e2.toString());
        } catch (IllegalAccessException e3) {
            throw new GenericError(e3.toString());
        }
    }
}
