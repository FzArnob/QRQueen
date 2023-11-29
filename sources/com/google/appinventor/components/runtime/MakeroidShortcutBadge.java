package com.google.appinventor.components.runtime;

import android.content.Context;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import me.leolin.shortcutbadger.ShortcutBadger;

@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "Shortcut Badge component", iconName = "images/notification.png", nonVisible = true, version = 1)
@UsesLibraries({"ShortcutBadger.aar", "ShortcutBadger.jar"})
@SimpleObject
@UsesPermissions({"com.android.launcher.permission.READ_SETTINGS", "com.android.launcher.permission.WRITE_SETTINGS", "com.android.launcher.permission.INSTALL_SHORTCUT", "com.android.launcher.permission.UNINSTALL_SHORTCUT", "com.sec.android.provider.badge.permission.READ", "com.sec.android.provider.badge.permission.WRITE", "com.htc.launcher.permission.READ_SETTINGS", "com.htc.launcher.permission.UPDATE_SHORTCUT", "com.sonyericsson.home.permission.BROADCAST_BADGE", "com.sonymobile.home.permission.PROVIDER_INSERT_BADGE", "com.anddoes.launcher.permission.UPDATE_COUNT", "com.majeur.launcher.permission.UPDATE_BADGE", "com.huawei.android.launcher.permission.CHANGE_BADGE", "com.huawei.android.launcher.permission.READ_SETTINGS", "com.huawei.android.launcher.permission.WRITE_SETTINGS", "android.permission.READ_APP_BADGE", "com.oppo.launcher.permission.READ_SETTINGS", "com.oppo.launcher.permission.WRITE_SETTINGS", "me.everything.badger.permission.BADGE_COUNT_READ", "me.everything.badger.permission.BADGE_COUNT_WRITE"})
public final class MakeroidShortcutBadge extends AndroidNonvisibleComponent {
    private Context context;
    private int nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = 0;

    public MakeroidShortcutBadge(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
    }

    @SimpleFunction(description = "Use this block to apply a notification badge count.")
    public final void ApplyCount(int i) {
        this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = i;
        ShortcutBadger.applyCount(this.context, i);
    }

    @SimpleFunction(description = "Use this block to return the notification count.")
    public final int Count() {
        return this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow;
    }

    @SimpleFunction(description = "Use this block to remove the notification badge count.")
    public final void RemoveCount() {
        ShortcutBadger.removeCount(this.context);
    }
}
