package com.example.hotel_management_system.command;

import com.example.hotel_management_system.command.commands.Command;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class BookingManager {
    private Stack<Command> commands = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        commands.push(command);
    }

    public void undoLastCommand() {
        if (!commands.isEmpty()) {
            Command lastCommand = commands.pop();
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }
}
