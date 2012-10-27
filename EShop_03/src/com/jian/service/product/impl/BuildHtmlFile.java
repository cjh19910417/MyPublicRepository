package com.jian.service.product.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.jian.bean.product.ProductInfo;
/**
 * 在保存产品之前要用velocity技术生成productView的静态页面,用于达到优化性能的目的
 * @author JOJO
 * @date 2012-9-23
 */
public class BuildHtmlFile {
	/**
	 * 生成静态html
	 * @param product  要保存或更新的产品
	 * @param saveDir  生成静态html后要保存的路径File
	 */
	public static void createProductHtml(ProductInfo product, File saveDir){
		try {
			if(!saveDir.exists()) saveDir.mkdirs();
			VelocityContext context = new VelocityContext();
			context.put("product", product);
			Template template = Velocity.getTemplate("product/productview.html");
			FileOutputStream outStream = new FileOutputStream(new File(saveDir, product.getId()+".shtml"));
			OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter sw = new BufferedWriter(writer);
			template.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}