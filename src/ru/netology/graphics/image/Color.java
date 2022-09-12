package ru.netology.graphics.image;

public class Color implements TextColorSchema {

    @Override
    public char convert(int color) {

        String c = "@%$#+*-'";
        //

        int charValue = Math.round(c.length() * color / 255);
        charValue = Math.max(charValue, 0);
        charValue = Math.min(charValue, c.length() - 1);


        return c.charAt(charValue);
    }
}
