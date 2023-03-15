/* PiLeibniz.java -- Class to compute the number pi using the Leibniz
 * series.
 *
 * The Leibniz series is an infinite series with terms:
 *
 *   pi = 4/1 - 4/3 + 4/5 - 4/7 + 4/9 - 4/11 +...
 *
 * Copyright 2023 Edward A.E. Rich & Associates
 *
 * This software is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.eaerich.pi;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PiLeibniz {
  public static void main(String[] args) {
    BigDecimal pi = BigDecimal.ZERO;
    BigDecimal d = BigDecimal.ONE;
    final BigDecimal n = new BigDecimal(4).setScale(0);

    for (long i = 0L; i < 1000000000L; i++) {
      if (i % 2 == 0) {
        pi = pi.add(n.divide(d, 64, RoundingMode.HALF_UP));
      } else {
        pi = pi.subtract(n.divide(d, 64, RoundingMode.HALF_UP));
      }

      d = d.add(BigDecimal.ONE).add(BigDecimal.ONE);
    }

    System.out.println("Pi is: " + pi.toPlainString());
  }
}
