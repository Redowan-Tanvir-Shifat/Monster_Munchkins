# Game Name: [Yet to be decided]

## Description
The game is an adventure RPG where players register and login to explore a map. The game loop starts once logged in, and players navigate through various buildings on the map, including School, Game Center, Shop, and hidden areas with monsters.

### Buildings:
1. **School Building:** Players gain knowledge by visiting the school, where they can access resources for different subjects, take quizzes, and earn experience points (XP).
2. **Game Center Building:** Players can pause the game loop and access various solo and multiplayer games, integrating socket concepts for multiplayer games.
3. **Shop Building:** Players can buy and sell various items like axes, clothing, lamps, etc.

### Hidden Areas:
Players can unlock hidden areas by reaching certain levels. These areas contain dangerous monsters for players to encounter.

## Technologies Used
- JavaFX
- Thread
- Collections
- Socket

## Screenshots
[Insert screenshots or GIFs demonstrating gameplay here]

## Features
- Registration and login system
- Map exploration
- School building with educational resources and quizzes
- Game center with solo and multiplayer games
- Shop for buying and selling items
- Hidden areas with monsters
- Leveling system to unlock new areas

## Installation
[Provide installation instructions here]

## Usage
[Explain how to play the game here]

## Code Structure
[Describe the structure of your codebase here]

## Future Improvements
- Adding more buildings and areas to explore
- Introducing more educational content in the school building
- Expanding the variety of games in the game center
- Implementing more complex monster encounters in hidden areas

## Contributors
- [Asadullah Imran](https://github.com/Asadullah-Imran)
- [Tanvir Shifat](https://github.com/Redowan-Tanvir-Shifat)
- [Tawfiqur Rahman](https://github.com/Tawfiq-Rahman)

## License
[Choose an appropriate open-source license]

## Acknowledgements
[If any resources or help were used, acknowledge them here]

## Contact
[Provide contact information for the team here]



















# return 3;

## Team Members


# Learning System


# Game Center

## Solo Game
- Nebula fighter.
- snake game
- puzzle game


## Multiplayer Games

- Tic-tac-toe
- ping pong
- tank fight




## Explanation of whole of our code is written here:










## Database Code:
1. create Database
```roomsql
CREATE SCHEMA `game` ;
```
2. create table `users` in `game` database
```roomsql
CREATE TABLE `game`.`users` (
    id INT NOT NULL AUTO_INCREMENT,
   `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));
```