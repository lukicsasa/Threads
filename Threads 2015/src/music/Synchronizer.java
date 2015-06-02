/*
 * Created on May 28, 2015
 *
 */
package music;

public class Synchronizer {
    
    private boolean leadLineFlag;
    
    public Synchronizer() {
    }

    public Synchronizer(boolean leadLineFlag) {
        super();
        this.leadLineFlag = leadLineFlag;
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
        singOneLine(leadLine, delay);
    }

    public synchronized void singBackingLine(String backingLine, long delay) {
        while (leadLineFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        singOneLine(backingLine, delay);
    }
    
    private void singOneLine(String line, long delay) {
        try {
            wait(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(line);
        leadLineFlag = !leadLineFlag;
        notifyAll();
    }

}
