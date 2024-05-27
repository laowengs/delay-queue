package io.github.laowengs.kafka.delay.queue.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse<T> implements Serializable {
    private int code;
    private String message;
    private T data;


    public static <T>  JsonResponse<T> getInstance(int code,String message,T data){
        return new JsonResponse<T>(code,message,data);
    }

    public static JsonResponse<Void> getInstance(){
        return new JsonResponse<Void>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), null);
    }

    public static <T>  JsonResponse<T> getInstance(T data){
        return new JsonResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static JsonResponse<Void> getInstance(int code,String message){
        return new JsonResponse<>(code,message,null);
    }


    public static JsonResponse<Void> getInstance(ResponseCode responseCode){
        return new JsonResponse<Void>(responseCode.getCode(),responseCode.getMessage(),null);
    }


}
