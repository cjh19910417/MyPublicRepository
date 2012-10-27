package com.jian.service.product.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jian.service.base.DAOSupport;
import com.jian.service.product.UploadFileService;
/**
 * 文件上传的服务类
 * @author JOJO
 * @date 2012-8-7
 */
@Service
@Transactional
public class UploadFileServiceImpl extends DAOSupport implements
        UploadFileService
{

}
