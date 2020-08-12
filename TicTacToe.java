/*Utkarsh Agrawal(11741090) Mukesh Kumar Gupta(11740550)*/
package com.company;
import java.io.*;
import java.util.*;

public class TicTacToe {

    private static int row1=0, row2=0, row3=0, col1=0, col2=0, col3=0, diag1=0, diag2=0, count=0;

    public static void main(String[] args) {
        char[] arr = new char[9];
        for (int i = 0; i < 9; i++)
            arr[i] = ' ';
        System.out.println("The positions on the board are according to the numpad");
        printBoard(arr);
        Game(arr);
    }
    private static void Game(char[] arr) {
        int flag=0;
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        while(count<9) {
            System.out.println("Choose position ");
            int pos=sc.nextInt();
            if(arr[pos-1]!=' ') {
                System.out.println("Position is already filled");
                continue;
            }
            arr[pos-1]='X';
            update(pos,++count);
            printBoard(arr);

            if(checkStatus()){
                System.out.println("You win");
                flag=1;
                System.out.println("Will you play again? y/n");
                char c = sc.next().charAt(0);
                if(c=='y')
                    playAgain();
                break;
            }
            else if(count!=9){
                computerMove(arr, ++count);
            }

            if(checkStatus()){
                System.out.println("You lose");
                flag=1;
                System.out.println("Will you play again? y/n");
                char c = sc.next().charAt(0);
                if(c=='y')
                    playAgain();
                break;
            }
        }
        if(count==9&&flag==0) {
            System.out.println("It's a Tie");
            System.out.println("Will you play again? y/n");
            char c = sc.next().charAt(0);
            if(c=='y')
                playAgain();
        }
    }

    private static void computerMove(char[] arr,int count) {
        while(true) {
            if(count==2&&(row1==1&&diag1==1)) {
                arr[8] = 'O';
                update( 9, count);
                printBoard(arr);
                break;
            }
            if(count==2&&(row3==1&&diag2==1)) {
                arr[2] = 'O';
                update( 3, count);
                printBoard(arr);
                break;
            }
            if(count==2&&(row3==1&&diag1==1)) {
                arr[0] = 'O';
                update( 1, count);
                printBoard(arr);
                break;
            }
            if(count==2&&(row1==1&&diag2==1)) {
                arr[6] = 'O';
                update( 7, count);
                printBoard(arr);
                break;
            }

            if(checkTwoWinning(arr,count)) {
                break;
            }
            if(checkTwoLosing(arr,count)) {
                break;
            }
            int pos=(int)(Math.random()*9);
            if(arr[pos]==' ') {
                arr[pos]='O';
                update(pos+1,count);
                printBoard(arr);
                break;
            }
        }
    }

    private static void printBoard(char[] arr) {
        System.out.println(" "+arr[6]+" | "+arr[7]+" | "+arr[8]);
        System.out.println("---+---+---");
        System.out.println(" "+arr[3]+" | "+arr[4]+" | "+arr[5]);
        System.out.println("---+---+---");
        System.out.println(" "+arr[0]+" | "+arr[1]+" | "+arr[2]);
    }

    private static boolean checkStatus() {
        if(row1==3||row1==-3)
            return true;
        if(row2==3||row2==-3)
            return true;
        if(row3==3||row3==-3)
            return true;
        if(col1==3||col1==-3)
            return true;
        if(col2==3||col2==-3)
            return true;
        if(col3==3||col3==-3)
            return true;
        if(diag1==3||diag1==-3)
            return true;
        if(diag2==3||diag2==-3)
            return true;
        return false;
    }

    private static void update(int pos, int count) {
        if(pos==1||pos==2||pos==3)
            row1+=(int)Math.pow((-1),(count+1));
        if(pos==6||pos==5||pos==4)
            row2+=(int)Math.pow((-1),(count+1));
        if(pos==7||pos==8||pos==9)
            row3+=(int)Math.pow((-1),(count+1));
        if(pos==1||pos==4||pos==7)
            col1+=(int)Math.pow((-1),(count+1));
        if(pos==5||pos==2||pos==8)
            col2+=(int)Math.pow((-1),(count+1));
        if(pos==3||pos==6||pos==9)
            col3+=(int)Math.pow((-1),(count+1));
        if(pos==1||pos==5||pos==9)
            diag1+=(int)Math.pow((-1),(count+1));
        if(pos==7||pos==5||pos==3)
            diag2+=(int)Math.pow((-1),(count+1));
    }

    private static boolean checkTwoLosing(char[] arr,int count) {
        if (row1 == 2) {
            computerCheckTwoMoveRow(arr,count,0);
            return true;
        }
        else if (row2 == 2) {
            computerCheckTwoMoveRow(arr,count,3);
            return true;
        }
        else if (row3 == 2) {
            computerCheckTwoMoveRow(arr,count,6);
            return true;
        }
        else if (col1 == 2) {
            computerCheckTwoMoveColumn(arr,count,0);
            return true;
        }
        else if (col2 == 2) {
            computerCheckTwoMoveColumn(arr,count,1);
            return true;
        }
        else if (col3 == 2) {
            computerCheckTwoMoveColumn(arr,count,2);
            return true;
        }
        else if (diag1 == 2) {
            computerCheckTwoMoveDiagonal1(arr,count,0);
            return true;
        }
        else if (diag2 == 2) {
            computerCheckTwoMoveDiagonal2(arr,count,2);
            return true;
        }
        return false;
    }

    private static boolean checkTwoWinning(char[] arr,int count) {
        if (row1 == -2) {
            computerCheckTwoMoveRow(arr,count,0);
            return true;
        }
        else if (row2 == -2) {
            computerCheckTwoMoveRow(arr,count,3);
            return true;
        }
        else if (row3 == -2) {
            computerCheckTwoMoveRow(arr,count,6);
            return true;
        }
        else if (col1 == -2) {
            computerCheckTwoMoveColumn(arr,count,0);
            return true;
        }
        else if (col2 == -2) {
            computerCheckTwoMoveColumn(arr,count,1);
            return true;
        }
        else if (col3 == -2) {
            computerCheckTwoMoveColumn(arr,count,2);
            return true;
        }
        else if (diag1 == -2) {
            computerCheckTwoMoveDiagonal1(arr,count,0);
            return true;
        }
        else if (diag2 == -2) {
            computerCheckTwoMoveDiagonal2(arr,count,2);
            return true;
        }
        return false;
    }

    private static void computerCheckTwoMoveDiagonal1(char[] arr,int count, int pos) {
        for (; pos < 9; pos += 4) {
            if (arr[pos] == ' ') {
                arr[pos] = 'O';
                update(pos + 1, count);
                printBoard(arr);
            }
        }
    }

    private static void computerCheckTwoMoveDiagonal2(char[] arr,int count, int pos) {
        for (; pos < 7; pos += 2) {
            if (arr[pos] == ' ') {
                arr[pos] = 'O';
                update(pos + 1, count);
                printBoard(arr);
            }
        }
    }

    private static void computerCheckTwoMoveColumn(char[] arr, int count, int pos) {
        int x=pos+7;
        for (; pos < x; pos += 3) {
            if (arr[pos] == ' ') {
                arr[pos] = 'O';
                update(pos + 1,count);
                printBoard(arr);
            }
        }
    }

    private static void computerCheckTwoMoveRow(char[] arr, int count, int pos) {
        int x=pos+3;
        for (; pos < x; pos++) {
            if (arr[pos] == ' ') {
                arr[pos] = 'O';
                update(pos+1 , count);
                printBoard(arr);
            }
        }
    }

    private static void playAgain() {
        row1=row2=row3=col3=col1=col2=diag2=diag1=count=0;
        char[] arr = new char[9];
        for(int i=0;i<9;i++)
            arr[i]=' ';
        printBoard(arr);
        Game(arr);
    }

}