
/**
 * The ClockDisplay class implements a digital clock display for an
 * American 12 hour clock. The clock shows hours, minutes, and an 
 * AM/PM indicator. The range of the clock is 00:00 (midnight) to 23:59 
 * (one minute before midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Ryan Canuel
 * @version 2020/02/10
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private boolean morning;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        morning = true;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, boolean am)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, am);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            if(hours.getValue() == 0) { // check if hours roll over, to switch AM/PM
                if(morning) { // if true then false, vice versa
                    morning = false;
                } else {
                    morning = true;
                }
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, boolean am)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        morning = am;
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM AM/PM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        if(morning) {
            if(hours.getValue() == 0) {
                displayString = "12:" + 
                    minutes.getDisplayValue() + " AM";
            } else {
                displayString = hours.getDisplayValue() + ":" + 
                    minutes.getDisplayValue() + " AM";
            }
        } else {
            if(hours.getValue() == 0) {
                displayString = "12:" + 
                    minutes.getDisplayValue() + " PM";
            } else {
                displayString = hours.getDisplayValue() + ":" + 
                    minutes.getDisplayValue() + " PM";
            }
        }
    }
}

