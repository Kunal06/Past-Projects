package ca.ubc.cpsc210.alarm.ui;

import ca.ubc.cpsc210.alarm.model.Observer;

import javax.swing.*;

// A label that displays the number of times
// the alarm has been triggered
public class AlarmTriggerLabel extends JLabel implements Observer {
    private int alarmTriggeredCount;

    public AlarmTriggerLabel() {
        alarmTriggeredCount = 0;
        updateText();
    }

    @Override
    public void update(boolean isRinging) {
        if (isRinging) {
            alarmTriggeredCount++;
            updateText();
        }
    }

    private void updateText() {
        setText("Alarm has been triggered " + alarmTriggeredCount
        + " times");
    }
}
