package Algorithms;

import labyrinth.Node;

import java.util.Random;

public class Tremaux {

    private Random random = new Random();
    private Node[][] graph;
    private Node current = new Node();
    private int lastDirection;

    public void searchExit(char[][] labirynth) {

        graph = current.createGraph(labirynth);
        Node start;

        start = current.findStart(graph);

        current = start;

        long startTime = System.nanoTime();
        while (!current.isExit()) {
            chooseWayAndGo();
        }

        while (current != start) {
            System.out.print(current.getNodeValue() + " -> ");
            switch (findOne()) {
                case -1:
                    current = current.getNodeLeft();
                    current.riseRightCounter();
                    break;
                case -2:
                    current = current.getNodeUp();
                    current.riseDownCounter();
                    break;
                case 1:
                    current = current.getNodeRight();
                    current.riseLeftCounter();
                    break;
                case 2:
                    current = current.getNodeDown();
                    current.riseUpCounter();
                    break;
                default:
            }
        }
        System.out.println(current.getNodeValue());
        long endTime = System.nanoTime();
        System.out.println("Time in nanos: " + (endTime - startTime));
    }

    private void chooseWayAndGo() {
        int rand = random.nextInt(4);
        //wychodzenie ze slepej uliczki
        if (areAllMarked()) {
            while (!wayAvailable(rand, 1)) {
                rand = random.nextInt(5) - 2;
            }
        } else//wchodzimy w oznaczone skrzyzowanie i czy przyszlismy z podwójnie oznaczonej
            if (areAllMarked() && lastDirectionCounter() != 2) {
                rand = lastDirection * (-1);
            } else
                while (!wayAvailable(rand, 0)) {
                    rand = random.nextInt(5) - 2;
                }
        //przesuwanie sie po grafie
        switch (rand) {
            case -1:
                current.riseLeftCounter();
                current = current.getNodeLeft();
                current.riseRightCounter();
                if (current.getRightCounter() == 2) {
                    current.getNodeRight().setNodeLeft(null);
                    current.setNodeRight(null);
                }
                break;
            case -2:
                current.riseUpCounter();
                current = current.getNodeUp();
                current.riseDownCounter();
                if (current.getDownCounter() == 2) {
                    current.getNodeDown().setNodeUp(null);
                    current.setNodeDown(null);
                }
                break;
            case 1:
                current.riseRightCounter();
                current = current.getNodeRight();
                current.riseLeftCounter();
                if (current.getLeftCounter() == 2) {
                    current.getNodeLeft().setNodeRight(null);
                    current.setNodeLeft(null);
                }
                break;
            case 2:
                current.riseDownCounter();
                current = current.getNodeDown();
                current.riseUpCounter();
                if (current.getUpCounter() == 2) {
                    current.getNodeUp().setNodeDown(null);
                    current.setNodeUp(null);
                }
                break;
            default:
                break;
        }
        lastDirection = rand;
    }

    private boolean wayAvailable(int direction, int markedBy) {
        switch (direction) {
            case -1:
                if (current.getNodeLeft() != null && current.getLeftCounter() == markedBy) {
                    return true;
                }
                return false;
            case -2:
                if (current.getNodeUp() != null && current.getUpCounter() == markedBy) {
                    return true;
                }
                return false;
            case 1:
                if (current.getNodeRight() != null && current.getRightCounter() == markedBy) {
                    return true;
                }
                return false;
            case 2:
                if (current.getNodeDown() != null && current.getDownCounter() == markedBy) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    private boolean areAllMarked() {
        int ways = 0;
        int howManyClosed = 0;

        if (current.getNodeLeft() != null) {
            ways++;
            if (current.getLeftCounter() > 0)
                howManyClosed++;
        }
        if (current.getNodeRight() != null) {
            ways++;
            if (current.getRightCounter() > 0)
                howManyClosed++;
        }
        if (current.getNodeUp() != null) {
            ways++;
            if (current.getUpCounter() > 0)
                howManyClosed++;
        }
        if (current.getNodeDown() != null) {
            ways++;
            if (current.getDownCounter() > 0)
                howManyClosed++;
        }
        return ways == howManyClosed;
    }

    //findOne sluzy do wypisywania sciezki po znalezniu wyjscia
    private int findOne() {
        if (current.getLeftCounter() == 1) return -1;
        if (current.getUpCounter() == 1) return -2;
        if (current.getRightCounter() == 1) return 1;
        if (current.getDownCounter() == 1) return 2;
        return 0;
    }

    private int lastDirectionCounter() {
        switch (lastDirection) {
            case -1:
                if (current.getNodeRight() == null)
                    return 2;
                return current.getRightCounter();
            case -2:
                if (current.getNodeDown() == null)
                    return 2;
                return current.getDownCounter();
            case 1:
                if (current.getNodeLeft() == null)
                    return 2;
                return current.getLeftCounter();
            case 2:
                if (current.getNodeUp() == null)
                    return 2;
                return current.getUpCounter();
            default:
                return 2;
        }
    }
}
