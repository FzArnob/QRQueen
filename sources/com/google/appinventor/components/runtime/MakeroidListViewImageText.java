package com.google.appinventor.components.runtime;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;

@DesignerComponent(category = ComponentCategory.LISTVIEWS, description = "This is a visible component that displays a list of a image and two labels", helpUrl = "https://docs.kodular.io/components/user-interface/list-view-image-and-text/", iconName = "images/listView.png", version = 4)
@UsesLibraries({"glide.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public class MakeroidListViewImageText extends MakeroidListViewBase {
    private int HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = 0;
    private boolean LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE = false;
    private boolean O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS = false;
    private String TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT = "";
    private boolean YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = false;
    /* access modifiers changed from: private */
    public boolean cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN = false;
    private int dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = 0;
    private float gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = 14.0f;
    private ListAdapter hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ArrayList<ArrayList<String>> f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ArrayList<>();
    private float n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = 14.0f;
    private String pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = "";
    private boolean q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = false;
    /* access modifiers changed from: private */
    public boolean seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = false;
    private int size = 1;
    private int tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = 0;
    private int titleColor = 0;
    private int xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = 1;

    public MakeroidListViewImageText(ComponentContainer componentContainer) {
        super(componentContainer, 1);
        TextViewUtil.setContext(this.context);
        ItemSize(1);
        ImageSide(1);
        TitleTextSize(this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv);
        TitleBold(this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k);
        TitleColor(DEFAULT_PRIMARY_TEXT_COLOR);
        TitleFontTypeface(this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR);
        TitleFontTypefaceImport("");
        TitleHTML(this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz);
        TitleItalic(this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5);
        SubtitleTextSize(this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R);
        SubtitleBold(this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE);
        SubtitleColor(DEFAULT_SECONDARY_TEXT_COLOR);
        SubtitleFontTypeface(this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy);
        SubtitleFontTypefaceImport("");
        SubtitleHTML(this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN);
        SubtitleItalic(this.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS);
    }

    public void click(int i) {
        try {
            Click(i, (String) this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(i).get(1), (String) this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(i).get(2), (String) this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(i).get(0));
        } catch (ArrayIndexOutOfBoundsException unused) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notifyDataSetChanged();
        }
    }

    public void longClick(int i) {
        try {
            LongClick(i, (String) this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(i).get(1), (String) this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(i).get(2), (String) this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(i).get(0));
        } catch (ArrayIndexOutOfBoundsException unused) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notifyDataSetChanged();
        }
    }

    @SimpleEvent(description = "Triggers when the user clicks on a item in the list")
    public void Click(int i, String str, String str2, String str3) {
        EventDispatcher.dispatchEvent(this, "Click", Integer.valueOf(i), str, str2, str3);
    }

    @SimpleEvent(description = "Triggers when the user long clicks on a item in the list")
    public void LongClick(int i, String str, String str2, String str3) {
        EventDispatcher.dispatchEvent(this, "LongClick", Integer.valueOf(i), str, str2, str3);
    }

    @SimpleFunction(description = "Add a item to the list")
    public void AddItem(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.add(arrayList);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notifyDataSetChanged();
    }

    @SimpleFunction(description = "Add a item to the list")
    public void AddItemFromList(YailList yailList) throws ArrayIndexOutOfBoundsException {
        AddItem(yailList.getString(0), yailList.getString(1), yailList.getString(2));
    }

    @SimpleFunction(description = "Remove all the items from the list")
    public void ClearList() {
        this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.clear();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notifyDataSetChanged();
    }

    @SimpleFunction(description = "Update a item of the list")
    public void UpdateItem(int i, String str, String str2, String str3) throws ArrayIndexOutOfBoundsException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.set(i, arrayList);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notifyDataSetChanged();
    }

    @SimpleFunction(description = "Remove a item from the list")
    public void RemoveItem(int i) throws ArrayIndexOutOfBoundsException {
        this.f208hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.remove(i);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notifyDataSetChanged();
    }

    @DesignerProperty(defaultValue = "1", editorType = "list_item_size")
    @SimpleProperty(description = "Set ListItem Size.\nSet it to 1 for Normal size, 2 for Small size and 3 for Big size.")
    public void ItemSize(int i) {
        this.size = i;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns ListItem size")
    public int ItemSize() {
        return this.size;
    }

    @DesignerProperty(defaultValue = "1", editorType = "left_right")
    @SimpleProperty(description = "Set the side of the image.\nSet it to 1 for Left side and 2 for Right side")
    public void ImageSide(int i) {
        this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = i;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the Image Side")
    public int ImageSide() {
        return this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Whether the title should be in bold text")
    public void TitleBold(boolean z) {
        this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = z;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean TitleBold() {
        return this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k;
    }

    @DesignerProperty(defaultValue = "&HFF212121", editorType = "color")
    @SimpleProperty(description = "Changed the color of the title text")
    public void TitleColor(int i) {
        if (i == 0) {
            i = DEFAULT_PRIMARY_TEXT_COLOR;
        }
        this.titleColor = i;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TitleColor() {
        return this.titleColor;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(description = "Change the Typeface of the Title", userVisible = false)
    public void TitleFontTypeface(int i) {
        this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = i;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TitleFontTypeface() {
        return this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom title font.")
    public void TitleFontTypefaceImport(String str) {
        this.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT = str;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If true, then the title will show html text else it will show plain text. Note: Not all HTML is supported.")
    public void TitleHTML(boolean z) {
        this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = z;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean TitleHTML() {
        return this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Whether the title should be in italic text")
    public void TitleItalic(boolean z) {
        this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = z;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean TitleItalic() {
        return this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Whether the subtitle should be in bold text")
    public void SubtitleBold(boolean z) {
        this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE = z;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @DesignerProperty(defaultValue = "&HFF757575", editorType = "color")
    @SimpleProperty(description = "Changed the color of the subtitle text")
    public void SubtitleColor(int i) {
        if (i == 0) {
            i = DEFAULT_SECONDARY_TEXT_COLOR;
        }
        this.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = i;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int SubtitleColor() {
        return this.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean SubtitleBold() {
        return this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(description = "Change the Typeface of the Subtitle", userVisible = false)
    public void SubtitleFontTypeface(int i) {
        this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = i;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int SubtitleFontTypeface() {
        return this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom title font.")
    public void SubtitleFontTypefaceImport(String str) {
        this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = str;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "If true, then the subtitle will show html text else it will show plain text. Note: Not all HTML is supported.")
    public void SubtitleHTML(boolean z) {
        this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN = z;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean SubtitleHTML() {
        return this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Whether the subtitle should be in italic text")
    public void SubtitleItalic(boolean z) {
        this.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS = z;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean SubtitleItalic() {
        return this.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS;
    }

    @DesignerProperty(defaultValue = "14", editorType = "non_negative_float")
    @SimpleProperty(description = "The text size of the title.")
    public void TitleTextSize(float f) {
        this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = f;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(description = "Return the text size of the title.")
    public float TitleTextSize() {
        return this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv / this.context.getResources().getDisplayMetrics().scaledDensity;
    }

    @DesignerProperty(defaultValue = "14", editorType = "non_negative_float")
    @SimpleProperty(description = "The text size of the subtitle.")
    public void SubtitleTextSize(float f) {
        this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = f;
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT();
    }

    @SimpleProperty(description = "Return the text size of the subtitle.")
    public float SubtitleTextSize() {
        return this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R / this.context.getResources().getDisplayMetrics().scaledDensity;
    }

    private void yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ListAdapter();
        this.recyclerView.setAdapter(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    public class ListAdapter extends RecyclerView.Adapter<a> {
        public ListAdapter() {
            Log.i("ListViewImageText", "ListAdapter Created");
        }

        public a onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            Resources resources = MakeroidListViewImageText.this.context.getResources();
            return new a(from.inflate(resources.getIdentifier("makeroid_list_image_text_" + MakeroidListViewImageText.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidListViewImageText.this) + MakeroidListViewImageText.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(MakeroidListViewImageText.this), "layout", MakeroidListViewImageText.this.context.getPackageName()), viewGroup, false));
        }

        public void onBindViewHolder(a aVar, int i) {
            ArrayList arrayList = (ArrayList) MakeroidListViewImageText.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidListViewImageText.this).get(i);
            KodularUtil.LoadPicture(MakeroidListViewImageText.this.context, (String) arrayList.get(0), aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, MakeroidListViewImageText.this.isCompanion);
            TextViewUtil.setTextColor(aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, MakeroidListViewImageText.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidListViewImageText.this));
            TextViewUtil.setTextColor(aVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE, MakeroidListViewImageText.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(MakeroidListViewImageText.this));
            TextViewUtil.setFontSize(aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, MakeroidListViewImageText.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidListViewImageText.this));
            TextViewUtil.setFontSize(aVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE, MakeroidListViewImageText.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(MakeroidListViewImageText.this));
            if (MakeroidListViewImageText.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(MakeroidListViewImageText.this) == null || MakeroidListViewImageText.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(MakeroidListViewImageText.this).isEmpty()) {
                TextViewUtil.setFontTypeface(aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, MakeroidListViewImageText.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(MakeroidListViewImageText.this), MakeroidListViewImageText.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidListViewImageText.this), MakeroidListViewImageText.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(MakeroidListViewImageText.this));
            } else {
                TextViewUtil.setCustomFontTypeface(MakeroidListViewImageText.this.form, aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, MakeroidListViewImageText.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(MakeroidListViewImageText.this), MakeroidListViewImageText.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidListViewImageText.this), MakeroidListViewImageText.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(MakeroidListViewImageText.this));
            }
            if (MakeroidListViewImageText.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(MakeroidListViewImageText.this) == null || MakeroidListViewImageText.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(MakeroidListViewImageText.this).isEmpty()) {
                TextViewUtil.setFontTypeface(aVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE, MakeroidListViewImageText.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(MakeroidListViewImageText.this), MakeroidListViewImageText.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(MakeroidListViewImageText.this), MakeroidListViewImageText.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(MakeroidListViewImageText.this));
            } else {
                TextViewUtil.setCustomFontTypeface(MakeroidListViewImageText.this.form, aVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE, MakeroidListViewImageText.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(MakeroidListViewImageText.this), MakeroidListViewImageText.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(MakeroidListViewImageText.this), MakeroidListViewImageText.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(MakeroidListViewImageText.this));
            }
            if (((String) arrayList.get(1)).length() > 0) {
                aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.setVisibility(0);
                if (MakeroidListViewImageText.this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz) {
                    TextViewUtil.setTextHTML(aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, (String) arrayList.get(1));
                } else {
                    TextViewUtil.setText(aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, (String) arrayList.get(1));
                }
            } else {
                aVar.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.setVisibility(8);
            }
            if (((String) arrayList.get(2)).length() > 0) {
                aVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.setVisibility(0);
                if (MakeroidListViewImageText.this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN) {
                    TextViewUtil.setTextHTML(aVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE, (String) arrayList.get(2));
                } else {
                    TextViewUtil.setText(aVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE, (String) arrayList.get(2));
                }
            } else {
                aVar.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.setVisibility(8);
            }
        }

        public int getItemCount() {
            return MakeroidListViewImageText.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidListViewImageText.this).size();
        }

        class a extends RecyclerView.ViewHolder {
            ImageView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            TextView qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
            TextView vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

            a(View view) {
                super(view);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (ImageView) view.findViewById(MakeroidListViewImageText.this.context.getResources().getIdentifier("image", CommonProperties.ID, MakeroidListViewImageText.this.context.getPackageName()));
                this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = (TextView) view.findViewById(MakeroidListViewImageText.this.context.getResources().getIdentifier("title", CommonProperties.ID, MakeroidListViewImageText.this.context.getPackageName()));
                this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = (TextView) view.findViewById(MakeroidListViewImageText.this.context.getResources().getIdentifier("subtitle", CommonProperties.ID, MakeroidListViewImageText.this.context.getPackageName()));
            }
        }
    }
}
