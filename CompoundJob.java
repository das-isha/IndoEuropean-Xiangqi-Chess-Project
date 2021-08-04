package project4;

import java.util.ArrayList;



/**
 * @author Isha Das
 *A CompoundJob class that consists of subJobs
 */
public class CompoundJob extends Job {
	private ArrayList<Job> jobList = new ArrayList<Job>();
	//constructor that updates the Compound Job with the sub job
	CompoundJob(int profit, Job... subJobs) {
		for(Job job : subJobs) {
			jobList.add(job);
		}
		this.updateCompundJob(profit);
	}
	
	/**
	 * get the sub jobs
	 * @return an array list of jobs
	 */
	public ArrayList<Job> getSubJobs() {
		return jobList;
	}
	
	/**
	 * update the compound job with earliest start, deadline, duration, and profit
	 * @param profit
	 */
	private void updateCompundJob(int profit) {
		//this sets the earliest start time 
		this.setEarliestStart(jobList.get(0).getEarliestStart());
		
		//this sets the deadlineof the subjobs
		this.setDeadline(jobList.get(jobList.size()-1).getDeadline());
		
		//adds all durations in the list and starts by setting the total duration of the job list as 0
		int totalDuartion = 0;
		for (Job job: jobList)
			totalDuartion = totalDuartion + job.getDuration();
		
		//sets the durations of the compound job
		this.setDuration(totalDuartion);
		
		//sets profit in compound job only if the earliest start time and the duration combined were less than the deadline
		if ( (this.getEarliestStart() + totalDuartion) <= this.getDeadline()) {
			this.setProfit(profit);
		} else {
			this.setProfit(0);
		}
	}


}
