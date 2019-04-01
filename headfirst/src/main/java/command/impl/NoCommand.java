package command.impl;

import command.Command;

/**
 * 为了避免检查是否某个插槽(数组元素)加载了命令,所以初始化一个空的执行的命令,避免了检查和空指针异常.
 * Created by DINGJUN on 2019.04.01.
 */
public class NoCommand implements Command{
    @Override
    public void execute() {
        // 重写的空方法,初始化使用空方法避免在命令数组中执行的时候每次使用命令的检查
    }
}
