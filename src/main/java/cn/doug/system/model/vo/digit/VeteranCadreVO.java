package cn.doug.system.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 老干部工作人员与离退休党员	 VO
 *
 * @author pengqihai
 * @since 2024-06-25
 */
@Getter
@Setter
@Schema( description = "老干部工作人员与离退休党员	视图对象")
public class VeteranCadreVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String fullName;

    /**
     * 曾用名
     */
    @Schema(description = "曾用名")
    private String otherName;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idCard;

    /**
     * 性别((1:男;2:女))
     */
    @Schema(description = "性别((1:男;2:女))")
    private Integer gender;

    /**
     * 出生日期
     */
    @Schema(description = "出生日期")
    private LocalDateTime birthTime;

    /**
     * 籍贯
     */
    @Schema(description = "籍贯")
    private String nativePlace;

    /**
     * 政治面貌(数据字典'政治面貌')
     */
    @Schema(description = "政治面貌(数据字典'政治面貌')")
    private String politic;

    /**
     * 电话号码
     */
    @Schema(description = "电话号码")
    private String phone;

    /**
     * 家庭住址
     */
    @Schema(description = "家庭住址")
    private String familyAdress;

    /**
     * 常住住址
     */
    @Schema(description = "常住住址")
    private String liveAdress;

    /**
     * 婚姻状况(0.已婚，1.未婚，2.丧偶，3.离婚，4.再婚)
     */
    @Schema(description = "婚姻状况(0.已婚，1.未婚，2.丧偶，3.离婚，4.再婚)")
    private Integer marryFlag;

    /**
     * 文化程度(调用字典)
     */
    @Schema(description = "文化程度(调用字典)")
    private String degree;

    /**
     * 居住情况
     */
    @Schema(description = "居住情况")
    private String liveFlag;

    /**
     * 入党时间
     */
    @Schema(description = "入党时间")
    private LocalDateTime joinParyTime;

    /**
     * 参加工作时间
     */
    @Schema(description = "参加工作时间")
    private LocalDateTime workTime;

    /**
     * 人员类型(0.干部，1.其他)
     */
    @Schema(description = "人员类型(0.干部，1.其他)")
    private Integer personnelType;

    /**
     * 是否关工委老同志(0.否，1.是)
     */
    @Schema(description = "是否关工委老同志(0.否，1.是)")
    private Boolean isCommitte;

    /**
     * 现任关工委职务(0.主任，1.常务副主任，2.副主任，3.其他)
     */
    @Schema(description = "现任关工委职务(0.主任，1.常务副主任，2.副主任，3.其他)")
   private Integer committeDut;

    /**
     * 党支部信息
     */
    @Schema(description = "党支部信息")
    private String branchId;

    /**
     * 退/离休类型(0.退休，1.离休)
     */
    @Schema(description = "退/离休类型(0.退休，1.离休)")
    private Integer retireType;

    /**
     * 退/离休时工作单位
     */
    @Schema(description = "退/离休时工作单位")
    private String retireGov;

    /**
     * 退/离休时工作职务
     */
    @Schema(description = "退/离休时工作职务")
    private String retireWork;

    /**
     * 退/离休时职级(调用基础数据字典'级别')
     */
    @Schema(description = "退/离休时职级(调用基础数据字典'级别')")
    private String retireGrade;

    /**
     * 退/离休时间
     */
    @Schema(description = "退/离休时间")
    private LocalDateTime retireTime;

    /**
     * 现享受待遇(调用基础数据字典'现享受待遇')
     */
    @Schema(description = "现享受待遇(调用基础数据字典'现享受待遇')")
    private String salary;

    /**
     * 是否为异地安置(0.否，1.是)
     */
    @Schema(description = "是否为异地安置(0.否，1.是)")
    private Boolean relocation;

    /**
     * 安置地
     */
    @Schema(description = "安置地")
    private String arrangePlac;

    /**
     * 接受安置单位
     */
    @Schema(description = "接受安置单位")
    private String receiveGov;

    /**
     * 接受安置单位电话
     */
    @Schema(description = "接受安置单位电话")
    private String receivePhone;

    /**
     * 健康状况(调用 26 基础数据字典'健康状况')
     */
    @Schema(description = "健康状况(调用 26 基础数据字典'健康状况')")
    private String healthFlag;

    /**
     * 疾病状况
     */
    @Schema(description = "疾病状况")
    private String diseaseDes;

    /**
     * 特长
     */
    @Schema(description = "特长")
    private String speciality;

    /**
     * 爱好
     */
    @Schema(description = "爱好")
    private String hobby;

    /**
     * 参加社团情况
     */
    @Schema(description = "参加社团情况")
    private String joinCorporati;

    /**
     * 是否离世(0.否，1.是)
     */
    @Schema(description = "是否离世(0.否，1.是)")
    private Boolean isDie;

    /**
     * 离世时间
     */
    @Schema(description = "离世时间")
    private LocalDateTime dieTime;

    /**
     * 删除标识(0.正常，1.删除，2.彻底删除)
     */
    @Schema(description = "删除标识(0.正常，1.删除，2.彻底删除)")
    private Integer deleted;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}
