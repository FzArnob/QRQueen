package com.google.appinventor.components.annotations.androidmanifest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReceiverElement {
    String enabled() default "";

    String exported() default "true";

    String icon() default "";

    IntentFilterElement[] intentFilters() default {};

    String label() default "";

    MetaDataElement[] metaDataElements() default {};

    String name();

    String permission() default "";

    String process() default "";
}
