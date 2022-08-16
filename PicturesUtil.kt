package edu.ecu.cs.pirateplaces

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point

fun getScaledBitmap(path: String, activity: Activity): Bitmap {
    val size = Point()
    activity.windowManager.defaultDisplay.getSize(size)
    return getScaledBitmap(path, size.x, size.y)
}
fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap
{
    var opts = BitmapFactory.Options()
    opts.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, opts)
    val srcWidth = opts.outWidth.toFloat()
    val srcHeight = opts.outHeight.toFloat()
    var sampleSize = 1
    if (srcHeight > destHeight || srcWidth > destWidth)
    {
        val heightScale = srcHeight / destHeight
        val widthScale = srcWidth / destWidth
        val sampleScale = if (heightScale > widthScale)
        {
            heightScale
        }
        else
        {
            widthScale
        }
        sampleSize = Math.round(sampleScale)
    }
    opts = BitmapFactory.Options()
    opts.inSampleSize = sampleSize
    return BitmapFactory.decodeFile(path, opts)
}
