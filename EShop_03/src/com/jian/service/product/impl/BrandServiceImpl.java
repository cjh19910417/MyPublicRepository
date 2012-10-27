package com.jian.service.product.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jian.bean.product.Brand;
import com.jian.service.base.DAOSupport;
import com.jian.service.product.BrandService;
/**
 * 品牌服务类,重写了save方法
 * @author JOJO
 * @date 2012-8-7
 */
@Service
@Transactional
public class BrandServiceImpl extends DAOSupport implements BrandService
{
    @Override
    public void save(Object entity)
    {
        ((Brand)entity).setCode(UUID.randomUUID().toString());
        super.save(entity);
    }
}
