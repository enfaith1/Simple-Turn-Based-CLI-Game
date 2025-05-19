# EnemySlayer

EnemySlayer is a simple, text-based, turn-based battle game written in Java. Face off against a bot in a battle of luck and tactics! Attack, stun, or skip your turn as you try to reduce your opponent's HP to zero before they do the same to you.

---

## Table of Contents

- [Features](#features)
- [Game Mechanics](#game-mechanics)
- [How to Play](#how-to-play)
- [Example Gameplay](#example-gameplay)
- [Requirements](#requirements)
- [How to Run](#how-to-run)
- [Credits](#credits)

---

## Features

- **Turn-Based Combat:** Alternate turns between you and the bot.
- **Attack, Stun, or Skip:** Choose your action each turn.
- **Stun Cooldown:** Prevents stun abuse, adding a layer of strategy.
- **HP Backtrack:** The bot can sometimes instantly heal after being attacked.
- **ASCII Art:** Fun ASCII art for the title, victory, and defeat screens.

---

## Game Mechanics

### Turn Order
- The game alternates turns between the player and the bot.
- You choose who goes first from the main menu.

### Player Actions
On your turn, you can choose one of the following actions:
- **ATTACK:**  
  - Deal random damage (1-10) to the bot.
  - After attacking, there is a 25% chance the bot will instantly heal back the damage you dealt (HP Backtrack).
- **STUN:**  
  - Attempt to stun the bot, skipping its next turn(s) and dealing extra damage.
  - Stun chances:
    - 30% chance to stun once (deal 1x damage, skip 1 bot turn).
    - 10% chance to stun twice (deal 2x damage, skip 2 bot turns).
    - 1% chance to stun thrice (deal 3x damage, skip 3 bot turns).
  - After a successful stun, the action goes on cooldown for the same number of turns as the stun duration.
  - If stun is on cooldown, you cannot use it until the cooldown expires.
- **SKIP:**  
  - Skip your turn without taking any action.
- **EXIT:**  
  - Exit the game immediately.

### Bot Actions
- On its turn, the bot always attacks, dealing random damage (1-10) to the player.

### Victory & Defeat
- The game ends when either the player or the bot’s HP drops below zero.
- ASCII art is displayed for victory or defeat.

### Additional Details
- **HP Backtrack:**  
  - After a player attack, the bot has a 25% chance to instantly heal the damage just dealt.
- **Stun Cooldown:**  
  - After using stun, you must wait for the cooldown to expire before using it again.
- **Input:**  
  - All actions are entered as text commands (not case-sensitive).

---

## How to Play

1. **Start the Game:**  
   Run the program. You'll be greeted with a menu:
   - Press `1` to start as the player.
   - Press `2` to let the bot take the first turn.
   - Press `3` to exit the game.

2. **Player's Turn:**  
   On your turn, type one of the following commands:
   - `ATTACK`
   - `STUN`
   - `SKIP`
   - `EXIT`

3. **Bot's Turn:**  
   The bot will attack you, dealing random damage.

4. **Winning and Losing:**  
   - If the bot's HP drops below zero, you win!
   - If your HP drops below zero, the bot wins.

---

## Example Gameplay

```
PLAYER'S TURN  
 U_U PLAYER HP: 100
 O_O BOT HP: 100

ENTER YOUR CHOICE: 
> ATTACK

You've dealt 7 true damage! The enemy has 93HP remaining!

O_O Enemy's Turn  
The enemy did 5 damage to you. Ouch.
```

---

## Requirements

- Java 8 or higher

---

## How to Run

1. Clone or download this repository.
2. Open a terminal and navigate to the project directory.
3. Compile the Java file:

   ```sh
   javac src/main/java/com/mycompany/turnbasedstack/TurnBasedStack.java
   ```

4. Run the game:

   ```sh
   java -cp src/main/java com.mycompany.turnbasedstack.TurnBasedStack
   ```

---

## Credits

Game created by enfaith

---
