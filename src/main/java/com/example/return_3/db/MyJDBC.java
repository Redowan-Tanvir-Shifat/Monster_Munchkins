package com.example.return_3.db;

import com.example.return_3.entity.Entity;

import java.sql.*;

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
                //BigDecimal currentBalance= resultSet.getBigDecimal("current_balance");
                //BigDecimal currentBalance= resultSet.getBigDecimal("current_balance");
                //return user object
                return  new User(userId,username,password,coin,energy,maxEnergy,life,maxLife,exp,nextLevelExp,level,strength,dexterity,bullet,maxBullet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //not valid user
        return  null;
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
                int coin = resultSet.getInt("coin");
                int energy = resultSet.getInt("energy");
                int maxEnergy = resultSet.getInt("maxEnergy");
                int life = resultSet.getInt("life");
                int maxLife = resultSet.getInt("maxLife");
                int exp = resultSet.getInt("exp");
                int nextLevelExp = resultSet.getInt("nextLevelExp");
                int level = resultSet.getInt("level");
                int strength = resultSet.getInt("strength");
                int dexterity = resultSet.getInt("dexterity");
                int bullet = resultSet.getInt("bullet");
                int maxBullet = resultSet.getInt("maxBullet");

                // Create and return a User object
                return new User(userId, username, password, coin, energy, maxEnergy, life, maxLife, exp, nextLevelExp,
                        level, strength, dexterity, bullet, maxBullet);
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
    public static void updateUser(Entity user) {
        try {
            //establish a connection to the database using configuration
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            //create sql query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET coin = ?, energy = ?, maxEnergy = ?, life = ?, maxLife = ?, " +
                            "exp = ?, nextLevelExp = ?, level = ?, strength = ?, dexterity = ?, " +
                            "bullet = ?, maxBullet = ? WHERE user_id = ?"
            );

            //replace the placeholders with values
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
            preparedStatement.setInt(13, user.playerId); // user_id for WHERE clause

            //execute the update query
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

}