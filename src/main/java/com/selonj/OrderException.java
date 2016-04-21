package com.selonj;

import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderException extends RuntimeException {
  private List<? super ItemViolation> violations;

  public OrderException(List<? super ItemViolation> violations) {
    this.violations = violations;
  }

  public List<? super ItemViolation> getViolations() {
    return violations;
  }
}
