package cn.doug.system.controller.system;

import cn.doug.common.plugin.annotation.WebLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.doug.common.base.BaseIdForm;
import cn.doug.system.model.form.SysNoticeForm;
import cn.doug.system.model.query.SysNoticePageQuery;
import cn.doug.system.model.vo.SysNoticePageVO;
import cn.doug.system.service.SysNoticeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.doug.common.result.PageResult;
import cn.doug.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 通知公告表 前端控制器
 *
 * @author pengqihai
 * @since 2024-08-14
 */
@Tag(name = "通知公告接口")
@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class SysNoticeController {

    private final SysNoticeService noticeEntityService;

    /**
     * 查询公告列表
     *
     * @return SysNoticePageVO
     */
    @WebLog(value = "查询公告列表")
    @Operation(summary = "查询公告列表")
    @GetMapping("/page")
    public PageResult<SysNoticePageVO> listPagedNotices(SysNoticePageQuery queryParams) {
        IPage<SysNoticePageVO> result = noticeEntityService.listPagedNotices(queryParams);
        return PageResult.success(result);
    }

    /**
     * 获取通知公告表单数据
     *
     * @param form 通知公告ID
     * @return SysNoticeForm
     */
    @WebLog(value = "通知公告表单数据")
    @Operation(summary = "通知公告表单数据")
    @PostMapping("getInfo")
    public Result<SysNoticeForm> getInfo(@RequestBody @Validated BaseIdForm form) {
        SysNoticeForm formData = noticeEntityService.getNoticeFormData(form.getId());
        return Result.success(formData);
    }

    /**
     * 新增通知公告表
     *
     * @param formData 通知公告表表单对象
     * @return
     */
    @WebLog(value = "新增通知公告表")
    @Operation(summary = "新增通知公告表")
    @PostMapping("/add")
    public Result saveNotice(@RequestBody @Validated SysNoticeForm formData) {
        boolean result = noticeEntityService.saveNotice(formData);
        return Result.judge(result);
    }

    /**
     * 修改通知公告表
     *
     * @param formData 通知公告表表单对象
     * @return
     */
    @WebLog(value = "修改通知公告")
    @Operation(summary = "修改通知公告")
    @PutMapping(value = "/edit")
    public Result updateNotice(@RequestBody @Validated SysNoticeForm formData) {
        boolean result = noticeEntityService.updateNotice(formData);
        return Result.judge(result);
    }

    /**
     * 删除通知公告表
     *
     * @param form 通知公告表ID，多个以英文逗号(,)分割
     * @return
     */
    @WebLog(value = "删除通知公告")
    @Operation(summary = "删除通知公告")
    @DeleteMapping("/del")
    public Result deleteNotices(@RequestBody @Valid BaseIdForm form) {
        boolean result = noticeEntityService.deleteNotices(form);
        return Result.judge(result);
    }
}
