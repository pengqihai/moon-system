package cn.doug.system.model.vo.digit;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户导出视图对象
 *
 * @author pengqihai
 * @since 2022/4/11 8:46
 */

@Data
@ColumnWidth(20)
public class VeteranCadreExportVO {

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String fullName;

    /**
     * 曾用名
     */
    @ExcelProperty(value = "曾用名")
    private String otherName;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号")
    private String idCard;

    /**
     * 性别((1:男;2:女))
     */
    @ExcelProperty(value = "性别")
    private String gender;

    /**
     * 出生日期
     */
    @ExcelProperty(value = "出生日期")
    private LocalDateTime birthTime;

    /**
     * 籍贯
     */
    @ExcelProperty(value = "籍贯")
    private String nativePlace;

    /**
     * 政治面貌(数据字典'政治面貌')
     */
    @ExcelProperty(value = "政治面貌")
    private String politic;

    /**
     * 电话号码
     */
    @ExcelProperty(value = "电话号码")
    private String phone;

    /**
     * 家庭住址
     */
    @ExcelProperty(value = "家庭住址")
    private String familyAdress;

    /**
     * 常住住址
     */
    @ExcelProperty(value = "常住住址")
    private String liveAdress;

    /**
     * 婚姻状况(0.已婚，1.未婚，2.丧偶，3.离婚，4.再婚)
     */
    @ExcelProperty(value = "婚姻状况")
    private String marryFlag;

    /**
     * 文化程度(调用字典)
     */
    @ExcelProperty(value = "文化程度")
    private String degree;

    /**
     * 居住情况
     */
    @ExcelProperty(value = "居住情况")
    private String liveFlag;

    /**
     * 入党时间
     */
    @ExcelProperty(value = "入党时间")
    private LocalDateTime joinParyTime;

    /**
     * 参加工作时间
     */
    @ExcelProperty(value = "参加工作时间")
    private LocalDateTime workTime;

    /**
     * 人员类型(0.干部，1.其他)
     */
    @ExcelProperty(value = "人员类型")
    private String personnelType;

    /**
     * 是否关工委老同志(0.否，1.是)
     */
    @ExcelProperty(value = "是否关工委老同志")
    private String isCommitte;

    /**
     * 现任关工委职务(0.主任，1.常务副主任，2.副主任，3.其他)
     */
    @ExcelProperty(value = "现任关工委职务")
    private String committeDut;

    /**
     * 党支部信息
     */
    @ExcelProperty(value = "党支部信息")
    private String branchId;

    /**
     * 退/离休类型(0.退休，1.离休)
     */
    @ExcelProperty(value = "退/离休类型")
    private Integer retireType;

    /**
     * 退/离休时工作单位
     */
    @ExcelProperty(value = "退/离休时工作单位")
    private String retireGov;

    /**
     * 退/离休时工作职务
     */
    @ExcelProperty(value = "退/离休时工作职务")
    private String retireWork;

    /**
     * 退/离休时职级(调用基础数据字典'级别')
     */
    @ExcelProperty(value = "退/离休时职级")
    private String retireGrade;

    /**
     * 退/离休时间
     */
    @ExcelProperty(value = "退/离休时间")
    private LocalDateTime retireTime;

    /**
     * 现享受待遇(调用基础数据字典'现享受待遇')
     */
    @ExcelProperty(value = "现享受待遇")
    private String salary;

    /**
     * 是否为异地安置(0.否，1.是)
     */
    @ExcelProperty(value = "是否为异地安置")
    private String relocation;

    /**
     * 安置地
     */
    @ExcelProperty(value = "安置地")
    private String arrangePlac;

    /**
     * 接受安置单位
     */
    @ExcelProperty(value = "接受安置单位")
    private String receiveGov;

    /**
     * 接受安置单位电话
     */
    @ExcelProperty(value = "接受安置单位电话")
    private String receivePhone;

    /**
     * 健康状况(调用 26 基础数据字典'健康状况')
     */
    @ExcelProperty(value = "健康状况")
    private String healthFlag;

    /**
     * 疾病状况
     */
    @ExcelProperty(value = "疾病状况")
    private String diseaseDes;

    /**
     * 特长
     */
    @ExcelProperty(value = "特长")
    private String speciality;

    /**
     * 爱好
     */
    @ExcelProperty(value = "爱好")
    private String hobby;

    /**
     * 参加社团情况
     */
    @ExcelProperty(value = "参加社团情况")
    private String joinCorporati;

    /**
     * 是否离世(0.否，1.是)
     */
    @ExcelProperty(value = "是否离世")
    private String isDie;

    /**
     * 离世时间
     */
    @ExcelProperty(value = "离世时间")
    private LocalDateTime dieTime;

    /**
     * 删除标识(0.正常，1.删除，2.彻底删除)
     */
    @ExcelProperty(value = "删除标识")
    private Integer deleted;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ExcelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
