package com.andry.rover.invoker;

import com.andry.rover.exception.RoverPositionInvalidException;

import java.io.IOException;
import java.util.List;

public class ConcurrentInvoker implements IInvoker{
    @Override
    public List<String> process() throws RoverPositionInvalidException {
        return null;
    }

    @Override
    public void initProcess() throws IOException {

    }
}
