package command;

/**
 * Created by DINGJUN on 2019.04.01.
 */
public class Stereo {
    public void on(){
        System.out.println("Stereo on");
    }
    public void off(){
        System.out.println("Stereo off");
    }
    public void setCD(){}
    public void setDvd(){}
    public void setRadio(){}
    public void setVolume(int volume){
        System.out.println("Stereo volume:" + volume);
    }
}
