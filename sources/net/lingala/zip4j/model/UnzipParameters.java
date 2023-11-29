package net.lingala.zip4j.model;

public class UnzipParameters {
    private boolean ignoreAllFileAttributes;
    private boolean ignoreArchiveFileAttribute;
    private boolean ignoreDateTimeAttributes;
    private boolean ignoreHiddenFileAttribute;
    private boolean ignoreReadOnlyFileAttribute;
    private boolean ignoreSystemFileAttribute;

    public boolean isIgnoreReadOnlyFileAttribute() {
        return this.ignoreReadOnlyFileAttribute;
    }

    public void setIgnoreReadOnlyFileAttribute(boolean z) {
        this.ignoreReadOnlyFileAttribute = z;
    }

    public boolean isIgnoreHiddenFileAttribute() {
        return this.ignoreHiddenFileAttribute;
    }

    public void setIgnoreHiddenFileAttribute(boolean z) {
        this.ignoreHiddenFileAttribute = z;
    }

    public boolean isIgnoreArchiveFileAttribute() {
        return this.ignoreArchiveFileAttribute;
    }

    public void setIgnoreArchiveFileAttribute(boolean z) {
        this.ignoreArchiveFileAttribute = z;
    }

    public boolean isIgnoreSystemFileAttribute() {
        return this.ignoreSystemFileAttribute;
    }

    public void setIgnoreSystemFileAttribute(boolean z) {
        this.ignoreSystemFileAttribute = z;
    }

    public boolean isIgnoreAllFileAttributes() {
        return this.ignoreAllFileAttributes;
    }

    public void setIgnoreAllFileAttributes(boolean z) {
        this.ignoreAllFileAttributes = z;
    }

    public boolean isIgnoreDateTimeAttributes() {
        return this.ignoreDateTimeAttributes;
    }

    public void setIgnoreDateTimeAttributes(boolean z) {
        this.ignoreDateTimeAttributes = z;
    }
}
