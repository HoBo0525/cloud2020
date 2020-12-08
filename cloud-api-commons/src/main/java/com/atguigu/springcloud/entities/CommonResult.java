package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hobo
 * @create 2020-12-07 18:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T payment;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
