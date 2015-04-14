package p3;

public class CPU {
	
	/** The queue of processes waiting for free memory */
	private Queue CPUQueue;
	/** A reference to the statistics collector */
	private Statistics statistics;
	/** The maximum time amount used by the RR algorithm. */
	private long maxCpuTime;
	/** A reference to the statistics collector */
	private boolean idle;
	private Process currentProcess;

	/**
	 * Creates a new cpu device with the given parameters.
	 * @param memoryQueue	The memory queue to be used.
	 * @param CPUSize	The amount of memory in the memory device.
	 * @param statistics	A reference to the statistics collector.
	 */
    public CPU(Queue CPUQueue, long maxCpuTime, Statistics statistics) {
		this.CPUQueue = CPUQueue;
		this.statistics = statistics;
		this.maxCpuTime = maxCpuTime;
    }	
	
    /**
	 * Adds a process to the cpu queue.
	 * @param p	The process to be added.
	 */
	public void insertProcess(Process p) {
		CPUQueue.insert(p);
		addTimeSpentInCPU(maxCpuTime, p);
	}
	
    public boolean getStatus(){
    	return this.idle;
    }
    	
    public Process nextProcess(){
    	Process nextProcess = (Process)CPUQueue.getNext();
    	CPUQueue.removeNext();
    	return nextProcess;
    }


	public void setCurrentProcess(Process currentProcess) {
		this.currentProcess = currentProcess;
	}

	public Process getCurrentProcess() {
		return this.currentProcess;
	}
	
	public void addTimeSpentInCPU(long time, Process p) {
		long newTime= p.getTimeSpentInCpu() + time;
		p.setTimeSpentInCPU(newTime);
	}
	
	public Process removeActiveProcess() {
		Process currentProcess = this.currentProcess;
		this.currentProcess = null;
		return currentProcess;
	}
}
