package com.jack.aiweb.intercpter;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author wangwj
 * @data 2018/12/18
 * 用户session拦截
 */
public class SessionIntercpter implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if ("/aie/service".equals(request.getRequestURI())) {
//            return true;
//        }
//        Object obj = request.getSession().getAttribute("token");
//        if(obj==null){
//            response.getWriter().write(JSONObject.toJSONString(ApiResponseBuilder.failed("请先登录")));
//            return false;
//        }
//        return true;
//    }
}
