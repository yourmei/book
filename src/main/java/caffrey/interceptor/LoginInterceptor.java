package caffrey.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	
	
	public LoginInterceptor() {
		super();
		System.out.println("LoginInterceptor constructor");
		// TODO Auto-generated constructor stub
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

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		System.out.println("[preHandle SessionId]" + session.getId());
		System.out.println(request.getContextPath());
		System.out.println(request.getRequestURI());
		System.out.println("Interceptor for ShoppingCar :" + session.getAttribute("isLogin"));
		System.out.println(session.getAttribute("isLogin"));
		System.out.println(session.getAttribute("id"));
		System.out.println(session.getAttribute("name"));
		if((boolean)session.getAttribute("isLogin") == true)
		{
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
		else 
		{
			System.out.println("not login");
			request.getRequestDispatcher("WEB-INF/views/loginPage.jsp").forward(request,response); 
			
			return false;
		}
	}
}
