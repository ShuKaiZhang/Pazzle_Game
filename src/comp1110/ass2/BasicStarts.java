package comp1110.ass2;

import java.util.Random;

public class BasicStarts {
    Random rand = new Random();
    int random = rand.nextInt(2);
    String starter [] = {"AHGBGSCDNDAiEFBGCgHFl", "AEnBBgCCpDDlEGBFAiGCM", "AALCFjDBgEDSECMGANECP"};
    String hard [] = {"CCLEEgEBi", "CGNGDLAGjEAg", "BGSDGQEFBGHn"};
    String master [] = {"BEeEEfFBm", "CGOEEnCDL", "AFnEFBGFS"};
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
