package com.example.return_3.db;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;

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
    public static boolean register(String username,String password){
        try{
            //first we will need to check if the username has already been taken
            if(!checkUser(username)){
                Connection connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
                PreparedStatement preparedStatement= connection.prepareStatement(
                        "INSERT INTO users(username,password)"+"VALUES(? , ?)"
                );
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);
                preparedStatement.executeUpdate();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();}
        return false;
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
    public static void updateUser(User user) {
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
            preparedStatement.setInt(1, user.getCoin());
            preparedStatement.setInt(2, user.getEnergy());
            preparedStatement.setInt(3, user.getMaxEnergy());
            preparedStatement.setInt(4, user.getLife());
            preparedStatement.setInt(5, user.getMaxLife());
            preparedStatement.setInt(6, user.getExp());
            preparedStatement.setInt(7, user.getNextLevelExp());
            preparedStatement.setInt(8, user.getLevel());
            preparedStatement.setInt(9, user.getStrength());
            preparedStatement.setInt(10, user.getDexterity());
            preparedStatement.setInt(11, user.getBullet());
            preparedStatement.setInt(12, user.getMaxBullet());
            preparedStatement.setInt(13, user.getWorldX());
            preparedStatement.setInt(14, user.getWorldY());
            //preparedStatement.setBoolean(15, user.can_touch_event);
            preparedStatement.setInt(15, user.getUserId()); // user_id for WHERE clause

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
                inventory.add(new UtilityTool().inventoryItem(itemCode));
                }
            }
            return inventory;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateInventory(int userId, String itemCode, int count) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Inventory (user_id, itemCode, count) VALUES (?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE count = VALUES(count)"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, itemCode);
            preparedStatement.setInt(3, count);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}