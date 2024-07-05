package cn.doug.system.common.enums;

import cn.doug.common.base.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @Author: pengqihai
 * @Date: 2024/07/05/15:10
 * @Description:
 */
public enum DutiesTypeEnum implements IBaseEnum<Integer> {

    DIRECTOR(0,"主任"),

    EXECUTIVE_DEPUTY_DIRECTOR(1,"常务副主任"),

    DEPUTY_DIRECTOR(2,"副主任"),

    OTHER(3,"其它")

    ;
    @Getter
    @EnumValue //  Mybatis-Plus 提供注解表示插入数据库时插入该值
    private Integer value;

    @Getter
    // @JsonValue //  表示对枚举序列化时返回此字段
    private String label;

    DutiesTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
