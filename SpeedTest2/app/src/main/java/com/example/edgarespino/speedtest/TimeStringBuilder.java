package com.example.edgarespino.speedtest;

/**
 * Created by edgarespino on 6/29/16.
 */
public class TimeStringBuilder {

    public static String getTimeDurationStringFromMinutesFloat(float minutesFloatInput) {
        String timeDurationString = "";

        // get number of hours if any
        float hoursFloat = minutesFloatInput / 60;
        float hoursFloor = (float) Math.floor(hoursFloat);
        if (hoursFloor >= 1) {
            String hoursString;
            if (hoursFloor == 1) {
                hoursString = (int)hoursFloor + " hour";
            } else {
                hoursString = (int)hoursFloor + " hours";
            }
            timeDurationString += hoursString;
        }

        // get number of minutes if any
        float leftOverMinutesFloat = (hoursFloat - hoursFloor) * 60;
        float minutesFloor = (float) Math.floor(leftOverMinutesFloat);
        if (minutesFloor >= 1) {
            if (timeDurationString.length() > 0) {
                timeDurationString += " ";
            }
            String minutesString;
            if (minutesFloor == 1) {
                minutesString = (int)minutesFloor + " minute";
            } else {
                minutesString = (int)minutesFloor + " minutes";
            }
            timeDurationString += minutesString;
        }

        // get number of seconds if any
        int roundedSecondsInt = (int) ((leftOverMinutesFloat - minutesFloor) * 60 + 0.5);
        if (roundedSecondsInt >= 1) {
            if (timeDurationString.length() > 0) {
                timeDurationString += " ";
            }
            String secondsString;
            if (roundedSecondsInt == 1) {
                secondsString = roundedSecondsInt + " second";
            } else {
                secondsString = roundedSecondsInt + " seconds";
            }
            timeDurationString += secondsString;
        }

        return timeDurationString;
    }

    public static void main(String[] args) {
        // sample float amount of minutes for testing
        float minutesFloat = 143.4f;
        String timeDurationString = getTimeDurationStringFromMinutesFloat(minutesFloat);
        System.out.println(timeDurationString);
    }

}
