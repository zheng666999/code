package asocks;

/**
 * component lifeCycle
 */
public interface ComponentLifeCycle {

	void start();
	void stop();
	NTStatus getComponentStatus();
	
}
