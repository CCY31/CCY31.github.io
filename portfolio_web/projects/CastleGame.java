import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

//Create a record for players' names and scores
class CastleGamePlayer {
    String playerName;
    int score;
    boolean leave;
}

// Create a record for rooms
class CastleRoom

{
    String roomName;
    boolean hasTrap;
    boolean hasTreasure;
    int treasurePoints;
    int disableTrapPoints;
}

class CastleGameLevel7 {
    public static void main(String[] args) throws IOException {
        outerMethod7();
    }

    public static String askChoice() {
        String choice;
        System.out.println("Do you what searching the room, taking any object found or leaving the room?");
        choice = askQuestionStr(
                "(type 1 for searching the room , type 2 for taking any object found, type 3 for disarming a trap and type 4 for leaving the room)");
        while (!checkForBounds(choice, 1, 4)) {
            System.out.println("invalid input");
            choice = askQuestionStr(
                    "(type 1 for searching the room , type 2 for taking any object found, type 3 for disarming a trap and type 4 for leaving the room)");
        }
        return choice;
    } // END askChoice

    // Return the value in string
    public static String askQuestionStr(String question) {
        String value;
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        value = scanner.nextLine();
        return value;
    }

    // Return the value in integer
    public static int askQuestionInt(String question) {
        int value;
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        value = Integer.parseInt(scanner.nextLine());
        return value;
    }

    // Check whether the value is between bounds
    public static boolean checkForBounds(String input, int min, int max) {
        int x;
        try {
            x = Integer.parseInt(input);
        }

        catch (Exception err) {
            return false;
        }
        if (x < min || x > max) {
            return false;
        }
        return true;
    }

    public static String getCastleGamePlayerName(CastleGamePlayer player) {
        return player.playerName;
    }

    public static int getCastleGameScore(CastleGamePlayer player) {
        return player.score;
    }

    public static boolean getCastleGameLeaving(CastleGamePlayer player) {
        return player.leave;
    }

    public static String getCastleGameRoomName(CastleRoom room) {
        return room.roomName;
    }

    public static boolean getCastleGameHasTrap(CastleRoom room) {
        return room.hasTrap;
    }

    public static boolean getCastleGameHasTreasure(CastleRoom room) {
        return room.hasTreasure;
    }

    public static int getCastleGameTreasurePoints(CastleRoom room) {
        return room.treasurePoints;
    }

    public static int getCastleGameDisableTrapPoints(CastleRoom room)

    {
        return room.disableTrapPoints;
    }

    public static void setCastleGamePlayerName(CastleGamePlayer player, String playerName) {
        player.playerName = playerName;
        return;
    }

    public static void setCastleGameScore(CastleGamePlayer player, int score) {
        player.score = score;
        return;
    }

    public static void setCastleGameLeave(CastleGamePlayer player, boolean leave) {
        player.leave = leave;
        return;
    }

    public static void setCastleGameRoomName(CastleRoom room, String roomName) {
        room.roomName = roomName;
        return;
    }

    public static void setCastleGameHasTrap(CastleRoom room, boolean hasTrap) {
        room.hasTrap = hasTrap;
        return;
    }

    public static void setCastleGameHasTreasure(CastleRoom room, boolean hasTreasure) {
        room.hasTreasure = hasTreasure;
        return;
    }

    public static void setCastleGameTreasurePoints(CastleRoom room, int treasurePoints) {
        room.treasurePoints = treasurePoints;

        return;
    }

    public static void setCastleGameDisableTrapPoints(CastleRoom room, int disableTrapPoints) {
        room.disableTrapPoints = disableTrapPoints;
        return;
    }

    // To ask what to do in the room
    public static String askRoom(String name, int len) {
        String choice;
        System.out.println(name + ", it is your turn. You are in a giant hall.");
        choice = askQuestionStr("There are " + len + " rooms in front of you. Which room do you want to go? (type 1-"
                + len + ", and q to quit, s to save):");
        if (choice.equals("q") || choice.equals("s")) {
            return choice;
        }
        while (!checkForBounds(choice, 1, len)) {
            System.out.println("invalid input");
            choice = askQuestionStr(
                    "There are " + len + " rooms in front of you. Which room do you want to go? (type 1-" + len
                            + ", and q to quit, s to save): ");
            if (choice.equals('q'))
                return choice;
        }
        return choice;
    }

    // Print out which room the player are
    public static void printSurroundings(CastleRoom room) {
        System.out.println("You are in " + getCastleGameRoomName(room) + ".");
        return;
    }

    // Give the player a score randomly
    public static int getPoint(int max) {
        Random bigdice = new Random();
        int diceroll = bigdice.nextInt(max) + 1;

        return diceroll;
    }

    // Tell the player whether they find the cup or touch the trap
    public static int findCup(CastleRoom room, int score) {
        if (getCastleGameHasTrap(room) == false) {
            if (getCastleGameHasTreasure(room) == true) {
                int point = getCastleGameTreasurePoints(room);
                System.out.println("You find a cup and get " + point + " point(s).");
                score = score + point;
                setCastleGameHasTreasure(room, false);
            } else {
                System.out.println("You didn't find anything.");
            }
        } else {
            System.out.println("It's a trap!");
            score = 0;
        }
        return score;
    }

    // If the player choose to disarm the trap, they will lose points
    public static int disableTrap(CastleRoom room, int score) {
        int point = getCastleGameDisableTrapPoints(room);
        System.out.println("You disarm the trap and it costs you " + point + " point(s).");
        score = score - point;
        setCastleGameHasTrap(room, false);
        return score;
    }

    // Print how many score they have
    public static void printScore(int score) {
        System.out.println("Your score is " + score + ".");
        return;
    }

    // Ask the player whether to save the game or load the game
    public static String askNewGame() {
        String choice;
        System.out.println("Do you want to start a new game or load a saved game?");
        choice = askQuestionStr("(type 1 for start a new game, or type 2 for load a saved game): ");
        while (!checkForBounds(choice, 1, 2)) {
            System.out.println("invalid input");
            choice = askQuestionStr("(type 1 for start a new game, or type 2 for load a saved game): ");
        }
        return choice;
    }
    // Store the records of the rooms

    public static CastleRoom createRoomRecord(String roomName, boolean hasTrap, boolean hasTreasure, int treasurePoints,
            int disableTrapPoints) {
        CastleRoom room = new CastleRoom();
        setCastleGameRoomName(room, roomName);
        setCastleGameHasTrap(room, hasTrap);
        setCastleGameHasTreasure(room, hasTreasure);
        setCastleGameTreasurePoints(room, treasurePoints);
        setCastleGameDisableTrapPoints(room, disableTrapPoints);
        return room;
    }

    // Store the records of players
    public static CastleGamePlayer createPlayerRecord(String playerName, int score, boolean leave) {
        CastleGamePlayer player = new CastleGamePlayer();
        setCastleGamePlayerName(player, playerName);
        setCastleGameScore(player, score);
        setCastleGameLeave(player, leave);
        return player;
    }

    // Ask for the players name
    public static void askPlayers(CastleGamePlayer[] players, int len) {
        String name;
        for (int i = 0; i < len; i++) {
            name = askQuestionStr("Player " + (i + 1) + ". What is your name?");
            players[i] = createPlayerRecord(name, 0, false);
        }
    }

    // To create the records for the castle, whether the room have traps or
    // treasures or given randomly
    public static void createRooms(String[] roomNames, CastleRoom[] rooms, int len) {
        for (int i = 0; i < len; i++) {
            boolean hasTrap = false;
            boolean hasTreasure = false;
            int treasurePoints = getPoint(6);
            int disableTrapPoints = 1;

            if (getPoint(2) > 1)
                hasTrap = true;
            if (getPoint(2) > 1)
                hasTreasure = true;
            rooms[i] = createRoomRecord(roomNames[i], hasTrap, hasTreasure, treasurePoints, disableTrapPoints);
        }
    }

    // To save the game
    public static void saveGame(CastleGamePlayer[] players, int playerCount, CastleRoom[] rooms, int roomCount,
            int currentPlayer) throws IOException {
        PrintWriter outputStream = new PrintWriter(new FileWriter("game.txt"));
        int i;
        outputStream.println(playerCount);
        outputStream.println(roomCount);
        for (i = 0; i < playerCount; i++) {
            outputStream.println(getCastleGamePlayerName(players[i]));
            outputStream.println(getCastleGameScore(players[i]));
            if (getCastleGameLeaving(players[i]) == true) {
                outputStream.println("1");
            } else {
                outputStream.println("0");
            }
        }
        for (i = 0; i < roomCount; i++) {
            outputStream.println(getCastleGameRoomName(rooms[i]));
            outputStream.println(getCastleGameTreasurePoints(rooms[i]));
            outputStream.println(getCastleGameDisableTrapPoints(rooms[i]));
            if (getCastleGameHasTrap(rooms[i]) == true) {
                outputStream.println("1");
            } else {
                outputStream.println("0");
            }
            if (getCastleGameHasTreasure(rooms[i]) == true) {
                outputStream.println("1");
            } else {

                outputStream.println("0");
            }
        }
        outputStream.println(currentPlayer);
        outputStream.close();
    }

    // To load the game
    public static int loadGame(BufferedReader inputStream, CastleGamePlayer[] players, int playerCount,
            CastleRoom[] rooms, int roomCount) throws IOException {
        int i;
        for (i = 0; i < playerCount; i++) {
            String playerName;
            int score;
            boolean leave;
            playerName = inputStream.readLine();
            score = Integer.parseInt(inputStream.readLine());
            if (Integer.parseInt(inputStream.readLine()) >= 1) {
                leave = true;
            } else {
                leave = false;
            }
            players[i] = createPlayerRecord(playerName, score, leave);
        }
        for (i = 0; i < roomCount; i++) {
            int treasurePoints;
            int disableTrapPoints;
            boolean hasTrap;
            boolean hasTreasure;
            String roomName;
            roomName = inputStream.readLine();
            treasurePoints = Integer.parseInt(inputStream.readLine());
            disableTrapPoints = Integer.parseInt(inputStream.readLine());
            if (Integer.parseInt(inputStream.readLine()) >= 1) {
                hasTrap = true;
            } else {
                hasTrap = false;

            }
            if (Integer.parseInt(inputStream.readLine()) >= 1) {
                hasTreasure = true;
            } else {
                hasTreasure = false;
            }
            rooms[i] = createRoomRecord(roomName, hasTrap, hasTreasure, treasurePoints, disableTrapPoints);
        }
        int firstPlayer = Integer.parseInt(inputStream.readLine());
        return firstPlayer;
    }

    // Return the id of first player to play next time (after save/load). Return -1
    // if there's no player left
    public static int roomAction(CastleGamePlayer[] players, int playerCount, CastleRoom[] rooms, int roomCount,
            int firstPlayer) {
        String choice;
        boolean hasPlayer = true;
        while (hasPlayer == true) {
            hasPlayer = false;
            for (int i = firstPlayer; i < playerCount; i++) {
                CastleGamePlayer player = players[i];
                if (getCastleGameLeaving(player) == false) {
                    hasPlayer = true;
                    choice = askRoom(getCastleGamePlayerName(player), roomCount);
                    if (choice.equals("q")) {
                        System.out.println("You quit the game.");
                        setCastleGameLeave(player, true);
                    } else if (choice.equals("s")) {
                        // Return the id of current player when save
                        return i;
                    } else {
                        CastleRoom room = rooms[Integer.parseInt(choice) - 1];

                        System.out.println("You entered a room.");
                        printSurroundings(room);
                        boolean leaveRoom = false;
                        while (leaveRoom == false) {
                            choice = askChoice();
                            if (choice.equals("1")) {
                                printSurroundings(room);
                            } else if (choice.equals("2")) {
                                setCastleGameScore(player, findCup(room, getCastleGameScore(player)));
                            } else if (choice.equals("3")) {
                                setCastleGameScore(player, disableTrap(room, getCastleGameScore(player)));
                            } else if (choice.equals("4")) {
                                System.out.println("You leave the room safely");
                                leaveRoom = true;
                            } else {
                                System.out.println("Please input choice between 1-4!");
                            }
                            printScore(getCastleGameScore(player));
                        }
                    }
                }
            }
            // Reset first player for next round
            firstPlayer = 0;
        }
        // Return -1 when no player left
        return -1;
    }

    // Print the final score
    public static void printFinalScore(CastleGamePlayer[] players, int len) {
        System.out.println("Final score:");
        for (int i = 0; i < len; i++)

        {
            System.out.println(players[i].playerName + ": " + players[i].score + " points.");
        }
    }

    // Ask whehter to save the game or load the game
    /**
     * @throws IOException
     */
    public static void outerMethod7() throws IOException {
        int playerCount;
        int firstPlayer;
        int roomCount;
        CastleGamePlayer[] players;
        CastleRoom[] rooms;
        String choice = askNewGame();
        if (choice.equals("1")) {
            playerCount = askQuestionInt("How many players? ");
            players = new CastleGamePlayer[playerCount];
            askPlayers(players, playerCount);
            String[] roomNames = new String[] { "Lab", "Study Room", "Common Room", "Restroom", "Accomadation",
                    "Laundry" }; // Room names
            roomCount = askQuestionInt("How many rooms? (max " + roomNames.length + "): ");
            rooms = new CastleRoom[roomCount];
            createRooms(roomNames, rooms, roomCount);
            firstPlayer = 0;
        } else {
            BufferedReader inputStream = new BufferedReader(new FileReader("game.txt"));
            playerCount = Integer.parseInt(inputStream.readLine());
            players = new CastleGamePlayer[playerCount];
            roomCount = Integer.parseInt(inputStream.readLine());
            rooms = new CastleRoom[roomCount];
            firstPlayer = loadGame(inputStream, players, playerCount, rooms, roomCount);
            inputStream.close();
        }
        firstPlayer = roomAction(players, playerCount, rooms, roomCount, firstPlayer);

        if (firstPlayer < 0) {
            System.out.println("Everyone quit the game and the game is over.");
            printFinalScore(players, playerCount);
        } else {
            saveGame(players, playerCount, rooms, roomCount, firstPlayer);
            System.out.println(
                    "The game is saved. You can load the game next time and the game will continue from the player who saved the game.");
        }
        return;
    }
}