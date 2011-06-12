package org.springframework.social.quickstart.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.quickstart.user.SecurityContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.RedirectView;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public void configureInterceptors(InterceptorConfigurer configurer) {
		configurer.addInterceptor(new SignedInHandlerInterceptor());
	}

	public void configureViewControllers(ViewControllerConfigurer configurer) {
		configurer.mapViewName("/signin", "signin");
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	private static final class SignedInHandlerInterceptor extends HandlerInterceptorAdapter {

		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			if (SecurityContext.userSignedIn() || forSignin(request)) {
				return true;
			} else {
				new RedirectView("/signin", true).render(null, request, response);
				return false;
			}
		}
		
		private boolean forSignin(HttpServletRequest request) {
			return request.getServletPath().startsWith("/signin");
		}
		
	}

}
