package geno.micro.zuulproxy.filters;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ProxyFilter extends ZuulFilter {

	@Override
	public Object run() {
		System.out.println("calling a filter");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		boolean isMobile   = false;
		RequestContext ctx = getCurrentContext();
		String parameter   = ctx.getRequest().getParameter("source");
		
		if(parameter != null && parameter.equals("mobile")) {
			isMobile = true;
		}
		return isMobile;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}
}
