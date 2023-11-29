package com.google.appinventor.components.runtime;

import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatEditText;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>A box for the user to enter text.  The initial or user-entered text value is in the <code>Text</code> property.  If blank, the <code>Hint</code> property, which appears as faint text in the box, can provide the user with guidance as to what to type.</p><p>The <code>MultiLine</code> property determines if the text can havemore than one line.  For a single line text box, the keyboard will closeautomatically when the user presses the Done key.  To close the keyboard for multiline text boxes, the app should use  the HideKeyboard method or  rely on the user to press the Back key.</p><p>The <code> NumbersOnly</code> property restricts the keyboard to acceptnumeric input only.</p><p>Other properties affect the appearance of the text box (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be used (<code>Enabled</code>).</p><p>Text boxes are usually used with the <code>Button</code> component, with the user clicking on the button when text entry is complete.</p><p>If the text entered by the user should not be displayed, use <code>PasswordTextBox</code> instead.</p>", helpUrl = "https://docs.kodular.io/components/user-interface/textbox/", version = 13)
public final class TextBox extends TextBoxBase {
    private boolean K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0;
    private int TDuNe2Upxoi7WX7QZc6R8eUnFPyyDFw3hP7z3w6U8jMxQwJRQM03zSa9HzWXRv1 = 0;
    private String UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5;
    private boolean hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s = false;
    private int wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD = 1;
    private boolean wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO;

    public TextBox(ComponentContainer componentContainer) {
        super(componentContainer, new AppCompatEditText(componentContainer.$context()));
        HighlightColor(0);
        MultiLine(false);
        ReadOnly(false);
        InputType(1);
        this.view.setImeOptions(6);
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, then this text box accepts only numbers as keyboard input.  Numbers can include a decimal point and an optional leading minus sign.  This applies to keyboard input only.  Even if NumbersOnly is true, you can use [set Text to] to enter any text at all.")
    public final boolean NumbersOnly() {
        return this.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @Deprecated
    @SimpleProperty(description = "If true, then this text box accepts only numbers as keyboard input.  Numbers can include a decimal point and an optional leading minus sign.  This applies to keyboard input only.  Even if NumbersOnly is true, you can use [set Text to] to enter any text at all.")
    public final void NumbersOnly(boolean z) {
        if (z) {
            this.view.setInputType(12290);
        } else {
            this.view.setInputType(131073);
        }
        this.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0 = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The input type you want for this TextBox.")
    public final int InputType() {
        return this.wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD;
    }

    @DesignerProperty(defaultValue = "1", editorType = "input_type")
    @SimpleProperty
    public final void InputType(int i) {
        switch (i) {
            case 1:
                this.view.setInputType(1);
                break;
            case 2:
                this.view.setInputType(129);
                break;
            case 3:
                this.view.setInputType(97);
                break;
            case 4:
                this.view.setInputType(33);
                break;
            case 5:
                this.view.setInputType(12290);
                break;
            case 6:
                this.view.setInputType(12306);
                break;
            case 7:
                this.view.setInputType(4);
                break;
            case 8:
                this.view.setInputType(8194);
                break;
            case 9:
                this.view.setInputType(3);
                break;
            case 10:
                this.view.setInputType(113);
                break;
            default:
                this.view.setInputType(1);
                break;
        }
        this.wfWoJD9s05zGciiq11jj7TXqW1AmJIE9BV68DWBBkLZSv9NayRoTG3XJb9WJMlyD = i;
    }

    @SimpleFunction(description = "Hide the keyboard.  Only multiline text boxes need this. Single line text boxes close the keyboard when the users presses the Done key.")
    public final void HideKeyboard() {
        ((InputMethodManager) this.container.$context().getSystemService("input_method")).hideSoftInputFromWindow(this.view.getWindowToken(), 0);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, then this text box accepts multiple lines of input, which are entered using the return key.  For single line text boxes there is a Done key instead of a return key, and pressing Done hides the keyboard.  The app should call the HideKeyboard method to hide the keyboard for a mutiline text box.")
    public final boolean MultiLine() {
        return this.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public final void MultiLine(boolean z) {
        this.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s = z;
        this.view.setSingleLine(!z);
    }

    @DesignerProperty(defaultValue = "2147483647", editorType = "non_negative_integer", propertyType = "advanced")
    @SimpleProperty
    public final void MaxLines(int i) {
        this.view.setMaxLines(i);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the %type% is read-only. By default, this is true.")
    public final boolean ReadOnly() {
        return this.wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public final void ReadOnly(boolean z) {
        this.wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO = z;
        this.view.setEnabled(!z);
    }

    @SimpleFunction(description = "Shows an error message next to the textbox.")
    public final void ShowError() {
        this.view.setError(this.UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the error text.")
    public final String ErrorText() {
        return this.UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Sets the error text message.")
    public final void ErrorText(String str) {
        this.UT8jc0jwpnlGoVnBZm9Vqfc7zQxpOFTo7zW0ZxKDjXZZsrQ7LsG7hsoko7RBNNg5 = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the color of the highlighted text.")
    public final int HighlightColor() {
        return this.TDuNe2Upxoi7WX7QZc6R8eUnFPyyDFw3hP7z3w6U8jMxQwJRQM03zSa9HzWXRv1;
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color", propertyType = "advanced")
    @SimpleProperty
    public final void HighlightColor(int i) {
        this.TDuNe2Upxoi7WX7QZc6R8eUnFPyyDFw3hP7z3w6U8jMxQwJRQM03zSa9HzWXRv1 = i;
        this.view.setHighlightColor(i);
    }
}
