package org.alie.alieioc;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Alie on 2020/2/17.
 * 类描述 ioc类，用于方法调用
 * 版本
 */
public class ViewInject {

    public static void doInject(Context context) {
        doInjectLayout(context);
        doInjectView(context);
    }

    private static void doInjectLayout(Context context) {
        Class<? extends Context> aClass = context.getClass();
        ContentView contentView = aClass.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layoutId = contentView.layoutId();
            try {
                Method method = aClass.getMethod("setContentView", int.class);
                method.invoke(context, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


    private static void doInjectView(Context context) {
        Class<? extends Context> aClass = context.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            SingleView annotation = declaredField.getAnnotation(SingleView.class);
            if (annotation != null) {
                int viewId = annotation.viewId();
                try {
                    Method findViewByIdMethod = aClass.getMethod("findViewById", int.class);
                    View invokeView = (View) findViewByIdMethod.invoke(context, viewId);
                    declaredField.setAccessible(true);
                    declaredField.set(context,invokeView);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
