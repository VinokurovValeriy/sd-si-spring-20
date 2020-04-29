package com.netcracker.ec;

import com.netcracker.ec.services.console.Console;

public class Application {
    public void run() throws Exception {
        Console console = Console.getInstance();
        console.getNextOperation().execute();
    }
}
