package cn.doug.system.common.enums;

import cn.doug.common.base.IBaseEnum;

/**
 * @Author: pengqihai
 * @Date: 2024/07/04/15:32
 * @Description:
 */
public enum MarryEnum implements IBaseEnum<Integer> {

    MARRIED(0,"已婚"),

    UNMARRIED(1,"未婚"),

    BEREAVE(2,"丧偶"),

    DIVORCE(3,"离婚"),

    REMARRY(4,"再婚")

    ;

    private Integer value;
    private String label;

    private MarryEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }
}
