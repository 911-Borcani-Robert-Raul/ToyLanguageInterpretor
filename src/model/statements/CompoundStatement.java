package model.statements;

import model.MyException;
import model.utils.MyIStack;
import model.ProgramState;

public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stk = state.getExecutionStack();

        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(first.deepCopy(), second.deepCopy());
    }
}
