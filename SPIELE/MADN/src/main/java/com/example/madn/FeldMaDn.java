package com.example.madn;
import java.util.Random;
import java.util.Scanner;


public class FeldMaDn extends Feld{

    private char markMain;
    private char markHome;
    private char markEnd;
    char [][]feld=new char[11][11];
    Scanner scanner=new Scanner(System.in);
    int zz = 0;
    Random random = new Random();
    Spieler spieler1 = new Spieler('A', 0);
    Spieler spieler2 = new Spieler('B', 20);
    boolean win = false;


    public FeldMaDn(char markM, char markH, char markE){
        markMain=markM;
        markHome=markH;
        markEnd=markE;
    }

    public void fillField(){

        spieler1.setStoneHome(4);
        spieler1.setStoneIn(0);
        spieler2.setStoneHome(4);
        spieler2.setStoneIn(0);

        for(int i=0; i<11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 0 || i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 9 || i == 10) {
                    if (j == 0 || j == 1 || j == 2 || j == 3 || j == 7 || j == 8 || j == 9 || j == 10) {
                        feld[i][j] = ' ';
                    } else {
                        feld[i][j] = markMain;
                    }
                }
                else if (i == 5 && j == 5) {
                    feld[i][j] = ' ';

                } else {
                    feld[i][j] = markMain;
                }

            }

        }
    }
    public void fillHome() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 1 || i == 2) {
                    if (j == 1 || j == 2) {
                        markHome = 'A';
                        feld[i][j] = markHome;

                    }
                }

                    if (i == 8 || i == 9) {
                        if (j == 8 || j == 9) {
                            markHome = 'B';
                            feld[i][j] = markHome;

                        }
                    }
                }
            }
        }
    public void fillEnd(){
        for(int i=0; i<11; i++) {
            for (int j = 0; j < 11; j++) {
                if(i==5){
                    if(j==1||j==2||j==3||j==4){
                        markEnd='a';
                        feld[i][j] = markEnd;

                    }
                }
                if(i==5){
                    if(j==6||j==7||j==8||j==9){
                        markEnd='b';
                        feld[i][j] = markEnd;

                    }
                }
                if(j==5){
                    if(i==1||i==2||i==3||i==4){
                        feld[i][j] = ' ';

                    }
                }
                if(j==5){
                    if(i==6||i==7||i==8||i==9){
                        feld[i][j] = ' ';

                    }
                }
            }
        }
    }
    public void outField(){
        for(int i=0; i<11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(feld[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }

    public void lookWin(){

    }

    //_________________________________________________________________________________________________--

    //FUNKTIONEN
    public void begin(){
        int beginningPlayer = random.nextInt(100);

        for(;win!=true; beginningPlayer++){
            if(beginningPlayer%2==0){
                System.out.println("Spieler A: ");
                draw(spieler1);
            }
            else if(beginningPlayer%2==1){
                System.out.println("Spieler B: ");
                draw(spieler2);
            }
        }

    }

    public void rollDice(){
        String s;
        System.out.println("Drücke irgendeine Taste zum Würfeln!");
        s=scanner.next();

        do {
            zz = random.nextInt(7);
        }while(zz==0);

        System.out.println(zz);
    }

    public void draw(Spieler s){
        int a=0;
        int zeahlerA=s.getStartPosition();
        int Ahouse = s.getStoneHome();

        if(Ahouse==4){
            do {
                rollDice();
                a++;
            }while (zz!=6&&a<10);

            if(zz==6){
                switch(s.getStartPosition()){
                    case 0: feld[4][0]=s.getPlayerInitial(); feld[2][2]=markMain; zeahlerA=0; break;
                    case 20: feld[6][10]=s.getPlayerInitial(); feld[8][8]=markMain; zeahlerA=20; break;

                }
                outField();
                a=0;
                s.setStoneIn(1);
                s.setStoneHome(3);
            }
            else{
                System.out.println("KEIN 6");
                outField();
                a=0;
            }
        }
        else{
            int test=0;

                rollDice();
                int moving = zz;

                if (zeahlerA == 0) {

                    if (moving < 4) {
                        feld[4][moving] = s.getPlayerInitial();
                        feld[4][0] = markMain;
                        zeahlerA = zeahlerA + moving;
                    } else {
                        moving = moving - 4;
                        feld[4-moving][4] = s.getPlayerInitial();
                        feld[4][0] = markMain;
                        zeahlerA = zeahlerA + moving+4;
                    }
                } else if (zeahlerA > 0 && zeahlerA <= 4) {

                    if (zeahlerA + moving <= 4) {
                        feld[4][zeahlerA + moving] = s.getPlayerInitial();
                        feld[4][zeahlerA] = markMain;
                        zeahlerA = zeahlerA + moving;
                    } else if (zeahlerA+moving > 4 &&zeahlerA+moving<=8) {
                        moving = moving - 4 + zeahlerA;
                        feld[4-moving][4] = s.getPlayerInitial();
                        feld[4][zeahlerA] = markMain;
                        zeahlerA = zeahlerA + moving;
                    }
                    else if(zeahlerA+moving>8&&zeahlerA+moving<=10){
                        if(zeahlerA+moving==9){
                            feld[0][5]=s.getPlayerInitial();
                            feld[4][zeahlerA]=markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if(zeahlerA+moving==10){
                            feld[0][6]=s.getPlayerInitial();
                            feld[4][zeahlerA]=markMain;
                            zeahlerA = zeahlerA + moving;
                        }

                    }
                }
                else if(zeahlerA>4&&zeahlerA<=8){
                    if (zeahlerA + moving <= 8) {
                        feld[4-moving-(zeahlerA-4)][4] = s.getPlayerInitial();
                        feld[4-(zeahlerA-4)][4] = markMain;
                        zeahlerA = zeahlerA + moving;
                    }
                    else if(zeahlerA+moving>8&&zeahlerA+moving<=10){
                        if(zeahlerA+moving==9){
                            feld[0][5]=s.getPlayerInitial();
                            feld[4-(zeahlerA-4)][4]=markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if(zeahlerA+moving==10){
                            feld[0][6]=s.getPlayerInitial();
                            feld[4-(zeahlerA-4)][4]=markMain;
                            zeahlerA = zeahlerA + moving;
                        }

                    }
                    else if(zeahlerA+moving>10&&zeahlerA+moving<=14){
                        feld[zeahlerA-8+(moving-2)][6]=s.getPlayerInitial();
                        feld[4-(zeahlerA-4)][4]=markMain;
                        zeahlerA = zeahlerA + moving;
                    }

                }
                else if(zeahlerA==9){
                    if(zeahlerA+moving==10){
                        feld[0][6]=s.getPlayerInitial();
                        feld[0][5]=markMain;
                        zeahlerA = zeahlerA + moving;
                    }
                    else if(zeahlerA+moving>10&&zeahlerA+moving<=14){
                        feld[moving-1][6]=s.getPlayerInitial();
                        feld[0][5]=markMain;
                        zeahlerA = zeahlerA + moving;
                    }
                    else if(zeahlerA+moving==15){
                        feld[4][7]=s.getPlayerInitial();
                        feld[0][5]=markMain;
                        zeahlerA = zeahlerA + moving;
                    }


                }
                else if(zeahlerA>=10&&zeahlerA<14){
                    if(zeahlerA+moving<=14){
                        feld[zeahlerA-10+moving][6]=s.getPlayerInitial();
                        feld[zeahlerA-10][6]=markMain;
                        zeahlerA = zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>14&&zeahlerA+moving<=18){
                        feld[4][6+zeahlerA-14+moving]=s.getPlayerInitial();
                        feld[zeahlerA-10][6]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving==19){
                        feld[5][10]=s.getPlayerInitial();
                        feld[3][6]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                }
                else if(zeahlerA>=14&&zeahlerA<=18){
                    if(s.getPlayerInitial()=='B' && zeahlerA+moving<=20){
                        int in = (zeahlerA+moving)-20;
                        feld[5][9-in]=s.getPlayerInitial();
                        feld[4][5+(zeahlerA-14)]=markMain;
                        zeahlerA=in+60;
                    }
                    else if(zeahlerA+moving<=18){
                        feld[4][5+zeahlerA-13+moving]=s.getPlayerInitial();
                        feld[4][5+zeahlerA-13]=markMain;
                        zeahlerA = zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>=18&&zeahlerA+moving<=20){
                        if(zeahlerA+moving==18){
                            feld[10][4]=s.getPlayerInitial();
                        }
                        else if(zeahlerA+moving==19){
                            feld[5][10]=s.getPlayerInitial();
                        }
                        else if(zeahlerA+moving==20){
                            feld[6][10]=s.getPlayerInitial();
                        }
                        feld[4][5+zeahlerA-13]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>20&&zeahlerA+moving<=24){
                        feld[6][10-(zeahlerA-18+moving-2)]=s.getPlayerInitial();
                        feld[4][5+zeahlerA-13]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }

                }
                else if(zeahlerA>18&&zeahlerA<=20){
                    if(zeahlerA+moving==20){
                        feld[6][10]=s.getPlayerInitial();
                        feld[5][10]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>20&&zeahlerA+moving<=24){
                        feld[6][10-moving+(20-zeahlerA)]=s.getPlayerInitial();
                        feld[4+(zeahlerA-18)][10]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>24){
                        feld[4+(moving-4)][6]=s.getPlayerInitial();
                        feld[4+(zeahlerA-18)][10]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                }
                else if(zeahlerA>20&&zeahlerA<=24){
                    if(zeahlerA+moving<=24){
                        feld[6][10-(zeahlerA-20+moving)]=s.getPlayerInitial();
                        feld[6][10-(zeahlerA-20)]=markMain;
                        zeahlerA = zeahlerA+moving;

                    }else if(zeahlerA+moving>24&&zeahlerA+moving<=28){
                        feld[6+(moving-(24-zeahlerA))][6]=s.getPlayerInitial();
                        feld[6][10-(zeahlerA-20)]=markMain;
                        zeahlerA = zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>28){
                        if(zeahlerA+moving==29){
                            feld[10][5]=s.getPlayerInitial();
                            feld[6][10-(zeahlerA-20)]=markMain;
                            zeahlerA = zeahlerA+moving;
                        }
                        else if(zeahlerA+moving==30){
                            feld[10][4]=s.getPlayerInitial();
                            feld[6][10-(zeahlerA-20)]=markMain;
                            zeahlerA = zeahlerA+moving;
                        }

                    }
                }
                else if(zeahlerA>24&&zeahlerA<=28){
                    if(zeahlerA+moving<=28){
                        feld[6+(zeahlerA-24+moving)][6]=s.getPlayerInitial();
                        feld[6][6+(zeahlerA-24)]=markMain;
                        zeahlerA = zeahlerA+moving;
                    }
                    else if(zeahlerA+moving==29){
                        feld[10][5]=s.getPlayerInitial();
                        feld[6][6+(zeahlerA-24)]=markMain;
                        zeahlerA = zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>=30 && zeahlerA+moving<=34){
                        feld[10-(moving-(30-zeahlerA))][4]=s.getPlayerInitial();
                        feld[6+(zeahlerA-24)][6]=markMain;
                        zeahlerA = zeahlerA+moving;
                    }
                }
                else if(zeahlerA==29||zeahlerA==30){
                    if(zeahlerA+moving==30){
                        feld[10][4]=s.getPlayerInitial();
                    }
                    else if(zeahlerA+moving>30&&zeahlerA+moving<=34){
                        if(zeahlerA==29){
                            feld[10-(moving-(30-zeahlerA))][4]=s.getPlayerInitial();
                            feld[10][5]=markMain;
                            zeahlerA = zeahlerA+moving;
                        }
                        else if(zeahlerA==30){
                            feld[10-(moving-(30-zeahlerA))][4]=s.getPlayerInitial();
                            feld[10][4]=markMain;
                            zeahlerA = zeahlerA+moving;
                        }
                    }
                    else if(zeahlerA+moving==35){
                        if(zeahlerA==29){
                            feld[6][3]=s.getPlayerInitial();
                            feld[10][5]=markMain;
                            zeahlerA = zeahlerA+moving;
                        }
                        else if(zeahlerA==30){
                            feld[6][3]=s.getPlayerInitial();
                            feld[10][4]=markMain;
                            zeahlerA = zeahlerA+moving;
                        }

                    }
                    else if(zeahlerA+moving==36){
                        if(zeahlerA==29){
                            feld[6][2]=s.getPlayerInitial();
                            feld[10][5]=markMain;
                            zeahlerA = zeahlerA+moving;
                        }
                        else if(zeahlerA==30){
                            feld[6][2]=s.getPlayerInitial();
                            feld[10][4]=markMain;
                            zeahlerA = zeahlerA+moving;
                        }

                    }

                }
                else if(zeahlerA>30&&zeahlerA<=34){
                    if(zeahlerA+moving<=34){
                        feld[10-(moving-(30-zeahlerA))][4]=s.getPlayerInitial();
                        feld[6+(34-zeahlerA)][4]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>34&&zeahlerA+moving<=38){
                        feld[6][4-(moving-(34-zeahlerA))]=s.getPlayerInitial();
                        feld[6+(34-zeahlerA)][4]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving==39){
                        feld[5][0]=s.getPlayerInitial();
                        feld[6+(34-zeahlerA)][4]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>=40 && zeahlerA+moving<=43) {
                        if(s.getPlayerInitial()=='A') {
                            feld[1 + (zeahlerA + moving - 40)][5] = s.getPlayerInitial();
                            feld[4][6] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if(s.getPlayerInitial()=='B'){
                            feld[0][4] = s.getPlayerInitial();
                            feld[4][6] = markMain;
                            zeahlerA = zeahlerA + moving;
                            zeahlerA=zeahlerA-40;
                        }
                    }

                }
                else if(zeahlerA>34&&zeahlerA<=38){
                    if(zeahlerA+moving<=38){
                        feld[6][4-(moving-(34-zeahlerA))]=s.getPlayerInitial();
                        feld[6][38-zeahlerA]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving==39){
                        feld[5][0]=s.getPlayerInitial();
                        feld[6][38-zeahlerA]=markMain;
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>=40 && zeahlerA+moving<=43) {
                        if(s.getPlayerInitial()=='A') {
                            feld[5][moving - (39 - zeahlerA)] = s.getPlayerInitial();
                            feld[6][38 - zeahlerA] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if(s.getPlayerInitial()=='B'){
                            feld[6][38-zeahlerA]=markMain;
                            zeahlerA = zeahlerA + moving;
                            zeahlerA=zeahlerA-40;
                            feld[4][zeahlerA] = s.getPlayerInitial();

                        }
                    }

                }
                else if(zeahlerA==39){
                    if(zeahlerA+moving>=40 && zeahlerA+moving<=43) {
                        if(s.getPlayerInitial()=='A'){
                            feld[5][moving] = s.getPlayerInitial();
                            feld[5][0] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if(s.getPlayerInitial()=='B'){
                            feld[5][0] = markMain;
                            zeahlerA = zeahlerA + moving;
                            zeahlerA=zeahlerA-40;
                            feld[4][zeahlerA] = s.getPlayerInitial();
                        }
                    }
                    else if(zeahlerA+moving>=44&&s.getPlayerInitial()=='B'){
                        feld[5][0] = markMain;
                        zeahlerA = zeahlerA + moving;
                        zeahlerA=zeahlerA-40;
                        feld[4][zeahlerA] = s.getPlayerInitial();
                    }
                    else if(zeahlerA+moving==45&&s.getPlayerInitial()=='B'){
                        feld[5][0] = markMain;
                        zeahlerA = zeahlerA + moving;
                        zeahlerA=zeahlerA-40;
                        feld[3][4] = s.getPlayerInitial();
                    }

                }
                else if((zeahlerA>=40&&zeahlerA<=43) && s.getPlayerInitial()=='A'){
                    if(zeahlerA+moving<=34){
                        feld[5][zeahlerA-39+moving] = s.getPlayerInitial();
                        feld[5][zeahlerA-39] = markMain;
                        zeahlerA = zeahlerA + moving;
                    }
                    else{
                        System.out.println("Runde ist aus - A hat gewonnen");
                    }
                }
                else if(zeahlerA>=60 && s.getPlayerInitial()=='B'){
                    if(zeahlerA+moving<=63) {
                        int temp = zeahlerA - 60;
                        feld[5][9 - (temp + moving)] = s.getPlayerInitial();
                        feld[5][9 - temp] = markEnd;
                        zeahlerA = zeahlerA + moving;
                    }
                    else{
                        System.out.println("Runde ist aus - B hat gewonnen");
                    }
                }


                outField();


        }
    }


}
