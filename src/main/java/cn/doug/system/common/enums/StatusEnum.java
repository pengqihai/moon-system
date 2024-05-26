package cn.doug.system.common.enums;

import cn.doug.system.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 状态枚举
 *
 * @author pengqihai
 * @since 2022/10/14
 */
public enum StatusEnum implements IBaseEnum<Integer> {

    ENABLE(1, "启用"),
    DISABLE (0, "禁用");

    @Getter
    private Integer value;

    @Getter
    private String label;

    StatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}