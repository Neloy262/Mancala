import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
class AI_Agent {
    String name;
    AI_Agent(String name){
        this.name=name;
    }
    int minimax(Board board,int depth,int alpha,int beta,boolean isMaximizing,boolean parent){
        if(depth==8||board.isgameOver()){
            if(parent){
                return board.evaluateForMax();
            }
            else{
                return board.evaluateForMin();
            }
            
        }
        if(isMaximizing){
            int maxval=Integer.MIN_VALUE;
            //Generate child nodes
            ArrayList<Board> childNodes=new ArrayList<>();
            for(int i=0;i<6;i++){
              if(board.Player1[i]!=0){    
              Board newBoard = MovePlayer(board.Player1, board.Player2, board.pot1, board.pot2, i, isMaximizing);
              childNodes.add(new Board(newBoard.Player1,newBoard.Player2,newBoard.pot1,newBoard.pot2,newBoard.lastTurn,newBoard.specialMove));
              }  
            }
            
            for(int i=0;i<childNodes.size();i++){
                if(childNodes.get(i).lastTurn==1){
                    isMaximizing=true;
                    parent=true;
                }
                else{
                    isMaximizing=false;
                    parent=true;
                }
                int value=minimax(childNodes.get(i), depth+1, alpha, beta, isMaximizing,parent);
                maxval=Math.max(value,maxval);
                alpha=Math.max(alpha, value);
                 if(beta<=alpha){
                     break;
                 }
            }
            return maxval;
        }
        else{
            int minval=Integer.MAX_VALUE;
            //Generate child nodes
            ArrayList<Board> childNodes=new ArrayList<>();
            for(int i=0;i<6;i++){
              if(board.Player2[i]!=0){    
              Board newBoard = MovePlayer(board.Player1, board.Player2, board.pot1, board.pot2, i, isMaximizing);
              childNodes.add(new Board(newBoard.Player1,newBoard.Player2,newBoard.pot1,newBoard.pot2,newBoard.lastTurn,newBoard.specialMove));
              
              }  
            }
            for(int i=0;i<childNodes.size();i++){
                if(childNodes.get(i).lastTurn==1){
                    isMaximizing=false;
                    parent=false;
                }
                else{
                    isMaximizing=true;
                    parent=false;
                }
                int value=minimax(childNodes.get(i), depth+1, alpha, beta, isMaximizing,parent);

                minval=Math.min(value,minval);

                beta=Math.min(beta, value);
                 if(beta<=alpha){
                     break;
                 }
            }
            return minval;
        }
    }

    Board MovePlayer(int[] Player1,int[] Player2,int pot1,int pot2,int pos,boolean isMaximizing){

        int []Player1Copy=new int[6];
        int []Player2Copy=new int[6];

        for(int i=0;i<6;i++){
            Player1Copy[i]=Player1[i];
            Player2Copy[i]=Player2[i];
        }
        int pot1Copy=pot1;
        int pot2Copy=pot2;
        if(isMaximizing){
        int lastTurn=0;
        int specialMove=0;
        int count=0;
        int stone_no=Player1Copy[pos];
        int initialPlayer2pos=5;
        Player1Copy[pos]=0;
        while(stone_no>0){
            if(pos>6){
                if(initialPlayer2pos<0){
                    pos=0;
                    Player1Copy[pos]=Player1Copy[pos]+1;
                }
                else{
                Player2Copy[initialPlayer2pos]=Player2Copy[initialPlayer2pos]+1;
                initialPlayer2pos--;
                lastTurn=0; 
                } 
            }
            else if(pos<6){
                pos++;
                //System.out.println(pos);
                if(pos==6){
                   lastTurn=1;
                   pot1Copy++;
                   pos++;
                   //stone_no--;
                }
                else if(Player1Copy[pos]==0 && stone_no==0){
                    pot1Copy=Player2Copy[pos]+1;
                    Player2Copy[pos]=0;
                    specialMove=1;
                    lastTurn=0;
                }
                else{
                Player1Copy[pos]=Player1Copy[pos]+1;
                lastTurn=0;
                }
                
            }
            stone_no--;
                  
        }
        Board board=new Board(Player1Copy, Player2Copy, pot1Copy, pot2Copy, lastTurn, specialMove);
        //board.ShowBoard(Player1Copy, Player2Copy, pot1Copy, pot2Copy);
        return board;
    }
    else{
        int lastTurn=0;
        int specialMove=0;
        int count=0;
        int stone_no=Player2Copy[pos];
        int initialPlayer2pos=5;
        Player2Copy[pos]=0;
        while(stone_no>0){
            if(pos>6){
                if(initialPlayer2pos<0){
                    pos=0;
                    Player2Copy[pos]=Player2Copy[pos]+1;
                }
                else{
                Player1Copy[initialPlayer2pos]=Player1Copy[initialPlayer2pos]+1;
                initialPlayer2pos--;
                lastTurn=0; 
                } 
            }
            else if(pos<6){
                pos++;
                //System.out.println(pos);
                if(pos==6){
                   lastTurn=1;
                   pot2Copy++;
                   pos++;
                   //stone_no--;
                }
                else if(Player2Copy[pos]==0 && stone_no==0){
                    pot2Copy=Player1Copy[pos]+1;
                    Player1Copy[pos]=0;
                    specialMove=1;
                    lastTurn=0;
                }
                else{
                Player2Copy[pos]=Player2Copy[pos]+1;
                lastTurn=0;
                }
                
            }
            stone_no--;
                  
        }
        Board board=new Board(Player1Copy, Player2Copy, pot1Copy, pot2Copy, lastTurn, specialMove);
        //board.ShowBoard(Player1Copy, Player2Copy, pot1Copy, pot2Copy);
        return board;
    }
        
        
       
    }

     Board PickBestMove(Board board,int value,boolean isMaximizing){
        Board board2;
        int[] Player1=new int[6];
        int[] Player2=new int[6];
        int pot1=board.pot1;
        int pot2=board.pot2;
        int lastTurn=0;
        int specialMove=0;
        for(int i=0;i<6;i++){
            Player1[i]=board.Player1[i];
            Player2[i]=board.Player2[i];
        }
        board2 = new Board(Player1, Player2, pot1, pot2, lastTurn, specialMove);

        ArrayList<Board> childNodes=new ArrayList<>();
        for(int i=0;i<6;i++){
          if(board2.Player1[i]!=0){    
          Board newBoard = MovePlayer(board2.Player1, board2.Player2, board2.pot1, board2.pot2, i, isMaximizing);
          childNodes.add(new Board(newBoard.Player1,newBoard.Player2,newBoard.pot1,newBoard.pot2,newBoard.lastTurn,newBoard.specialMove));
          }  
        }
        int bestPos=0;
        for(int i=0;i<childNodes.size();i++){
            if(isMaximizing){
                int val=childNodes.get(i).evaluateForMax();
                if(val==value){
                    bestPos=i;
                }
            }
            else{
                int val=childNodes.get(i).evaluateForMin();
                if(val==value){
                    bestPos=i;
                }
            }
        }
        return childNodes.get(bestPos);
     }


    
}