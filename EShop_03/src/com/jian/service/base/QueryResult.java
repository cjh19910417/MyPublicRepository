package com.jian.service.base;

import java.util.List;
/**
 * 查询结果封装类,有结果集resultlist,和条目总数totalrecord
 * @author JOJO
 * @date 2012-8-7
 * @param <T>
 */
public class QueryResult<T> {
	private List<T> resultlist;
	private long totalrecord;
	public List<T> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
	
}
