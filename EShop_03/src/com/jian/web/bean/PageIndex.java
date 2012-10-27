package com.jian.web.bean;

public class PageIndex {
    private long startindex = 1;
    private long endindex;
    
    public PageIndex(long startindex, long endindex) {
        this.startindex = startindex;
        this.endindex = endindex;
    }
    public long getStartindex() {
        return startindex;
    }
    public void setStartindex(long startindex) {
        this.startindex = startindex;
    }
    public long getEndindex() {
        return endindex;
    }
    public void setEndindex(long endindex) {
        this.endindex = endindex;
    }
     /**
      * 
      * @param maxpagecount 页面中最多显示多少个页面索引
      * @param currentPage 当前页
      * @param totalpage 页面总数
      * @return
      */
    public static PageIndex getPageIndex(long maxpagecount, int currentPage, long totalpage){
            long startpage = currentPage-(maxpagecount%2==0? maxpagecount/2-1 : maxpagecount/2);
            long endpage = currentPage+maxpagecount/2;
            if(startpage<1){
                startpage = 1;
                if(totalpage>=maxpagecount) endpage = maxpagecount;
                else endpage = totalpage;
            }
            if(endpage>totalpage){
                endpage = totalpage;
                if((endpage-maxpagecount)>0) startpage = endpage-maxpagecount+1;
                else startpage = 1;
            }
            if(endpage == 0){
                endpage =1;
            }
            return new PageIndex(startpage, endpage);       
    }
}
