/**
 * 
 */
package com.barun.example.job;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author jokersingh
 *
 */
public class JobExecutor {
	private ExecutorService executorService;

    /**
     * Create a job executor. The number of workers will be set to the number of available processors.
     */
    public JobExecutor() {
        executorService = newFixedThreadPool(getRuntime().availableProcessors());
    }

    /**
     * Create a job executor.
     *
     * @param nbWorkers number of worker threads
     */
    public JobExecutor(int nbWorkers) {
        executorService = newFixedThreadPool(nbWorkers);
    }

    /**
     * Create a job executor.
     *
     * @param executorService to use to execute jobs
     */
    public JobExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * Execute a job synchronously.
     *
     * @param job to execute
     * @return the job report
     */
    public JobReport execute(Job job) {
        try {
            return executorService.submit(job).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Unable to execute job " + job.getName(), e);
        }
    }

    /**
     * Submit a job for asynchronous execution.
     *
     * @param job to execute
     * @return a future of the job report
     */
    public Future<JobReport> submit(Job job) {
        return executorService.submit(job);
    }

    /**
     * Submit jobs for execution.
     *
     * @param jobs to execute
     * @return the list of job reports in the same order of submission
     */
    public List<Future<JobReport>> submitAll(Job... jobs) {
        return submitAll(Arrays.asList(jobs));
    }

    /**
     * Submit jobs for execution.
     *
     * @param jobs to execute
     * @return the list of job reports in the same order of submission
     */
    public List<Future<JobReport>> submitAll(List<Job> jobs) {
        try {
            return executorService.invokeAll(jobs);
        } catch (InterruptedException e) {
            throw new RuntimeException("Unable to execute jobs", e);
        }
    }

    /**
     * Shutdown the job executor.
     */
    public void shutdown() {
        executorService.shutdown();
    }

    /**
     * Wait for jobs to terminate.
     */
    public void awaitTermination(long timeout, TimeUnit unit) {
        try {
            executorService.awaitTermination(timeout, unit);
        } catch (InterruptedException e) {
            throw new RuntimeException("Job executor was interrupted while waiting");
        }
    }

}
