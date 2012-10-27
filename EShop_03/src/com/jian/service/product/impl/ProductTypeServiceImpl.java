package com.jian.service.product.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jian.bean.product.ProductType;
import com.jian.service.base.DAOSupport;
import com.jian.service.product.ProductTypeService;
/**
 * ProductTypeService的实现类,重写了delete方法
 * @author JOJO
 * @date 2012-8-7
 */
@Service
@Transactional
public class ProductTypeServiceImpl extends DAOSupport implements ProductTypeService {

    
	/**
	 * ☆  重要  ☆
	 * 因为产品类别里面的删除并不是真正的物理删除,而是隐藏不显示而已(visible=false)
	 */
	@Override
	public <T> void delete(Class<T> entityClass, Object[] ids) {
		StringBuffer args = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			args.append("?").append(i+2).append(",");
		}
		args.deleteCharAt(args.length()-1);
		/*ProductType是实体名称,严格来说并不是类名,因为这里没有设定实体名称,所以为默认的*/
		Query query = this.em.createQuery("update ProductType p set p.visible = ?1 where p.typeId in ("+ args.toString() +")")
		.setParameter(1, false);
		
		for (int i = 0; i < ids.length; i++) {
			query.setParameter(i+2, ids[i]);
		}
		
		query.executeUpdate();
	}

    @Override
    @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
    public List<ProductType> getChildTypes(Integer typeid)
    {
        Query query = em.createQuery("select o from ProductType o where o.parent.typeId = ?1");
        query.setParameter(1, typeid);
        return query.getResultList();
    }


	
	
}
