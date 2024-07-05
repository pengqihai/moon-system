package cn.doug.system.common.enums;

import cn.doug.common.base.IBaseEnum;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @Author: pengqihai
 * @Date: 2024/07/05/15:05
 * @Description:
 */
public enum PersonnelTypeEnum implements IBaseEnum<Integer> {

    CADRE(0, "干部"),

    OTHER(1,"其它")

    ;
    @Getter
    @EnumValue //  Mybatis-Plus 提供注解表示插入数据库时插入该值
    private Integer value;

    @Getter
    // @JsonValue //  表示对枚举序列化时返回此字段
    private String label;

    PersonnelTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
