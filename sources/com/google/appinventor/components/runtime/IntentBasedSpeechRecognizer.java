package com.google.appinventor.components.runtime;

import android.content.Intent;

public class IntentBasedSpeechRecognizer extends SpeechRecognizerController implements ActivityResultListener {
    private Intent B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    private ComponentContainer container;
    private int requestCode;

    public void stop() {
    }

    public IntentBasedSpeechRecognizer(ComponentContainer componentContainer, Intent intent) {
        this.container = componentContainer;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = intent;
    }

    public void start() {
        if (this.requestCode == 0) {
            this.requestCode = this.container.$form().registerForActivityResult(this);
        }
        this.container.$context().startActivityForResult(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.requestCode);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (i == this.requestCode && i2 == -1) {
            if (intent.hasExtra("android.speech.extra.RESULTS")) {
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = intent.getExtras().getStringArrayList("android.speech.extra.RESULTS").get(0);
            } else {
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = "";
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onResult(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
        }
    }
}
