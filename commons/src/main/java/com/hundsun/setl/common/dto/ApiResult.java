package cn.hz.ddbm.setl.common.dto;


import lombok.Getter;

@Getter
public class ApiResult {
    Boolean code;
    String  result;
    String  message;

    public static ApiResult of(String result) {
        ApiResult r = new ApiResult();
        r.code   = true;
        r.result = result;
        return r;
    }

    public static ApiResult error(String result) {
        ApiResult r = new ApiResult();
        r.code   = false;
        r.result = result;
        return r;
    }
}
