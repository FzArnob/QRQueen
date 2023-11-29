package net.lingala.zip4j.model;

import java.util.TimeZone;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

public class ZipParameters implements Cloneable {
    private int aesKeyStrength = -1;
    private int compressionLevel;
    private int compressionMethod = 8;
    private String defaultFolderPath;
    private boolean encryptFiles = false;
    private int encryptionMethod = -1;
    private String fileNameInZip;
    private boolean includeRootFolder = true;
    private boolean isSourceExternalStream;
    private char[] password;
    private boolean readHiddenFiles = true;
    private String rootFolderInZip;
    private int sourceFileCRC;
    private TimeZone timeZone = TimeZone.getDefault();

    public int getCompressionMethod() {
        return this.compressionMethod;
    }

    public void setCompressionMethod(int i) {
        this.compressionMethod = i;
    }

    public boolean isEncryptFiles() {
        return this.encryptFiles;
    }

    public void setEncryptFiles(boolean z) {
        this.encryptFiles = z;
    }

    public int getEncryptionMethod() {
        return this.encryptionMethod;
    }

    public void setEncryptionMethod(int i) {
        this.encryptionMethod = i;
    }

    public int getCompressionLevel() {
        return this.compressionLevel;
    }

    public void setCompressionLevel(int i) {
        this.compressionLevel = i;
    }

    public boolean isReadHiddenFiles() {
        return this.readHiddenFiles;
    }

    public void setReadHiddenFiles(boolean z) {
        this.readHiddenFiles = z;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        if (str != null) {
            setPassword(str.toCharArray());
        }
    }

    public void setPassword(char[] cArr) {
        this.password = cArr;
    }

    public int getAesKeyStrength() {
        return this.aesKeyStrength;
    }

    public void setAesKeyStrength(int i) {
        this.aesKeyStrength = i;
    }

    public boolean isIncludeRootFolder() {
        return this.includeRootFolder;
    }

    public void setIncludeRootFolder(boolean z) {
        this.includeRootFolder = z;
    }

    public String getRootFolderInZip() {
        return this.rootFolderInZip;
    }

    public void setRootFolderInZip(String str) {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            if (!str.endsWith("\\") && !str.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                str = new StringBuffer(String.valueOf(str)).append(InternalZipConstants.FILE_SEPARATOR).toString();
            }
            str = str.replaceAll("\\\\", InternalZipConstants.ZIP_FILE_SEPARATOR);
        }
        this.rootFolderInZip = str;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone2) {
        this.timeZone = timeZone2;
    }

    public int getSourceFileCRC() {
        return this.sourceFileCRC;
    }

    public void setSourceFileCRC(int i) {
        this.sourceFileCRC = i;
    }

    public String getDefaultFolderPath() {
        return this.defaultFolderPath;
    }

    public void setDefaultFolderPath(String str) {
        this.defaultFolderPath = str;
    }

    public String getFileNameInZip() {
        return this.fileNameInZip;
    }

    public void setFileNameInZip(String str) {
        this.fileNameInZip = str;
    }

    public boolean isSourceExternalStream() {
        return this.isSourceExternalStream;
    }

    public void setSourceExternalStream(boolean z) {
        this.isSourceExternalStream = z;
    }
}
