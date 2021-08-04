package project4;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


/**
 * @author Isha Das
 * Tester Class for the testing Schedule Jobs
 */
class AllJobTester {

	/**
	 * Tests the default sorting by Id for the Job class
	 */
	@Test
	public void testSortJobById() {

		ArrayList<Job> jobList = new ArrayList<Job>(); 
		jobList.add(new Job(0, 1, 48,9, 11)); 
		jobList.add(new Job(3, 3, 54, 6, 8)); 
		jobList.add(new Job(2, 4, 14,4, 15));
		jobList.add(new Job(1, 2, 27, 4, 7));


		Collections.sort(jobList);
		assertEquals("Testing the first Id of the sorted  list", 0, jobList.get(0).getId()); 
		assertEquals("Testing the second Id of the sorted  list", 1, jobList.get(1).getId()); 
		assertEquals("Testing the third Id of the sorted  list", 2, jobList.get(2).getId()); 
		assertEquals("Testing the last Id of the sorted  list", 3, jobList.get(3).getId()); 

	}
	
	/**
	 * Tests the sorting by Earliest Start for the Job class
	 */
	@Test
	public void testCompareJobByEarliestStart() {

		ArrayList<Job> jobList = new ArrayList<Job>(); 
		jobList.add(new Job(0, 1, 48,9, 11)); 
		jobList.add(new Job(3, 3, 54, 6, 8)); 
		jobList.add(new Job(2, 4, 14,4, 15));
		jobList.add(new Job(1, 2, 27, 4, 7));

		Collections.sort(jobList, Job.getStartComparator());
		assertEquals("Testing the first Earliest Start of the sorted  list", 1, jobList.get(0).getEarliestStart()); 
	    assertEquals("Testing the second Earliest Start of the sorted  list", 2, jobList.get(1).getEarliestStart()); 
		assertEquals("Testing the third Earliest Start of the sorted  list", 3, jobList.get(2).getEarliestStart()); 
		assertEquals("Testing the last Earliest Start of the sorted  list", 4, jobList.get(3).getEarliestStart()); 
	}
	
	/**
	 * Tests the  sorting by max profit for the Job class
	 */
	@Test
	public void testCompareJobByMaxProfit() {

		ArrayList<Job> jobList = new ArrayList<Job>(); 
		jobList.add(new Job(0, 1, 48,9, 11)); 
		jobList.add(new Job(3, 3, 54, 6, 8)); 
		jobList.add(new Job(2, 4, 14,4, 15));
		jobList.add(new Job(1, 2, 27, 4, 7));

		Collections.sort(jobList, Job.getProfitComparator());
		
		assertEquals("Testing the highest profit from the sorted  list", 15, jobList.get(0).getProfit()); 
	    assertEquals("Testing the second most profit from the sorted  list", 11, jobList.get(1).getProfit()); 
		assertEquals("Testing the third most profit from the sorted  list", 8, jobList.get(2).getProfit()); 
		assertEquals("Testing the least profit from the sorted  list", 7, jobList.get(3).getProfit()); 
		 
	}
	
	/**
	 * Tests the compound job for the Job class
	 */
	@Test
	public void testCompoundJob() {

		CompoundJob cj = new CompoundJob(40, new Job(0, 1, 14, 6, 10), new Job(1, 7, 17, 3, 5), new Job(2, 10, 25, 8, 25));
		
		assertEquals("Testing the Compund Job Earliest Start", 1, cj.getEarliestStart()); 
	    assertEquals("Testing the Compund Job Deadline", 25, cj.getDeadline()); 
		assertEquals("Testing the Compund Job Duration", 17, cj.getDuration()); 
		assertEquals("Testing the Compund Job Profit", 40, cj.getProfit()); 
	}
	
	/**
	 * Tests the ScheduleAsEarlyAsPossible or  ScheduleAsLateAsPossible for the ScheduleJob 
	 */
	@Test
	public void testScheduleJob() {

		ArrayList<Job> jobs = new ArrayList<Job>(); 

		jobs.add(new Job(0, 1, 6, 2, 10)); 
		jobs.add(new Job(1, 7, 11, 4, 15));
		jobs.add(new Job(2, 3, 10, 3, 5)); 
		jobs.add(new Job(3, 4, 15, 4, 5));
		jobs.add(new Job(3, 3, 7, 3, 5));

		System.out.println("Schedule Jobs");
		for (Job job: jobs)
			System.out.println(job.getId() + " " + 
					job.getEarliestStart() + " " + 
					job.getDeadline() + " " +
					job.getDuration() + " " +
					job.getProfit() + " ");

       //Get Early Schedule Job
		ScheduleAsEarlyAsPossible schAsEarlyAsPossible = new ScheduleAsEarlyAsPossible();
			Collections.sort(jobs, Job.getProfitComparator());
			LinkedList<ScheduleSlot> scheduleEarly = new LinkedList<ScheduleSlot>();
			for (Job job: jobs) {
				schAsEarlyAsPossible.scheduleJob(scheduleEarly, job);
			}
			
			System.out.println();
			System.out.println("Early Scheduled");
			for(ScheduleSlot ss: scheduleEarly)
				System.out.println(ss.getJob().getId() + " " + 
						ss.getJob().getEarliestStart() + " " + 
						ss.getJob().getDeadline() + " " +
						ss.getJob().getDuration() + " " +
						ss.getJob().getProfit() + " ");
			//Get Late Schedule job
			ScheduleAsLateAsPossible schAsLateAsPossible = new ScheduleAsLateAsPossible();
				Collections.sort(jobs, Job.getProfitComparator());
				LinkedList<ScheduleSlot> scheduleLate = new LinkedList<ScheduleSlot>();
				for (Job job: jobs) {
					schAsLateAsPossible.scheduleJob(scheduleLate, job);
				}
				
				System.out.println();
				System.out.println("Late Scheduled");
				for(ScheduleSlot ss: scheduleLate)
					System.out.println(ss.getJob().getId() + " " + 
							ss.getJob().getEarliestStart() + " " + 
							ss.getJob().getDeadline() + " " +
							ss.getJob().getDuration() + " " +
							ss.getJob().getProfit() + " ");
				
				
				assertEquals("Testing the most Profit for Earliest Schedule Job", 15,scheduleEarly.get(0).getJob().getProfit() ); 
				assertEquals("Testing the most Profit for Late Schedule Job", 15,scheduleLate.get(0).getJob().getProfit() ); 
				assertEquals("Testing the least Profit for Earliest Schedule Job", 5,scheduleEarly.get(3).getJob().getProfit() ); 
				assertEquals("Testing the least Profit for Late Schedule Job", 5,scheduleLate.get(4).getJob().getProfit() ); 
		}
 

}

	
	

