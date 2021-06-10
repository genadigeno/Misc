package geno.micro.zuulproxy.filters;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import java.time.Instant;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class StartPreFilter extends ZuulFilter {

	@Override
	public Object run() {
		RequestContext ctx = getCurrentContext();
		System.out.println(Instant.now());
		ctx.set("starttime", Instant.now());
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 2;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
