package com.example.jsf.phonebook.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.jsf.phonebook.model.User;

/**
 * Servlet Filter implementation class Auth
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { })
public class Auth implements Filter {

    /**
     * Default constructor. 
     */
    public Auth() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
          HttpServletRequest httpReq=(HttpServletRequest) request;
      User u= (User) httpReq.getSession().getAttribute("user");
     
          if(u!=null && u.isLoggedIn()== true) {
        	  chain.doFilter(request, response);
          }else {
        	  HttpServletResponse res= (HttpServletResponse)response;
        	  res.sendRedirect(httpReq.getContextPath()+"/login.jsp");
          }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
