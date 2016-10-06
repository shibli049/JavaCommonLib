package com.shibli049.common;

import java.io.Console;
import java.io.IOException;
import java.util.List;

/**
 * @author shibli
 * Created on 06/10/2016.
 */
public class Utils {
    public static String readConsolePassword(String msg) throws IOException {
        Console console = System.console();
        if(console == null){
            throw new IOException("Console not attached.");
        }
        if(isEmpty(msg)){
            msg = "password: ";
        }
        char passwordArray[] = console.readPassword(msg);
        return new String(passwordArray);
    }


    public static boolean isEmpty(String s) {
        return ((s == null) || (s.length() == 0));
    }


    public static boolean isEmpty(List list) {
        return ((list == null) || (list.size() == 0));
    }



}
