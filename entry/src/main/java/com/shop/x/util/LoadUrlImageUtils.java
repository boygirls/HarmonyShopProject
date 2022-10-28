package com.shop.x.util;

import ohos.agp.components.Image;
import ohos.app.Context;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadUrlImageUtils {

    /**
     * 将图片URL地址转换为资源文件工具类
     * @param context 当前对象
     * @param netImgUrl 图片URL地址
     * @param image image
     */
    public static  void loadImage(Context context, String netImgUrl, Image image){
        // 为每个图片的加载都单独创建一个线程实现类
        TaskDispatcher globalTaskDispatcher = context.getGlobalTaskDispatcher(TaskPriority.DEFAULT);
        globalTaskDispatcher.asyncDispatch(()->{
            HttpURLConnection connection = null;

            try {
                // 建立与网络图片之间的链接
                URL url = new URL(netImgUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // 从连接中获取输入流（可以读取到图片数据）
                InputStream inputStream = connection.getInputStream();

                // 根据流将图片缓存到IamgeSrouce,创建图片对象
                ImageSource imageSource = ImageSource.create(inputStream, new ImageSource.SourceOptions());

                // 缓存图片解码参数
                ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
                decodingOptions.desiredPixelFormat = PixelFormat.ARGB_8888;

                // pixelMap表示图片
                PixelMap pixelmap = imageSource.createPixelmap(decodingOptions);

                // 将图片载入到数组中
                // 将载入图片放入新的线程中
                context.getUITaskDispatcher().asyncDispatch(()->{
                    image.setPixelMap(pixelmap);
                    pixelmap.release();//释放资源
                });


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
