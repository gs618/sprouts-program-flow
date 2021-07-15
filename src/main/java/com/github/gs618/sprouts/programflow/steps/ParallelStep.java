package com.github.gs618.sprouts.programflow.steps;

import com.github.gs618.sprouts.programflow.Step;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import com.github.gs618.sprouts.programflow.exception.StepRuntimeException;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
public class ParallelStep extends Step {

	private static final int DEFAULT_CONCURRENCY = 10;
	private static final int DEFAULT_PARALLEL_BRANCH_SIZE = 10;

	private static final ExecutorService localExecutorService = new ThreadPoolExecutor(DEFAULT_CONCURRENCY, DEFAULT_CONCURRENCY,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(), new ParallelThreadFactory());

	@Getter
	final List<Step> branches;

	public static ParallelStep newInstance() {
		return new ParallelStep();
	}

	public ParallelStep() {
		this(DEFAULT_PARALLEL_BRANCH_SIZE);
	}

	public ParallelStep(int parallelBranchSize) {
		branches = new ArrayList<>(parallelBranchSize);
	}

	@Override
	protected void handle(Input input, Output output) {
		try {
			CompletableFuture.allOf(branches.stream().map(step ->
					CompletableFuture.runAsync(() -> step.start(input, output), localExecutorService)).toArray(CompletableFuture[]::new))
					.join();
		} catch (Exception e) {
			throw new StepRuntimeException(e);
		}
	}

	/**
	 * add a branch for parallel running
	 *
	 * @param parallelStep a parallel branch
	 * @return current parallel step
	 */
	public ParallelStep addParallelBranch(Step parallelStep) {
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
			namePrefix = "Parallel-step-" +
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
