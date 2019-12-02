package labyrinth;

public class Node {

    private Node nodeUp;
    private Node nodeDown;
    private Node nodeLeft;
    private Node nodeRight;
    //countery do Tremauxa
    private int upCounter = 0;
    private int downCounter = 0;
    private int leftCounter = 0;
    private int rightCounter = 0;

    private int nodeValue;
    private boolean isExit;

    public int getNodeValue() {
        return nodeValue;
    }

    public int getUpCounter() {
        return upCounter;
    }

    public void riseUpCounter() {
        this.upCounter++;
    }

    public int getDownCounter() {
        return downCounter;
    }

    public void riseDownCounter() {
        this.downCounter++;
    }

    public int getLeftCounter() {
        return leftCounter;
    }

    public void riseLeftCounter() {
        this.leftCounter++;
    }

    public int getRightCounter() {
        return rightCounter;
    }

    public void riseRightCounter() {
        this.rightCounter++;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public Node getNodeUp() {
        return nodeUp;
    }

    public void setNodeUp(Node nodeUp) {
        this.nodeUp = nodeUp;
    }

    public Node getNodeDown() {
        return nodeDown;
    }

    public void setNodeDown(Node nodeDown) {
        this.nodeDown = nodeDown;
    }

    public Node getNodeLeft() {
        return nodeLeft;
    }

    public void setNodeLeft(Node nodeLeft) {
        this.nodeLeft = nodeLeft;
    }

    public Node getNodeRight() {
        return nodeRight;
    }

    public void setNodeRight(Node nodeRight) {
        this.nodeRight = nodeRight;
    }

    public boolean isConnected(Node node) {
        if (nodeUp == node) {
            return true;
        }
        if (nodeRight == node) {
            return true;
        }
        if (nodeLeft == node) {
            return true;
        }
        if (nodeDown == node) {
            return true;
        }
        return false;
    }

    public Node[][] createGraph(char[][] labyrinth) {

        Node[][] graph = new Node[(labyrinth.length - 1) / 2][(labyrinth[0].length - 1) / 2];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = new Node();
                graph[i][j].nodeValue = i * graph[0].length + j + 1;
            }
        }

        for (int i = 1; i < labyrinth.length; i += 2) {
            for (int j = 1; j < labyrinth[1].length; j += 2) {
                //lewy
                if (labyrinth[i][j - 1] == '0') {
                    graph[(i - 1) / 2][(j - 1) / 2].setNodeLeft(graph[(i - 1) / 2][(j - 3) / 2]);
                }
                //prawy
                if (labyrinth[i][j + 1] == '0') {
                    graph[(i - 1) / 2][(j - 1) / 2].setNodeRight(graph[(i - 1) / 2][(j + 1) / 2]);
                }
                //gora
                if (labyrinth[i - 1][j] == '0') {
                    graph[(i - 1) / 2][(j - 1) / 2].setNodeUp(graph[(i - 3) / 2][(j - 1) / 2]);
                }
                //dol
                if (labyrinth[i + 1][j] == '0') {
                    graph[(i - 1) / 2][(j - 1) / 2].setNodeDown(graph[(i + 1) / 2][(j - 1) / 2]);
                }
            }
        }

        for (int i = 1; i < labyrinth[0].length; i += 2) {
            if (labyrinth[0][i] == '#') graph[0][(i - 1) / 2].setExit(true);
            if (labyrinth[labyrinth.length - 1][i] == '*') graph[graph.length - 1][(i - 1) / 2].setExit(true);
        }
        return graph;
    }

    public Node findStart(Node[][] graph) {
        for (int i = 0; i < graph[0].length; i++) {
            if (graph[0][i].isExit()) {
                graph[0][i].setExit(false);
                return graph[0][i];
            }
        }
        return null;
    }
}
