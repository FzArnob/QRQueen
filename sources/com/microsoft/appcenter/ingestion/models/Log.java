package com.microsoft.appcenter.ingestion.models;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public interface Log extends Model {
    void addTransmissionTarget(String str);

    Device getDevice();

    String getDistributionGroupId();

    UUID getSid();

    Object getTag();

    Date getTimestamp();

    Set<String> getTransmissionTargetTokens();

    String getType();

    String getUserId();

    void setDevice(Device device);

    void setDistributionGroupId(String str);

    void setSid(UUID uuid);

    void setTag(Object obj);

    void setTimestamp(Date date);

    void setUserId(String str);
}
