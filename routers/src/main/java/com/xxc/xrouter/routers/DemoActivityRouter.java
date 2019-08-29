package com.xxc.xrouter.routers;

import android.content.Context;
import com.mrcd.xrouter.core.IntentArgs;
import com.mrcd.xrouter.core.IntentInterceptor;
import com.mrcd.xrouter.core.IntentWrapper;
import java.lang.String;

public final class DemoActivityRouter {
  private int mRequestCode;

  private IntentArgs mArgs;

  private IntentInterceptor mInterceptor;

  public DemoActivityRouter() {
    mArgs = IntentWrapper.getInstance().prepare();
    mRequestCode = -1;
  }

  public DemoActivityRouter setRequestCode(int requestCode) {
    mRequestCode = requestCode;
    return this;
  }

  public DemoActivityRouter setFrom(String param) {
    mArgs.setValue("mFrom", param);
    return this;
  }

  public DemoActivityRouter setCurrentPage(String param) {
    mArgs.setValue("mCurrentPage", param);
    return this;
  }

  public DemoActivityRouter setInterceptor(IntentInterceptor interceptor) {
    mInterceptor = interceptor;
    return this;
  }

  public final void launch(Context context) {
    mArgs.requestCode(mRequestCode).wrap().intercept(mInterceptor).launch(context, "com.mrcd.demo.DemoActivity");
  }
}
