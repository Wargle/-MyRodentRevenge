/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.*;

/**
 *
 * @author Eleme
 */
public class MyRegex {
    private static Pattern pattern;
    private static Matcher matcher;

    public static boolean isMatch(String regex, String match) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(match);
        if(matcher.find()) {
            return true;
        }
        return false;
    }    
}
