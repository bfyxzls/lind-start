package com.lind.common.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令调用者.
 */
public class CommandManager {
    private List<Command> commandList = new ArrayList<Command>();

    public void add(Command command) {
        commandList.add(command);
    }

    public void run() {
        for (Command command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}