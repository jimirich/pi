/* PiMultithread.java -- Class to compute the number pi using the Nilakantha
 * series in a multi-threaded way.
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PiMultithread {

	public static final long blocklength = 1000000000L;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Chunk chunk1 = new Chunk(0L);
		Chunk chunk2 = new Chunk(blocklength);
		Chunk chunk3 = new Chunk(blocklength * 2);
		Chunk chunk4 = new Chunk(blocklength * 3);
		Chunk chunk5 = new Chunk(blocklength * 4);
		Chunk chunk6 = new Chunk(blocklength * 5);
		Chunk chunk7 = new Chunk(blocklength * 6);
		Chunk chunk8 = new Chunk(blocklength * 7);
		Chunk chunk9 = new Chunk(blocklength * 8);
		Chunk chunk10 = new Chunk(blocklength * 9);
		Future<BigDecimal> result1 = executor.submit(chunk1);
		Future<BigDecimal> result2 = executor.submit(chunk2);
		Future<BigDecimal> result3 = executor.submit(chunk3);
		Future<BigDecimal> result4 = executor.submit(chunk4);
		Future<BigDecimal> result5 = executor.submit(chunk5);
		Future<BigDecimal> result6 = executor.submit(chunk6);
		Future<BigDecimal> result7 = executor.submit(chunk7);
		Future<BigDecimal> result8 = executor.submit(chunk8);
		Future<BigDecimal> result9 = executor.submit(chunk9);
		Future<BigDecimal> result10 = executor.submit(chunk10);

		try {
			BigDecimal sum = result1.get().add(result2.get()).add(result3.get()).
					add(result4.get()).add(result5.get()).add(result6.get()).
					add(result7.get()).add(result8.get()).add(result9.get()).
					add(result10.get()).add(BigDecimal.ONE).add(BigDecimal.ONE).
					add(BigDecimal.ONE);
			System.out.println("Pi is: " + sum.toPlainString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		executor.shutdown();

		try {
			if (!executor.awaitTermination(800L,
					TimeUnit.MILLISECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
	}
}
