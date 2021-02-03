package com.example.demo.commons.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StringUtil {

    public static String getCommaSeparatedStringFromList(List<String> list){
        if(Objects.nonNull(list)) {
            String stringValue = Arrays.toString(list.toArray()).replace("[", "").replace("]", "");
            return stringValue;
        }
        return null;
    }
}
