package de.rusticprism.kreiscraft.utils;

import java.lang.reflect.Field;

public class EasyReflection {

    public static void changeField(Object object,String name,Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(object,value);
        field.setAccessible(!field.isAccessible());
    }
}
