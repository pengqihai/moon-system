package cn.doug.system.service;

import cn.doug.system.model.entity.VeteranCadreEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.doug.system.model.form.VeteranCadreForm;
import cn.doug.system.model.query.VeteranCadrePageQuery;
import cn.doug.system.model.vo.VeteranCadrePageVO;
import cn.doug.common.base.BaseIdForm;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 老干部工作人员与离退休党员	 服务类
 *
 * @author pengqihai
 * @since 2024-06-25
 */
public interface VeteranCadreService extends IService<VeteranCadreEntity> {


    /**
     *老干部工作人员与离退休党员	分页列表
     *
     * @return
     */
    IPage<VeteranCadrePageVO> listPagedVeteranCadres(VeteranCadrePageQuery queryParams);


    /**
     * 获取老干部工作人员与离退休党员	表单数据
     *
     * @param id 老干部工作人员与离退休党员	ID
     * @return
     */
     VeteranCadreForm getVeteranCadreFormData(String id);


    /**
     * 新增老干部工作人员与离退休党员	
     *
     * @param formData 老干部工作人员与离退休党员	表单对象
     * @return
     */
    boolean saveVeteranCadre(VeteranCadreForm formData);

    /**
     * 修改老干部工作人员与离退休党员	
     *
     * @param id   老干部工作人员与离退休党员	ID
     * @param formData 老干部工作人员与离退休党员	表单对象
     * @return
     */
    boolean updateVeteranCadre(VeteranCadreForm formData);


    /**
     * 删除老干部工作人员与离退休党员	
     *
     * @param ids 老干部工作人员与离退休党员	ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteVeteranCadres(BaseIdForm form);

}