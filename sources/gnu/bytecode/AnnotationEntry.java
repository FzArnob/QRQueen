package gnu.bytecode;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnnotationEntry implements Annotation {
    ClassType annotationType;
    LinkedHashMap<String, Object> elementsValue = new LinkedHashMap<>(10);

    public ClassType getAnnotationType() {
        return this.annotationType;
    }

    public void addMember(String str, Object obj) {
        this.elementsValue.put(str, obj);
    }

    public Class<? extends Annotation> annotationType() {
        return this.annotationType.getReflectClass();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AnnotationEntry)) {
            return false;
        }
        AnnotationEntry annotationEntry = (AnnotationEntry) obj;
        if (!getAnnotationType().getName().equals(annotationEntry.getAnnotationType().getName())) {
            return false;
        }
        for (Map.Entry next : this.elementsValue.entrySet()) {
            Object value = next.getValue();
            Object obj2 = annotationEntry.elementsValue.get((String) next.getKey());
            if (value != obj2 && (value == null || obj2 == null || !value.equals(obj2))) {
                return false;
            }
        }
        for (Map.Entry next2 : annotationEntry.elementsValue.entrySet()) {
            Object value2 = next2.getValue();
            Object obj3 = this.elementsValue.get((String) next2.getKey());
            if (obj3 != value2 && (obj3 == null || value2 == null || !obj3.equals(value2))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (Map.Entry next : this.elementsValue.entrySet()) {
            i += next.getValue().hashCode() ^ (((String) next.getKey()).hashCode() * 127);
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(getAnnotationType().getName());
        sb.append('(');
        int i = 0;
        for (Map.Entry next : this.elementsValue.entrySet()) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append((String) next.getKey());
            sb.append('=');
            sb.append(next.getValue());
            i++;
        }
        sb.append(')');
        return sb.toString();
    }
}
