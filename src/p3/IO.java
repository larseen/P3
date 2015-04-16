package p3;

public class IO {

	/** The queue of processes waiting for free memory */
	private Queue IOQueue;
	/** A reference to the statistics collector */
	private Statistics statistics;
	/** The maximum time amount used by the RR algorithm. */
	private Process currentProcess;
	private long avgIoTime;
	private boolean idle;

	/**
	 * Creates a new cpu device with the given parameters.
	 * @param memoryQueue	The memory queue to be used.
	 * @param CPUSize	The amount of memory in the memory device.
	 * @param statistics	A reference to the statistics collector.
	 */
    public IO(Queue IOueue, long avgIoTime, Statistics statistics) {
		this.IOQueue = IOueue;
		this.statistics = statistics;
		this.avgIoTime = avgIoTime;
    }
    
    public void insertProcess(Process p) {
    	IOQueue.insert(p);
    }
    
    public Process removeActiveProcess() {
    	Process currentProcess = this.currentProcess;
    	this.currentProcess = (Process)IOQueue.getNext();
    	return currentProcess;
    }
    
    public boolean isIdle() {
    	return this.idle;
    }
    
    private void setActive() {
    	this.idle = false;
    }
    
    public Process pollFromQue() {
    	// We only accept processing of a new IO if the IO isnt already doign somehting
    	if(this.hasElementsInQue() && this.isIdle()) {
    		Process newProcess = (Process) IOQueue.getNext();
    		insertProcess(newProcess);
    		setActive();
    		return newProcess;
    	}
    	return null;
    }
    
    public boolean hasElementsInQue() {
    	if(IOQueue.getQueueLength() > 0) {
    		return true;
    	}
    	return false;
    }
   
}
