package com.maisel.hospital.util;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @aucthor:jjx
 * @Create:2019-01-04 19:02
 */
public class DateConvert implements Converter<String, Date> {

    private Set<String> set = new HashSet<String>();

    public void setSet(Set<String> set) {
        this.set = set;
    }

    @Override
    public Date convert(String s) {
        System.out.println("***********"+s);
        try {
            for (String s1 : set) {
                SimpleDateFormat adf = new SimpleDateFormat(s1);
                Date date = adf.parse(s);
                return date;
            }
        } catch (Exception e) {

        }
        return null;
    }
}
