package com.andry.rover.invoker;

import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.model.Position;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentInvoker implements IInvoker{

    private CopyOnWriteArrayList<Position> alreadyPinnedPositions;

    public ConcurrentInvoker() {
        this.alreadyPinnedPositions = new CopyOnWriteArrayList<>();
    }

    @Override
    public List<String> process() throws RoverPositionInvalidException {
        return null;
    }

    @Override
    public void initProcess() throws IOException {

    }
}
