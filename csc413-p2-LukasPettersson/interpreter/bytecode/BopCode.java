package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;
import java.util.HashMap;

public class BopCode extends ByteCode {

    private String codeName;
    private String operator;


    @Override
    public void execute(VirtualMachine virtualMachine) {

        int first = virtualMachine.popRTStack();
        int second = virtualMachine.popRTStack();

        virtualMachine.pushRTStack(resolvebop(first, second));

        if(virtualMachine.isDumb()){
            System.out.println(codeName +  " " + operator);
        }
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);
        operator = args.get(1);
    }

    public int resolvebop(int first, int second){
        //this shouldnt be implemented like this but you know sometimes you gotta do what you gotta do
        //could and probably should be implemented using a HashMap
        //out of time, didn't think it was too important to implement given the size of the operator selection
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return (second - first);
            case "/":
                return second / first;
            case "*":
                return second * first;
            case "==":
                if (second == first) {
                    return 1;
                } else return 0;
            case "!=":
                if (second != first) {
                    return 1;
                } else return 0;
            case "<=":
                if (second <= first) {
                    return 1;
                } else return 0;
            case ">=":
                if (second >= first) {
                    return 1;
                } else return 0;
            case ">":
                if (second > first) {
                    return 1;
                } else return 0;
            case "<":
                if (second < first) {
                    return 1;
                } else return 0;
            case "|":
                if (second == 1) {
                    return 1;
                } else if (first == 1) {
                    return 1;
                } else {
                    return 0;
                }
            case "&":
                if (second == 1 && first == 1) {
                    return 1;
                } else {
                    return 0;
                }
        }
        //SHOULD NEVER GET HERE UNLESS THE PROGRAM DOESNT INCLUDE
        System.out.println("Error in BOP code: invalid code");
        return 0;
    }

}
