package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {

    private boolean isDump;
    private String codeName;
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setDump(isDump);
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);

        isDump = args.get(1).equals("ON");
    }
}
