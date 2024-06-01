package com.simple.cloud.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Charles
 * @create 2024-05-10-19:49
 */
public class ObjectCastUtil {
    public static byte[] toByteArray(Object obj){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] byteArray = null;

        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            byteArray = bos.toByteArray();

            oos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArray;
    }
}
