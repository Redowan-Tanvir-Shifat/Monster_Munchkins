package com.example.return_3.db;

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

}