package com.andry.rover.invoker;

import com.andry.rover.exception.RoverPositionInvalidException;

import java.io.IOException;
import java.util.List;

/**
 * Invoker, according to the command pattern, it knows how to execute commands
 */
public interface IInvoker {
    /**
     * Execute commands.
     * @return
     * @throws RoverPositionInvalidException
     */
    List<String> process() throws RoverPositionInvalidException;

    /**
     * Init list of rover and command that matches.
     * @throws IOException
     */
    void initProcess() throws IOException;
}
