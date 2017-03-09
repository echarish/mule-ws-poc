package com.example.poc;

import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;

public class CustomSGAggregationStrategy implements AggregationStrategy {

	@Override
	public MuleEvent aggregate(AggregationContext context) throws MuleException {
		StringBuilder responseBuilder=new StringBuilder();
		MuleEvent result = null;
		long value = Long.MAX_VALUE;
		for (MuleEvent event : context.collectEventsWithoutExceptions()) {
			String response = (String) event.getMessage().getPayload();
			System.out.println("Agg Class " + response);
			responseBuilder.append(response);
			responseBuilder.append(" , ");
			result = DefaultMuleEvent.copy(event);
		}
		
		result.getMessage().setPayload(responseBuilder.toString());

		if (result != null) {
			return result;
		}

		throw new RuntimeException("no response obtained");
	}

}
