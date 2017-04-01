package com.wn.quickrechatge.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wn.quickrechatge.R;

/**
 * Created by ds on 2017/3/2.
 * imageloader工具类
 */
public class ImageLoaderUtil {
    private static ImageLoaderUtil instance;
    public static ImageLoaderUtil getInstance(){
        if (instance==null){
            synchronized (ImageLoaderUtil.class){
                instance = new ImageLoaderUtil();
            }
        }
        return instance;
    }
    private DisplayImageOptions getDisplayImageOptions(int loadImg,int errorImg,int radius){
        loadImg = loadImg==0? R.drawable.backtwo:loadImg;
        errorImg = errorImg==0? R.drawable.backtwo:errorImg;
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(loadImg) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(errorImg)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(errorImg)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(radius))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成
        return options;
    }
    private ImageLoaderConfiguration getConfiguration(Context context){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
//                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(100) //缓存的文件数量
//                .diskCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        return config;
    }
    public void init(Context context){
        ImageLoader.getInstance().init(getConfiguration(context));
    }
    /**
     * 显示本地网络图片
     * @param imageUrl 图片id
     * @param iv 显示控件
     * @param listener 显示结果监听
     * @param loadImg 加载中显示的图片
     * @param errorImg 加载错误显示图片
     */
    public void displayImg(String imageUrl,ImageView iv,int loadImg,int errorImg, ImageLoadingListener listener){
        show(imageUrl,iv,listener,loadImg,errorImg,0);
    }
    public void displayImg(String imageUrl,ImageView iv,int loadImg,int errorImg,int radiu, ImageLoadingListener listener){
        show(imageUrl,iv,listener,loadImg,errorImg,radiu);
    }
    /**
     * 显示本地drawable图片
     * @param imageUrl 图片id
     * @param iv 显示控件
     * @param listener 显示结果监听
     * @param loadImg 加载中显示的图片
     * @param errorImg 加载错误显示图片
     */
    public void displayImg(int imageUrl,ImageView iv,int loadImg,int errorImg, ImageLoadingListener listener){
        show("drawable://"+imageUrl,iv,listener,loadImg,errorImg,0);
    }
    public void displayImg(int imageUrl,ImageView iv,int loadImg,int errorImg,int radiu, ImageLoadingListener listener){
        show("drawable://"+imageUrl,iv,listener,loadImg,errorImg,radiu);
    }

    public void displayImgSDcard(String imageUrl,ImageView iv,int loadImg,int errorImg, ImageLoadingListener listener){
        show("file:///"+imageUrl,iv,listener,loadImg,errorImg,0);
    }
    public void displayImgSDcard(String imageUrl,ImageView iv,int loadImg,int errorImg,int radiu,ImageLoadingListener listener){
        show("file:///"+imageUrl,iv,listener,loadImg,errorImg,radiu);
    }

    public void displayImgAsset(String imageUrl,ImageView iv,int loadImg,int errorImg, ImageLoadingListener listener){
        show("assets://"+imageUrl,iv,listener,loadImg,errorImg,0);
    }
    public void displayImgAsset(String imageUrl,ImageView iv,int loadImg,int errorImg,int radiu, ImageLoadingListener listener){
        show("assets://"+imageUrl,iv,listener,loadImg,errorImg,radiu);
    }

    private void show(String url,ImageView iv, ImageLoadingListener listener,int loadImg,int errorImg,int radiu){
        DisplayImageOptions options = getDisplayImageOptions(loadImg,errorImg,radiu);
        ImageLoader.getInstance().displayImage(url,iv,options,listener);
    }

//    String imageUri = "content://media/external/audio/albumart/13"; // 媒体文件夹
}
