// Generated from Translator.g4 by ANTLR 4.5.3

	import java.util.HashMap;
	import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TranslatorParser}.
 */
public interface TranslatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(TranslatorParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(TranslatorParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(TranslatorParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(TranslatorParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#assigment}.
	 * @param ctx the parse tree
	 */
	void enterAssigment(TranslatorParser.AssigmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#assigment}.
	 * @param ctx the parse tree
	 */
	void exitAssigment(TranslatorParser.AssigmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(TranslatorParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(TranslatorParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#swap}.
	 * @param ctx the parse tree
	 */
	void enterSwap(TranslatorParser.SwapContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#swap}.
	 * @param ctx the parse tree
	 */
	void exitSwap(TranslatorParser.SwapContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#read}.
	 * @param ctx the parse tree
	 */
	void enterRead(TranslatorParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#read}.
	 * @param ctx the parse tree
	 */
	void exitRead(TranslatorParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(TranslatorParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(TranslatorParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(TranslatorParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(TranslatorParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(TranslatorParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(TranslatorParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(TranslatorParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(TranslatorParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#logicExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpr(TranslatorParser.LogicExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#logicExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpr(TranslatorParser.LogicExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(TranslatorParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(TranslatorParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link TranslatorParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TranslatorParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TranslatorParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TranslatorParser.TypeContext ctx);
}