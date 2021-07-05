package com.github.gs618.sprouts.programflow.steps;

import com.github.gs618.sprouts.programflow.BaseStep;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import com.github.gs618.sprouts.programflow.exception.StepRuntimeException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sgao
 */
public class StepParallel extends BaseStep {

	private static final int DEFAULT_CONCURRENCY = 5;
	private static final int DEFAULT_PARALLEL_BRANCH_SIZE = 10;
	@Getter
	private final int concurrency;

	private final ExecutorService executorService;

	@Getter
	private final List<BaseStep> branches;

	public static StepParallel newInstance() {
		return new StepParallel();
	}

	public StepParallel() {
		this(DEFAULT_CONCURRENCY, DEFAULT_PARALLEL_BRANCH_SIZE);
	}

	public StepParallel(int concurrency, int parallelBranchSize) {
		this.concurrency = concurrency;
		branches = new ArrayList<>(parallelBranchSize);
		executorService = new ThreadPoolExecutor(concurrency, concurrency,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(), new ParallelThreadFactory());
	}

	@Override
	public void handle(Input input, Output output) {
		try {
			CompletableFuture.allOf(branches.stream().map(step ->
					CompletableFuture.runAsync(() -> step.start(input, output), executorService)).toArray(CompletableFuture[]::new))
					.join();
		} catch (Exception e) {
			throw new StepRuntimeException(e);
		}
	}

	public StepParallel addParallelBranch(BaseStep parallelStep) {
		branches.add(parallelStep);
		return this;
	}

	/**
	 * The thread factory
	 */
	static class ParallelThreadFactory implements ThreadFactory {
		private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		ParallelThreadFactory() {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() :
					Thread.currentThread().getThreadGroup();
			namePrefix = "step-parallel-" +
					POOL_NUMBER.getAndIncrement() +
					"-thread-";
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r,
					namePrefix + threadNumber.getAndIncrement(),
					0);
			if (t.isDaemon()) {
				t.setDaemon(false);
			}
			if (t.getPriority() != Thread.NORM_PRIORITY) {
				t.setPriority(Thread.NORM_PRIORITY);
			}
			return t;
		}
	}
}
