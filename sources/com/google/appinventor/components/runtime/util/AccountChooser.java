package com.google.appinventor.components.runtime.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class AccountChooser {
    private Activity activity;
    private String fYawfM9zby4GKauiBqb4ci1jAzhTnTs5rPk6pEfhgdCGlaj30ALyaese8PExemNE;
    private String h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW;
    private AccountManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG;

    public AccountChooser(Activity activity2, String str, String str2, String str3) {
        this.activity = activity2;
        this.xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG = str;
        this.fYawfM9zby4GKauiBqb4ci1jAzhTnTs5rPk6pEfhgdCGlaj30ALyaese8PExemNE = str2;
        this.h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW = str3;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = AccountManager.get(activity2);
    }

    public Account findAccount() {
        Account hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
        Account[] accountsByType = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAccountsByType("com.google");
        if (accountsByType.length == 1) {
            vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(accountsByType[0].name);
            return accountsByType[0];
        } else if (accountsByType.length == 0) {
            String hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO();
            if (hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO != null) {
                vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
                return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAccountsByType("com.google")[0];
            }
            Log.i("AccountChooser", "User failed to create a valid account");
            return null;
        } else {
            String string = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T().getString("account", (String) null);
            if (string != null && (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(string, accountsByType)) != null) {
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
            }
            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(accountsByType);
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 != null) {
                vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3);
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3, accountsByType);
            }
            Log.i("AccountChooser", "User failed to choose an account");
            return null;
        }
    }

    private static Account hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, Account[] accountArr) {
        for (Account account : accountArr) {
            if (account.name.equals(str)) {
                Log.i("AccountChooser", "chose account: ".concat(String.valueOf(str)));
                return account;
            }
        }
        return null;
    }

    private String hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO() {
        Log.i("AccountChooser", "Adding auth token account ...");
        try {
            String string = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addAccount("com.google", this.xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG, (String[]) null, (Bundle) null, this.activity, (AccountManagerCallback) null, (Handler) null).getResult().getString("authAccount");
            Log.i("AccountChooser", "created: ".concat(String.valueOf(string)));
            return string;
        } catch (OperationCanceledException e) {
            Log.e("AccountChooser", String.valueOf(e));
            return null;
        } catch (AuthenticatorException e2) {
            Log.e("AccountChooser", String.valueOf(e2));
            return null;
        } catch (IOException e3) {
            Log.e("AccountChooser", String.valueOf(e3));
            return null;
        }
    }

    private String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Account[] accountArr) {
        String str;
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        new a(accountArr, synchronousQueue).start();
        Log.i("AccountChooser", "Select: waiting for user...");
        try {
            str = (String) synchronousQueue.take();
        } catch (InterruptedException e) {
            Log.e("AccountChooser", String.valueOf(e));
            str = null;
        }
        Log.i("AccountChooser", "Selected: ".concat(String.valueOf(str)));
        if ("".equals(str)) {
            return null;
        }
        return str;
    }

    private SharedPreferences B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
        return this.activity.getSharedPreferences(this.h8Png9oaVDfYGvgWdHcn1DMBnn2tbT5MRZoUvXvvhLrY5O6ybby2QmTOJ6PZJucW, 0);
    }

    private void vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(String str) {
        Log.i("AccountChooser", "persisting account: ".concat(String.valueOf(str)));
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T().edit().putString("account", str).commit();
    }

    public void forgetAccountName() {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T().edit().remove("account").commit();
    }

    class a extends Thread implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener {
        /* access modifiers changed from: private */
        public String[] F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private SynchronousQueue<String> f283hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        a(Account[] accountArr, SynchronousQueue<String> synchronousQueue) {
            this.f283hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = synchronousQueue;
            this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = new String[accountArr.length];
            for (int i = 0; i < accountArr.length; i++) {
                this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho[i] = accountArr[i].name;
            }
        }

        public final void run() {
            AccountChooser.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AccountChooser.this).runOnUiThread(new Runnable() {
                public final void run() {
                    new AlertDialog.Builder(AccountChooser.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AccountChooser.this)).setTitle(TextViewUtil.fromHtml(AccountChooser.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AccountChooser.this))).setOnCancelListener(a.this).setSingleChoiceItems(a.this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho, -1, a.this).show();
                    Log.i("AccountChooser", "Dialog showing!");
                }
            });
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            if (i >= 0) {
                try {
                    String str = this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho[i];
                    Log.i("AccountChooser", "Chose: ".concat(String.valueOf(str)));
                    this.f283hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(str);
                } catch (InterruptedException unused) {
                }
            } else {
                this.f283hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put("");
            }
            dialogInterface.dismiss();
        }

        public final void onCancel(DialogInterface dialogInterface) {
            Log.i("AccountChooser", "Chose: canceled");
            onClick(dialogInterface, -1);
        }
    }
}
