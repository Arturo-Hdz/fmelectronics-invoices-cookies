package com.fmelectronics.orders.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Date;

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Slf4j
public class StandardResponse {
//    private String code;
//    private String message;
//    private Object data;

    private StandardResponse(){}

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){
        return new ResponseEntity<String>("Text : " + message,httpStatus);
    }

    public static String getUUID(){
        Date date = new Date();
        long time = date.getTime();
        return "INVOICE-" + time;
    }
//
//    public static JSONArray getJsonArrayFromString(String data) throws JSONException {
//        JSONArray jsonArray = new JSONArray(data);
//        return jsonArray;
//    }
//
//    public static Map<String,Object> getMapFromJson(String data){
//        if(!Strings.isNullOrEmpty(data)){
//            return new Gson().fromJson(data,new TypeToken<Map<String,Object>>(){
//            }.getType());
//        }
//        return new HashMap<>();
//    }

    public static boolean isFileExist(String path){
        log.info("Inside of isFileExist{}",path);
        try{
            File file = new File(path);
            return file != null && file.exists() ? Boolean.TRUE : Boolean.FALSE;
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return false;
    }
}
