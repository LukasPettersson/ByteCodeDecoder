package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {

    private int offsetInCurrentFrame;
    private String codeName;
    private String id;


    @Override
    public void execute(VirtualMachine virtualMachine){

        //if the offsetincurrent frame would cross a frame boundary
        //throw exception halt program, the reason to halt the program is that it is now not safe to run and
        //virtualMachine.peekFrameStack();
        //throw new Exception();
        //checks if the offset would cross a frame boundary for storing
        if(  this.offsetInCurrentFrame > virtualMachine.getCurrentFrameSize()||this.offsetInCurrentFrame > virtualMachine.getRTStackSize()){
            virtualMachine.haltProgram();
            virtualMachine.setDump(false);
            System.out.println("\nError: Illegal STORE across frame boundary. ");
            System.out.println("<set isDump in virtualMachine to true for extended information of the stack at the current point. >\n");
        }
        virtualMachine.store(offsetInCurrentFrame);

        if(virtualMachine.isDumb()) {
            if (virtualMachine.isDumb()) {
                System.out.println(codeName + " " + offsetInCurrentFrame + id + "     " + id + "=" + virtualMachine.getTopOfStack());
            }


        }


    }

    @Override
    public void init(ArrayList< String > args){
        codeName = args.get(0);
        offsetInCurrentFrame = Integer.parseInt(args.get(1));
        if(args.size() > 1) {
            id = args.get(2);
        }
    }
}
