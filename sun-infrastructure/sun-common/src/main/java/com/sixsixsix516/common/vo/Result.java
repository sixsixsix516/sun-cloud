package com.sixsixsix516.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用返回对象
 *
 * @author SUN
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 返回状态
     */
    private Integer code;

    /**
     * 提示语
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 总记录数(分页用)
     */
    private long total;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(0, "请求成功", data, 0);
    }

    public static <T> Result<T> failed() {
        return new Result<>(500, "请求失败", null, 0);
    }

}
