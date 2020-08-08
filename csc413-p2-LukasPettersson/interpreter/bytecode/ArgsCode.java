package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {

    private String codeName;
    private int activationFrameNum;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        //add what the size of stack is at this moment in time to create a frame boundary

       int size = virtualMachine.getRTStackSize();
       virtualMachine.addFrame(size - activationFrameNum);

        if(virtualMachine.isDumb()){
            System.out.println(codeName +  " " + activationFrameNum);
        }
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);
        activationFrameNum = Integer.parseInt(args.get(1));
    }

}
