package com.study.radasm.go;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.study.radasm.go.Enum.LogLevelEnum;
import com.study.radasm.go.Model.LogModel;
import com.study.radasm.go.common.LogCatManager;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        LogCatManager manger=new LogCatManager();
        manger.cache(new LogModel("222", LogLevelEnum.DEBUG,"333","333","444"));
        manger.cache2File();





    }
}