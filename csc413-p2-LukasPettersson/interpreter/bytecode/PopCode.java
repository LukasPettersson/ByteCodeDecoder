package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {

    private int popNum;
    private String codeName;
    @Override
    public void execute(VirtualMachine virtualMachine) {
            //pops popNum amount of items off of the runtime stack
            //cannot pop values across frame boundaries
            //be able to check that it does not cross frame boundaries from popping values
        int currentFrameSize = virtualMachine.getCurrentFrameSize();

        for(int i = 0; i < popNum; i++) {
            virtualMachine.popRTStack();
        }
        if(virtualMachine.isDumb()){
            //this is not done
            System.out.println(codeName + " "+ popNum);
        }

        if(popNum > currentFrameSize && virtualMachine.getFramePointerStackSize() > 1){
            virtualMachine.haltProgram();
            //virtualMachine.setDump(false);
            System.out.println("\nError: Illegal POP across frame boundary. ");
            System.out.println("<set isDump in virtualMachine to true for extended information of the stack at the current point. >\n");
        }
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);
        try {
            popNum = Integer.parseInt(args.get(1));
        }
        catch(Exception e){
            System.out.println("Error in PopCode: "+ e);
        }
    }




}
