
public class HumanAgent {
    String name;
    HumanAgent(String name){
        this.name=name;
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
}