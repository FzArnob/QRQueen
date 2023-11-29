package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;
import com.google.appinventor.components.runtime.util.crypt.KodularAES128;
import com.google.appinventor.components.runtime.util.crypt.KodularAES256;
import com.google.appinventor.components.runtime.util.crypt.KodularBCrypt;
import com.google.appinventor.components.runtime.util.crypt.KodularBase64;
import com.google.appinventor.components.runtime.util.crypt.KodularEnigma;
import com.google.appinventor.components.runtime.util.crypt.KodularMD5;
import com.google.appinventor.components.runtime.util.crypt.KodularPBKDF2;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA1;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA224;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA256;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA384;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA512;
import com.google.appinventor.components.runtime.util.crypt.KodularTripleDES;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "Component which performs several encrypt/decrypt functions", helpUrl = "https://docs.kodular.io/components/storage/cryptography/", iconName = "images/encrypt.png", nonVisible = true, version = 2)
@UsesLibraries({"javax-xml-bind.jar", "apache-xerces.jar"})
public class KodularEncrypt extends AndroidNonvisibleComponent {
    private int AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt = 10;
    private int HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = 64000;
    private int KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG = 24;
    private String MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = "";
    private Context context;
    private String e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = "";
    private String nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = "";
    private int xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG = 18;

    public KodularEncrypt(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Log.d("Kodular Encrypt", "Kodular Encrypt Component Created");
    }

    @DesignerProperty(defaultValue = "64000", editorType = "integer")
    @SimpleProperty(description = "Set the PBKDF2 number of Iterations")
    public void PBKDF2Iterations(int i) {
        this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the PBKDF2 number of Iterations")
    public int PBKDF2Iterations() {
        return this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0;
    }

    @DesignerProperty(defaultValue = "24", editorType = "integer")
    @SimpleProperty(description = "Set the PBKDF2 Salt Byte Size")
    public void PBKDF2SaltByteSize(int i) {
        this.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the PBKDF2 Salt Byte Size")
    public int PBKDF2SaltByteSize() {
        return this.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG;
    }

    @DesignerProperty(defaultValue = "18", editorType = "integer")
    @SimpleProperty(description = "Set the PBKDF2 Hash Byte Size")
    public void PBKDF2HashByteSize(int i) {
        this.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the PBKDF2 Hash Byte Size")
    public int PBKDF2HashByteSize() {
        return this.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the TripleDES Key")
    public void TripleDESKey(String str) {
        this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the TripleDES Key")
    public String TripleDESKey() {
        return this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw;
    }

    @DesignerProperty(defaultValue = "10", editorType = "integer")
    @SimpleProperty(description = "Set the BCrypt Salt Size")
    public void BCryptSaltSize(int i) {
        this.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the BCrypt Salt Size")
    public int BCryptSaltSize() {
        return this.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the AES-128 Key")
    public void AES128Key(String str) {
        this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the AES-128 Key")
    public String AES128Key() {
        return this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the AES-256 Key")
    public void AES256Key(String str) {
        this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the AES-256 Key")
    public String AES256Key() {
        return this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT;
    }

    @SimpleFunction(description = "Verifies if the input password is the same one as the correct hashed password using PBKDF2 algorithm")
    public boolean PBKDF2VerifyPassword(String str, String str2) {
        try {
            return KodularPBKDF2.verifyPassword(str, str2);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return false;
        }
    }

    @SimpleFunction(description = "Generates a hash using PBKDF2")
    public String PBKDF2CreateHash(String str) {
        try {
            return KodularPBKDF2.createHash(str, this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0, this.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG, this.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a MD5 hash")
    public String MD5CreateHash(String str) {
        try {
            return KodularMD5.createHash(str);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Decodes the given hash using Base64")
    public String Base64Decode(String str) {
        try {
            return KodularBase64.decode(str);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Encodes the given string using Base64")
    public String Base64Encode(String str) {
        try {
            return KodularBase64.encode(str);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Decodes the given hash using the given key through TripleDES")
    public String TripleDESDecode(String str) {
        try {
            return KodularTripleDES.decode(str, this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Encodes the given string using the given key through TripleDES")
    public String TripleDESEncode(String str) {
        try {
            return KodularTripleDES.encode(str, this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Verifies if the input password is the same one as the correct hashed password using BCrypt algorithm")
    public boolean BCryptVerifyPassword(String str, String str2) {
        try {
            return KodularBCrypt.checkPassword(str, str2);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return false;
        }
    }

    @SimpleFunction(description = "Generates a hash using BCrypt")
    public String BCryptCreateHash(String str, String str2) {
        try {
            return KodularBCrypt.hashPassword(str, str2);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a salt usable for hashing with BCrypt")
    public String BCryptGenerateSalt() {
        try {
            return KodularBCrypt.gensalt(this.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-1 string")
    public String SHA1(String str) {
        try {
            return KodularSHA1.sha1(str);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-224 string")
    public String SHA224(String str) {
        try {
            return KodularSHA224.sha224(str);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-256 string")
    public String SHA256(String str) {
        try {
            return KodularSHA256.sha256(str);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-384 string")
    public String SHA384(String str) {
        try {
            return KodularSHA384.sha384(str);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-512 string")
    public String SHA512(String str) {
        try {
            return KodularSHA512.sha512(str);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Decodes the given hash using the given key through AES-128. If any exception occurs, returns empty string.")
    public String AES128Decode(String str) {
        return KodularAES128.decode(str, this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0);
    }

    @SimpleFunction(description = "Encodes the given string using the given key through AES-128. If any exception occurs, returns empty string.")
    public String AES128Encode(String str) {
        return KodularAES128.encode(str, this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0);
    }

    @SimpleFunction(description = "Generates a secure random AES 128 key")
    public String AES128GenKey() {
        return KodularAES128.generateKey();
    }

    @SimpleFunction(description = "Generates a secure random AES 256 key")
    public String AES256GenKey() {
        return KodularAES128.generateKey();
    }

    @SimpleFunction(description = "Decodes the given hash using the given key through AES-256. If there are any exceptions, returns empty string")
    public String AES256Decode(String str) {
        return KodularAES256.decode(str, this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT, this.context);
    }

    @SimpleFunction(description = "Encodes the given string using the given key through AES-256. If there are any exceptions, returns empty string")
    public String AES256Encode(String str) {
        return KodularAES256.encode(str, this.e1IHYfRNckEvpOWbFvMtuN7w9PEpZtVYShhIlzbQR8mwSxiOizA6OYtX7vMfGCUT, this.context);
    }

    @SimpleFunction(description = "Encrypts or decrypts the given string simulating an Enigma machine. Rotors can go from 1 to 8, but they cannot be repeated. Reflector can be B, C or 0 if none. Plugboard is a list with sub-list of two items containing a character each one, which replace the first character with the second one.")
    public String Enigma(String str, int i, int i2, int i3, String str2, YailList yailList) {
        try {
            StringBuilder sb = new StringBuilder();
            String str3 = "";
            for (int i4 = 0; i4 < yailList.size(); i4++) {
                Object object = yailList.getObject(i4);
                if (!(object instanceof YailList)) {
                    return "Sub-item not a list";
                }
                YailList yailList2 = (YailList) object;
                if (yailList2.size() != 2) {
                    return "Sub-item does not have two items";
                }
                String obj = yailList2.getObject(0).toString();
                String obj2 = yailList2.getObject(1).toString();
                sb.append(str3);
                sb.append(obj);
                sb.append(obj2);
                str3 = " ";
            }
            String sb2 = sb.toString();
            if (i > 8 || i2 > 8 || i3 > 8 || i <= 0 || i2 <= 0 || i3 <= 0) {
                return "Rotors has to be between 1 and 8";
            }
            if (str2 != "B" && str2 != "C" && str2 != "0") {
                return "Reflector has to be B, C or 0 8 (none)";
            }
            if (!KodularEnigma.pbParser(sb2)) {
                return "Duplicated entries in plugboard";
            }
            return KodularEnigma.encrypt(str, i, i2, i3, str2, sb2);
        } catch (Exception e) {
            Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }
}
