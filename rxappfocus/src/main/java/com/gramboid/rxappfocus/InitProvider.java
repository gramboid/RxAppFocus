package com.gramboid.rxappfocus;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.Nullable;

public final class InitProvider extends ContentProvider {

    public InitProvider() {
    }

    @Override
    public boolean onCreate() {
        Context appContext = getContext().getApplicationContext();
        if (appContext instanceof Application) {
            Application app = (Application) appContext;
            AppFocusProvider.getInstance().init(app);
            return true;
        }

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}