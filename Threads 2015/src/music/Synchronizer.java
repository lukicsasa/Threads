/*
 * Created on May 28, 2015
 *
 */
package music;

public class Synchronizer {
    
    private boolean leadLineFlag;
    private boolean anotherFlag;
    
    public Synchronizer() {
    }

    public Synchronizer(boolean leadLineFlag, boolean anotherFlag) {
        super();
        this.leadLineFlag = leadLineFlag;
        this.anotherFlag = anotherFlag;
    }

    public synchronized void singLeadLine(String leadLine, long delay) {
        while (!leadLineFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        singOneLineLead(leadLine, delay);
    }

    public synchronized void singBackingLine(String backingLine, long delay) {
        while (!anotherFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        singOneLineBack(backingLine, delay);
    }
    
    public synchronized void singAnotherLeadLine(String anotherLeadingLine, long delay) {
    	while (leadLineFlag || anotherFlag) {
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	singOneLineAnother(anotherLeadingLine, delay);
    }
    
    private void singOneLineLead(String line, long delay) {
        try {
            wait(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(line);
        leadLineFlag = false;
        anotherFlag = true;
        notifyAll();
    }
    
    private void singOneLineBack(String line, long delay) {
        try {
            wait(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(line);
        leadLineFlag = false;
        anotherFlag = false;
        notifyAll();
    }
    
    private void singOneLineAnother(String line, long delay) {
        try {
            wait(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(line);
        leadLineFlag = true;
        anotherFlag = false;
        notifyAll();
    }
    
    
    
    

}
