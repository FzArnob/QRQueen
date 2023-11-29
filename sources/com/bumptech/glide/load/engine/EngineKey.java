package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

class EngineKey implements Key {
    private static final String EMPTY_LOG_STRING = "";
    private final ResourceDecoder cacheDecoder;
    private final ResourceDecoder decoder;
    private final ResourceEncoder encoder;
    private int hashCode;
    private final int height;
    private final String id;
    private Key originalKey;
    private final Key signature;
    private final Encoder sourceEncoder;
    private String stringKey;
    private final ResourceTranscoder transcoder;
    private final Transformation transformation;
    private final int width;

    public EngineKey(String str, Key key, int i, int i2, ResourceDecoder resourceDecoder, ResourceDecoder resourceDecoder2, Transformation transformation2, ResourceEncoder resourceEncoder, ResourceTranscoder resourceTranscoder, Encoder encoder2) {
        this.id = str;
        this.signature = key;
        this.width = i;
        this.height = i2;
        this.cacheDecoder = resourceDecoder;
        this.decoder = resourceDecoder2;
        this.transformation = transformation2;
        this.encoder = resourceEncoder;
        this.transcoder = resourceTranscoder;
        this.sourceEncoder = encoder2;
    }

    public Key getOriginalKey() {
        if (this.originalKey == null) {
            this.originalKey = new OriginalKey(this.id, this.signature);
        }
        return this.originalKey;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EngineKey engineKey = (EngineKey) obj;
        if (!this.id.equals(engineKey.id) || !this.signature.equals(engineKey.signature) || this.height != engineKey.height || this.width != engineKey.width) {
            return false;
        }
        Transformation transformation2 = this.transformation;
        if ((transformation2 == null) ^ (engineKey.transformation == null)) {
            return false;
        }
        if (transformation2 != null && !transformation2.getId().equals(engineKey.transformation.getId())) {
            return false;
        }
        ResourceDecoder resourceDecoder = this.decoder;
        if ((resourceDecoder == null) ^ (engineKey.decoder == null)) {
            return false;
        }
        if (resourceDecoder != null && !resourceDecoder.getId().equals(engineKey.decoder.getId())) {
            return false;
        }
        ResourceDecoder resourceDecoder2 = this.cacheDecoder;
        if ((resourceDecoder2 == null) ^ (engineKey.cacheDecoder == null)) {
            return false;
        }
        if (resourceDecoder2 != null && !resourceDecoder2.getId().equals(engineKey.cacheDecoder.getId())) {
            return false;
        }
        ResourceEncoder resourceEncoder = this.encoder;
        if ((resourceEncoder == null) ^ (engineKey.encoder == null)) {
            return false;
        }
        if (resourceEncoder != null && !resourceEncoder.getId().equals(engineKey.encoder.getId())) {
            return false;
        }
        ResourceTranscoder resourceTranscoder = this.transcoder;
        if ((resourceTranscoder == null) ^ (engineKey.transcoder == null)) {
            return false;
        }
        if (resourceTranscoder != null && !resourceTranscoder.getId().equals(engineKey.transcoder.getId())) {
            return false;
        }
        Encoder encoder2 = this.sourceEncoder;
        if ((encoder2 == null) ^ (engineKey.sourceEncoder == null)) {
            return false;
        }
        return encoder2 == null || encoder2.getId().equals(engineKey.sourceEncoder.getId());
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            int hashCode2 = this.id.hashCode();
            this.hashCode = hashCode2;
            int hashCode3 = (((((hashCode2 * 31) + this.signature.hashCode()) * 31) + this.width) * 31) + this.height;
            this.hashCode = hashCode3;
            int i = hashCode3 * 31;
            ResourceDecoder resourceDecoder = this.cacheDecoder;
            int i2 = 0;
            int hashCode4 = i + (resourceDecoder != null ? resourceDecoder.getId().hashCode() : 0);
            this.hashCode = hashCode4;
            int i3 = hashCode4 * 31;
            ResourceDecoder resourceDecoder2 = this.decoder;
            int hashCode5 = i3 + (resourceDecoder2 != null ? resourceDecoder2.getId().hashCode() : 0);
            this.hashCode = hashCode5;
            int i4 = hashCode5 * 31;
            Transformation transformation2 = this.transformation;
            int hashCode6 = i4 + (transformation2 != null ? transformation2.getId().hashCode() : 0);
            this.hashCode = hashCode6;
            int i5 = hashCode6 * 31;
            ResourceEncoder resourceEncoder = this.encoder;
            int hashCode7 = i5 + (resourceEncoder != null ? resourceEncoder.getId().hashCode() : 0);
            this.hashCode = hashCode7;
            int i6 = hashCode7 * 31;
            ResourceTranscoder resourceTranscoder = this.transcoder;
            int hashCode8 = i6 + (resourceTranscoder != null ? resourceTranscoder.getId().hashCode() : 0);
            this.hashCode = hashCode8;
            int i7 = hashCode8 * 31;
            Encoder encoder2 = this.sourceEncoder;
            if (encoder2 != null) {
                i2 = encoder2.getId().hashCode();
            }
            this.hashCode = i7 + i2;
        }
        return this.hashCode;
    }

    public String toString() {
        if (this.stringKey == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("EngineKey{");
            sb.append(this.id);
            sb.append('+');
            sb.append(this.signature);
            sb.append("+[");
            sb.append(this.width);
            sb.append('x');
            sb.append(this.height);
            sb.append("]+");
            sb.append('\'');
            ResourceDecoder resourceDecoder = this.cacheDecoder;
            String str = "";
            sb.append(resourceDecoder != null ? resourceDecoder.getId() : str);
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            ResourceDecoder resourceDecoder2 = this.decoder;
            sb.append(resourceDecoder2 != null ? resourceDecoder2.getId() : str);
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            Transformation transformation2 = this.transformation;
            sb.append(transformation2 != null ? transformation2.getId() : str);
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            ResourceEncoder resourceEncoder = this.encoder;
            sb.append(resourceEncoder != null ? resourceEncoder.getId() : str);
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            ResourceTranscoder resourceTranscoder = this.transcoder;
            sb.append(resourceTranscoder != null ? resourceTranscoder.getId() : str);
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            Encoder encoder2 = this.sourceEncoder;
            if (encoder2 != null) {
                str = encoder2.getId();
            }
            sb.append(str);
            sb.append('\'');
            sb.append('}');
            this.stringKey = sb.toString();
        }
        return this.stringKey;
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        byte[] array = ByteBuffer.allocate(8).putInt(this.width).putInt(this.height).array();
        this.signature.updateDiskCacheKey(messageDigest);
        messageDigest.update(this.id.getBytes("UTF-8"));
        messageDigest.update(array);
        ResourceDecoder resourceDecoder = this.cacheDecoder;
        String str = "";
        messageDigest.update((resourceDecoder != null ? resourceDecoder.getId() : str).getBytes("UTF-8"));
        ResourceDecoder resourceDecoder2 = this.decoder;
        messageDigest.update((resourceDecoder2 != null ? resourceDecoder2.getId() : str).getBytes("UTF-8"));
        Transformation transformation2 = this.transformation;
        messageDigest.update((transformation2 != null ? transformation2.getId() : str).getBytes("UTF-8"));
        ResourceEncoder resourceEncoder = this.encoder;
        messageDigest.update((resourceEncoder != null ? resourceEncoder.getId() : str).getBytes("UTF-8"));
        Encoder encoder2 = this.sourceEncoder;
        if (encoder2 != null) {
            str = encoder2.getId();
        }
        messageDigest.update(str.getBytes("UTF-8"));
    }
}
