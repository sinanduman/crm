package crm.irfan;

import crm.irfan.entity.Aylar;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    
    public static String mamulInfoDecompose(String bileseninfo, int type) {
        if (bileseninfo == null)
            return null;
        else {
            //System.out.print(bileseninfo);
            String returnValue[] = null;
            // 1: mamul ad, 2: mamul kod, 3: mamul agirlik 4: GKR No
            returnValue = bileseninfo.split("\\|");
            //System.out.println("; " + type + "==" + returnValue[type - 1].split(";")[0]);
            return returnValue[type - 1].toString();
            //return returnValue[type - 1].split(";")[0];
        }
    }
    
    public static String splitLine(String line, String delimeter, String newLineChar) {
        String [] tempArray = line.split(delimeter);
        String retVal       = "";
        
        String newLine = "";
        for(String word: tempArray) {
            retVal  = retVal + newLine + word;
            newLine = newLineChar;
        }
        if(retVal.equals("-1")) {
            retVal = "-";
        }
        return retVal;
    }
    
    public static String date_tr_to_month(String dateinfo) {
        String returnValue = dateinfo;
        Integer tmp = 1;
        if (returnValue.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
            tmp = Integer.valueOf(dateinfo.substring(3, 5));
            System.out.println("tmp :"+ tmp);
            for(Aylar a: Aylar.values() ) {
                if (a.value()==tmp) {
                    returnValue = a.tr();
                }
            }
        }
        return returnValue;
    }
    
    public static String date_tr_to_eng(String dateinfo) {
        System.out.println(dateinfo);
        String returnValue = dateinfo;
        if (returnValue.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
            returnValue = dateinfo.substring(6) + "-" + dateinfo.substring(3, 5) + "-" +  dateinfo.substring(0, 2);
        }
        System.out.println(dateinfo);
        return returnValue;
    }
    
    public static String date_eng_to_tr(String dateinfo) {
        String returnValue = dateinfo;
        if (returnValue.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            returnValue = dateinfo.substring(8) + "-" + dateinfo.substring(5, 7) + "-" +  dateinfo.substring(0, 4);
        }
        return returnValue;
    }
    
    public static Boolean isNumeric(String str) {
        Boolean returnValue = false;
        if (str.matches("[0-9]+")) {
            returnValue = true;
        }
        return returnValue;
    }
    
    public static Boolean isDate(String str) {
        Boolean returnValue = false;
        if (str.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}") || str.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
            returnValue = true;
        }
        return returnValue;
    }
    
    public static long getToken() {
        return System.currentTimeMillis();
    }
    
    public static Timestamp date_tr_to_timestamp(String dateinfo) {
        
        Timestamp returnValue = null;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
        Date date = new Date();
        try {
            date = df.parse(dateinfo);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        returnValue = new Timestamp(date.getTime());
        return returnValue;
    }
    
    public static String getTarih(Date tarih) {
        String date = (tarih != null) ? (new SimpleDateFormat("yyyy-MM-dd").format(tarih)) : "";
        return date;
    }
    
    public static String getTarihTR(Date tarih) {
        String date = (tarih != null) ? (new SimpleDateFormat("dd-MM-yyyy").format(tarih)) : "";
        return date;
    }
    
    public static String getTarihTRShort(Date tarih) {
        String date = (tarih != null) ? (new SimpleDateFormat("dd.MM.yy").format(tarih)) : "";
        return date;
    }
    
    public static double Round (int pay, int payda) {   
        double temp = (pay)*1.0/payda;
        temp= ((double)((int)(temp*100)))/100;
        return temp;        
    }
    public static double Round (double deger, double ondalik) {   
        double temp = Math.round(deger * Math.pow(10, ondalik)) / Math.pow(10, ondalik);
        return temp;    
    }
    
    public static boolean isNotEmptyOrNull(Object o ) {        
        if(o!=null && !o.equals("")){
            return true;
        }
        return false;
    }
}
