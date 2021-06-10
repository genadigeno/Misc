package geno.micro.zuulproxy.filters;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class StopPostFilter extends ZuulFilter {

	@Override
	public Object run() {
		
		Instant stop = Instant.now();

		//get start value
		RequestContext ctx = getCurrentContext();
		Instant start = (Instant)ctx.get("starttime");
		
		long secondsDifference = ChronoUnit.MILLIS.between(start, stop);
		System.out.println("Call took " + secondsDifference + " milliseconds.");

		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "post";
	}

}
