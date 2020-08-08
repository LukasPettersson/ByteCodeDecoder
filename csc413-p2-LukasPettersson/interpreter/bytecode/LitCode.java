package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {

    private String codeName;
    private int value;
    private String id;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        //push a value onto the runtime stack
        virtualMachine.pushRTStack(value);
        if(virtualMachine.isDumb()){
            if(id != null) {
                System.out.println(codeName + " " + value + " " + id + "     int " + id);
            }
            else{
                System.out.println(codeName + " " + value );
            }
        }
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);

        value = Integer.parseInt(args.get(1));

        if(args.size() > 2) {
            id = args.get(2);
        }
    }
}
