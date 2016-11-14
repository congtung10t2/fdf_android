package com.framgia.foodanddrink.data;

/**
 * Created by framgia on 10/11/2016.
 */
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@IntDef({RequestDef.CAMERA_REQUEST, RequestDef.GALLERY_REQUEST})
public @interface RequestDef {
    int CAMERA_REQUEST = 1;
    int GALLERY_REQUEST = 2;
}
