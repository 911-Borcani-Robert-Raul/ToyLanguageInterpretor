package model.statements;

import model.*;
import model.expressions.MyExpression;
import model.utils.MyIList;
import model.values.Value;

public class PrintStatement implements IStatement {
    MyExpression exp;

    public PrintStatement(MyExpression expression) {
        exp = expression;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIList<Value> output = state.getOutput();
        output.add(exp.eval(state.getSymTable(), state.getHeap()));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(exp.deepCopy());
    }
}
