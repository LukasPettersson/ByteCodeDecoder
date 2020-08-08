package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode {

    private int offsetInCurrentFrame;
    private String id;
    private String codeName;

    @Override
    public void execute(VirtualMachine virtualMachine) {

        int temp = virtualMachine.peekFrameStack(); //the index where this current frame starts
        int value = virtualMachine.get(temp + offsetInCurrentFrame); //get the value including the offset in the current frame where the value we want to copy resides
        virtualMachine.pushRTStack(value); // push that value onto the stack

        if(virtualMachine.isDumb()){
           System.out.println(codeName +  " " + offsetInCurrentFrame + " " + id + "     <load " + id + ">");
        }
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);
        offsetInCurrentFrame = Integer.parseInt(args.get(1));
        if(args.size() > 2) {
            id = args.get(2);
        }
    }
}
