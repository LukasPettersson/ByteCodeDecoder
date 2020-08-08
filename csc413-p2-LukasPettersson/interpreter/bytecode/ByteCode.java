package interpreter.bytecode;

        import interpreter.VirtualMachine;

        import java.util.ArrayList;
        import java.util.HashMap;

public abstract class ByteCode{

    public abstract void execute(VirtualMachine virtualMachine);

    public abstract void init(ArrayList<String> args);

}
