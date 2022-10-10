package creator.gameChar.monster.monsterAI;

import main.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    GamePanel gPanel;
    Node[][] nodes;
    List<Node> openList = new ArrayList<>();
    public List<Node> pathList = new ArrayList<>();
    Node startPoint;
    Node finishPoint;
    Node currentPoint;
    boolean finishPointReached = false;
    int step = 0;

    public PathFinder(GamePanel gPanel) {
        this.gPanel = gPanel;
        createNodes();
    }
    private void createNodes() {
        nodes = new Node[gPanel.getBigMapRow()][gPanel.getBigMapCol()];

        int row = 0;
        int col = 0;
        while (row < gPanel.getBigMapRow() && col < gPanel.getBigMapCol()) {
            nodes[row][col] = new Node(row, col);
            col++;
            if(col == gPanel.getBigMapCol()) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {
        int row = 0;
        int col = 0;
        while (row < gPanel.getBigMapRow() && col < gPanel.getBigMapCol()) {
            nodes[row][col].open = false;
            nodes[row][col].blocked = false;
            nodes[row][col].checked = false;

            col++;
            if(col == gPanel.getBigMapCol()) {
                col = 0;
                row++;
            }
        }
        openList.clear();
        pathList.clear();
        finishPointReached = false;
        step = 0;
    }

    public void setNodes(int startRow, int startCol, int finishRow, int finishCol) {
        resetNodes();
        startPoint = nodes[startRow][startCol];
        currentPoint = startPoint;
        finishPoint = nodes[finishRow][finishCol];
        openList.add(currentPoint);

        int row = 0, col = 0;
        while (row < gPanel.getBigMapRow() && col < gPanel.getBigMapCol()) {
            int tileType = gPanel.tileMng.getTileMap()[row][col];
            if(tileType == -1) {
                nodes[row][col].blocked = true;
            }
            else if(gPanel.tileMng.getTiles()[tileType].isImpassable()) {
                nodes[row][col].blocked = true;
            }
//            System.out.print(tileType + "" + nodes[row][col].blocked + " ");
            getCost(nodes[row][col]);
            col++;
            if(col == gPanel.getBigMapCol()) {
                col = 0;
                row++;
//                System.out.println();
            }
        }
    }
    private void getCost(Node node) {

        // g value
        int g_distanceX = Math.abs(node.col - startPoint.col);
        int g_distanceY = Math.abs(node.row - startPoint.row);
        node.gCost = g_distanceX + g_distanceY;
        // h value
        int h_distanceX = Math.abs(node.col - finishPoint.col);
        int h_distanceY = Math.abs(node.row - finishPoint.row);
        node.hCost = h_distanceX + h_distanceY;
        // f value
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        int prominentNodeIndex = 0;
        int prominentNodeFCost = Integer.MAX_VALUE;
        while (!finishPointReached && step < 500) {
            int col = currentPoint.col;
            int row = currentPoint.row;

            currentPoint.checked = true;
            openList.remove(currentPoint);

            // open up block
            if(row - 1 >= 0) {
                openNode(nodes[row-1][col]);
            }
            // open down block
            if(row + 1 < gPanel.getBigMapRow()) {
                openNode(nodes[row + 1][col]);
            }
            // open left block
            if(col - 1 >= 0) {
                openNode(nodes[row][col - 1]);
            }
            // open right block
            if(col + 1 < gPanel.getBigMapCol()) {
                openNode(nodes[row][col + 1]);
            }
            if(openList.isEmpty()) break;
            // compare f value, if f equal, compare g value
            for(int i = 0; i < openList.size(); i++) {
                if(openList.get(i).fCost < prominentNodeFCost) {
                    prominentNodeIndex = i;
                    prominentNodeFCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == prominentNodeFCost) {
                    if(openList.get(i).gCost < openList.get(prominentNodeIndex).gCost) {
                        prominentNodeFCost = i;
                    }
                }
            }

            currentPoint = openList.get(prominentNodeIndex);
            if(currentPoint == finishPoint) {
                finishPointReached = true;
                trackPath();
            }
            step++;
        }
        return finishPointReached;
    }

    private void openNode(Node node) {
        if(!node.open && !node.checked && !node.blocked) {
            node.open = true;
            node.parent = currentPoint;
            openList.add(node);
        }
    }

    private void trackPath() {
        Node cur = finishPoint;
        while (cur != startPoint) {
            pathList.add(0,cur);
            cur = cur.parent;
        }
    }
}
