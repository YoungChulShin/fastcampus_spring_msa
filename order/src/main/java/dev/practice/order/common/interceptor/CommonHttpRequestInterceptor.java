package dev.practice.order.common.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
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
