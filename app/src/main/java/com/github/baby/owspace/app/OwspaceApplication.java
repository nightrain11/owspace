package com.github.baby.owspace.app;

import android.app.Application;
import android.content.Context;

import com.github.baby.owspace.BuildConfig;
import com.github.baby.owspace.di.components.DaggerNetComponent;
import com.github.baby.owspace.di.components.NetComponent;
import com.github.baby.owspace.di.modules.ApplicationModule;
import com.github.baby.owspace.di.modules.NetModule;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * Owspace
 */
public class OwspaceApplication extends Application{

    private static OwspaceApplication instance;

    public static OwspaceApplication get(Context context){
        return (OwspaceApplication)context.getApplicationContext();
    }

    private NetComponent netComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogger();
        initNet();
    }
    private void initLogger(){
        if (!BuildConfig.API_ENV){
            Logger.init("GithubOwspace")                 // default PRETTYLOGGER or use just init()
                    .methodCount(3)                 // default 2
                    .logLevel(LogLevel.FULL) ;       // default LogLevel.FULL
        }else{
            Logger.init("GithubOwspace")                 // default PRETTYLOGGER or use just init()
                    .methodCount(3)                 // default 2
                    .logLevel(LogLevel.NONE) ;       // default LogLevel.FULL
        }
    }
    private void initNet(){
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public static OwspaceApplication getInstance() {
        return instance;
    }
}
