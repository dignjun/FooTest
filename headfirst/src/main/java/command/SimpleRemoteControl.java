package command;

/**
 * 控制器,使用命令对象
 * Created by DINGJUN on 2019.03.31.
 */
public class SimpleRemoteControl {
    Command slot;
    public SimpleRemoteControl(){}
    public void setCommand(Command command) {
        this.slot = command;
    }
    public void buttonWasPressed(){
        slot.execute();
    }
}
