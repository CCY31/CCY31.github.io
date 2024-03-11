// Assignment 4 Version 2
// ec22586
// 26/3/2023
class Room_ec22586 extends Room {

    // To record whether the light is on or off
    boolean lightOn = true;
    // Gift for honest visitors
    static final Item DIAMOND = new Item("diamond");

    public Direction visit(Visitor visitor, Direction d) {
        visitor.tell("Hello, do you want to enter the room?");
        char userchoice = visitor.getChoice("Please choose a for come in, b for leave", new char [] {'a','b'});
        // If the user choose to come in the room, he/she is told he/she are in room ec22586, 
        // else he/she is sent back to the opposite direction where he/she came from.
        if(userchoice == 'a') {
            visitor.tell("You are in room ec22586.");
        }
        else {
            visitor.tell("See you next time!");
            return Direction.opposite(d);
        }
        // If the visitor has a diamond, which means the user came before 
        if (visitor.hasEqualItem(DIAMOND))
		{
			visitor.tell("Thank you for your honesty when you visited the room last time.");
		}
        boolean treasureOrTrap = false;
        if(lightOn == true) {
            visitor.tell("The light is on, do you want to turn off the light?");
            userchoice = visitor.getChoice("Please choose y for yes, n for no", new char [] {'y','n'});
            // If the light is off then the user will touch the trap
            if(userchoice == 'y') {
                visitor.tell("You turn off the light, and you can't see anything, so you touch a trap suddenly.");
                lightOn = false;
                treasureOrTrap = false;
            }
            // If the light is on then he/she can see a coin
            else {
                visitor.tell("You see a coin in front of you.");
                treasureOrTrap = true;
            }
        }
        else {
            visitor.tell("The light is off, do you want to turn the light?");
            userchoice = visitor.getChoice("Please choose y for yes, n for no", new char [] {'y','n'});
            // If the light is on then he/she can see a coin
            if(userchoice == 'y') {
                visitor.tell("You turn on the light, you see a coin in front of you.");
                lightOn = true;
                treasureOrTrap = true;
            }
            // If the light is off then the user will touch the trap
            else {
                visitor.tell("You can't see anything, so you touch a trap suddenly.");
                treasureOrTrap = false;
            }
        }
        if (treasureOrTrap) 
        {
            visitor.tell("Do you want to take away the coin?");
            userchoice = visitor.getChoice("Please choose y for yes, n for no", new char [] {'y','n'});
            // If the user choose to take away the coin then he/she will get it, otherwise he/she can get a diamond
            if(userchoice == 'y') {
                visitor.tell("You get a coin.");
                visitor.giveGold(1);
            }
            else {
                visitor.tell("You are really honest, so I will give you a diamond");
                Item diamondItem = new Item("diamond");
                visitor.giveItem(diamondItem);
            }
        }
        else
        {
            visitor.tell("You need to pay a coin for escape the trap.");
            // If the user touch the trap, he/she need to spend a coin for escape
            int gold = visitor.takeGold(1);
            if(gold == 1) {
                visitor.tell("You are safe now!");
            }
            else {
                visitor.tell("I'll let you go this time. Remember to bring more gold with you!");
            }
        }
        // To choose the direction of leaving the room
        char exit = visitor.getChoice("no more items in room. where would you like to exit (n, s, e, w)?", new char[] {'n', 's', 'e', 'w'});
        if (exit == 'n') {
            d = Direction.TO_NORTH;    
        }
        else if (exit == 's') {
            d = Direction.TO_SOUTH;
        } 
        else if (exit == 'e') {
            d = Direction.TO_EAST;
        }
        else if (exit == 'w') {
            d = Direction.TO_WEST;
        }
        return d;
    }
}