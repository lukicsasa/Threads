/*
 * Created on May 28, 2015
 *
 */
package music;

public class Performance {

    private Song song;
    private int delay;
    
    public Performance() {
    }

    public Performance(Song song, int delay) {
        super();
        this.song = song;
        this.delay = delay;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

}
