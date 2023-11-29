package com.google.appinventor.components.runtime;

public interface SpeechListener {
    void onError(int i);

    void onPartialResult(String str);

    void onResult(String str);
}
