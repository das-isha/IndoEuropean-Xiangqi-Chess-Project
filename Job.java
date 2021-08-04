package project4;
import java.util.Comparator; 


public class Job  implements Comparable<Job> { 
	// Each job has a unique-id, 
    // profit and deadline 
    private int id; 
    private int earliestStart;
    private int deadline;
    private int duration;
    private int profit; 
    
  
    public Job() {} 
    /* A constructor to make a Job with the given id, startTime, deadline, duration, and profit */
    public Job(int id, int earliestStart, int deadline, int duration, int profit) { 
        this.id = id; 
        this.earliestStart = earliestStart;
        this.deadline = deadline; 
        this.duration = duration;
        this.profit = profit; 
    } 
    /*get the ID of the Job */
    public int getId() {
		return id;
	}
    /*set the ID of the Job */
	public void setId(int id) {
		this.id = id;
	}

    /*get the earliest start time of the Job */
	public int getEarliestStart() {
		return earliestStart;
	}
	
    /*set the earliest start time of the Job */
	public void setEarliestStart(int earliestStart) {
		this.earliestStart = earliestStart;
	}
	
	/*get the deadline of the Job */
	public int getDeadline() {
		return deadline;
	}

	/*set the deadline of the Job */
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	
	/*get the duration of the Job */
	public int getDuration() {
		return duration;
	}

	/*set the duration of the Job */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/*get the profit of the Job */
	public int getProfit() {
		return profit;
	}

	/*set the profit of the Job */
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	public static Comparator<Job> getStartComparator() {
		// TODO Auto-generated method stub
		return new CompareByStart();
	}
	
	public static Comparator<Job> getProfitComparator() {
		// TODO Auto-generated method stub
		return new CompareByProfit();
	}

	/**
	   * Compare this job with the input job, and order them by the job ID
	   * @param job the job to compare this jon to
	   * @return < 0,0, > 0, if this job is ordered before, the same as, or after the input
	   */
	@Override
	public int compareTo(Job job) {	
		return (int)(this.getId() - job.getId()); 
	}
	
	/** A class that implements Comparator, sort jobs by earliestStart, from smallest to largest */
	private static class CompareByStart implements Comparator<Job> {

		/**
		 * Compares Jobs by salary
		 * @param job1 the first Job to compare
		 * @param job22 the second job to compare
		 * @return < 0, = 0, or > 0 if job1 has a smaller, same as, or larger start time than job2
		 */
		public int compare(Job job1, Job job2) {
			return (int)(job1.getEarliestStart() - job2.getEarliestStart());
		}

	}

	/** A class that implements Comparator, sort jobs by earliestStart, from smallest to largest */
	private static class CompareByProfit implements Comparator<Job> {

		/**
		 * Compares Jobs by salary
		 * @param job1 the first Job to compare
		 * @param job22 the second job to compare
		 * @return < 0, = 0, or > 0 if job1 has a smaller, same as, or larger start time than job2
		 */
		public int compare(Job job1, Job job2) {
			return (int)(job2.getProfit() - job1.getProfit());
		}

	}

}