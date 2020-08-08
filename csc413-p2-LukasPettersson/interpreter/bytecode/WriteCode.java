package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode {

    private String codeName;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        //prints the top of the stack to the console without doing anythign else
        System.out.println(virtualMachine.getTopOfStack());

        if(virtualMachine.isDumb()){
            System.out.println(codeName);
        }
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);
    }
}
