/*
 * Created on May 28, 2015
 *
 */
package music;

import javax.swing.JTextArea;

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

    public synchronized void singLeadLine(String leadLine, long delay , JTextArea area) {
        while (!leadLineFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        singOneLineLead(leadLine, delay, area);
    }

    public synchronized void singBackingLine(String backingLine, long delay, JTextArea area) {
        while (!anotherFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        singOneLineBack(backingLine, delay, area);
    }
    
    public synchronized void singAnotherLeadLine(String anotherLeadingLine, long delay, JTextArea area) {
    	while (leadLineFlag || anotherFlag) {
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	singOneLineAnother(anotherLeadingLine, delay, area);
    }
    
    private void singOneLineLead(String line, long delay, JTextArea area) {
        try {
            wait(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        area.append(line +'\n');
        leadLineFlag = false;
        anotherFlag = true;
        notifyAll();
    }
    
    private void singOneLineBack(String line, long delay, JTextArea area) {
        try {
            wait(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        area.append(line +'\n');
        leadLineFlag = false;
        anotherFlag = false;
        notifyAll();
    }
    
    private void singOneLineAnother(String line, long delay, JTextArea area) {
        try {
            wait(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        area.append(line +'\n');
        leadLineFlag = true;
        anotherFlag = false;
        notifyAll();
    }
    
    
    
    

}
