package tests;

import android.test.InstrumentationTestCase;

import com.study.radasm.go.Utils.TextUtils;

/**
 * Created by RadAsm on 15/5/16.
 */
public class TextUtilsTestDemo extends InstrumentationTestCase {

    public void test(){
        boolean empty = TextUtils.isNotEmpty("11", null, "11");
        assertEquals(empty,false);
    }


}
