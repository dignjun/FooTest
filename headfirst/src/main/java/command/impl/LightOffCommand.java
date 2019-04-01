package command.impl;

import command.Command;
import command.Light;

/**
 * 其他的命令,ConcreteCommand
 * Created by DINGJUN on 2019.04.01.
 */
public class LightOffCommand implements Command {
    Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.off();
    }
}
