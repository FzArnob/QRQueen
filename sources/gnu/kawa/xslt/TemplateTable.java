package gnu.kawa.xslt;

import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class TemplateTable {
    static final TemplateTable nullModeTable = new TemplateTable(XSLT.nullMode);
    TemplateEntry entries;
    Symbol name;

    public TemplateTable(Symbol symbol) {
        this.name = symbol;
    }

    static TemplateTable getTemplateTable(Symbol symbol) {
        if (symbol == XSLT.nullMode) {
            return nullModeTable;
        }
        return null;
    }

    public void enter(String str, double d, Procedure procedure) {
        TemplateEntry templateEntry = new TemplateEntry();
        templateEntry.procedure = procedure;
        templateEntry.priority = d;
        templateEntry.pattern = str;
        templateEntry.next = this.entries;
        this.entries = templateEntry;
    }

    public Procedure find(String str) {
        for (TemplateEntry templateEntry = this.entries; templateEntry != null; templateEntry = templateEntry.next) {
            if (templateEntry.pattern.equals(str)) {
                return templateEntry.procedure;
            }
        }
        return null;
    }
}
