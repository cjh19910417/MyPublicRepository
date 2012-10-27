package com.jian.web.action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.struts.DelegatingRequestProcessor;

import com.jian.bean.privilege.Employee;
import com.jian.bean.privilege.Permission;
import com.jian.bean.privilege.SystemPrivilege;
import com.jian.bean.privilege.SystemPrivilegeGroup;
import com.jian.bean.privilege.SystemPrivilegePK;
import com.jian.web.util.UrlTools;
import com.jian.web.util.WebUtil;
/**
 * 对  /control/* 开头的请求action进行权限验证
 * 
 *      ******************    为什么不使用spring提供的AOP技术           ********************
 *          因为spring不能对用反射技术调用的方法进行通知(advice),这个bug是由spring依赖的动态
 *          代理技术cglib,jdkproxy有关,正因为这个原因,我们继承DispatchAction的action里
 *          面的方法就不能被拦截到(dispatchaction里面的方法由反射技术调用),spring也有提供解决
 *          方案,可是由于侵入性太强,要修改的代码过多,我们这里放弃使用spring提供的AOP技术,通过分析
 *          struts的源代码,我们可以通过重写RequestProcessor里的processActionPerform()
 *          方法,来实现权限拦截!
 *      **********************************************************************
 * @author JOJO
 * @date 2012-9-20
 */
public class PrivilegeProcessor extends DelegatingRequestProcessor
{

    @Override
    protected ActionForward processActionPerform(HttpServletRequest request,
            HttpServletResponse response, Action action, ActionForm form,
            ActionMapping mapping) throws IOException, ServletException
    {
        if(WebUtil.getRequestURI(request).startsWith("/control/"))//只对  /control/* 开头的请求进行权限验证
        {
            if(!validate(request,action,mapping))
            {
                request.setAttribute("message", "你没有执行该项操作的权限,请和管理员联系!");
                request.setAttribute(UrlTools.JUMP, UrlTools.getUrlSite("back"));
                return mapping.findForward("message");
            }
        }
        
        return super.processActionPerform(request, response, action, form, mapping);
    }
    /**
     * 验证用户是否有执行该action方法
     * @param request
     * @param action
     * @param mapping
     * @return
     */
    private boolean validate(HttpServletRequest request, Action action,
            ActionMapping mapping)
    {
        Method method = getRequestMethod(request,action,mapping);
        if(method != null && method.isAnnotationPresent(Permission.class))//该方法上标注有permission注解
        {
            //根据@Permission注解的数据,构建出privilege
            SystemPrivilege privilege = new SystemPrivilege(new SystemPrivilegePK(method.getAnnotation(Permission.class).module(), method.getAnnotation(Permission.class).privilege()));
            //得到员工
            Employee employee = WebUtil.getEmployeeInSession(request);
            for(SystemPrivilegeGroup group : employee.getGroups())
            {
                if(group.getPrivileges().contains(privilege))
                {
                    return true;//员工权限组里面包含所需要的权限,验证通过
                }
            }
            return false;
        }
        return true;
    }
    /**
     * 得到用户要请求的方法
     * @param request
     * @param action
     * @param mapping
     * @return
     */
    private Method getRequestMethod(HttpServletRequest request, Action action,
            ActionMapping mapping)
    {
        String methodName = "execute";
        
        if(DispatchAction.class.isAssignableFrom(action.getClass()))//判断DispatchAction是否为action的父类
        {
            methodName = request.getParameter(mapping.getParameter());//得到要请求DispatchAction里具体方法的名称,就是本项目url里面的method参数的值
        }
        try
        {
            return action.getClass().getMethod(methodName, ActionMapping.class, ActionForm.class,
                HttpServletRequest.class, HttpServletResponse.class);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        return null;
    }

   
    
}
