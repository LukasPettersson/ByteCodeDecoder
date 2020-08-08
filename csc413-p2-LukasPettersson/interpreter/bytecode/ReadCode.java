package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode {

    private int userInput;
    private String codeName;
    @Override
    public void execute(VirtualMachine virtualMachine) {
        boolean isInt = false;

        while(!isInt){
            System.out.print("Please enter an integer : ");
            Scanner scanner = new Scanner(System. in);
            String inputString = scanner. nextLine();
            try {
                userInput = Integer.parseInt(inputString);
                virtualMachine.pushRTStack(userInput);
                isInt = true;
            }catch(Exception e){
                System.out.println("invalid input.");
            }


        }
        if(virtualMachine.isDumb()){
            System.out.println("READ");
        }
    }

    @Override
    public void init(ArrayList< String > args){
        codeName = args.get(0);
    }
}
