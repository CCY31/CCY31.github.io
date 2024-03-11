class HouseTest {
    public static void main (String[]args) {
    House h = new House_ec22586();
    Visitor v = new IOVisitor(System.out,System.in);
    
    Direction d = h.visit(v, Direction.FROM_SOUTH);
    }
}