package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class TableLayout implements Layout {
    private final Handler handler = new Handler();
    private final android.widget.TableLayout layoutManager;
    private int numColumns;
    private int numRows;

    TableLayout(Context context, int i, int i2) {
        this.layoutManager = new android.widget.TableLayout(context);
        this.numColumns = i;
        this.numRows = i2;
        for (int i3 = 0; i3 < i2; i3++) {
            TableRow tableRow = new TableRow(context);
            for (int i4 = 0; i4 < i; i4++) {
                tableRow.addView(newEmptyCellView(), i4, newEmptyCellLayoutParams());
            }
            this.layoutManager.addView(tableRow, i3, new TableLayout.LayoutParams());
        }
    }

    /* access modifiers changed from: package-private */
    public int getNumColumns() {
        return this.numColumns;
    }

    /* access modifiers changed from: package-private */
    public void setNumColumns(int i) {
        int i2 = this.numColumns;
        int i3 = 0;
        if (i > i2) {
            this.layoutManager.getContext();
            while (i3 < this.numRows) {
                TableRow tableRow = (TableRow) this.layoutManager.getChildAt(i3);
                for (int i4 = this.numColumns; i4 < i; i4++) {
                    tableRow.addView(newEmptyCellView(), i4, newEmptyCellLayoutParams());
                }
                i3++;
            }
            this.numColumns = i;
        } else if (i < i2) {
            while (i3 < this.numRows) {
                ((TableRow) this.layoutManager.getChildAt(i3)).removeViews(i, this.numColumns - i);
                i3++;
            }
            this.numColumns = i;
        }
    }

    /* access modifiers changed from: package-private */
    public int getNumRows() {
        return this.numRows;
    }

    /* access modifiers changed from: package-private */
    public void setNumRows(int i) {
        int i2 = this.numRows;
        if (i > i2) {
            Context context = this.layoutManager.getContext();
            for (int i3 = this.numRows; i3 < i; i3++) {
                TableRow tableRow = new TableRow(context);
                for (int i4 = 0; i4 < this.numColumns; i4++) {
                    tableRow.addView(newEmptyCellView(), i4, newEmptyCellLayoutParams());
                }
                this.layoutManager.addView(tableRow, i3, new TableLayout.LayoutParams());
            }
            this.numRows = i;
        } else if (i < i2) {
            this.layoutManager.removeViews(i, i2 - i);
            this.numRows = i;
        }
    }

    public ViewGroup getLayoutManager() {
        return this.layoutManager;
    }

    public void add(AndroidViewComponent androidViewComponent) {
        androidViewComponent.getView().setLayoutParams(newCellLayoutParams());
        addChildLater(androidViewComponent);
    }

    private void addChildLater(final AndroidViewComponent androidViewComponent) {
        this.handler.post(new Runnable() {
            public final void run() {
                TableLayout.this.addChild(androidViewComponent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void addChild(AndroidViewComponent androidViewComponent) {
        int Row = androidViewComponent.Row();
        int Column = androidViewComponent.Column();
        if (Row == -1 || Column == -1) {
            addChildLater(androidViewComponent);
        } else if (Row < 0 || Row >= this.numRows) {
            Log.e("TableLayout", "Child has illegal Row property: ".concat(String.valueOf(androidViewComponent)));
        } else if (Column < 0 || Column >= this.numColumns) {
            Log.e("TableLayout", "Child has illegal Column property: ".concat(String.valueOf(androidViewComponent)));
        } else {
            TableRow tableRow = (TableRow) this.layoutManager.getChildAt(Row);
            tableRow.removeViewAt(Column);
            View view = androidViewComponent.getView();
            tableRow.addView(view, Column, view.getLayoutParams());
        }
    }

    private View newEmptyCellView() {
        return new TextView(this.layoutManager.getContext());
    }

    private static TableRow.LayoutParams newEmptyCellLayoutParams() {
        return new TableRow.LayoutParams(0, 0);
    }

    private static TableRow.LayoutParams newCellLayoutParams() {
        return new TableRow.LayoutParams();
    }
}
