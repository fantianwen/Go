package com.study.radasm.go.Enum;

/**
 * this is a status code for net-result
 *
 * Created by RadAsm on 15/5/11.
 */
public enum  LoadingStatusEnum {
    SUCCESS(1),
    FAILURE(2),
    UNKNOWN(3);

    private int status_code;

    LoadingStatusEnum(int status_code){
        this.status_code=status_code;
    }

    public int getStatus_code() {
        return status_code;
    }
}
