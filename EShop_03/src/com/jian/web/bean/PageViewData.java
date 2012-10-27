package com.jian.web.bean;

import java.io.Serializable;
import java.util.List;

import com.jian.service.base.QueryResult;
/**
 * 前台 页面 分页 显示数据
 * @author JOJO
 * @date 2012-8-3
 * @param <T>
 */
public class PageViewData<T> implements Serializable
{   
    private static final long serialVersionUID = 7949536900838238201L;

    /**
     * 总记录
     */
    private List<T> records;
    
    /**
     * 当前页
     */
    private int currentPage = 1;
    
    /**
     * 页码开始索引和结束索引
     */
    private PageIndex pageIndex;
    
    /**
     * 最大显示页面数
     */
    private int maxPageCount = 10;
    
    /**
     * 总记录数
     */
    private long totalRecord;
    
    /**
     * 每页最多显示几条记录
     */
    private int maxResult = 12;
    
    /**
     * 总页数
     */
    private long totalPage = 1;
    
    
    
    public PageViewData()
    {
        super();
        
    }
    public PageViewData(QueryResult<T> qr, int currentPage)
    {
        super();
        this.records = qr.getResultlist();
        this.currentPage = currentPage;
        this.totalRecord = qr.getTotalrecord();
        this.totalPage = totalRecord%maxResult == 0? totalRecord/maxResult : totalRecord/maxResult+1;
        this.pageIndex = PageIndex.getPageIndex(maxPageCount, currentPage, totalPage);
    }
    public PageViewData(QueryResult<T> qr, int currentPage,int maxResult)
    {
        super();
        this.records = qr.getResultlist();
        this.currentPage = currentPage;
        this.totalRecord = qr.getTotalrecord();
        this.maxResult = maxResult;
        this.totalPage = totalRecord%maxResult == 0? totalRecord/maxResult : totalRecord/maxResult+1;
        this.pageIndex = PageIndex.getPageIndex(maxPageCount, currentPage, totalPage);
    }

    public List<T> getRecords()
    {
        return records;
    }

    public void setRecords(List<T> records)
    {
        this.records = records;
    }

    public int getCurrentPage()
    {
        return currentPage==0?1:currentPage;
    }

    public void setCurrentPage(int conrrentPage)
    {
        this.currentPage = conrrentPage;
    }

    public PageIndex getPageIndex()
    {
        return pageIndex;
    }

    public void setPageIndex(PageIndex pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    public int getMaxPageCount()
    {
        return maxPageCount;
    }

    public void setMaxPageCount(int maxPageCount)
    {
        this.maxPageCount = maxPageCount;
    }

    public long getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord)
    {
        this.totalRecord = totalRecord;
    }

    public int getMaxResult()
    {
        return maxResult;
    }

    public void setMaxResult(int maxResult)
    {
        this.maxResult = maxResult;
    }

    public long getTotalPage()
    {
        if(this.totalPage == 0)
        {
            return 1;
        }else{
            return totalPage;
        }
        
    }

    public void setTotalPage(long totalPage)
    {
        this.totalPage = totalPage;
    }
  
    
}
