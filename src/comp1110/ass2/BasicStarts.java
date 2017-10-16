package comp1110.ass2;

import java.util.Random;

public class BasicStarts {
    Random rand = new Random();
    int random = rand.nextInt(2);
    String starter [] = {"DAiAHQBGSCDNEFBGCgHFl", "DElAFnFAiBBgCCQEGLGCM", "CFjGANAALDBgHEQFCmEDI"};
    String hard [] = {"CCLEEgFBj", "CGOGDLAGjEAg", "BGSDGQEFBGHn"};
    String master [] = {"FBmBEeEEf", "CGOEEnGDL", "AFnEFBGFS"};
    public String finalString;

    public String starterStarts(){
        finalString = starter[random];
        return finalString;
    }

    public String hardStarts() {
        finalString = hard[random];
        return finalString;
    }

    public String masterStarts() {
        finalString = master[random];
        return finalString;
    }


}
