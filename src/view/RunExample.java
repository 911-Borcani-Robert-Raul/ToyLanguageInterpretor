package view;

import controller.Controller;
import model.MyException;

public class RunExample extends Command {
    private final Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }

    @Override
    public void execute() {
        try {
            ctr.runAllSteps();
        }
        catch (MyException error) {
            System.out.println(error.getMessage());
        }
    }
}