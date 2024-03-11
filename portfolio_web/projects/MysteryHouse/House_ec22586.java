class House_ec22586 extends House {
    
    Room room1;
    Room room2;
    Room room3;
    public Room[] rooms;
    
    public House_ec22586() {
        room1 = new Room_ec22586();
        room2 = new Room_ec22585();
        room3 = new Room_ec22866();
        
        rooms = new Room[3];

        rooms[0] = room1;
        rooms[1] = room2;
        rooms[2] = room3;
    };
    
    public Direction visit(Visitor v, Direction d) {
        int roomNumber = 0;
        v.tell("You are outside the big house.");
        char choice = v.getChoice("Do you want to a) enter the house or b) leave the house?", new char[] { 'a', 'b' });
        if (choice == 'a') {
            while (true) {
                d = rooms[roomNumber].visit(v, d);
                if (d == Direction.TO_WEST) {
                    roomNumber++;
                    if (roomNumber == rooms.length) {
                        v.tell("You leave the house!");
                        return d;
                    } else {
                        v.tell("You find you are in the next room.");
                    }
                } else if (d == Direction.TO_NORTH) {
                    roomNumber--;
                    v.tell("You find you are in the previous room.");
                } else {
                    v.tell("You find that you still stay in the same room.");
                }
            }
            

        } 
        else {
            d = Direction.opposite(d);
        }
        return d;
    }
}
