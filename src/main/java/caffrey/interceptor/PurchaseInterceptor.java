package caffrey.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PurchaseInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if((session.getAttribute("isLogin") == null) || (boolean)session.getAttribute("isLogin") != true)
		{
			System.out.println("not login");
			System.out.println(request.getRequestURI());
			request.setAttribute("pageAfterLogin", request.getRequestURI());
			request.getRequestDispatcher("WEB-INF/views/loginPage.jsp").forward(request,response); 
			
			return false;
		}
		else {
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
