
public class Board {
int pot1;
int pot2;
int[] Player1;
int[] Player2;
int lastTurn;
int specialMove;
Board(int [] Player1,int [] Player2,int pot1,int pot2,int lastTurn,int specialMove){
    this.Player1=Player1;
    this.Player2=Player2;
    this.pot1=pot1;
    this.pot2=pot2;
    this.lastTurn=lastTurn;
    this.specialMove=specialMove;

}
Board(){

}
 void ShowBoard(int[] Player1,int[] Player2,int pot1,int pot2,String player1,String player2){
    System.out.println(player1+" row");
    for(int i=0;i<6;i++){
        System.out.print(Player1[i]+" ");
    }
    System.out.println("");
    for(int i=0;i<6;i++){
        System.out.print(Player2[i]+" ");
    }
    System.out.println("");
    System.out.println(player2+" row");
    System.out.println("");
    System.out.println(player1+" Stones "+pot1);
    System.out.println(player2+" Stones "+pot2);
    System.out.println(lastTurn);
    System.out.println("");
    
    
}

boolean isgameOver(){
    int count1=0;
    int count2=0;
    for(int i=0;i<6;i++){
        count1=Player1[i]+count1;
    }
    for(int i=0;i<6;i++){
        count2=Player2[i]+count2;
    }

    if(count1==0 || count2==0){
        return true;
    }
    return false;
}

 int evaluateForMax(){

 if(this.lastTurn==1){
    return 100;
 }
 if(this.specialMove==1){
    return 50;
 }
 if(this.diff()>0){
    return this.diff();
 }
 return 10;
 }
 int evaluateForMin(){
    if(this.lastTurn==1){
        return -100;
     }
     if(this.specialMove==1){
        return -50;
     }
     if(this.diff()<0){
        return this.diff();
     }
     return -10; 
 }
 int diff(){
     int player1=0;
     int player2=0;
     for(int i=0;i<6;i++){
        player1=this.Player1[i]+player1;
        player2=this.Player2[i]+player2;
     }
     //System.out.println(player1-player2+"diff");
     return player1-player2;

 }
}