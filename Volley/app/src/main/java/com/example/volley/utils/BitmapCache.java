package com.example.volley.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class BitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache

{
    public static int getDefaultLruCacheSize()
    {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        return cacheSize;
    }

    public BitmapCache()
    {
        this(getDefaultLruCacheSize());
    }
    public BitmapCache(int maxSize)
    {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value)
    {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url)
    {
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap)
    {
            put(url, bitmap);
    }
}
