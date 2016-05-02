// Generated from Example.g4 by ANTLR 4.5.3

	import java.util.HashMap;
	import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExampleParser}.
 */
public interface ExampleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExampleParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ExampleParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ExampleParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(ExampleParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(ExampleParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#assigment}.
	 * @param ctx the parse tree
	 */
	void enterAssigment(ExampleParser.AssigmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#assigment}.
	 * @param ctx the parse tree
	 */
	void exitAssigment(ExampleParser.AssigmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(ExampleParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(ExampleParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#swap}.
	 * @param ctx the parse tree
	 */
	void enterSwap(ExampleParser.SwapContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#swap}.
	 * @param ctx the parse tree
	 */
	void exitSwap(ExampleParser.SwapContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#read}.
	 * @param ctx the parse tree
	 */
	void enterRead(ExampleParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#read}.
	 * @param ctx the parse tree
	 */
	void exitRead(ExampleParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ExampleParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ExampleParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(ExampleParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(ExampleParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(ExampleParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(ExampleParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ExampleParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ExampleParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#logicExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpr(ExampleParser.LogicExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#logicExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpr(ExampleParser.LogicExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(ExampleParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(ExampleParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ExampleParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ExampleParser.TypeContext ctx);
}