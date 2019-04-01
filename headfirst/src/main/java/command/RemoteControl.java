package command;

import command.impl.NoCommand;

/**
 * 遥控器类,Invoker类,持有一个/一组命令对象
 * Created by DINGJUN on 2019.04.01.
 */
public class RemoteControl {
    Command[] onCommand;
    Command[] offCommand;
    public RemoteControl(){
        onCommand = new Command[7];
        offCommand = new Command[7];
        // 初始化所有的命令为空指令,这样在未给出任何的命令的时候不需要在进行空校验
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i ++) {
            onCommand[i] = noCommand;
            offCommand[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        this.onCommand[slot] = onCommand;
        this.offCommand[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommand[slot].execute();
    }
    public void offButtonWasPushed(int slot) {
        offCommand[slot].execute();
    }
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n-------Remote Control---------\n");
        for (int i = 0; i < onCommand.length; i++) {
            buffer.append("[slot " + i + "]" + onCommand[i].getClass().getName() + "    " + offCommand[i].getClass().getName() + "\n");
        }
        return buffer.toString();
    }
}
