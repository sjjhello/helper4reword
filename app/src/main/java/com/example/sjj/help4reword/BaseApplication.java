package com.example.sjj.help4reword;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sjj on 2018/3/9.
 */

public class BaseApplication extends Application{
    private static  BaseApplication application ;
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initDataBase();
    }

    private void initDataBase() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "mission_db", null);
        db = openHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static Application getInstance() {
        return application;
    }

    public static SQLiteDatabase getDb() {
        return db;
    }

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
