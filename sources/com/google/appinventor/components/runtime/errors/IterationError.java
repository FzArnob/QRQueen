package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.runtime.util.ErrorMessages;

public class IterationError extends DispatchableError {
    public IterationError(int i, Object[] objArr) {
        super(i, objArr);
    }

    public int getIndex() {
        return ((Integer) getArguments()[0]).intValue();
    }

    public String getExpected() {
        return (String) getArguments()[1];
    }

    public String getFound() {
        return (String) getArguments()[2];
    }

    public static DispatchableError fromError(int i, DispatchableError dispatchableError) {
        int errorCode = dispatchableError.getErrorCode();
        if (errorCode == 3405) {
            return new IterationError(ErrorMessages.ERROR_INVALID_POINT_AT_INDEX, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, dispatchableError.getArguments()));
        }
        if (errorCode == 3409) {
            return new IterationError(ErrorMessages.ERROR_INVALID_NUMBER_OF_VALUES_IN_POINT_AT_INDEX, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, dispatchableError.getArguments()));
        }
        if (errorCode != 3410) {
            return dispatchableError;
        }
        return new IterationError(ErrorMessages.ERROR_INVALID_TYPE_AT_INDEX, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, dispatchableError.getArguments()));
    }

    private static Object[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, Object[] objArr) {
        Object[] objArr2 = new Object[(objArr.length + 1)];
        objArr2[0] = Integer.valueOf(i);
        System.arraycopy(objArr, 0, objArr2, 1, objArr.length);
        return objArr2;
    }
}
