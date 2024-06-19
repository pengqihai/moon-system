package cn.doug.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: pengqihai@gogpay.cn
 * @description 根据区划获取列表
 * @date: 2024/6/19 14:58
 */
@Data
public class RegionCodeQuery {

    @Schema(description = "父级区划", example = "-1")
    private String areaCode;
}
