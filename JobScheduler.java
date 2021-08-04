package project4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Isha Das
 * This class schedules Job by reading from a file. It has also utility method to randomly generate test job files.
 */
public class JobScheduler {

	public static void main(String[] args) {

		// Define a variable to get file name from command line arguement
		String fileName = "";
		if (args.length == 0) {
            System.out.println("No File name is given.");
        }
        else {
        	fileName = args[0];
        }
		
		
		// Create an Arralist of jobs from the file and read the jobs from the file
		ArrayList<Job> jobList = readJobFile(fileName);

		CompoundJob cj = new CompoundJob(40, new Job(0, 1, 14, 6, 10), new Job(1, 7, 17, 3, 5), new Job(2, 10, 25, 8, 25));

		ScheduleAsEarlyAsPossible schAsEarlyAsPossible = new ScheduleAsEarlyAsPossible();
		LinkedList<ScheduleSlot> scheduleEarly = scheduleJobs(jobList, Job.getProfitComparator(), schAsEarlyAsPossible);
		
		// Variable to store total profit for the as early as possible schedule
		int totalEarlyProfit = 0;
		
		System.out.println();
		System.out.println("Early Scheduled");
		for(ScheduleSlot ss: scheduleEarly) {
			System.out.println(ss.getJob().getId() + " " + 
					ss.getJob().getEarliestStart() + " " + 
					ss.getJob().getDeadline() + " " +
					ss.getJob().getDuration() + " " +
					ss.getJob().getProfit() + " ");
			totalEarlyProfit = totalEarlyProfit + ss.getJob().getProfit() ;
		}

		ScheduleAsLateAsPossible schAsLateAsPossible = new ScheduleAsLateAsPossible();
		LinkedList<ScheduleSlot> scheduleLate = scheduleJobs(jobList, Job.getProfitComparator(), schAsLateAsPossible);
	
		// Variable to store total profit for the as late as possible schedule
		int totalLateProfit = 0;
		
		System.out.println();
		System.out.println("Late Scheduled");
		for(ScheduleSlot ss: scheduleLate) {
			System.out.println(ss.getJob().getId() + " " + 
					ss.getJob().getEarliestStart() + " " + 
					ss.getJob().getDeadline() + " " +
					ss.getJob().getDuration() + " " +
					ss.getJob().getProfit() + " ");
			totalLateProfit = totalLateProfit + ss.getJob().getProfit() ;
		}
		
		System.out.println();
		//compares the total early profit is greater than the late profit
		if(totalEarlyProfit >  totalLateProfit) {
			System.out.println("As early as possible has more profit than the as late as possible schedule.");
		} else if (totalEarlyProfit <  totalLateProfit) {
			System.out.println("As late as possible has more profit than the as early as possible schedule.");
		} else {
			System.out.println("Profits are same for both ss early as possible and as late as possible schedules.");
		}
	}
	
	/**
	 * @param jobs
	 * @param comparator
	 * @param schMetric
	 * @return the schedule that is made 
	 */
	public static LinkedList<ScheduleSlot> scheduleJobs(ArrayList<Job> jobs, Comparator<Job> comparator, ScheduleMetric schMetric) {
		Collections.sort(jobs, comparator);
		LinkedList<ScheduleSlot> schedule = new LinkedList<ScheduleSlot>();
		for (Job job: jobs) {
			schMetric.scheduleJob(schedule, job);
		}
		return schedule;
	}

	/**
	 * Creates a file with random job data, based on the parameters given.  The job data will be 
	 * sorted by earliest start time.
	 * @param numJobs   the number of jobs to create
	 * @param maxStart  the latest time at which a job may set as its earliest start time
	 * @param maxDuration  the maximum time that a job can take to complete
	 * @param maxLag   the greatest time between the earliest start time for a job and the latest time that a job must start to meet its deadline
	 * @param maxProfic  the maximum amount of profit from a job
	 * @return an array containing the random jobs
	 */
	public Job[] createRandomJobs(int numJobs, int maxStart, int maxDuration, int maxLag, int maxProfit) {
		Job[] jobs = new Job[numJobs];

		// For each desired job, create 4 random numbers based on the parameters, use the numbers to create a job,
		// and store the job in an array.
		for (int i = 0; i < numJobs; i++) {
			int start = (int)(Math.random() * maxStart) + 1;
			int duration = (int)(Math.random() * maxDuration) + 1;
			int deadline = start + duration + (int)(Math.random() * (maxLag + 1));
			int profit = (int)(Math.random() * maxProfit) + 1;
			jobs[i] = new Job(i, start, deadline, duration, profit); 
		}

		// Sort the jobs by earliest start time
		Arrays.sort(jobs, Job.getStartComparator());

		return jobs;
	}

	/**
	 * Creates a file with job data.
	 * @param fileName  the name of the file to store the job data.  The file must not exist.
	 * @param jobs an array containing the jobs
	 */
	public void createJobFile(String fileName, Job[] jobs) {
		// Check that the output file does not already exist
		File file = new File(fileName);
		if (file.exists()) {
			System.out.println("Error: file " + fileName + " already exists.");
			return;
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			// For each job in the array, write the job to the file as 5 numbers separated by spaces.
			// Place an each job on a new line.
			for (int i = 0; i < jobs.length; i++) {
				String s = i + " " + jobs[i].getEarliestStart() + " " + jobs[i].getDeadline() + " " + jobs[i].getDuration() + " " + jobs[i].getProfit();
				writer.write(s, 0, s.length());
				writer.newLine();
			}
			writer.flush();
			writer.close();
		}
		catch (IOException e) {
			System.out.println("Error writing to file " + fileName);
		}
	}

	/**
	 * Read a file with job data.
	 * @param fileName  the name of the file that contains random job data
	 * @return an ArrayList containing the random jobs
	 */	
	public static ArrayList<Job> readJobFile(String fileName) {
		ArrayList<Job> jobList = new ArrayList<Job>(); 
		try {
			// Check that the output file does not already exist
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			// Read one line and create the Job object from each line - loop until end of line
			while (scanner.hasNextLine()) {
				Scanner line = new Scanner(scanner.nextLine());
				int id = line.nextInt();
				int earlyStart = line.nextInt();
				int deadLine = line.nextInt();
				int duration = line.nextInt();
				int profit = line.nextInt();
				Job job = new Job(id, earlyStart, deadLine , duration, profit);
				jobList.add(job);					
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("Error in reading file " + e);
		}
		return jobList;
	}
}
