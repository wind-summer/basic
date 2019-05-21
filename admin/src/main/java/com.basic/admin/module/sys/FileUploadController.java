package com.basic.admin.module.sys;

import com.basic.core.config.ApplicationProperties;
import com.basic.core.module.sys.entity.SysFile;
import com.basic.core.module.sys.service.SysFileService;
import com.basic.core.utils.ApiResult;
import com.basic.core.utils.Base64Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 *  文件上传控制器
 * </p>
 *
 * @author wenlongfei
 * @since 2019/5/15
 */
@RestController
@RequestMapping("/sys/file")
@AllArgsConstructor
@Api(description = "文件上传")
public class FileUploadController {

    private ApplicationProperties applicationProperties;
    private SysFileService sysFileService;
    /**
     * 上传fsn和GZH文件
     */
    @PostMapping(value = "/upload/userLogo")
    @ApiOperation("上传文件")
    public ApiResult uploadFileByBase64ForUserLogo(@RequestBody MultipartFile file)  throws IOException {
        //文件类型
        String contentType = file.getContentType();
        //名字
        String fileName = file.getOriginalFilename();

        String base64File = Base64Util.fileToBase64(file);
        SysFile sysFile = new SysFile();
        sysFile.setBase64File(base64File);
        sysFile.setType("userLogo");
        sysFile.setFileName(fileName);
        sysFileService.insert(sysFile);
        return ApiResult.ok((Object)("/sys/file/info/"+sysFile.getId()));
    }

    @GetMapping(value = "/info/{id}")
    @ApiOperation("获取文件流")
    public void getFileInputStream(@PathVariable Long id, HttpServletResponse response)  throws IOException {
        SysFile sysFile = sysFileService.selectById(id);
        if(sysFile != null ){
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + sysFile.getFileName());
            OutputStream os = response.getOutputStream();
            os.write(Base64Util.base64ToBytes(sysFile.getBase64File()));
        }
    }
    @GetMapping(value = "/base64/info/{id}")
    @ApiOperation("获取文件base64字符串")
    public String getFileInputStream(@PathVariable Long id)  throws IOException {
        SysFile sysFile = sysFileService.selectById(id);
        String base64File = "";
        if(sysFile != null ){
            base64File = sysFile.getBase64File();
        }
        return base64File;
    }
}
