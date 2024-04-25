package com.example.return_3.db;

import com.example.return_3.entity.Entity;
import com.example.return_3.interactiveTile.CuttableTree;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import com.example.return_3.monster.*;
import com.example.return_3.object.OBJ_Axe;
import com.example.return_3.object.OBJ_Potion_Red;
import com.example.return_3.object.OBJ_Shield_Wood;
import com.example.return_3.object.OBJ_Sword_Normal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//JDBC class is used to interact with our MYSQL database to perform activites such as retrieving and updatin our db
public class MyJDBC {
    //database configuration
    private static final String DB_URL ="jdbc:mysql://127.0.0.1:3306/game";
    private static final String DB_USERNAME= "root";
    private static  final String DB_PASSWORD="password";

    //if valid return an object with the user's information
    public static User validateLogin(String username, String password){
        try {
            //establish a connection to the database using configuration
            Connection connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            //create sql query
            PreparedStatement preparedStatement= connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password= ?"
            );
            //replace the ? with values
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //execute query and store into a result set
            ResultSet resultSet = preparedStatement.executeQuery();

            //next() return true or false
            //true - query returned data and result set now points to the first row
            // false - query returned no dat and reset set equals to null
            if(resultSet.next()){
                //success
                //get id
                int userId=resultSet.getInt("user_id");

                //get others properties.
//                int coin= resultSet.getInt("coin");
//                int energy= resultSet.getInt("energy");
//                int maxEnergy= resultSet.getInt("maxEnergy");
//                int life= resultSet.getInt("life");
//                int maxLife= resultSet.getInt("maxLife");
//                int exp= resultSet.getInt("exp");
//                int nextLevelExp= resultSet.getInt("nextLevelExp");
//                int level= resultSet.getInt("level");
//                int strength= resultSet.getInt("strength");
//                int dexterity= resultSet.getInt("dexterity");
//                int bullet= resultSet.getInt("bullet");
//                int maxBullet= resultSet.getInt("maxBullet");
//                int playerRow= resultSet.getInt("p_row");
//                int playerCol= resultSet.getInt("p_col");
//                boolean canTouchEvent= resultSet.getBoolean("can_touch_event");
//                //BigDecimal currentBalance= resultSet.getBigDecimal("current_balance");
//                //BigDecimal currentBalance= resultSet.getBigDecimal("current_balance");
//                //return user object
//                return  new User(userId,username,password,playerCol,playerRow,canTouchEvent,coin,energy,maxEnergy,life,maxLife,exp,nextLevelExp,level,strength,dexterity,bullet,maxBullet);
                return getUserProperties(userId,username,password,resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //not valid user
        return  null;
    }
    private static User getUserProperties(int userId, String username, String password, ResultSet resultSet) throws SQLException {
        int coin= resultSet.getInt("coin");
        int energy= resultSet.getInt("energy");
        int maxEnergy= resultSet.getInt("maxEnergy");
        int life= resultSet.getInt("life");
        int maxLife= resultSet.getInt("maxLife");
        int exp= resultSet.getInt("exp");
        int nextLevelExp= resultSet.getInt("nextLevelExp");
        int level= resultSet.getInt("level");
        int strength= resultSet.getInt("strength");
        int dexterity= resultSet.getInt("dexterity");
        int bullet= resultSet.getInt("bullet");
        int maxBullet= resultSet.getInt("maxBullet");
        int worldX= resultSet.getInt("world_x");
        int worldY= resultSet.getInt("world_y");
        //BigDecimal currentBalance= resultSet.getBigDecimal("current_balance");
        //BigDecimal currentBalance= resultSet.getBigDecimal("current_balance");
        //return user object
        return  new User(userId,username,password,worldX,worldY,coin,energy,maxEnergy,life,maxLife,exp,nextLevelExp,level,strength,dexterity,bullet,maxBullet);
    }

    public static User getUserData(int userId) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE user_id = ?"
            );
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve user data from the result set
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
//                int coin = resultSet.getInt("coin");
//                int energy = resultSet.getInt("energy");
//                int maxEnergy = resultSet.getInt("maxEnergy");
//                int life = resultSet.getInt("life");
//                int maxLife = resultSet.getInt("maxLife");
//                int exp = resultSet.getInt("exp");
//                int nextLevelExp = resultSet.getInt("nextLevelExp");
//                int level = resultSet.getInt("level");
//                int strength = resultSet.getInt("strength");
//                int dexterity = resultSet.getInt("dexterity");
//                int bullet = resultSet.getInt("bullet");
//                int maxBullet = resultSet.getInt("maxBullet");
//
//                // Create and return a User object
//                return new User(userId, username, password, coin, energy, maxEnergy, life, maxLife, exp, nextLevelExp,
//                        level, strength, dexterity, bullet, maxBullet);
                return getUserProperties(userId, username, password, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found or error occurred
    }

    //true - register success
    //false - register failed
    public static int register(String username, String password) {
        try {
            if (!checkUser(username)) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users(username,password)" + "VALUES(? , ?)", Statement.RETURN_GENERATED_KEYS
                );
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();

                // Retrieve the auto-generated user ID
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the user ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Registration failed
    }


    //check if username already exists in the db
    //true - user exists
    //false - user doesn't exist
    private  static boolean checkUser(String username){
        try{
            Connection connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement preparedStatement= connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ?"
            );
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            //this means that the query returned no data meaning that the username is available
            if(!resultSet.next()){
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    //Update users table before exit the game.
    public static void updateUser(Entity user) {
        try {
            // Establish a connection to the database using configuration
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET coin = ?, energy = ?, maxEnergy = ?, life = ?, maxLife = ?, " +
                            "exp = ?, nextLevelExp = ?, level = ?, strength = ?, dexterity = ?, " +
                            "bullet = ?, maxBullet = ?, world_x = ?, world_y = ? WHERE user_id = ?"
            );
            //, can_touch_event = ?

            // Replace the placeholders with values
            preparedStatement.setInt(1, user.coin);
            preparedStatement.setInt(2, user.energy);
            preparedStatement.setInt(3, user.maxEnergy);
            preparedStatement.setInt(4, user.life);
            preparedStatement.setInt(5, user.maxLife);
            preparedStatement.setInt(6, user.exp);
            preparedStatement.setInt(7, user.nextLevelExp);
            preparedStatement.setInt(8, user.level);
            preparedStatement.setInt(9, user.strength);
            preparedStatement.setInt(10, user.dexterity);
            preparedStatement.setInt(11, user.mana);
            preparedStatement.setInt(12, user.maxMana);
            preparedStatement.setInt(13, user.worldX);
            preparedStatement.setInt(14, user.worldY);
            //preparedStatement.setBoolean(15, user.can_touch_event);
            preparedStatement.setInt(15, user.playerId); // user_id for WHERE clause

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User data updated successfully.");
            } else {
                System.out.println("No user found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //////For INVENTORY
//we i need to check this method once again because there might be one operation and i did multiple for not knowing the
    public static ArrayList<Entity> getUserInventory(int userId) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Inventory WHERE user_id = ?"
            );
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Entity> inventory = new ArrayList<>();
            while (resultSet.next()) {
                int itemCode = resultSet.getInt("itemCode");
                int count = resultSet.getInt("count");
                for(int i=0;i<count;i++){
                inventory.add(new UtilityTool().inventoryItem(itemCode,count));
                }
            }
            return inventory;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateInventory(int userId, int itemCode, int count) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Inventory (user_id, itemCode, count) VALUES (?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE count = VALUES(count)"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, itemCode);
            preparedStatement.setInt(3, count);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // <----------------OBJECT LINE------------------->
    //in this addInteractivetile  we will add tile by this method at the time of sign up .
    //when user will create then the interactive tile will also be created


    public static void addObject(int userId, int objectType, int itemCode, int col, int row, int mapNum) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Objects (user_id, object_type, item_code, tile_col, tile_row, map_num) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, objectType);
            preparedStatement.setInt(3, itemCode);
            preparedStatement.setInt(4, col);
            preparedStatement.setInt(5, row);
            preparedStatement.setInt(6, mapNum);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



















    public static void setObjects(int userId, int mapNum) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT tile_col, tile_row, item_code,object_type FROM Objects " +
                            "WHERE user_id = ? AND map_num = ? AND destroyed = FALSE"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, mapNum);
           // preparedStatement.setInt(3, objectType);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            int oIndex=0;
            int count =0;
            // Iterate over the result set and create instances of objects based on type
            while (resultSet.next()) {
                System.out.println("count: "+count);

                int itemCode= resultSet.getInt("item_code");
                int col = resultSet.getInt("tile_col");
                int row = resultSet.getInt("tile_row");
                int objectType = resultSet.getInt("object_type");

                // Create instance of object based on the type retrieved from the database
                if (objectType == Game.gameInstance.type_interactiveTIle) {
                    // Interactive tile
                    CuttableTree cuttableTree = new CuttableTree(Game.gameInstance, col, row);
                    Game.gameInstance.iTile[mapNum][i] = cuttableTree;
                    i++;
                    System.out.println("tile number " + i  );
                } else if (objectType == 20) {
                    // Monster
                    // Create instance of Monster
                } else if (objectType == Game.gameInstance.type_object) {
                    Entity entity;
                    switch (itemCode){
                        case 101:   entity =new OBJ_Sword_Normal(Game.gameInstance);break;
                        case 102:   entity =new OBJ_Axe(Game.gameInstance);break;
                        case 103:   entity =new OBJ_Shield_Wood(Game.gameInstance);break;
                        case 303:   entity =new OBJ_Potion_Red(Game.gameInstance);break;
                        default:entity = null;
                    }
                    if(entity!=null){
                        Game.gameInstance.obj[mapNum][oIndex]=entity;
                        Game.gameInstance.obj[mapNum][oIndex].worldX=Game.gameInstance.tileSize*col;
                        Game.gameInstance.obj[mapNum][oIndex].worldY=Game.gameInstance.tileSize*row;
                    }
                    oIndex++;
                    System.out.println("object number " + oIndex  );
                    // Object
                    // Create instance of Object using itemCode
                }
                count++;

            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public static void updateObjectDestroyedStatus(int userId, int mapNum, int row, int col, int objectType, boolean destroyed) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Objects SET destroyed = ? " +
                            "WHERE user_id = ? AND map_num = ? AND tile_row = ? AND tile_col = ? AND object_type = ?"
            );
            preparedStatement.setBoolean(1, destroyed);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, mapNum);
            preparedStatement.setInt(4, row);
            preparedStatement.setInt(5, col);
            preparedStatement.setInt(6, objectType);
            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Object data updated successfully.");
            } else {
                System.out.println("No object found with the given data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



////  <------------  MONSTER
public static void addMonster(int userId, int monsterType, int areaType, int col, int row, int mapNum, int indexNum) {
    try {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Monsters (user_id, monster_type, area_type, tile_col, tile_row, indexNum, map_num) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, monsterType);
        preparedStatement.setInt(3, areaType);
        preparedStatement.setInt(4, col);
        preparedStatement.setInt(5, row);
        preparedStatement.setInt(6, indexNum);
        preparedStatement.setInt(7, mapNum);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
//public static void addMonster(int userId, int monsterType, int areaType, int col, int row, int mapNum) {
//    try {
//        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                "INSERT INTO Monsters (user_id, monster_type, area_type, tile_col, tile_row, map_num) " +
//                        "VALUES (?, ?, ?, ?, ?, ?)"
//        );
//        preparedStatement.setInt(1, userId);
//        preparedStatement.setInt(2, monsterType);
//        preparedStatement.setInt(3, areaType);
//        preparedStatement.setInt(4, col);
//        preparedStatement.setInt(5, row);
//        preparedStatement.setInt(6, mapNum);
//        preparedStatement.executeUpdate();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}

//    public static void setMonsters(int userId, int mapNum) {
//        Game game=Game.gameInstance;
//        Entity entity00= new Entity(game);
//        try {
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT tile_col, tile_row, area_type, monster_type FROM Monsters " +
//                            "WHERE user_id = ? AND map_num = ? AND destroyed = FALSE"
//            );
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, mapNum);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            int i = 0; // Index for MONSTER
////            int mIndex = 0; // Index for monsters
//            // Iterate over the result set and create instances of monsters
//            while (resultSet.next()) {
//                int areaType = resultSet.getInt("area_type");
//                int col = resultSet.getInt("tile_col");
//                int row = resultSet.getInt("tile_row");
//                int monsterType = resultSet.getInt("monster_type");
//
//                    Entity monster=null;
//                 if(monsterType == entity00.type_pacman){
//                     monster= new Mon_Pac(game);
//                 }else if(monsterType == entity00.type_spider){
//                     monster= new Mon_Spider(game);
//                 }else if(monsterType == entity00.type_arc){
//                     monster= new Mon_ORC(game);
//                 }else if(monsterType == entity00.type_slime){
//                     monster= new Mon_Green(game);
//                 }
//                    // Place the monster in the game world
//                    if (monster != null) {
//                        Game.gameInstance.monster[mapNum][i] = monster;
//                        Game.gameInstance.monster[mapNum][i].worldX = Game.gameInstance.tileSize * col;
//                        Game.gameInstance.monster[mapNum][i].worldY = Game.gameInstance.tileSize * row;
//                        System.out.println("monster is not null");
//                    i++; // Increment the monster index
//                    }
//
//            }
//
//            // Close resources
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void setMonsters(int userId, int mapNum) {
        Game game = Game.gameInstance;
        Entity entity00 = new Entity(game);
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT tile_col, tile_row, area_type, monster_type, indexNum FROM Monsters " +
                            "WHERE user_id = ? AND map_num = ? AND destroyed = FALSE"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, mapNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            //int i = 0; // Index for MONSTER
            // Iterate over the result set and create instances of monsters
            while (resultSet.next()) {
                int areaType = resultSet.getInt("area_type");
                int col = resultSet.getInt("tile_col");
                int row = resultSet.getInt("tile_row");
                int monsterType = resultSet.getInt("monster_type");
                int indexNum = resultSet.getInt("indexNum");

                Entity monster = null;
                // Determine the type of monster and create an instance accordingly
                if (monsterType == entity00.type_pacman) {
                    monster = new Mon_Pac(game,areaType);
                } else if (monsterType == entity00.type_spider) {
                    monster = new Mon_Spider(game,areaType);
                } else if (monsterType == entity00.type_arc) {
                    monster = new Mon_ORC(game,areaType);
                } else if (monsterType == entity00.type_slime) {
                    monster = new Mon_Green(game,areaType);
                } else if (monsterType == entity00.type_worm) {
                    monster = new Mon_Worm(game,areaType);
                }else if (monsterType == entity00.type_pacmanGreen) {
                    monster = new Mon_PacGreen(game,areaType);
                }else if (monsterType == entity00.type_blueGhost) {
                    monster = new Mon_BlueGhost(game,areaType);
                }

                // Place the monster in the game world
                if (monster != null) {
                    Game.gameInstance.monster[mapNum][indexNum] = monster;
                    Game.gameInstance.monster[mapNum][indexNum].worldX = Game.gameInstance.tileSize * col;
                    Game.gameInstance.monster[mapNum][indexNum].worldY = Game.gameInstance.tileSize * row;
                    System.out.println("Monster is not null");
                }
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMonsterDestroyedStatus(int userId, int indexNum, int monsterType, int mapNum, boolean destroyed) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Monsters SET destroyed = ? " +
                            "WHERE user_id = ? AND indexNum = ? AND monster_type = ? AND map_num = ?"
            );
            preparedStatement.setBoolean(1, destroyed);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, indexNum);
            preparedStatement.setInt(4, monsterType);
            preparedStatement.setInt(5, mapNum);
            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Monster data updated successfully.");
            } else {
                System.out.println("No monster found with the given data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void updateMonsterDestroyedStatus(int userId, int mapNum, int row, int col, int monsterType, boolean destroyed) {
//        try {
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "UPDATE Monsters SET destroyed = ? " +
//                            "WHERE user_id = ? AND map_num = ? AND tile_row = ? AND tile_col = ? AND monster_type = ?"
//            );
//            preparedStatement.setBoolean(1, destroyed);
//            preparedStatement.setInt(2, userId);
//            preparedStatement.setInt(3, mapNum);
//            preparedStatement.setInt(4, row);
//            preparedStatement.setInt(5, col);
//            preparedStatement.setInt(6, monsterType);
//            // Execute the update query
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Monster data updated successfully.");
//            } else {
//                System.out.println("No monster found with the given data.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void saveMonsterPositions(int userId, int mapNum, Entity[][] monsters) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Monsters SET tile_col = ?, tile_row = ? WHERE user_id = ? AND map_num = ? AND indexNum = ?"
            );

            // Iterate over the monsters array and update their positions in the database
            for (int i = 0; i < monsters[mapNum].length; i++) {
                if (monsters[mapNum][i] != null) {
                    preparedStatement.setInt(1, monsters[mapNum][i].worldX / Game.gameInstance.tileSize);
                    preparedStatement.setInt(2, monsters[mapNum][i].worldY / Game.gameInstance.tileSize);
                    preparedStatement.setInt(3, userId);
                    preparedStatement.setInt(4, mapNum);
                    preparedStatement.setInt(5, i + 1); // Assuming indexNum starts from 1
                    preparedStatement.addBatch();
                }
            }

            // Execute batch update
            preparedStatement.executeBatch();
            System.out.println("Monster positions saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}