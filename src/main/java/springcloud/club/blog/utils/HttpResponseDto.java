package springcloud.club.blog.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 前端返回实体类
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class HttpResponseDto<T> {

    /**
     * 常用状态码
     * 200--正常
     * 403--无权限、未登录
     * 500--服务器内部错误
     */
    private int code = 200;

    /**
     * 提示信息
     */
    private String msg = "";

    /**
     * 在分页时,记录数据总数
     */
    private int count = 0;

    /**
     * 列表多个对象
     */
    private List<T> rows = new ArrayList<>();

    /**
     * 泛型对象
     */
    private T obj;

    /**
     * 单个对象
     */
    private Object data;



    public HttpResponseDto(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static HttpResponseDto success() {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setCode(200);
        httpResponseDto.setMsg("操作成功");
        return httpResponseDto;
    }

    public static HttpResponseDto success(String msg) {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setCode(200);
        httpResponseDto.setMsg(msg);
        return httpResponseDto;
    }

    public static HttpResponseDto error() {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setCode(500);
        httpResponseDto.setMsg("操作失败");
        return httpResponseDto;
    }

    public static HttpResponseDto error(int code, String errmsg) {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setCode(code);
        httpResponseDto.setMsg(errmsg);
        return httpResponseDto;
    }

    public static HttpResponseDto error(String errmsg) {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setCode(500);
        httpResponseDto.setMsg(errmsg);
        return httpResponseDto;
    }
}
