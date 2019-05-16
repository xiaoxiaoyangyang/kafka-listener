package com.sensetime.fis.senseguard.opendoor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 11:43
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenDoorException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
}
