package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotMap;

//TODO: Get new LEDs and test all these on robot

public class LED extends Subsystem {

    private static final int LED_STRIP_LENGTH = 150;
    private AddressableLED ledStrip = null;
    private Timer time;
    private double period = 0.25;

    @Override
    public void initDefaultCommand() {
      setDefaultCommand(null);
    }

    public void initLeds() {
        ledStrip = new AddressableLED(RobotMap.LED_PORT);
        // Per the docs, the setLength method is very expensive. Use sparingly
        ledStrip.setLength(LED_STRIP_LENGTH);
        time = new Timer();     //Creates a time object
        // By default the bit timing and sync set to drive WS2812s
        // See https://github.com/wpilibsuite/allwpilib/blob/master/wpilibj/src/main/java/edu/wpi/first/wpilibj/AddressableLED.java
        // led.setBitTiming(...)
        // led.setSyncTime(...)


    }

    /**
     * Generates a Color8Bit from a hexcode
     * @param hexcode Must be in the format "xxxxxx"
     */
    private Color8Bit generateColorFromHex(String hexcode) {
        int red = Integer.parseInt(hexcode.substring(0,2), 16);
        int green = Integer.parseInt(hexcode.substring(2,4), 16);
        int blue = Integer.parseInt(hexcode.substring(4,6), 16);
        Color8Bit color = new Color8Bit(red, green, blue);
        return color;

        
    }

    /**
     * Get a color from Hue, saturation and brigtness value
     * @param h hue value of color
     * @param s saturation of color (must be value from 0-1)
     * @param b brightness of color (must be value from 0-1)
     * @return
     */
    public static Color8Bit generateColorFromHSB(int h, double s, double b) {
        double c = b * s;
        double x = c * (1 - Math.abs(((h/60) % 2) - 1));
        double m = b - c;
        double rprime = 0;
        double gprime = 0;
        double bprime = 0;
        
        if(h >= 0 && s >= 0 && b >= 0 && h <= 360 && s <= 1 && b <= 1) {
            // Calculate rprime, gprime and bprime
            if(h >= 0 && h < 60){
                rprime = c;
                gprime = x;
                bprime = 0; 
            } 
            else if (h >= 60 && h < 120){
                rprime = x;
                gprime = c;
                bprime = 0;                 
            }
            else if (h >= 60 && h < 120){
                rprime = x;
                gprime = c;
                bprime = 0;                 
            } 
            else if (h >= 120 && h < 180){
                rprime = 0;
                gprime = c;
                bprime = x;                 
            }
            else if (h >= 180 && h < 240){
                rprime = 0;
                gprime = x;
                bprime = c;                 
            }
            else if (h >= 240 && h < 300){
                rprime = x;
                gprime = 0;
                bprime = c;                 
            }
            else if (h >= 300 && h < 360){
                rprime = c;
                gprime = 0;
                bprime = x;                 
            }
            else {
                return new Color8Bit(0,0,0);
            }
    
            int red = (int)((rprime + m)*255);
            int green = (int)((gprime + m)*255);
            int blue = (int)((bprime + m)*255);
            return new Color8Bit(red,green,blue);
        }
        else {
            return new Color8Bit(0,0,0);
        }
    }

    /**
     * Gets a hue of a color
     * @param color
     * @return hue of color
     */
    private Object[] rgbToHSB(Color8Bit color){
        Object[] buffer = new Object[3];
        int hue = 0;
        double saturation = 0;
        double brightness = 0;

        double rprime = color.red/255;
        double bprime = color.blue/255;
        double gprime = color.green/255;

        double cMax = Math.max(Math.max(rprime, bprime),gprime);
        double cMin = Math.min(Math.min(rprime, bprime),gprime);
        double delta = cMax - cMin;
    
        if(cMax == rprime){
            hue = (int)Math.round(((gprime - bprime)/delta) % 6);
        } else if (cMax == gprime){
            hue = (int)Math.round(((bprime - rprime)/delta) + 2);
        } else if (cMax == bprime) {
            hue = (int)Math.round(((rprime - gprime)/delta) + 4);
        } else {
            hue = 0;
        }
    
        hue = hue * 60;
    
        if (delta/cMax == 0){
            saturation = 0;
        } else{
            saturation = delta/cMax;
        }

        brightness = cMax;

        buffer[0] = hue;
        buffer[1] = saturation;
        buffer[2] = brightness;
        return buffer;
    }

    /**
     * Sets a static color based on a hex code
     * @param hexcode Must be in the format "xxxxxx"
     */
    public void setStaticColor(String hexcode){
        Color8Bit color = generateColorFromHex(hexcode);
        // Make a new AddressableLEDBuffer
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LED_STRIP_LENGTH);
        for(int ledIndex = 0; ledIndex < LED_STRIP_LENGTH; ledIndex++) {
            buffer.setLED(ledIndex, color);
        }
        ledStrip.setData(buffer);
        ledStrip.start();
    }

    public void setStaticColor(Color8Bit c){
        // Make a new AddressableLEDBuffer
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LED_STRIP_LENGTH);
        for(int ledIndex = 0; ledIndex < LED_STRIP_LENGTH; ledIndex++) {
            buffer.setLED(ledIndex, c);
        }
        ledStrip.setData(buffer);
        ledStrip.start();
    }
    
    public void fadeColor(String startHex, String endHex) {
        Color8Bit startColor = generateColorFromHex(startHex);
        Color8Bit endColor = generateColorFromHex(endHex);
        Object[] colorHsbStart = rgbToHSB(startColor);
        Object[] colorHsbEnd = rgbToHSB(endColor);
    
    }

    public void fadeColor(String startHex, String endHex, double brightness) {
        Color8Bit startColor = generateColorFromHex(startHex);
        Color8Bit endColor = generateColorFromHex(endHex);
        Object[] colorHsbStart = rgbToHSB(startColor);
        Object[] colorHsbEnd = rgbToHSB(endColor);
        
        
    }

    public void rainbowFade(double brightness) {
        Color8Bit color = null;
        for(int hue = 0; hue < 180; hue ++){
            color = generateColorFromHSB(hue, 1, brightness);
            setStaticColor(color);
        }
    }

    public void setAlternatingColor(String hexcode1, String hexcode2) {
        Color8Bit color1 = generateColorFromHex(hexcode1);
        Color8Bit color2 = generateColorFromHex(hexcode2);
        // Make a new AddressableLEDBuffer
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LED_STRIP_LENGTH);
        for(int ledIndex = 0; ledIndex < LED_STRIP_LENGTH; ledIndex++) {
            if(ledIndex % 2 == 0) {
              buffer.setLED(ledIndex, color1);
            } else {
                buffer.setLED(ledIndex, color2);
            }
        }
        ledStrip.setData(buffer);
        ledStrip.start();
    }

    public void blueYellowTrail(){
        //This is made by Ben - yes ben coded, or at least tried to.
        Color8Bit blue = new Color8Bit(0,0,255);
        Color8Bit yellow = new Color8Bit(255,244,0);
        Color8Bit lightBlue = new Color8Bit(36,255,255);
        Color8Bit green = new Color8Bit(0,255,0);
        Color8Bit lightGreen = new Color8Bit(154,255,0);
        //Set all LEDs to blue.
        setStaticColor("0000FF");
        // Make a new AddressableLEDBuffer
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LED_STRIP_LENGTH);
        //Yellow trail with a blue fade in the end.
        for(int ledIndex = 0; ledIndex < LED_STRIP_LENGTH; ledIndex++){
            //TODO: Test on robot
            ledStrip.stop();    //Banking off the hope that stop, stops the output, not clears leds
//          buffer.setLED(ledIndex, yellow);
            if(time.get() % 1 < period){
                buffer.setLED(ledIndex, yellow);
                if(ledIndex-1 >= 0){
                    buffer.setLED(ledIndex-1,yellow);
                }
                if(ledIndex-2 >= 0){
                    buffer.setLED(ledIndex-2, lightGreen);
                }
                if(ledIndex-3 >= 0){
                    buffer.setLED(ledIndex-3, green);
                }
                if(ledIndex-4 >= 0){
                    buffer.setLED(ledIndex-4, lightBlue);
                }
                if(ledIndex-5 >= 0){
                    buffer.setLED(ledIndex-5, blue);
                }
            }
            ledStrip.setData(buffer);
            ledStrip.start();
        }
    }

    public void blueWhiteTrail(){
        //This is made by Ben - yes ben coded, or at least tried to.
        Color8Bit blue = new Color8Bit(0,0,255);
        Color8Bit white = new Color8Bit(255,255,255);
        Color8Bit slightBlue = new Color8Bit(189,189,255);
        Color8Bit lightBlue = new Color8Bit(126,126,255);
        Color8Bit almostBlue = new Color8Bit(63,63,0);
        //Set all LEDs to blue.
        setStaticColor("0000FF");
        // Make a new AddressableLEDBuffer
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LED_STRIP_LENGTH);
        //Yellow trail with a blue fade in the end.
        for(int ledIndex = 0; ledIndex < LED_STRIP_LENGTH; ledIndex++){
            ledStrip.stop();    //Banking off the hope that stop, stops the output, not clears leds
//          buffer.setLED(ledIndex, white);
            if(time.get() % 1 < period){
                buffer.setLED(ledIndex, white);
                if(ledIndex-1 >= 0){
                    buffer.setLED(ledIndex-1,white);
                }
                if(ledIndex-2 >= 0){
                    buffer.setLED(ledIndex-2, slightBlue);
                }
                if(ledIndex-3 >= 0){
                    buffer.setLED(ledIndex-3, lightBlue);
                }
                if(ledIndex-4 >= 0){
                    buffer.setLED(ledIndex-4, almostBlue);
                }
                if(ledIndex-5 >= 0){
                    buffer.setLED(ledIndex-5, blue);
                }
            }
            ledStrip.setData(buffer);
            ledStrip.start();
        }
    }

    public void randomColors() {
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LED_STRIP_LENGTH);
        for(int ledIndex = 0; ledIndex < LED_STRIP_LENGTH; ledIndex++) {
            Color8Bit color = new Color8Bit((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
            buffer.setLED(ledIndex, color);
        }

        ledStrip.setData(buffer);
        ledStrip.start();
    }

    public void flint() {
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LED_STRIP_LENGTH);
        Color8Bit color;
        for(int ledIndex = 0; ledIndex < LED_STRIP_LENGTH; ledIndex++) {
            if(time.get() % 1 < 0.5) {
              if(ledIndex <= LED_STRIP_LENGTH) {
                  color = new Color8Bit(255, 0, 0);
                  buffer.setLED(ledIndex, color);
              }
            } else {
                if(ledIndex > LED_STRIP_LENGTH) {
                    color = new Color8Bit(0, 0, 255);
                    buffer.setLED(ledIndex, color);
                }
            }
        }
    }
}