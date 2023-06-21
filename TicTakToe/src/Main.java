import java.util.*;

public class Main {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        String result;
        char[][] board;
        board = new char[][]{
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };
        displayBoard(board);

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter position of your move (1-9) : ");
            int userPos = scan.nextInt();
            while(cpuPositions.contains(userPos) || playerPositions.contains(userPos)){
                System.out.println("Enter valid position!");
                userPos = scan.nextInt();
            }
            updateBoard(board,userPos,"player");

            result = checkWinner();
            if(result.length()>0){
                displayBoard(board);
                break;
            }
            Random rand = new Random();
            int cpuPos =rand.nextInt(9)+1;
            while(cpuPositions.contains(cpuPos) || playerPositions.contains(cpuPos)){
                cpuPos =rand.nextInt(9)+1;
            }
            updateBoard(board,cpuPos,"cpu");
            displayBoard(board);
            result = checkWinner();
            if(result.length()>0){
                break;
            }
        }

    }
    public static void displayBoard(char[][] board){
        for(char[] row: board){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void updateBoard(char[][] board, int pos, String user){
        char symbol = ' ';
        if (user.equals("player")){
            symbol = 'X';
            playerPositions.add(pos);
        }
        else if(user.equals("cpu")){
            symbol='O';
            cpuPositions.add(pos);
        }
        switch(pos){
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;

            default:
                System.out.println("Enter correct Position");
                break;

        }
    }
    public static String checkWinner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List topCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List botCol = Arrays.asList(3,6,9);
        List digonal1 = Arrays.asList(1,5,9);
        List digonal2 = Arrays.asList(3,5,7);


        List<List> winningConditions =new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(topCol);
        winningConditions.add(midCol);
        winningConditions.add(botCol);
        winningConditions.add(digonal1);
        winningConditions.add(digonal2);

        for (List l: winningConditions) {
            if (playerPositions.containsAll(l)){
                System.out.println("Congratulations! You have won");
                return "Congratulations! You have won";
            } else if (cpuPositions.containsAll(l)) {
                System.out.println("Sorry! You lost");
                return "Sorry! You los!";

            } else if(playerPositions.size()+cpuPositions.size() == 9){
                System.out.println("Its a tie!");
                return "Its a tie!";

            }

        }
        return "";

    }
}