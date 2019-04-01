package command.impl;

import command.Command;
import command.Light;

/**
 * concrete command 指令实现
 * Created by DINGJUN on 2019.03.31.
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
