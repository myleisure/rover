package com.andry.rover.invoker;

import com.andry.rover.command.Command;
import com.andry.rover.command.MoveForwardCommand;
import com.andry.rover.command.TurnLeftCommand;
import com.andry.rover.command.TurnRightCommand;
import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.model.Direction;
import com.andry.rover.model.Position;
import com.andry.rover.receiver.IRover;
import com.andry.rover.receiver.Rover;
import com.andry.rover.utils.Triple;
import com.andry.rover.validator.MaxPositionValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of invoker using BufferedReader.
 */
public class Invoker implements IInvoker {

    private BufferedReader bufferedReader;
    private List<Triple<Position, Direction, List<Character>>> InitialParameters;
    private Position maxPosition;

    public Invoker(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.InitialParameters = new ArrayList<>();
    }

    @Override
    public List<String> process() throws RoverPositionInvalidException {
        List<String> newPositionsAsArray = new ArrayList<>();
        for(Triple<Position, Direction, List<Character>> item: this.InitialParameters) {
            Position currentPosition = item.getLeft();
            final IRover rover = new Rover(currentPosition, item.getMiddle(), new MaxPositionValidator(this.maxPosition));
            List<Character> commandsAsCharacters = item.getRight();
            for (Character c : commandsAsCharacters) {
                Command command = this.resolveCommand(c, rover);
                command.execute();
            }
            //toString returns the new position after all commands have been executed.
            newPositionsAsArray.add(rover.toString());
        }
        return newPositionsAsArray;
    }

    @Override
    public void initProcess() throws IOException {

        final var firstLineOfStringInBufferReader = bufferedReader.readLine();
        this.maxPosition = this.createMaxPosition(firstLineOfStringInBufferReader);
        String secondLineOfStringInBufferArray;
        while ((secondLineOfStringInBufferArray = bufferedReader.readLine()) != null) {

            if (!secondLineOfStringInBufferArray.matches("^\\d+\\s\\d+\\s[NESW]")) {
                throw new InvalidParameterException("position invalid in the current file");
            }

            final var newPosition = this.createCurrentPosition(secondLineOfStringInBufferArray);
            final var direction = this.createDirection(secondLineOfStringInBufferArray);

            final var thirdLineOfStringInBufferReader = bufferedReader.readLine();

            final List<Character> commands = this.createCommandArrays(thirdLineOfStringInBufferReader);
            this.InitialParameters.add(new Triple<>(newPosition, direction, commands));
        }
    }

    private List<Position> getInitialPositionsInFile() {
        return this.InitialParameters.stream()
                .map(t -> new Position(t.getLeft().getX(), t.getLeft().getY()))
                .collect(Collectors.toList());
    }

    private Position createCurrentPosition(String secondLineOfStringInBufferArray) {
        final var positionArray = secondLineOfStringInBufferArray.split(" ");
        return new Position(Integer.parseInt(positionArray[0]), Integer.parseInt(positionArray[1]));
    }

    private Direction createDirection(final String rawDirection) {
        String[] positionArray = rawDirection.split(" ");
        return Direction.fromCharacter(positionArray[2].charAt(0));
    }

    private Position createMaxPosition(final String firstLineOfString) {
        if (firstLineOfString == null || firstLineOfString.isBlank() || firstLineOfString.split(" ").length < 2
                || !firstLineOfString.matches("^\\d+\\s\\d+")) {
            throw new InvalidParameterException("Could not get maxx and maxy");
        }
        final String[] maxAndMin = firstLineOfString.split(" ");
        final int maxX = Integer.parseInt(maxAndMin[0]);
        final int maxY = Integer.parseInt(maxAndMin[1]);
        return new Position(maxX, maxY);
    }

    private List<Character> createCommandArrays(String rawCommandString) throws IOException {
        if (rawCommandString == null || !rawCommandString.matches("^[LMR]+")) {
            throw new InvalidParameterException("Commands invalid in the current file");
        }
        return rawCommandString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }

    private Command resolveCommand(Character commandAsCHar, IRover rover) {
        final Command command;
        switch (commandAsCHar) {
            case 'L':
                command = new TurnLeftCommand(rover);
                break;
            case 'M':
                command = new MoveForwardCommand(rover);
                break;
            case 'R':
                command = new TurnRightCommand(rover);
                break;
            default:
                throw new InvalidParameterException("This command is not handled yet");
        }
        return command;
    }
}
