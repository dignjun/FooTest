package command;

import command.impl.LightOffCommand;
import command.impl.LightOnCommand;

/**
 * 测试类
 * Created by DINGJUN on 2019.04.01.
 */
public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoom = new Light("living room");
        Light kitchen = new Light("kitchen");

        LightOnCommand livingRoomOn = new LightOnCommand(livingRoom);
        LightOffCommand livingOffCommand = new LightOffCommand(livingRoom);
        LightOnCommand kitchenRoomOn = new LightOnCommand(kitchen);
        LightOffCommand kitchenOffCommand = new LightOffCommand(kitchen);

        remoteControl.setCommand(0, livingRoomOn, livingOffCommand);
        remoteControl.setCommand(1, kitchenRoomOn, kitchenOffCommand);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
    }
}
