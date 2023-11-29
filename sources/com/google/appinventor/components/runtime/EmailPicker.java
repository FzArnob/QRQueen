package com.google.appinventor.components.runtime;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SOCIAL, description = "An EmailPicker is a kind of text box.  If the user begins entering the name or email address of a contact, the phone will show a dropdown menu of choices that complete the entry.  If there are many contacts, the dropdown can take several seconds to appear, and can show intermediate results while the matches are being computed. <p>The initial contents of the text box and the contents< after user entry is in the <code>Text</code> property.  If the <code>Text</code> property is initially empty, the contents of the <code>Hint</code> property will be faintly shown in the text box as a hint to the user.</p>\n <p>Other properties affect the appearance of the text box (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be used (<code>Enabled</code>).</p>\n<p>Text boxes like this are usually used with <code>Button</code> components, with the user clicking on the button when text entry is complete.", version = 6)
@UsesPermissions({"android.permission.READ_CONTACTS"})
public class EmailPicker extends TextBoxBase {
    /* access modifiers changed from: private */
    public final EmailAddressAdapter hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public EmailPicker(ComponentContainer componentContainer) {
        super(componentContainer, new AppCompatAutoCompleteTextView(componentContainer.$context()));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new EmailAddressAdapter(componentContainer.$context());
    }

    public void Initialize() {
        this.container.$form().askPermission("android.permission.READ_CONTACTS", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    ((AppCompatAutoCompleteTextView) EmailPicker.this.view).setAdapter(EmailPicker.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                } else {
                    EmailPicker.this.container.$form().dispatchPermissionDeniedEvent((Component) EmailPicker.this, "Initialize", str);
                }
            }
        });
    }

    @SimpleEvent
    public void GotFocus() {
        EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
    }
}
