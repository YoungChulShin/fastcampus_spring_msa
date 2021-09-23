package dev.practice.gift.common.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public class CommonHttpRequestInterceptor implements HandlerInterceptor {

  public static final String HEADER_REQUEST_UUID_KEY = "x-request-id";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String requestEventId = request.getHeader(HEADER_REQUEST_UUID_KEY);
    if (StringUtils.isEmpty(requestEventId)) {
      requestEventId = UUID.randomUUID().toString();
    }
    MDC.put(HEADER_REQUEST_UUID_KEY, requestEventId);

    return true;
  }
}
