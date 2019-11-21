package cn.miaoying.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;
}
