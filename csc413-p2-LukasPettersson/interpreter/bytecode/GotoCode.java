package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode implements jumpCodes{

    private String label;
    private int resolvedLabel;
    private String codeName;
    @Override
    public void execute(VirtualMachine virtualMachine) {
        //virtualMachine.setReturnAddress(virtualMachine.getProgramCounter()); // sets the value of programcounter+ 1 to avoid infinite loop
        virtualMachine.setProgramCounter(resolvedLabel - 1);
        if(virtualMachine.isDumb()){
            System.out.println(codeName +  " " +label);
        }
    }

    @Override
    public void init(ArrayList< String > args) {

        codeName = args.get(0);
        label = args.get(1);
    }

    public boolean isLabel(){
        return false;
    }
    public boolean isAddressable() {
        return true;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(int label) {
        this.resolvedLabel = label;
    }
}
