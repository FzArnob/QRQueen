package com.google.appinventor.components.runtime;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import java.io.File;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SOCIAL, description = "Sharing is a non-visible component that enables sharing files and/or messages between your app and other apps installed on a device. The component will display a list of the installed apps that can handle the information provided, and will allow the user to choose one to share the content with, for instance a mail app, a social network app, a texting app, and so on.<br>The file path can be taken directly from other components such as the Camera or the ImagePicker, but can also be specified directly to read from storage. Be aware that different devices treat storage differently, so a few things to try if, for instance, you have a file called arrow.gif in the folder <code>Appinventor/assets</code>, would be: <ul><li><code>\"file:///sdcard/Appinventor/assets/arrow.gif\"</code></li> or <li><code>\"/storage/Appinventor/assets/arrow.gif\"</code></li></ul>", iconName = "images/sharing.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"})
public class Sharing extends AndroidNonvisibleComponent {
    private String nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = "";

    public Sharing(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        ShareDialogMessage("");
    }

    @SimpleFunction(description = "Shares a message through any capable application installed on the phone by displaying a list of the available apps and allowing the user to choose one from the list. The selected app will open with the message inserted on it.")
    public void ShareMessage(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setType("text/plain");
        this.form.startActivity(Intent.createChooser(intent, this.nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE));
    }

    @SimpleFunction(description = "Shares a file through any capable application installed on the phone by displaying a list of the available apps and allowing the user to choose one from the list. The selected app will open with the file inserted on it.")
    public void ShareFile(String str) {
        ShareFileWithMessage(str, "");
    }

    @SimpleFunction(description = "Shares both a file and a message through any capable application installed on the phone by displaying a list of available apps and allowing the user to  choose one from the list. The selected app will open with the file and message inserted on it.")
    public void ShareFileWithMessage(String str, String str2) {
        if (str != null && !str.isEmpty()) {
            try {
                if (!str.startsWith("file://")) {
                    str = "file://".concat(String.valueOf(str));
                }
                Uri parse = Uri.parse(str);
                File file = new File(parse.getPath());
                if (file.isFile()) {
                    String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(str.lastIndexOf(".") + 1).toLowerCase());
                    if (mimeTypeFromExtension == null) {
                        mimeTypeFromExtension = NanoHTTPD.MIME_DEFAULT_BINARY;
                    }
                    Form form = this.form;
                    Uri uriForFile = FileProvider.getUriForFile(form, this.form.getPackageName() + ".provider", file);
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.STREAM", parse);
                    intent.putExtra("android.intent.extra.STREAM", uriForFile);
                    intent.setFlags(1);
                    intent.setType(mimeTypeFromExtension);
                    if (str2 != null && !str2.isEmpty()) {
                        intent.putExtra("android.intent.extra.TEXT", str2);
                    }
                    this.form.startActivity(Intent.createChooser(intent, this.nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE));
                    return;
                }
                String str3 = "ShareFile";
                if (str2 != null && str2.isEmpty()) {
                    str3 = "ShareFileWithMessage";
                }
                this.form.dispatchErrorOccurredEvent(this, str3, ErrorMessages.ERROR_FILE_NOT_FOUND_FOR_SHARING, str);
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, "ShareFileWithMessage", e);
            } catch (Exception e2) {
                Log.e("SocialMediaSharing", String.valueOf(e2));
            }
        }
    }

    @SimpleEvent(description = "This event returns the social media name if an app is not installed. Possible names are 'Facebook Messenger', 'Facebook', 'Twitter', 'Telegram', 'Twitter', 'Snapchat', 'Google Plus' or the given custom package name.")
    public void AppNotFound(String str) {
        EventDispatcher.dispatchEvent(this, "AppNotFound", str);
    }

    @DesignerProperty(defaultValue = "Send using...", editorType = "string")
    @SimpleProperty(description = "Set the text for the sharing dialog. The default text is 'Send using...'.")
    public void ShareDialogMessage(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareDialogMessage- User forgot to add a default share dialog text. Use default text.");
            str = "Send using...";
        }
        this.nItr2rEUwjLh7peU3NgfalZQrx3V2u3REmTnCv6vRXk7VqyYqrNzZhPvrb2eDYTE = str;
    }

    @SimpleFunction(description = "Shares a message through Facebook Messenger. If Messenger is not installed, then the 'AppNotFound' event will be invoked and return the name 'Facebook Messenger'.")
    public void ShareMessageToFacebookMessenger(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageToFacebookMessenger- User forgot to add a message. Use default message.");
            str = "Checkout www.kodular.io - Android Builder.";
        }
        ShareUtil("com.facebook.orca", str, "Facebook Messenger", "ShareMessageToFacebookMessenger- ");
    }

    @SimpleFunction(description = "Shares a message through Facebook. If Facebook is not installed, then the 'AppNotFound' event will be invoked and return the name 'Facebook'.")
    public void ShareMessageToFacebook(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageToFacebook- User forgot to add a message. Use default message.");
            str = "Checkout www.kodular.io - Android Builder.";
        }
        ShareUtil("com.facebook.katana", str, KodularAnalyticsUtil.Ads.NETWORK_FACEBOOK, "ShareMessageToFacebook- ");
    }

    @SimpleFunction(description = "Shares a message through Twitter. If Twitter is not installed, then the 'AppNotFound' event will be invoked and return the name 'Twitter'.")
    public void ShareMessageToTwitter(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageToTwitter- User forgot to add a message. Use default message.");
            str = "Checkout www.kodular.io - Android Builder.";
        }
        ShareUtil("com.twitter.android", str, "Twitter", "ShareMessageToTwitter- ");
    }

    @SimpleFunction(description = "Shares a message through Telegram. If Telegram is not installed, then the 'AppNotFound' event will be invoked and return the name 'Telegram'.")
    public void ShareMessageToTelegram(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageToTelegram- User forgot to add a message. Use default message.");
            str = "Checkout www.kodular.io - Android Builder.";
        }
        ShareUtil("org.telegram.messenger", str, "Telegram", "ShareMessageToTelegram- ");
    }

    @SimpleFunction(description = "Shares a message through WhatsApp. If WhatsApp is not installed, then the 'AppNotFound' event will be invoked and return the name 'WhatsApp'.")
    public void ShareMessageToWhatsApp(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageToWhatsApp- User forgot to add a message. Use default message.");
            str = "Checkout www.kodular.io - Android Builder.";
        }
        ShareUtil("com.whatsapp", str, "WhatsApp", "ShareMessageToWhatsApp- ");
    }

    @SimpleFunction(description = "Shares a message through Snapchat. If Snapchat is not installed, then the 'AppNotFound' event will be invoked and return the name 'Snapchat'.")
    public void ShareMessageToSnapchat(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageToSnapchat- User forgot to add a message. Use default message.");
            str = "Checkout www.kodular.io - Android Builder.";
        }
        ShareUtil("com.snapchat.android", str, "Snapchat", "ShareMessageToSnapchat- ");
    }

    @SimpleFunction(description = "Shares a message through Google Plus. If Google+ is not installed, then the 'AppNotFound' event will be invoked and return the name 'Google Plus'.")
    public void ShareMessageToGooglePlus(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageToGooglePlus- User forgot to add a message. Use default message.");
            str = "Checkout www.kodular.io - Android Builder.";
        }
        ShareUtil("com.google.android.apps.plus", str, "Google Plus", "ShareMessageToGooglePlus- ");
    }

    @SimpleFunction(description = "Shares a message through the given app. If the given app is not installed, then the 'AppNotFound' event will be invoked and return the given name.")
    public void ShareMessageTo(String str, String str2, String str3) {
        if (str == null || str.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageTo- User forgot to add a message. Use default message.");
            str = "Checkout www.kodular.io - Android Builder.";
        }
        if (str2 == null || str2.isEmpty()) {
            Log.d("SocialMediaSharing", "ShareMessageTo- User forgot to add a name. Use default custom name.");
            str2 = "Custom";
        }
        ShareUtil(str3, str, str2, "ShareMessageTo- ");
    }

    public void ShareUtil(String str, String str2, String str3, String str4) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str2);
        intent.setType("text/plain");
        intent.setPackage(str);
        try {
            this.form.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            AppNotFound(str3);
            Log.d("SocialMediaSharing", str4 + e.getMessage());
        }
    }
}
