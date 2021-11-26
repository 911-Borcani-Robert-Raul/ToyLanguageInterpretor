import controller.Controller;
import model.ProgramState;
import model.expressions.*;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.utils.*;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import repository.IProgramStateRepository;
import repository.ProgramStateRepository;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

import java.io.BufferedReader;
import java.sql.Ref;

public class Main {
    public static void main(String[] args) {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        ProgramState program1 = getProgram(ex1);
        Controller controller1 = getController(program1, "log1.txt");

        IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a",
                                new ArithmeticExpression('+', new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression('*',new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",
                                        new ArithmeticExpression('+',new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))))));
        ProgramState program2 = getProgram(ex2);
        Controller controller2 = getController(program2, "log2.txt");

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",
                                        new ValueExpression(new IntValue(2))), new AssignStatement("v",
                                        new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        ProgramState program3 = getProgram(ex3);
        Controller controller3 = getController(program3, "log3.txt");

        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("fileName", new StringType()),
                new CompoundStatement(new AssignStatement("fileName", new ValueExpression(new StringValue("test.txt"))),
                        new CompoundStatement(new OpenFileRead(new VariableExpression("fileName")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadStatement(new VariableExpression("fileName"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                new CompoundStatement(new ReadStatement(new VariableExpression("fileName"),
                                                                        "varc"), new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CompoundStatement( new CloseFileRead(new VariableExpression("fileName")),
                                                                                new CloseFileRead(new VariableExpression("fileName")) )))))))));
        ProgramState program4 = getProgram(ex4);
        Controller controller4 = getController(program4, "log4.txt");

        IStatement ex5 = new IfStatement(new RelationalExpression(new ValueExpression(new IntValue(2)),
                new ValueExpression(new IntValue(5)), RelationalExpressionOperator.GreaterThanOrEqual),
                new PrintStatement(new ValueExpression(new StringValue("conditia e adevarata"))),
                new PrintStatement(new ValueExpression(new StringValue("conditia nu e adevarata"))));
        ProgramState program5 = getProgram(ex5);
        Controller controller5 = getController(program5, "log5.txt");

        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
        ProgramState program6 = getProgram(ex6);
        Controller controller6 = getController(program6, "log6.txt");

        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)),
                                RelationalExpressionOperator.GreaterThan), new CompoundStatement(new PrintStatement((new VariableExpression("v"))),
                        new AssignStatement("v", new ArithmeticExpression(ArithmeticExpressionOperator.MINUS, new VariableExpression("v"),
                                new ValueExpression(new IntValue(1))))))));
        ProgramState program7 = getProgram(ex7);
        Controller controller7 = getController(program7, "log7.txt");
        
        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+',
                                                        new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))),
                                                        new ValueExpression(new IntValue(5))))
                                        )))));
        ProgramState program8 = getProgram(ex8);
        Controller controller8 = getController(program8, "log8.txt");

        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                                        new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression((new VariableExpression("v")))))));
        ProgramState program9 = getProgram(ex9);
        Controller controller9 = getController(program9, "log9.txt");

        IStatement ex10 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new IntType())),
                        new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))),
                        new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                                new CompoundStatement(new ForkStatement(
                                        new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))
                                ),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))
                                        )))));
        ProgramState program10 = getProgram(ex10);
        Controller controller10 = getController(program10, "log10.txt");

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), controller1));
        menu.addCommand(new RunExample("2", ex2.toString(), controller2));
        menu.addCommand(new RunExample("3", ex3.toString(), controller3));
        menu.addCommand(new RunExample("4", ex4.toString(), controller4));
        menu.addCommand(new RunExample("5", ex5.toString(), controller5));
        menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        menu.addCommand(new RunExample("7", ex7.toString(), controller7));
        menu.addCommand(new RunExample("8", ex8.toString(), controller8));
        menu.addCommand(new RunExample("9", ex9.toString(), controller9));
        menu.addCommand(new RunExample("10", ex10.toString(), controller10));
        menu.show();
    }

    private static Controller getController(ProgramState program, String logFile) {
        IProgramStateRepository repository = new ProgramStateRepository(logFile);
        repository.addProgramState(program);
        return new Controller(repository);
    }

    private static ProgramState getProgram(IStatement statement) {
        MyIStack<IStatement> executionStack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyHeap heapTable = new MyHeap();

        return new ProgramState(executionStack, symbolTable, output, fileTable, heapTable, statement);
    }
}
