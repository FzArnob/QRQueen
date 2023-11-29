package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import com.google.appinventor.components.runtime.util.ErrorMessages;

public class ServiceBasedSpeechRecognizer extends SpeechRecognizerController implements RecognitionListener {
    private Intent B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    private ComponentContainer container;
    private SpeechRecognizer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;

    public void onBeginningOfSpeech() {
    }

    public void onBufferReceived(byte[] bArr) {
    }

    public void onEndOfSpeech() {
    }

    public void onEvent(int i, Bundle bundle) {
    }

    public void onReadyForSpeech(Bundle bundle) {
    }

    public void onRmsChanged(float f) {
    }

    public ServiceBasedSpeechRecognizer(ComponentContainer componentContainer, Intent intent) {
        this.container = componentContainer;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = intent;
    }

    public void start() {
        SpeechRecognizer createSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this.container.$context());
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = createSpeechRecognizer;
        createSpeechRecognizer.setRecognitionListener(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startListening(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
    }

    public void stop() {
        SpeechRecognizer speechRecognizer = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
        }
    }

    public void onError(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = ErrorMessages.ERROR_NETWORK_TIMEOUT;
                break;
            case 2:
                i2 = ErrorMessages.ERROR_NETWORK;
                break;
            case 3:
                i2 = ErrorMessages.ERROR_AUDIO;
                break;
            case 4:
                i2 = ErrorMessages.ERROR_SERVER;
                break;
            case 5:
                i2 = ErrorMessages.ERROR_CLIENT;
                break;
            case 6:
                i2 = ErrorMessages.ERROR_SPEECH_TIMEOUT;
                break;
            case 7:
                i2 = ErrorMessages.ERROR_NO_MATCH;
                break;
            case 8:
                i2 = ErrorMessages.ERROR_RECOGNIZER_BUSY;
                break;
            case 9:
                i2 = ErrorMessages.ERROR_INSUFFICIENT_PERMISSIONS;
                break;
            default:
                i2 = 0;
                break;
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onError(i2);
    }

    public void onResults(Bundle bundle) {
        if (bundle.isEmpty()) {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = "";
        } else {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = bundle.getStringArrayList("results_recognition").get(0);
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onResult(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
    }

    public void onPartialResults(Bundle bundle) {
        if (bundle.isEmpty()) {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = "";
        } else {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = bundle.getStringArrayList("results_recognition").get(0);
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onPartialResult(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
    }
}
