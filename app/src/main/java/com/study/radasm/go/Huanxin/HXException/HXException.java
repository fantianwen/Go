package com.study.radasm.go.Huanxin.HXException;

/**
 * Created by RadAsm on 15/5/15.
 */
public class HXException extends Exception {
    protected int errorCode = -1;

    public HXException() {

    }

    public HXException(String detailMessage) {
        this(detailMessage, null);
    }

    public HXException(Throwable throwable) {
        this(null, throwable);
    }

    public HXException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
//        //初始cause(这个cause是父类中的成员属性)
//        super.initCause(throwable);
    }

    /**
     * 自定义的exception类。
     * @param errorCode
     * @param detailMessage
     */
    public HXException(int errorCode,String detailMessage){
        this(detailMessage);
        //初始化自定义的errorCode
        this.errorCode=errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
