package com.example.return_3.db;


/*
User entity which is used to store user information ()i.e. id, username, password and current balance
* */
public class User {
    private final int userId;
    private final String  username, password;
    private int coin, energy,maxEnergy, life,maxLife, exp,nextLevelExp, level,strength,dexterity, bullet,maxBullet;
    public User(int userId,String  username, String password,int coin,int energy,int maxEnergy,int life,int maxLife, int exp,int nextLevelExp,int level,int strength,int dexterity,int bullet,int maxBullet ){
        this.userId=userId;
        this.username=username;
        this.password=password;
        this.coin=coin;
        this.energy=energy;
        this.maxEnergy=maxEnergy;
        this.life=life;
        this.maxLife=maxLife;
        this.exp=exp;
        this.nextLevelExp=nextLevelExp;
        this.level=level;
        this.strength=strength;
        this.dexterity=dexterity;
        this.bullet=bullet;
        this.maxBullet=maxBullet;

    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCoin() {
        return coin;
    }

    public int getEnergy() {
        return energy;
    }

    public int getLife() {
        return life;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    public void setMaxBullet(int maxBullet) {
        this.maxBullet = maxBullet;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getBullet() {
        return bullet;
    }

    public int getMaxBullet() {
        return maxBullet;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
