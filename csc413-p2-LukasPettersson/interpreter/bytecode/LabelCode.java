package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode implements jumpCodes{


    private String label;
    private int resolvedLabel;
    private String codeName;
    @Override
    public void execute(VirtualMachine virtualMachine) {
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
        return true;
    }
    public boolean isAddressable() {
        return false;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(int label) { this.resolvedLabel = label; }

}
