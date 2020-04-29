package com.andry.rover.receiver;

import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.model.Direction;
import com.andry.rover.model.Position;
import com.andry.rover.validator.PositionValidator;

public class Rover implements IRover {

    private Position position;
    private Direction direction;

    private PositionValidator positionValidator;

    public Rover(Position position, Direction direction, PositionValidator positionValidator) {
        this.position = position;
        this.direction = direction;
        this.positionValidator = positionValidator;
    }

    @Override
    public void turnLeft() {
        switch (this.direction) {
            case EAST:
                this.setDirection(Direction.NORTH);
                break;
            case NORTH:
                this.setDirection(Direction.WEST);
                break;
            case WEST:
                this.setDirection(Direction.SOUTH);
                break;
            case SOUTH:
                this.setDirection(Direction.EAST);
                break;
        }
    }

    @Override
    public void turnRight() {
        switch (this.direction) {
            case EAST:
                this.setDirection(Direction.SOUTH);
                break;
            case NORTH:
                this.setDirection(Direction.EAST);
                break;
            case WEST:
                this.setDirection(Direction.NORTH);
                break;
            case SOUTH:
                this.setDirection(Direction.WEST);
                break;
        }
    }

    @Override
    public void moveForward() throws RoverPositionInvalidException {

        final Position newPosition = new Position(this.getPosition().getX(), this.getPosition().getY());

        switch (this.direction) {
            case EAST:
                newPosition.setX(newPosition.getX() + 1);
                break;
            case NORTH:
                newPosition.setY(newPosition.getY() + 1);
                break;
            case WEST:
                newPosition.setX(newPosition.getX() - 1);
                break;
            case SOUTH:
                newPosition.setY(newPosition.getY() - 1);
                break;
        }
        this.positionValidator.validate(newPosition);
        this.position = newPosition;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return this.getPosition().getX() + " " + this.getPosition().getY() + " " + this.getDirection().getCode();
    }
}
