package cn.doug.system.controller;

import cn.doug.common.plugin.annotation.WebLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.doug.common.base.BaseIdForm;
import cn.doug.system.model.form.VeteranCadreForm;
import cn.doug.system.model.query.VeteranCadrePageQuery;
import cn.doug.system.model.vo.VeteranCadrePageVO;
import cn.doug.system.service.VeteranCadreService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.doug.common.result.PageResult;
import cn.doug.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 老干部工作人员与离退休党员	 前端控制器
 *
 * @author pengqihai
 * @since 2024-06-25
 */
@Tag(name = "老干部工作人员与离退休党员	接口")
@RestController
@RequestMapping("/api/veteranCadreEntitys")
@RequiredArgsConstructor
public class VeteranCadreController {

    private final VeteranCadreService veteranCadreEntityService;

    /**
     *老干部工作人员与离退休党员	分页列表
     *
     * @return
     */
    @WebLog(value = "老干部工作人员与离退休党员	分页列表")
    @Operation(summary = "老干部工作人员与离退休党员	分页列表")
    @GetMapping("/page")
    public PageResult<VeteranCadrePageVO> listPagedVeteranCadres(VeteranCadrePageQuery queryParams) {
        IPage<VeteranCadrePageVO> result = veteranCadreEntityService.listPagedVeteranCadres(queryParams);
        return PageResult.success(result);
    }

    /**
     * 获取老干部工作人员与离退休党员	表单数据
     *
     * @param form 老干部工作人员与离退休党员	ID
     * @return
     */
    @WebLog(value = "老干部工作人员与离退休党员	表单数据")
    @Operation(summary = "老干部工作人员与离退休党员	表单数据")
    @GetMapping("getInfo")
    public Result<VeteranCadreForm> getInfo(@RequestBody @Valid BaseIdForm form) {
        VeteranCadreForm formData = veteranCadreEntityService.getVeteranCadreFormData(form.getId());
        return Result.success(formData);
    }

    /**
     * 新增老干部工作人员与离退休党员	
     *
     * @param formData 老干部工作人员与离退休党员	表单对象
     * @return
     */
    @WebLog(value = "新增老干部工作人员与离退休党员	")
    @Operation(summary = "新增老干部工作人员与离退休党员	")
    @PostMapping("/add")
    public Result saveVeteranCadre(@RequestBody @Valid VeteranCadreForm formData ) {
        boolean result = veteranCadreEntityService.saveVeteranCadre(formData);
        return Result.judge(result);
    }

    /**
     * 修改老干部工作人员与离退休党员	
     *
     * @param formData 老干部工作人员与离退休党员	表单对象
     * @return
     */
    @WebLog(value = "修改老干部工作人员与离退休党员	")
    @Operation(summary = "修改老干部工作人员与离退休党员	")
    @PutMapping(value = "/edit")
    public Result updateVeteranCadre(@RequestBody @Validated VeteranCadreForm formData) {
        boolean result = veteranCadreEntityService.updateVeteranCadre(formData);
        return Result.judge(result);
    }

    /**
     * 删除老干部工作人员与离退休党员	
     *
     * @param form 老干部工作人员与离退休党员	ID，多个以英文逗号(,)分割
     * @return
     */
    @WebLog(value = "删除老干部工作人员与离退休党员	")
    @Operation(summary = "删除老干部工作人员与离退休党员	")
    @DeleteMapping("/del")
    public Result deleteVeteranCadres(@RequestBody @Valid BaseIdForm form) {
        boolean result = veteranCadreEntityService.deleteVeteranCadres(form);
        return Result.judge(result);
    }
}
