package kawa;

import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import kawa.ReplDocument;

public class GuiConsole extends JFrame implements ActionListener, ReplDocument.DocumentCloseListener {
    private static String CLOSE = "Close";
    private static String EXIT = "Exit";
    private static String NEW = "New";
    private static String NEW_SHARED = "New (Shared)";
    private static String PURGE_MESSAGE = "Purge Buffer";
    static int window_number;
    ReplDocument document;
    ReplPane pane;

    public static void main(String[] strArr) {
        repl.noConsole = false;
        int processArgs = repl.processArgs(strArr, 0, strArr.length);
        repl.getLanguage();
        repl.setArgs(strArr, processArgs);
        repl.checkInitFile();
        new GuiConsole();
    }

    public GuiConsole() {
        this(Language.getDefaultLanguage(), Environment.getCurrent(), false);
    }

    public GuiConsole(ReplDocument replDocument) {
        super("Kawa");
        init(replDocument);
    }

    /* access modifiers changed from: package-private */
    public void init(ReplDocument replDocument) {
        this.document = replDocument;
        replDocument.addDocumentCloseListener(this);
        this.pane = new ReplPane(this.document);
        window_number++;
        setLayout(new BorderLayout(0, 0));
        add("Center", new JScrollPane(this.pane));
        setupMenus();
        int i = window_number;
        setLocation(i * 100, i * 50);
        setSize(700, 500);
        setVisible(true);
    }

    public GuiConsole(Language language, Environment environment, boolean z) {
        super("Kawa");
        repl.getLanguage();
        init(new ReplDocument(language, environment, z));
    }

    public void closed(ReplDocument replDocument) {
        close();
    }

    /* access modifiers changed from: package-private */
    public void close() {
        this.document.removeDocumentCloseListener(this);
        dispose();
    }

    private void setupMenus() {
        AnonymousClass1 r0 = new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                GuiConsole.this.close();
            }
        };
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        Menu menu2 = new Menu("Utilities");
        menuBar.add(menu);
        menuBar.add(menu2);
        MenuItem menuItem = new MenuItem(NEW);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        MenuItem menuItem2 = new MenuItem(NEW_SHARED);
        menuItem2.addActionListener(this);
        menu.add(menuItem2);
        MenuItem menuItem3 = new MenuItem(CLOSE);
        menuItem3.addActionListener(this);
        menu.add(menuItem3);
        MenuItem menuItem4 = new MenuItem(EXIT);
        menuItem4.addActionListener(this);
        addWindowListener(r0);
        menu.add(menuItem4);
        MenuItem menuItem5 = new MenuItem(PURGE_MESSAGE);
        menuItem5.addActionListener(this);
        menu2.add(menuItem5);
        setMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals(NEW)) {
            new GuiConsole(this.document.language, Environment.getGlobal(), false);
        } else if (actionCommand.equals(NEW_SHARED)) {
            new GuiConsole(this.document.language, this.document.environment, true);
        } else if (actionCommand.equals(EXIT)) {
            System.exit(0);
        } else if (actionCommand.equals(CLOSE)) {
            close();
        } else if (actionCommand.equals(PURGE_MESSAGE)) {
            this.pane.document.deleteOldText();
        } else {
            OutPort outDefault = OutPort.outDefault();
            outDefault.println("Unknown menu action: " + actionCommand);
        }
    }
}
