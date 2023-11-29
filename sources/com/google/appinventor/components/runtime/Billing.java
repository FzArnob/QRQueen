package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesApplicationMetadata;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.androidmanifest.MetaDataElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@DesignerComponent(category = ComponentCategory.MONETIZATION_GENERAL, description = "Google Play In-App Billing component", helpUrl = "https://docs.kodular.io/components/monetization/in-app-billing/", iconName = "images/billing.png", nonVisible = true, version = 2)
@UsesLibraries({"play-billing.aar", "play-billing.jar"})
@SimpleObject
@UsesApplicationMetadata(metaDataElements = {@MetaDataElement(name = "com.google.android.play.billingclient.version", value = "4.1.0")})
@UsesPermissions({"com.android.vending.BILLING", "android.permission.INTERNET"})
public class Billing extends AndroidNonvisibleComponent implements BillingClientStateListener, PurchasesUpdatedListener, OnDestroyListener, OnResumeListener, OnInitializeListener {
    private static final String LOG_TAG = "KodularBilling";
    private static final String TEST_PRODUCT_ID = "android.test.purchased";
    /* access modifiers changed from: private */
    public Activity activity;
    /* access modifiers changed from: private */
    public BillingClient billingClient;
    private Context context;
    private boolean testMode = false;

    @Deprecated
    @SimpleFunction(description = "Do not use this block anymore. This block is deprecated and does nothing and will be removed in the future!")
    public void Initialize(String str, String str2) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Is one time purchase supported.")
    public boolean IsOneTimePurchaseSupported() {
        return true;
    }

    @DesignerProperty(defaultValue = "true", editorType = "boolean")
    @Deprecated
    @SimpleProperty
    public void SuppressToast(boolean z) {
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Do not use this block anymore. This block is deprecated and does nothing and will be removed in the future!", userVisible = false)
    public boolean SuppressToast() {
        return true;
    }

    @Deprecated
    @SimpleFunction(description = "Do not use this block anymore. This block is deprecated and does nothing and will be removed in the future!")
    public void UpdateSubscription(String str) {
    }

    public void onBillingServiceDisconnected() {
    }

    public void onBillingSetupFinished(BillingResult billingResult) {
    }

    public Billing(Form form) {
        super(form);
        this.context = form.$context();
        this.activity = form.$context();
        form.registerForOnInitialize(this);
        form.registerForOnResume(this);
        form.registerForOnDestroy(this);
        BillingClient build = BillingClient.newBuilder(this.context).setListener(this).enablePendingPurchases().build();
        this.billingClient = build;
        build.startConnection(this);
        Log.d(LOG_TAG, "Billing Created");
    }

    @SimpleFunction(description = "Load Owned Purchases from Google.")
    public void LoadOwnedPurchases() {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        this.billingClient.queryPurchaseHistoryAsync("inapp", new PurchaseHistoryResponseListener() {
            public final void onPurchaseHistoryResponse(BillingResult billingResult, List<PurchaseHistoryRecord> list) {
                if (Billing.this.isSuccessful(billingResult) && Billing.isListNonNullAndNotEmpty(list)) {
                    for (PurchaseHistoryRecord skus : list) {
                        arrayList.add(skus.getSkus().get(0));
                    }
                }
                Billing.this.billingClient.queryPurchaseHistoryAsync("subs", new PurchaseHistoryResponseListener() {
                    public final void onPurchaseHistoryResponse(BillingResult billingResult, List<PurchaseHistoryRecord> list) {
                        if (Billing.this.isSuccessful(billingResult) && Billing.isListNonNullAndNotEmpty(list)) {
                            for (PurchaseHistoryRecord skus : list) {
                                arrayList2.add(skus.getSkus().get(0));
                            }
                        }
                        Billing.this.GotOwnedPurchases(arrayList, arrayList2);
                    }
                });
            }
        });
        Log.d(LOG_TAG, "Load Owned Purchases called");
    }

    @SimpleFunction(description = "Purchase a product with the given product id.")
    public void Purchase(String str) {
        Log.d(LOG_TAG, "Purchase: ".concat(String.valueOf(str)));
        if (this.testMode) {
            str = TEST_PRODUCT_ID;
        }
        this.billingClient.querySkuDetailsAsync(getSkuDetailsParams(str, "inapp"), new SkuDetailsResponseListener() {
            public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                if (Billing.this.isSuccessful(billingResult) && Billing.isListNonNullAndNotEmpty(list)) {
                    Billing.this.startBillingFlow(list.get(0));
                }
            }
        });
    }

    @SimpleFunction(description = "Consumes a purchase to enable users to buy it again.")
    public void Consume(final String str) {
        boolean z;
        Log.i(LOG_TAG, "Consuming purchase: ".concat(String.valueOf(str)));
        Purchase.PurchasesResult queryPurchases = this.billingClient.queryPurchases("inapp");
        if (!isSuccessful(queryPurchases.getBillingResult())) {
            Consumed(false, str);
            return;
        }
        List purchasesList = queryPurchases.getPurchasesList();
        if (purchasesList == null || purchasesList.isEmpty()) {
            Consumed(false, str);
            return;
        }
        Iterator it = purchasesList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Purchase purchase = (Purchase) it.next();
            if (str.equals(purchase.getSkus().get(0)) && isPurchased(purchase)) {
                this.billingClient.consumeAsync(ConsumeParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build(), new ConsumeResponseListener() {
                    public final void onConsumeResponse(BillingResult billingResult, String str) {
                        boolean access$000 = Billing.this.isSuccessful(billingResult);
                        Log.i(Billing.LOG_TAG, "Consume response: BillingResult = ".concat(String.valueOf(access$000)));
                        Billing.this.Consumed(access$000, str);
                    }
                });
                z = true;
                break;
            }
        }
        if (!z) {
            Consumed(false, str);
        }
    }

    @SimpleFunction(description = "Returns true if the product with the specific id is purchased.")
    public boolean IsPurchased(String str) {
        return isPurchased(str, "inapp");
    }

    @SimpleFunction(description = "Get product details from the specific product id.")
    public void ProductDetails(String str) {
        Log.d(LOG_TAG, "ProductDetails: ".concat(String.valueOf(str)));
        this.billingClient.querySkuDetailsAsync(getSkuDetailsParams(str, "inapp"), new SkuDetailsResponseListener() {
            public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                if (!Billing.this.isSuccessful(billingResult) || !Billing.isListNonNullAndNotEmpty(list)) {
                    Billing.this.GotProductDetails(false, "", "", "", "", "");
                    return;
                }
                SkuDetails skuDetails = list.get(0);
                Billing.this.GotProductDetails(true, skuDetails.getSku(), skuDetails.getTitle(), skuDetails.getDescription(), skuDetails.getPriceCurrencyCode(), skuDetails.getPrice());
            }
        });
    }

    @SimpleFunction(description = "Subscribe a product with the given product id.")
    public void Subscribe(String str) {
        Log.d(LOG_TAG, "Subscribe: ".concat(String.valueOf(str)));
        if (this.testMode) {
            str = TEST_PRODUCT_ID;
        }
        if (isFeatureSupported("subscriptions")) {
            this.billingClient.querySkuDetailsAsync(getSkuDetailsParams(str, "subs"), new SkuDetailsResponseListener() {
                public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                    if (Billing.this.isSuccessful(billingResult) && Billing.isListNonNullAndNotEmpty(list)) {
                        Billing.this.startBillingFlow(list.get(0));
                    }
                }
            });
        }
    }

    @SimpleFunction(description = "Returns true if the product is subscribed.")
    public boolean IsSubscribed(String str) {
        return isPurchased(str, "subs");
    }

    @SimpleFunction(description = "Get subscription details from the given id.")
    public void SubscriptionDetails(String str) {
        Log.d(LOG_TAG, "SubscriptionDetails: ".concat(String.valueOf(str)));
        this.billingClient.querySkuDetailsAsync(getSkuDetailsParams(str, "subs"), new SkuDetailsResponseListener() {
            public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                if (!Billing.this.isSuccessful(billingResult) || !Billing.isListNonNullAndNotEmpty(list)) {
                    Billing.this.GotSubscriptionDetails(false, false, "", "", "", "", "");
                    return;
                }
                SkuDetails skuDetails = list.get(0);
                Billing.this.GotSubscriptionDetails(true, true, skuDetails.getSku(), skuDetails.getTitle(), skuDetails.getDescription(), skuDetails.getPriceCurrencyCode(), skuDetails.getPrice());
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether In-app billing service is ready to purchase.")
    public boolean ReadyToPurchase() {
        return this.billingClient.isReady();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Check Play Market services availability.")
    public boolean IsIabServiceAvailable() {
        return this.billingClient.isReady();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Is subscriptions supported.")
    public boolean IsSubscriptionsSupported() {
        return isFeatureSupported("subscriptions");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Is subscription update supported.")
    public boolean IsSubscriptionUpdateSupported() {
        return isFeatureSupported("subscriptionsUpdate");
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void TestMode(boolean z) {
        this.testMode = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether it is testing mode enabled or not.")
    public boolean TestMode() {
        return this.testMode;
    }

    @SimpleEvent(description = "Error occurred event.")
    public void ErrorOccurred(final String str) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(Billing.this, "ErrorOccurred", str);
            }
        });
    }

    @SimpleEvent(description = "After purchase event.")
    public void AfterPurchase(final String str) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(Billing.this, "AfterPurchase", str);
            }
        });
    }

    @SimpleEvent(description = "When product is consumed.")
    public void Consumed(final boolean z, final String str) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(Billing.this, "Consumed", Boolean.valueOf(z), str);
            }
        });
    }

    @SimpleEvent(description = "Got Product Details")
    public void GotProductDetails(boolean z, String str, String str2, String str3, String str4, String str5) {
        final boolean z2 = z;
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str4;
        final String str10 = str5;
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(Billing.this, "GotProductDetails", Boolean.valueOf(z2), str6, str7, str8, str9, str10);
            }
        });
    }

    @SimpleEvent(description = "Got Subscription Details")
    public void GotSubscriptionDetails(boolean z, boolean z2, String str, String str2, String str3, String str4, String str5) {
        final boolean z3 = z;
        final boolean z4 = z2;
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str4;
        final String str10 = str5;
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(Billing.this, "GotSubscriptionDetails", Boolean.valueOf(z3), Boolean.valueOf(z4), str6, str7, str8, str9, str10);
            }
        });
    }

    @SimpleEvent(description = "Got Owned Purchases")
    public void GotOwnedPurchases(final List<String> list, final List<String> list2) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(Billing.this, "GotOwnedPurchases", list, list2);
            }
        });
    }

    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> list) {
        if (isSuccessful(billingResult) && isListNonNullAndNotEmpty(list)) {
            for (Purchase handlePurchase : list) {
                handlePurchase(handlePurchase);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isSuccessful(BillingResult billingResult) {
        return billingResult.getResponseCode() == 0;
    }

    private boolean isSuccessful(Purchase.PurchasesResult purchasesResult) {
        return purchasesResult.getResponseCode() == 0;
    }

    private boolean isFeatureSupported(String str) {
        return isSuccessful(this.billingClient.isFeatureSupported(str));
    }

    private boolean isPurchased(Purchase purchase) {
        return purchase.getPurchaseState() == 1;
    }

    private boolean isPurchased(String str, String str2) {
        Purchase.PurchasesResult queryPurchases = this.billingClient.queryPurchases(str2);
        if (isSuccessful(queryPurchases)) {
            List<Purchase> purchasesList = queryPurchases.getPurchasesList();
            if (isListNonNullAndNotEmpty(purchasesList)) {
                for (Purchase skus : purchasesList) {
                    if (str.equals(skus.getSkus().get(0))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static <T> boolean isListNonNullAndNotEmpty(List<T> list) {
        return list != null && list.size() > 0;
    }

    private SkuDetailsParams getSkuDetailsParams(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return SkuDetailsParams.newBuilder().setSkusList(arrayList).setType(str2).build();
    }

    /* access modifiers changed from: private */
    public void startBillingFlow(SkuDetails skuDetails) {
        final BillingFlowParams build = BillingFlowParams.newBuilder().setSkuDetails(skuDetails).build();
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                Billing.this.billingClient.launchBillingFlow(Billing.this.activity, build);
            }
        });
    }

    private void handlePurchase(final Purchase purchase) {
        if (isPurchased(purchase) && !purchase.isAcknowledged()) {
            this.billingClient.acknowledgePurchase(AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build(), new AcknowledgePurchaseResponseListener() {
                public final void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                    Log.i(Billing.LOG_TAG, "Acknowledge response: BillingResult = " + Billing.this.isSuccessful(billingResult));
                    Billing.this.AfterPurchase((String) purchase.getSkus().get(0));
                }
            });
        }
    }

    public void onInitialize() {
        this.billingClient.queryPurchases("inapp");
        this.billingClient.queryPurchases("subs");
    }

    public void onResume() {
        this.billingClient.queryPurchases("inapp");
        this.billingClient.queryPurchases("subs");
    }

    public void onDestroy() {
        this.billingClient.endConnection();
    }
}
