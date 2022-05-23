package tj.lty.backend.model;

import lombok.Data;
import tj.lty.backend.enums.ResultCode;

@Data
public class Result<T> {
    /*返回体*/
    private  Integer code;
    private String msg;
    private T data;

    /**成功**/
    public  Result success(Object object){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**失败**/
    public  Result fail(Object object){
        Result result = new Result();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMsg(ResultCode.FAIL.getMsg());
        result.setData(object);
        return result;
    }
    public  Result result(Integer code, String msg, Object object){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
}