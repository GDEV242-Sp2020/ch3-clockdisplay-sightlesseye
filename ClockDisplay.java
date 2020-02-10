
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
    private int pmHours;
    private NumberDisplay minutes;
    private boolean morning;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        morning = true;
        updateDisplay();
    }
 
    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
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
            if(hours.getValue() == 0) { // now it's morning
                morning = true;
            }
            if(hours.getValue() == 12) {
                morning =  false;
                pmHours = hours.getValue() - 12;
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        if(hours.getValue() >= 12) {
            morning =  false;
            pmHours = hours.getValue() - 12;
        } else {
            morning = true;
        }
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
            if(pmHours == 0) {
                displayString = "12:" + 
                    minutes.getDisplayValue() + " PM";
            } else if(pmHours < 10){
                displayString = "0" + pmHours + ":" + 
                    minutes.getDisplayValue() + " PM";
            } else {
                displayString = "" + pmHours + ":" + 
                    minutes.getDisplayValue() + " PM";
            }
        }
    }
}