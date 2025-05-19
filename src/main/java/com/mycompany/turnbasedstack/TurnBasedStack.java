package com.mycompany.turnbasedstack;

import java.util.Scanner;
import java.util.Stack;
import java.util.Random;

public class TurnBasedStack {

    public static int playerHP = 100, botHP = 100, stunCooldown = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random randomizer = new Random();
        Stack<Integer> lastHP = new Stack<>(); // stack for bot HP
        int botDMG;
        int gameTimer = 1;
        System.out.println(" _____ _   _  ________  ____   __  _____ _       _____   _____________ \n"
                + "|  ___| \\ | ||  ___|  \\/  \\ \\ / / /  ___| |     / _ \\ \\ / /  ___| ___ \\\n"
                + "| |__ |  \\| || |__ | .  . |\\ V /  \\ `--.| |    / /_\\ \\ V /| |__ | |_/ /\n"
                + "|  __|| . ` ||  __|| |\\/| | \\ /    `--. \\ |    |  _  |\\ / |  __||    / \n"
                + "| |___| |\\  || |___| |  | | | |   /\\__/ / |____| | | || | | |___| |\\ \\ \n"
                + "\\____/\\_| \\_/\\____/\\_|  |_/ \\_/   \\____/\\_____/\\_| |_/\\_/ \\____/\\_| \\_|\n"
                + "                                                                       \n"
                + "                                                                        ");
        System.out.println("Game Created by: Natt\n");
        System.out.println("Enter your choice: ");
        System.out.println("PRESS 1 TO START AS PLAYER");
        System.out.println("PRESS 2 TO START BOT");
        System.out.println("PRESS 3 TO EXIT GAME");
        
        // user choice
        int mainMenuChoice = scanner.nextInt();
        scanner.nextLine();

        // EXIT 
        if (mainMenuChoice == 3) {
            exit();
        }
        
        switch (mainMenuChoice) {
            case 1: // start as player
                gameTimer = 1;
                break;
            case 2: // bot as first turn
                gameTimer = 2;
                break;
        }

        while (healthChecker()) {
            if (!isOddOrEven(gameTimer)) { // PLAYER'S TURN
                System.out.println("-------------------------");
                System.out.println("PLAYER'S TURN  ");
                System.out.println(" U_U PLAYER HP: " + playerHP);
                System.out.println(" O_O BOT HP: " + botHP);
                System.out.println("\nENTER YOUR CHOICE: ");
                System.out.println("> ATTACK");
                System.out.println("> STUN");
                System.out.println("> SKIP TURN");
                System.out.println("> EXIT");
                String input = scanner.nextLine();
                System.out.println("-------------------------");

                // EXIT 
                if (input.equalsIgnoreCase("exit")) {
                    exit();
                }

                // ATTACK
                if (input.equalsIgnoreCase("attack")) {
                    lastHP.push(botHP);
                    int playerDMG = randomizer.nextInt(10) + 1;
                    botHP = botHP - playerDMG;
                    lastHP.push(botHP);
                    System.out.println("\nYou've dealt " + playerDMG + " true damage! The enemy has " + botHP + "HP remaining!\n");

                    /*
                    HP BACKTRACK 
                    I coded the HP Backtrack as 25% chance of triggering. 
                     */
                    boolean backTrack = randomizer.nextInt(100) < 25; // 25% chance to backtrack

                    if (backTrack && !(playerDMG == 0)) {
                        lastHP.pop();
                        botHP = lastHP.peek();
                        System.out.println("The enemy healed itself. Talk about an HP backtrack!\n");
                        System.out.println("Enemy heals " + playerDMG + " of the lost HP. Enemy HP is currently at " + botHP + "!\n");
                    }
                    ++gameTimer;
                    continue;

                } // STUN 
                else if (input.equalsIgnoreCase("stun")) {
                    /*
                    STUN:
                    50% chance to stun the enemy, 25% chance to stun twice, 1% chance to stun thrice
                    I also integrated a stunCooldown so that the player cannot abuse the STUN.
                     */
                    if (stunCooldown > 0) {
                        System.out.println("Stun is on cooldown! " + stunCooldown + " turns remaining.\n");
                        ++gameTimer;
                        continue;
                    }
                    boolean stunOnce = randomizer.nextInt(100) < 30;
                    boolean stunTwice = randomizer.nextInt(100) < 10;
                    boolean stunThrice = randomizer.nextInt(100) < 1;
                    if (stunThrice) {
                        System.out.println("\nYou stunned the enemy THRICE! THAT IS A 1% CHANCE!!! CRAZY LUCK!!!");
                        lastHP.push(botHP);
                        int playerDMG = randomizer.nextInt(10) + 1;
                        playerDMG = playerDMG * 3;
                        botHP = botHP - playerDMG;
                        lastHP.push(botHP);
                        System.out.println("While the enemy was stunned, you attacked THRICE.");
                        System.out.println("You dealt " + playerDMG + " DMG while the enemy was stunned!\n");
                        stunCooldown = 3;
                        gameTimer += 3;
                    } else if (stunTwice) {
                        System.out.println("\nYou stunned the enemy TWICE! That's a 10% CHANCE of happening");
                        lastHP.push(botHP);
                        int playerDMG = randomizer.nextInt(10) + 1;
                        playerDMG = playerDMG * 2;
                        botHP = botHP - playerDMG;
                        lastHP.push(botHP);
                        System.out.println("While the enemy was stunned, you attacked TWICE.");
                        System.out.println("You dealt " + playerDMG + " DMG while the enemy was stunned TWICE!\n");
                        stunCooldown = 2;
                        gameTimer += 2;
                    } else if (stunOnce) {
                        System.out.println("\nYou stunned the enemy ONCE! That's a 30% CHANCE of happening!");
                        lastHP.push(botHP);
                        int playerDMG = randomizer.nextInt(10) + 1;
                        botHP = botHP - playerDMG;
                        lastHP.push(botHP);
                        System.out.println("While the enemy was stunned, you attacked ONCE.");
                        System.out.println("You dealt " + playerDMG + " DMG while the enemy was stunned ONCE!\n");
                        stunCooldown = 1;
                        gameTimer += 1;
                    } else {
                        System.out.println("\nStun attempt failed! Enemy resists!\n");
                    }
                    gameTimer++;
                    continue;

                } // SKIP
                else if (input.equalsIgnoreCase("skip")) {
                    System.out.println("\nYou skipped your turn! What an actual coward!\n");
                    ++gameTimer;
                    continue;
                }

                theEnd();

            } else { // start bot
                stunCdFunc();
                System.out.println("-------------------------\n");
                System.out.println("O_O Enemy's Turn  ");
                botDMG = randomizer.nextInt(10) + 1;
                playerHP = playerHP - botDMG;
                System.out.println("The enemy did " + botDMG + " damage to you. Ouch.\n");
                ++gameTimer;

                theEnd();
            }
        }
    }

    static void stunCdFunc() {
        if (stunCooldown > 0) {
            stunCooldown--;
        }
    }

    static void exit() {
        System.out.println("Exiting the game. Thank you for playing!");
        System.out.println(" _____ _   _  ________  ____   __  _____ _       _____   _____________ \n"
                + "|  ___| \\ | ||  ___|  \\/  \\ \\ / / /  ___| |     / _ \\ \\ / /  ___| ___ \\\n"
                + "| |__ |  \\| || |__ | .  . |\\ V /  \\ `--.| |    / /_\\ \\ V /| |__ | |_/ /\n"
                + "|  __|| . ` ||  __|| |\\/| | \\ /    `--. \\ |    |  _  |\\ / |  __||    / \n"
                + "| |___| |\\  || |___| |  | | | |   /\\__/ / |____| | | || | | |___| |\\ \\ \n"
                + "\\____/\\_| \\_/\\____/\\_|  |_/ \\_/   \\____/\\_____/\\_| |_/\\_/ \\____/\\_| \\_|\n"
                + "                                                                       \n"
                + "                                                                        ");
        
        System.exit(0);
    }

    static void theEnd() {
        // if player wins
        if (botHP < 0) {
            System.out.println("Enemy has 0 HP!\n");
            System.out.println("PLAYER WINS!");
            System.out.println("U_U");
            System.out.println("VICTORY!\n");
            System.out.println(" _____ _   _  ________  ____   __  _____ _       _____   _____________ \n"
                    + "|  ___| \\ | ||  ___|  \\/  \\ \\ / / /  ___| |     / _ \\ \\ / /  ___| ___ \\\n"
                    + "| |__ |  \\| || |__ | .  . |\\ V /  \\ `--.| |    / /_\\ \\ V /| |__ | |_/ /\n"
                    + "|  __|| . ` ||  __|| |\\/| | \\ /    `--. \\ |    |  _  |\\ / |  __||    / \n"
                    + "| |___| |\\  || |___| |  | | | |   /\\__/ / |____| | | || | | |___| |\\ \\ \n"
                    + "\\____/\\_| \\_/\\____/\\_|  |_/ \\_/   \\____/\\_____/\\_| |_/\\_/ \\____/\\_| \\_|\n"
                    + "                                                                       \n"
                    + "                                                                        ");
        }
        // if bot wins
        if (playerHP < 0) {
            System.out.println("You have 0HP!\n");
            System.out.println("BOT WINS!");
            System.out.println("O_O");
            System.out.println("Womp womp.");
            System.out.println(" _____ _       _____   _____________ \n"
                    + "/  ___| |     / _ \\ \\ / /  ___|  _  \\\n"
                    + "\\ `--.| |    / /_\\ \\ V /| |__ | | | |\n"
                    + " `--. \\ |    |  _  |\\ / |  __|| | | |\n"
                    + "/\\__/ / |____| | | || | | |___| |/ / \n"
                    + "\\____/\\_____/\\_| |_/\\_/ \\____/|___/  \n"
                    + "                                     \n"
                    + "                                     ");
        }
    }

    // odd or even checker for the gameTimer 
    static boolean isOddOrEven(int number) {
        return number % 2 == 0; // even number
    }

    static boolean healthChecker() {
        if (botHP < 0) {
            return false;
        } else if (playerHP < 0) {
            return false;
        } else {
            return true;
        }
    }
}
