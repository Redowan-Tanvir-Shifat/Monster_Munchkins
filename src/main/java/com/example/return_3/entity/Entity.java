package com.example.return_3.entity;

import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import com.example.return_3.object.OBJ_ChatBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Entity {
    Game game;

    // <----------VARIABLES---------->
    public String name;
    public int speed;
    public int worldX, worldY;
    public String direction = "down";
    public  int spriteNum = 1;
    public int chatNum = 1;
    public int itemCode;
    public  int itemCount;
    public int goalCol;
    public int goalRow;
    public int dialogueIndex = 0;
    public String[] dialogue =new String[20];
    public Entity attacker;
    public String knockBackDirection;


    //for space invaders
    public int posX,posY;



    // <----------IMAGE--------->
    public Image image1, image2, image3;
    public Image up1, up2, down1, down2, left1, left2, right1, right2;
    public Image attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;



    // <--------Boolean-------->
    public boolean collision = false;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean transparent = false;
    public boolean attacking = false;
    public boolean guarding  = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean chatOnStatus = false;
    public boolean knockBack = false;
    public boolean offBalance;

    //For SpaceInvadors
    public boolean destroyed=false;
    public boolean bottomTouched=false;



    // <---------COUNTER--------->
    public int spriteCounter = 0;
    public int chatCounter = 0;
    public int actionLookCounter=0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    public int knockBackCounter = 0;
    public int guardCounter = 0;
    public  int offBalanceCounter = 0;



    // <---------CHARACTER STATUS---------->
    public int maxLife;
    public int life;

    public int maxMana;
    public int mana;
    public  int ammo;



    // <------------CHARACTER ATTRIBUTES----------->
    public int playerId;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int energy;
    public int maxEnergy;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    public int defaultSpeed;
    public int motion1_duration;
    public int motion2_duration;



    // <----------ITEM ATTRIBUTES---------->
    public int attackValue;
    public int defenseValue;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public int maxInventorySize = 20;
    public String description = "";
    public int useCost;
    public int price;
    public int value;
    public int knockBackPower = 0;



    // <---------Type of All Element--------->
    public int type; // 0 for player, 1 for npc, 2 for monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_wizard = 2;
    public final int type_monster = 3;
    public final int type_axe = 4;
    public final int type_sword = 5;
    public final int type_shield = 6;
    public final int type_consumable = 7;
    public final int type_pickupOnly = 8;
    public final int type_obstacle = 9;

    // <---------Type of NPC--------->
    public int npc_area;
    public final int area_village=1;


  // <-------------COLLiSION------------->
    public Rectangle solidArea = new Rectangle(0,0,32,32);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX,solidAreaDefaultY;

 public UtilityTool uTool;

    public Entity(Game game){
        this.game=game;
         uTool=new UtilityTool();
    }
    //get entity properties
    public double getLeftX(){
        return  (worldX+solidArea.getX());
    }
    public double getRightX(){
        return (worldX+solidArea.getX()+solidArea.getWidth());
    }
    public double getTopY(){
        return worldY+solidArea.getY();
    }
    public double getBottomY(){
        return worldY+solidArea.getY()+solidArea.getHeight();
    }
    public int getCol(){
        return (int) ((worldX+solidArea.getX())/game.tileSize);
    }
    public int getRow(){
        return (int) ((worldY+solidArea.getY())/game.tileSize);
    }

// Return `TRUE` if you used the item and `FALSE` if you failed to use it
    public boolean use(Entity entity){
        return false;
    }
    public void interact(){}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for (int i=0; i<game.obj[0].length;i++){
            if(game.obj[game.currentMap][i]==null){
                game.obj[game.currentMap][i]=droppedItem;
                game.obj[game.currentMap][i].worldX=worldX; //DEAD MONSTERS X POSITION
                game.obj[game.currentMap][i].worldY=worldY; //DEAD MONSTERS Y POSITION
                break;
            }
        }
    }


    public int getXDistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYDistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }
    public int getTileDistance(Entity target) {
        int tileDistance = (getXDistance(target) + getYDistance(target)) / game.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target) {
        int goalCol = (int)(target.worldX + target.solidArea.getX()) / game.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target) {
        int goalRow = (int)(target.worldY + target.solidArea.getY()) / game.tileSize;
        return goalRow;
    }


    public void setAction(){}
    public void damageReaction(){}
    public void checkCollision(){
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        game.cChecker.checkTile(this);
        game.cChecker.checkPlayer(this);
//        gp.cChecker.checkObject(this,false); //its not player so its remain false
        game.cChecker.checkEntity(this,game.npc);
        game.cChecker.checkEntity(this,game.monster);
        game.cChecker.checkEntity(this,game.iTile);


    }
    public void update(){
        if (knockBack == true) {

            checkCollision();

            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (collisionOn == false) {

                switch (knockBackDirection) {
                    case "up":worldY -= speed; break;
                    case "down":worldY+= speed; break;
                    case "left":worldX -= speed; break;
                    case "right":worldX += speed; break;
                }
            }

            knockBackCounter++;
            if (knockBackCounter == 2) {  // KnockBack distance....
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }

        } else if (attacking == true) {
            attacking();
        } else {
            setAction();
            checkCollision();

            //if collisionOn is false then player can be able to move
            if(collisionOn == false){

                switch (direction){
                    case "up":worldY -= speed; break;
                    case "down":worldY+= speed; break;
                    case "left":worldX -= speed; break;
                    case "right":worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 24) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


        //MONSTER ATTACK ON PLAYER.
        boolean contactPlayer= game.cChecker.checkPlayer(this);
        if(this.type == type_monster && contactPlayer == true){
            damagePlayer(attack);
        }

        set_Area(); //


        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
        }

        if (offBalance == true) {
            offBalanceCounter++;
            if (offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }


        if(type==type_npc){
            int xDistance=Math.abs(worldX-game.player.worldX);
            int yDistance=Math.abs(worldY-game.player.worldY);
            int tileDistance=(xDistance+yDistance)/game.tileSize;

            if(tileDistance<3){
                chatOnStatus=true;
            }else{
                chatOnStatus=false;
            }


            if( chatOnStatus==true){
                chatCounter++;
                if (chatCounter <15 ) {
                    chatNum=1;


                } else if (chatCounter >=15&& chatCounter<30 ) {
                    chatNum=2;

                } else if (chatCounter >=30&& chatCounter<45 ) {
                    chatNum=3;

                } else if (chatCounter >=45&& chatCounter<60 ) {
                    chatNum=4;

                }else {
                    chatCounter = 0;
                }
            }

        }
    }


    public void checkShootOrNot(int rate, int shootInterval) {
        int i = new Random().nextInt(rate);
        if (i == 0 && projectile.alive == false && shotAvailableCounter == shootInterval) {
            projectile.set(worldX, worldY, direction, true, this);

            // Check vacancy...
            for (int ii = 0; ii < game.projectile[game.currentMap].length; ii++) {
                if (game.projectile[game.currentMap][ii] == null) {
                    game.projectile[game.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i > 50) {
                onPath = true;
            }
        }

    }

    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }

    }

    public void getRandomDirection() {
        actionLookCounter++;
        Random random= new Random();
        int actionLookCounterLimit = random.nextInt(50)+90;

        if(actionLookCounter > actionLookCounterLimit){   // for two seconds it means

            int i=random.nextInt(100)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
            if(i<=25){
                direction="up";
            }
            if(i>25&&i<=50){
                direction="down";
            }
            if(i>50&&i<=75){
                direction="left";
            }
            if(i>75&&i<=100){
                direction="right";
            }
            actionLookCounter=0;
        }
    }


    public String getOppositeDirection(String direction) {
        String oppositeDirection = "";
        switch (direction) {
            case "up" : oppositeDirection = "down"; break;
            case "down" : oppositeDirection = "up"; break;
            case "left" : oppositeDirection = "right"; break;
            case "right" : oppositeDirection = "left"; break;
        }
        return oppositeDirection;
    }


    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            // SAVE THE CURRENT DATA......
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = (int) solidArea.getWidth();
            int solidAreaHeight = (int) solidArea.getHeight();

            // ADJUST PLAYRER S WORLD X/Y FOR THE ATTACK AREA
            switch (direction) {
                case "up" : worldY -= (int) attackArea.getHeight(); break;
                case "down" : worldY += (int) attackArea.getHeight(); break;
                case "left" : worldX -= (int) attackArea.getWidth(); break;
                case "right" : worldX += (int) attackArea.getWidth(); break;
            }
            // ATTACK BECOME SOLID AREA
            solidArea.setWidth(attackArea.getWidth());
            solidArea.setHeight(attackArea.getHeight());


            if (type == type_monster) {
                if (game.cChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            }
            else { // Player
                //CHECK monster collision with the updated worldX, worldY and solidArea....
                int monsterIndex = game.cChecker.checkEntity(this, game.monster);
                game.player.damagedMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                //CHECK INTERACTIVE TILES COLLSION AND GET ATTACK
                int iTileIndex= game.cChecker.checkEntity(this,game.iTile);
                game.player.damageInteractiveTiles(iTileIndex);

                int projectileIndex = game.cChecker.checkEntity(this, game.projectile);
                game.player.damageProjectile(projectileIndex);
            }

            // After checking collision restore the original data...
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.setWidth(solidAreaWidth);
            solidArea.setHeight(solidAreaHeight);
        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {

        boolean targetInRange = false;
        int xDis = getXDistance(game.player);
        int yDis = getYDistance(game.player);

        switch (direction) {
            case "up":
                if (game.player.worldY < worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (game.player.worldY > worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (game.player.worldY < worldX && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (game.player.worldY > worldX && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
        }
        if (targetInRange == true) {
            // Check if it initiates an attack...
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public void damagePlayer(int attack){
        if (game.player.invincible == false) {
            int damage = attack - game.player.defense;

            // Get and opposite direction of an attacker...
            String canGuardDirection = getOppositeDirection(direction);
            if (game.player.guarding == true && game.player.direction.equals(canGuardDirection)) {
                // Parry : // if you parry 10 frame before monsters attack you can parry monster...if you increase the value the parry became easier...
                if (game.player.guardCounter < 10) {
                    damage = 0;
                    setKnockBack(this, game.player, game.player.currentShield.knockBackPower);
                    offBalance = true;
                    spriteCounter -= 60;
                }

                // Normal guard...
                damage /= 3;
            }
            else {
                // Not Guarding...
                if (damage < 0) {
                    damage = 0;
                }
            }

            if (damage != 0) {
                game.player.transparent = true;
                setKnockBack(game.player, this, knockBackPower);
            }

            game.player.life -= damage;
            game.player.invincible = true;

            if (game.player.life >= 0 && game.player.life <= 20) {
                game.ui.uiMainGame.addMessage( "Careful, " + game.player.life + " % Life left!");
            }
            if (game.player.life <= 0) {
                game.player.dying = true;
                game.player.setHospitalPosition();
                game.keyHandler.setBooleanAll(false);
                game.player.life = game.player.maxLife;
                if (game.player.coin >= 300) {
                    game.player.coin -= 300;
                }
                else {
                    game.player.coin = 0;
                }
            }
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public void draw(GraphicsContext gc){
        Image image= null;
        int screenX= worldX-game.player.worldX + game.player.screenX;
        int screenY= worldY-game.player.worldY + game.player.screenY;


        //this process is for not drawing the whole world map but the map tiles we needed only
        if(worldX+ game.tileSize>game.player.worldX-game.player.screenX&&
                worldX-game.tileSize<game.player.worldX+game.player.screenX&&
                worldY+ game.tileSize>game.player.worldY-game.player.screenY&&
                worldY- game.tileSize<game.player.worldY+game.player.screenY
        ){
            //temp variable
            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up":
                    if (attacking == false) {
                        if (spriteNum == 1){
                            //playerImageView.setImage(up1);
                            image = up1;
                        }
                        if(spriteNum == 2){
                            image = up2;
                        }
                    }
                    if (attacking == true) {
                        tempScreenY = screenY - game.tileSize;
                        if (spriteNum == 1){
                            image = attackUp1;
                        }
                        if(spriteNum == 2){
                            image = attackUp2;
                        }
                    }
                    break;
                case "down":
                    if (attacking == false) {
                        if (spriteNum == 1){

                            image = down1;
                        }
                        if(spriteNum == 2){
                            image = down2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1){

                            image = attackDown1;
                        }
                        if(spriteNum == 2){
                            image = attackDown2;
                        }
                    }
                    break;
                case "left":
                    if (attacking == false) {
                        if (spriteNum == 1){

                            image = left1;
                        }
                        if(spriteNum == 2){
                            image = left2;
                        }
                    }
                    if (attacking == true) {
                        tempScreenX = screenX - game.tileSize;
                        if (spriteNum == 1){
                            image = attackLeft1;
                        }
                        if(spriteNum == 2){
                            image = attackLeft2;
                        }
                    }
                    break;
                case "right":
                    if (attacking == false) {
                        if (spriteNum == 1){

                            image = right1;
                        }
                        if(spriteNum == 2){
                            image = right2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1){

                            image = attackRight1;
                        }
                        if(spriteNum == 2){
                            image = attackRight2;
                        }
                    }
                    break;

                // Handle other directions similarly
            }


            //Monster HP Bar......
            if (type == type_monster && hpBarOn == true) {
                double oneScale = (double)game.tileSize/maxLife;
                double hpBarValue = oneScale*life;


                gc.setFill(Color.RED);
                gc.fillRect(screenX, screenY - 5, (int)hpBarValue, 10);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1); // Width of the outline
                gc.strokeRect(screenX, screenY - 5, game.tileSize, 10);

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                gc.setGlobalAlpha(0.4);
            }
            if (dying == true) {
                dyingAnimation(gc);
            }

            gc.drawImage(image,tempScreenX,tempScreenY);

            //<<<<<RESET ALPHA>>>>>
            gc.setGlobalAlpha(1);




        }
    }

    public void dyingAnimation(GraphicsContext gc) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i && dyingCounter <= i*2) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*8) {
            alive = false;
        }
    }


    public void speak(){
        game.gameState = game.dialogueState;
        game.ui.uiMainGame.npc=this;
        game.ui.uiMainGame.currentDialogue=dialogue[dialogueIndex];
        // dialogueIndex++;
        switch (game.player.direction){
            case "up":
                direction="down";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;
            case "down":
                direction="up";
                break;
        }
    }


    public void set_Area(){ // we use different area here  for different position
        switch (npc_area){ //x is for map starting position and y is for map ending position
            //width and height give us the expected recatngular area for us
            case area_village:uTool.areaSetup(this,36*game.tileSize,133*game.tileSize,12*game.tileSize,4*game.tileSize);   ;break;
//            case area_village:        areaSetup(36*game.tileSize,133*game.tileSize,12*game.tileSize,4*game.tileSize);   ;break;
        }
    }

    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }

    // Method to restrict entity movement within a specified area
//    public void areaSetup(int x, int y, int width, int height) {
//        if (worldX < x) {
//            worldX = x; // Prevent entity from moving beyond the left boundary
//        } else if (worldX + game.tileSize > x + width) {
//            worldX = x + width - game.tileSize; // Prevent entity from moving beyond the right boundary
//        }
//
//        if (worldY < y) {
//            worldY = y; // Prevent entity from moving beyond the top boundary
//        } else if (worldY + game.tileSize > y + height) {
//            worldY = y + height - game.tileSize; // Prevent entity from moving beyond the bottom boundary
//        }
//    }

    public Color getParticleColor(){ //this indicates the color of the particle
         Color color=  null;
        return color;
    }
    public int getParticleSize(){// this indicates the size of the particle
        int size=0;
        return size;
    }
    public int getParticleSpeed(){ //this indicates the speed of the particle
        int speed=0;
        return speed;
    }

    public int getParticleMaxLife(){ //this indicates how long the particle will last
        int maxLife=0;
        return maxLife;
    }

    public void generateParticle(Entity generator,Entity target){
        //We will do it later
        Color color= generator.getParticleColor();
        int size= generator.getParticleSize();
        int speed= generator.getParticleSpeed();
        int maxLife= generator.getParticleMaxLife();



        //creating particle
        Particle p1= new Particle(game,target,color,size,speed,maxLife,-2,-1 );
        Particle p2= new Particle(game,target,color,size,speed,maxLife,2,-1 );
        Particle p3= new Particle(game,target,color,size,speed,maxLife,-2,1 );
        Particle p4= new Particle(game,target,color,size,speed,maxLife,2,1 );
        game.particleList.add(p1);
        game.particleList.add(p2);
        game.particleList.add(p3);
        game.particleList.add(p4);
    }

    public void searchPath(int goalCol, int goalRow){

        int startCol=(int)(worldX+solidArea.getX())/game.tileSize;
        int startRow=(int)(worldY+solidArea.getY())/game.tileSize;

        game.pFinder.setNodes(startCol, startRow, goalCol,goalRow);

        if(game.pFinder.search()==true){
            //Next worldX & worldY
            int nextX= game.pFinder.pathList.get(0).col *game.tileSize;
            int nextY= game.pFinder.pathList.get(0).row *game.tileSize;

            //Entity's solidArea position
            int enleftX=(int)(worldX + solidArea.getX());
            int enRightX=(int)(worldX + solidArea.getX()+solidArea.getWidth());
            int enTopY=(int)(worldY+solidArea.getY());
            int enBottomY=(int)(worldY+solidArea.getY()+solidArea.getHeight());

            if(enTopY>nextY && enleftX>=nextX && enRightX< nextX+game.tileSize){
                direction= "up";
            }else if(enTopY<nextY && enleftX>=nextX && enRightX< nextX+game.tileSize){
                direction= "down";
            }else if(enTopY>= nextY && enBottomY<nextY+game.tileSize){
                //left or right
                if(enleftX>nextX){
                    direction="left";
                }if(enleftX<nextX){
                    direction="right";
                }
            } else if (enTopY>nextY && enleftX> nextX) {
                //up or left
                direction="up";
                checkCollision();
                if(collisionOn==true){
                    direction="left";
                }
            } else if (enTopY > nextY && enleftX < nextX) {
                //up or right
                direction="up";
                checkCollision();
                if(collisionOn==true){
                    direction="right";
                }
            }else if(enTopY< nextY && enleftX>nextX) {
                //down or left
                direction="down";
                checkCollision();
                if(collisionOn==true){
                    direction="left";
                }
            }else if(enTopY< nextY && enleftX<nextX) {
                //down or right
                direction="down";
                checkCollision();
                if(collisionOn==true){
                    direction="right";
                }
            }
            int nextCol=game.pFinder.pathList.get(0).col;
            int nextRow=game.pFinder.pathList.get(0).row;

            if(nextCol==goalCol&& nextRow==goalRow){
                onPath=false;
            }

        }
    }

    public int getDetected(Entity user, Entity target[][], String targetName){
        int index= 999;
        //check the sourrending object
        double nextWorldX=user.getLeftX();
        double nextWorldY=user.getTopY();
        switch(user.direction){
            case "up":nextWorldY=user.getTopY()-3;break;
            case "down":nextWorldY=user.getBottomY()+1;break;
            case "left":nextWorldX=user.getLeftX()-1;break;
            case "right":nextWorldX=user.getRightX()+1;break;
        }
        int col= (int) (nextWorldX/game.tileSize);
        int row= (int)(nextWorldY/game.tileSize);

        for(int i=0;i<target[1].length;i++){
            if(target[game.currentMap][i]!=null){
                if(target[game.currentMap][i].getCol()==col &&
                target[game.currentMap][i].getRow()==row &&
                target[game.currentMap][i].name.equals(targetName)){
                    index=i;
                    break;
                }
            }
        }

        return index;
    }


}
