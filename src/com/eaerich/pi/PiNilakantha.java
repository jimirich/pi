/* PiNilakantha.java -- Class to compute the number pi using the Nilakantha
 * series.
 *
 * The Nilakantha series is an infinite series with terms:
 *
 *   pi = 3 + 4/(2*3*4) - 4/(4*5*6) + 4/(6*7*8) - 4/(8*9*10) + ...
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

public class PiNilakantha {

	public static void main(String[] args) {
		BigDecimal sum = new BigDecimal(3).setScale(0);
		int s = 2;
		final BigDecimal n = new BigDecimal(4).setScale(0);

		for (long i = 0L; i < 1000000000L; i++) {
			BigDecimal d1 = new BigDecimal(s).setScale(0);
			BigDecimal d2 = new BigDecimal(++s).setScale(0);
			BigDecimal d3 = new BigDecimal(++s).setScale(0);
			BigDecimal d = d1.multiply(d2).multiply(d3);

			if (i % 2 == 0) {
				sum = sum.add(n.divide(d, 128, RoundingMode.HALF_UP));
			} else {
				sum = sum.subtract(n.divide(d, 128, RoundingMode.HALF_UP));
			}
		}

		System.out.println("Pi is: " + sum.toPlainString());
	}
}
