package com.image.undo;

import java.util.Stack;

public class CommandManager {
    private final Stack<Command> commandStack = new Stack<>();

    // 执行命令并保存
    public void executeCommand(Command command) {
        command.execute();
        commandStack.push(command);
    }

    // 撤销上一个命令
    public void undo() {
        if (!commandStack.isEmpty()) {
            Command lastCommand = commandStack.pop();
            lastCommand.undo();
        }
    }

    // 检查是否可以撤销
    public boolean canUndo() {
        return !commandStack.isEmpty();
    }
}
