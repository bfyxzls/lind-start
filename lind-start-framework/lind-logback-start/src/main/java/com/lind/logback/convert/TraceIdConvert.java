package com.lind.logback.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.MDC;

import static com.lind.logback.mdc.LogInterceptor.TRACE_ID;

/**
 * @author lind
 * @date 2023/1/28 13:39
 * @since 1.0.0
 */
public class TraceIdConvert extends ClassicConverter {
	private static String EMPTY = "";

	@Override
	public String convert(ILoggingEvent event) {
		return MDC.get(TRACE_ID) == null ? EMPTY : MDC.get(TRACE_ID);
	}

}
