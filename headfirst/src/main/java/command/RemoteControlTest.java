package command;

import command.impl.LightOnCommand;

/**
 * Created by DINGJUN on 2019.03.31.
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();
        Light light = new Light("living room");
        LightOnCommand command = new LightOnCommand(light);
        simpleRemoteControl.setCommand(command);
        simpleRemoteControl.buttonWasPressed();
    }
}
