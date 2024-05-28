package com.example.return_3.ai;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

import java.util.ArrayList;

public class PathFinder {
    Game game;
    Node[][] node;
    ArrayList <Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList= new ArrayList<>();
    Node startNode,goalNode,currentNode;
    boolean goalReached= false;
    int step=0;
    public PathFinder(Game game){
        this.game=game;
        instantiateNodes();
    }

    public void instantiateNodes(){
        node= new Node[game.maxWorldCol][game.maxWorldRow];
        int col=0;
        int row=0;

        while(col<game.maxWorldCol&& row<game.maxWorldRow){
            node[col][row]=new Node(col,row);
            col++;
            if(col==game.maxWorldCol){
                col=0;
                row++;
            }
        }

    }


    public void resetNodes(){
        int col=0;
        int row=0;

        while(col<game.maxWorldCol&&row<game.maxWorldRow){
            //Reset opened ,checked and solid states
            node[col][row].open=false;
            node[col][row].checked=false;
            node[col][row].solid=false;
            col++;
            if(col==game.maxWorldCol){
                col=0;
                row++;
            }

        }
        //Reset other settings
        openList.clear();
        pathList.clear();
        goalReached=false;
        step=0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow){
        resetNodes();
        //set start node and goal node
        startNode= node[startCol][startRow];
        currentNode=startNode;
        goalNode= node[goalCol][goalRow];
        openList.add(currentNode);

        int col=0;
        int row=0;
        while(col<game.maxWorldCol&&row<game.maxWorldRow){
            //Set Solid Node
            //Check Tiles
            int tileNum= game.tileM.mapTileNum[game.currentMap][col][row];
            if(game.tileM.tile[tileNum].collision){
                node[col][row].solid=true;
            }
            //check Interactive Tile
            for(int i=0; i<game.iTile[game.currentMap].length;i++){
                if(game.iTile[game.currentMap][i]!=null && game.iTile[game.currentMap][i].destructible==true){
                    int itCol=game.iTile[game.currentMap][i].worldX/game.tileSize;
                    int itRow=game.iTile[game.currentMap][i].worldY/game.tileSize;
                    node[itCol][itRow].solid=true;
                }
            }

            //set COST
            getCost(node[col][row]);
            col++;
            if(col==game.maxWorldCol){
                col=0;
                row++;
            }
        }

    }

    public void getCost(Node node){
        //G COST
        int xDistance = Math.abs(node.col-startNode.col);
        int yDistance = Math.abs(node.row- startNode.row);
        node.gCost=xDistance+yDistance;
        //H COST
         xDistance = Math.abs(node.col-goalNode.col);
         yDistance = Math.abs(node.row- goalNode.row);
        node.hCost=xDistance+yDistance;

        //F COST
        node.fCost=node.gCost + node.hCost;
    }


    public boolean search(){
        while (goalReached==false && step < 500){
            int col = currentNode.col;
            int row = currentNode.row;

            //CHECK the current node
            currentNode.checked= true;
            openList.remove(currentNode);

            //open the up node
            if(row-1>=0){
                openNode(node[col][row-1]);
            }
            //open the left node
            if(col-1>=0){
                openNode(node[col-1][row]);
            }
            //open the down node
            if(row+1<game.maxWorldRow){
                openNode(node[col][row+1]);
            }
            //open the right node
            if(col+1<game.maxWorldCol){
                openNode(node[col+1][row]);
            }

            //Find the best node
            int bestNodeIndex=0;
            int bestNodefCost=999;

            for(int i=0; i<openList.size(); i++){

                //check if this node's F cost is better
                if(openList.get(i).fCost<bestNodefCost){
                    bestNodeIndex=i;
                    bestNodefCost=openList.get(i).fCost;
                }
                //if the  F cost is Equal , check the G Cost
                else if(openList.get(i).fCost==bestNodefCost){
                    if(openList.get(i).gCost<openList.get(bestNodeIndex).gCost){
                        bestNodeIndex=i;
                    }
                }
            }

            //IF there is no node in the openList ....end the loop
            if(openList.size()==0){
                break;
            }

            //After the loop., oopenList[bestNodeIndex] is the next step (=current node)
            currentNode = openList.get(bestNodeIndex);

            if(currentNode==goalNode){
                goalReached= true;
                trackThePath();
            }
            step++;

        }
        return goalReached;
    }

    public void openNode(Node node){
        if(node.open== false && node.checked == false && node.solid== false){
            node.open = true;
            node.parent= currentNode;
            openList.add(node);
        }
    }

    public void trackThePath(){
        Node current = goalNode;
        while(current!=startNode){
            pathList.add(0,current);
            current= current.parent;
        }
    }


}
