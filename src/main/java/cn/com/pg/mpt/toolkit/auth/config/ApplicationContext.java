package cn.com.pg.mpt.toolkit.auth.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 将contextPath写入application中，给css,js引用时用
 */
@Component
public class ApplicationContext implements ServletContextAware {

    @Override
    public void setServletContext(ServletContext context) {
        String contextPath = context.getContextPath();
        context.setAttribute("ctx", contextPath);
    }

}