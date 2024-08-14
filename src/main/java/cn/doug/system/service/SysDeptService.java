package cn.doug.system.service;

import cn.doug.system.model.form.SysDeptForm;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.doug.system.common.model.Option;
import cn.doug.system.model.entity.SysDept;
import cn.doug.system.model.query.SysDeptQuery;
import cn.doug.system.model.vo.SysDeptVO;

import java.util.List;

/**
 * 部门业务接口
 *
 * @author pengqihai
 * @since 2021/8/22
 */
public interface SysDeptService extends IService<SysDept> {
    /**
     * 部门列表
     *
     * @return
     */
    List<SysDeptVO> listDepartments(SysDeptQuery queryParams);

    /**
     * 部门树形下拉选项
     *
     * @return
     */
    List<Option> listDeptOptions();

    /**
     * 新增部门
     *
     * @param formData
     * @return
     */
    Long saveDept(SysDeptForm formData);

    /**
     * 修改部门
     *
     * @param deptId
     * @param formData
     * @return
     */
    Long updateDept(Long deptId, SysDeptForm formData);

    /**
     * 删除部门
     *
     * @param ids 部门ID，多个以英文逗号,拼接字符串
     * @return
     */
    boolean deleteByIds(String ids);

    /**
     * 获取部门详情
     *
     * @param deptId
     * @return
     */
    SysDeptForm getDeptForm(Long deptId);
}
