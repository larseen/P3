package p3;

public class IO {

	/** The queue of processes waiting for free memory */
	private Queue IOueue;
	/** A reference to the statistics collector */
	private Statistics statistics;
	/** The maximum time amount used by the RR algorithm. */
	private Process currentProcess;
	private long avgIoTime;

	/**
	 * Creates a new cpu device with the given parameters.
	 * @param memoryQueue	The memory queue to be used.
	 * @param CPUSize	The amount of memory in the memory device.
	 * @param statistics	A reference to the statistics collector.
	 */
    public IO(Queue IOueue, long avgIoTime, Statistics statistics) {
		this.IOueue = IOueue;
		this.statistics = statistics;
		this.avgIoTime = avgIoTime;
    }
    
    public void insertProcess(Process p) {
    	IOueue.insert(p);
    }
    
    public Process removeActiveProcess() {
    	Process currentProcess = this.currentProcess;
    	this.currentProcess = (Process)IOueue.getNext();
    	return currentProcess;
    }
   
}
