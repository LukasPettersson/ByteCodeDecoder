package interpreter.bytecode;

public interface jumpCodes {

    String getLabel();
    void setLabel(int label);
    boolean isLabel();
    boolean isAddressable();
}
