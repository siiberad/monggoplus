package com.monggovest.MonggoVestBackEnd.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class UtilsDate {

    // FUNGSI GENERATE DATE TO STRING
    public String generateDateInvoice (){
        return generateDate ();
    }
    private String generateDate () {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
