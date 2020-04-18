import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Driver{
    public static void main(String[] args) {
    int[] Player1=new int[6];
    int[] Player2=new int[6];
    int pot1=0;
    int pot2=0;
    int lastTurn=0;
    int specialMove=0;
    for(int i=0;i<6;i++){
        Player1[i]=Player2[i]=4;
    }
    int val;
    AI_Agent ai=new AI_Agent("Jarvis");
    HumanAgent humanAgent=new HumanAgent("Neloy");
    boolean aiMove;
    boolean human;
    Board b=new Board(Player1, Player2, pot1, pot2, lastTurn, specialMove);
    //b.ShowBoard(b.Player1, b.Player2, b.pot1,b.pot2);
    Random random=new Random();
    int x=random.nextInt(2);
    int flag;
    //System.out.println(val);
    if(x==0){
        System.out.println(ai.name+" will go first");
        aiMove=true;
        human=false;
         flag=0;
        
    }
    else{
        System.out.println(humanAgent.name+" will go first");
        aiMove=false;
        human=true;
         flag=1;
        
    }
    int depth=0;
    
    while(true){
        if(flag==0){
            int bestvalue=ai.minimax(b, depth, 0, 0, aiMove,aiMove);
            Board newBoard=ai.PickBestMove(b, bestvalue, aiMove);
            if(newBoard.isgameOver()){
                newBoard.ShowBoard(newBoard.Player1, newBoard.Player2, newBoard.pot1, newBoard.pot2, ai.name, humanAgent.name);
                break;
            }
            if(newBoard.lastTurn==1){
                newBoard.ShowBoard(newBoard.Player1, newBoard.Player2, newBoard.pot1, newBoard.pot2, ai.name, humanAgent.name);
                b=newBoard;
                flag=0;
                continue;
            }
            if(aiMove==true){
            newBoard.ShowBoard(newBoard.Player1, newBoard.Player2, newBoard.pot1, newBoard.pot2, ai.name, humanAgent.name);
            }
            else{
                newBoard.ShowBoard(newBoard.Player1, newBoard.Player2, newBoard.pot2, newBoard.pot1, humanAgent.name, ai.name);
            }
            System.out.println("Enter your move :");
            Scanner sc=new Scanner(System.in);
            int movepos=sc.nextInt();
            Board humanboard= humanAgent.MovePlayer(newBoard.Player1, newBoard.Player2, newBoard.pot1, newBoard.pot2, movepos, human);
            if(humanboard.isgameOver()){
                break;
            }
            if(human==false){
            humanboard.ShowBoard(humanboard.Player1, humanboard.Player2, humanboard.pot1, humanboard.pot2, ai.name, humanAgent.name);
            }
            else{
                humanboard.ShowBoard(humanboard.Player1, humanboard.Player2, humanboard.pot2, humanboard.pot1, humanAgent.name, ai.name);
          
            }
            if(humanboard.lastTurn==1){
                flag=1;
                b=humanboard;
                continue;
            }
            flag=0;
            b=humanboard;
            
        }
        else if(flag==1){
            System.out.println("Enter your move :");
            Scanner sc=new Scanner(System.in);
            int movepos=sc.nextInt();
            Board humanboard= humanAgent.MovePlayer(b.Player1, b.Player2, b.pot1, b.pot2, movepos, human);
            if(humanboard.isgameOver()){
                humanboard.ShowBoard(humanboard.Player1, humanboard.Player2, humanboard.pot1, humanboard.pot2, humanAgent.name, ai.name);
                break;
            }
            if(human==true){
            humanboard.ShowBoard(humanboard.Player1, humanboard.Player2, humanboard.pot1, humanboard.pot2, humanAgent.name, ai.name);
            }
            else{
                humanboard.ShowBoard(humanboard.Player1, humanboard.Player2, humanboard.pot2, humanboard.pot1, ai.name, humanAgent.name);
            }
            if(humanboard.lastTurn==1){
                b=humanboard;
                flag=1;
                continue;
            }
            b=humanboard;
            int bestvalue=ai.minimax(b, depth, 0, 0, aiMove,aiMove);
            Board newBoard=ai.PickBestMove(b, bestvalue, aiMove);
            if(aiMove==false){
            newBoard.ShowBoard(newBoard.Player1, newBoard.Player2, newBoard.pot1, newBoard.pot2, humanAgent.name, ai.name);
            }
            else{
                newBoard.ShowBoard(newBoard.Player1, newBoard.Player2, newBoard.pot2, newBoard.pot1, ai.name, humanAgent.name);  
            }
            if(newBoard.isgameOver()){
                newBoard.ShowBoard(newBoard.Player1, newBoard.Player2, newBoard.pot1, newBoard.pot2, humanAgent.name, ai.name);
                break;
            }
            if(newBoard.lastTurn==1){
               // newBoard.ShowBoard(newBoard.Player1, newBoard.Player2, newBoard.pot1, newBoard.pot2, humanAgent.name, ai.name);
                b=newBoard;
                flag=0;
                continue;
            }
            b=newBoard;
            flag=1;
        }
    }
    }

  
}