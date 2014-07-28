package org.jinn.typhoon.utils;

import java.text.DecimalFormat;

/**
 * Created by gumingcn on 14-7-28.
 */
public class FormatUtil {
    public static final DecimalFormat df=new DecimalFormat("#.00000000");

    public static String doubleFormat(Double value){
        return df.format(value);
    }

}
