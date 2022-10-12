package creator.gameChar.monster.monsterAI;

public class Node {
    Node parent;
    public int  row;
    public int col;
    public int gCost;
    public int hCost;
    public int fCost;
    public boolean blocked;
    public boolean open;
    public boolean checked;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

}
