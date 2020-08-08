package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class HaltCode extends ByteCode{


    private String codeName;
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setDump(false);
        virtualMachine.haltProgram();
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);
    }

}
