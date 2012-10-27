package com.jian.service.product;

import java.util.List;

import com.jian.bean.product.ProductType;
import com.jian.service.base.DAO;

public interface ProductTypeService extends DAO{

    /**
     * 得到typeid下的子类别
     * @param typeid
     * @return
     */
    List<ProductType> getChildTypes(Integer typeid);


}