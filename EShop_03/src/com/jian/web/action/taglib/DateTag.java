package com.jian.web.action.taglib;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 得到当前日期
 * @author JOJO
 * @date 2012-9-22
 */
public class DateTag extends TagSupport
{
    private String pattern;
    
    @Override
    public int doStartTag() throws JspException
    {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String timeStr = format.format(new Date());
        pageContext.getRequest().setAttribute("now", timeStr);
        return EVAL_BODY_INCLUDE;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

}
