package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode implements jumpCodes {


    private String label;
    private int resolvedLabel;
    private String codeName;
    @Override
    public void execute(VirtualMachine virtualMachine) {

       if (virtualMachine.popRTStack() == 0) {
           virtualMachine.setProgramCounter(resolvedLabel - 1);
       }

        if (virtualMachine.isDumb()) {
            System.out.println(codeName + " " + label);
        }
    }

    @Override
    public void init(ArrayList< String > args) {
        codeName = args.get(0);
        try {
            label = args.get(1);
        }
        catch(Exception e){
            System.out.println("Error in PopCode: "+ e);
        }
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
