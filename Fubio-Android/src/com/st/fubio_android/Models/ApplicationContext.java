package com.st.fubio_android.Models;

import android.content.Context;

public class ApplicationContext extends android.app.Application {

	private static ApplicationContext instance;

    public ApplicationContext() {
    	instance = this;
    }

    public static Context getContext() {
    	return instance;
    }

}
