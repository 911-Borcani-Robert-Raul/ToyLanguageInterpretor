package model.expressions;

import model.MyException;
import model.utils.MyHeap;
import model.utils.MyIDictionary;
import model.values.Value;

public interface MyExpression {
    public Value eval(MyIDictionary<String, Value> symTable, MyHeap heapTable) throws MyException;

    MyExpression deepCopy();
}
