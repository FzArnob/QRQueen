package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.physicaloid.lib.Physicaloid;
import java.io.UnsupportedEncodingException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Arduino USB Serial Component", iconName = "images/arduino.png", nonVisible = true, version = 1)
@UsesLibraries({"physicaloid.jar"})
public class MakeroidArduino extends AndroidNonvisibleComponent implements Component {
    private int YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = 9600;
    private Context context;
    private Physicaloid hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public MakeroidArduino(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Log.d("Makeroid Arduino USB Serial Component", "Created");
    }

    @SimpleFunction(description = "Initializes Arduino Connection")
    public void InitializeArduino() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Physicaloid(this.context);
        Log.d("Makeroid Arduino USB Serial Component", "Initialized");
    }

    @SimpleFunction(description = "Opens Arduino Connection")
    public boolean OpenArduino() {
        Log.d("Makeroid Arduino USB Serial Component", "Opening connection");
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.open();
    }

    @SimpleFunction(description = "Closes Arduino Connection")
    public boolean CloseArduino() {
        Log.d("Makeroid Arduino USB Serial Component", "Closing connection");
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.close();
    }

    @SimpleFunction(description = "Default baud rate is 9600 bps")
    public void BaudRate(int i) {
        this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBaudrate(i);
        Log.d("Makeroid Arduino USB Serial Component", "Baud Rate: ".concat(String.valueOf(i)));
    }

    @SimpleFunction(description = "Read from Serial")
    public void ReadArduino() {
        byte[] bArr = new byte[256];
        boolean z = false;
        String str = "";
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.read(bArr) > 0) {
            try {
                z = true;
                str = new String(bArr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Log.e("Makeroid Arduino USB Serial Component", e.getMessage());
            }
        }
        AfterReadArduino(z, str);
    }

    @SimpleFunction(description = "Write Data to Serial")
    public void WriteArduino(String str) {
        if (!str.isEmpty()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.write(str.getBytes());
        }
    }

    @SimpleFunction(description = "Returns true when the Arduino connection is open")
    public boolean IsOpenedArduino() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isOpened();
    }

    @SimpleEvent(description = "Triggered after Read function")
    public void AfterReadArduino(boolean z, String str) {
        EventDispatcher.dispatchEvent(this, "AfterRead", Boolean.valueOf(z), str);
    }
}
