// Generated from GrammarsGrammar.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarsGrammarParser}.
 */
public interface GrammarsGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(GrammarsGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(GrammarsGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#rule1}.
	 * @param ctx the parse tree
	 */
	void enterRule1(GrammarsGrammarParser.Rule1Context ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#rule1}.
	 * @param ctx the parse tree
	 */
	void exitRule1(GrammarsGrammarParser.Rule1Context ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#attrs}.
	 * @param ctx the parse tree
	 */
	void enterAttrs(GrammarsGrammarParser.AttrsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#attrs}.
	 * @param ctx the parse tree
	 */
	void exitAttrs(GrammarsGrammarParser.AttrsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#state}.
	 * @param ctx the parse tree
	 */
	void enterState(GrammarsGrammarParser.StateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#state}.
	 * @param ctx the parse tree
	 */
	void exitState(GrammarsGrammarParser.StateContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#state2}.
	 * @param ctx the parse tree
	 */
	void enterState2(GrammarsGrammarParser.State2Context ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#state2}.
	 * @param ctx the parse tree
	 */
	void exitState2(GrammarsGrammarParser.State2Context ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#nnext}.
	 * @param ctx the parse tree
	 */
	void enterNnext(GrammarsGrammarParser.NnextContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#nnext}.
	 * @param ctx the parse tree
	 */
	void exitNnext(GrammarsGrammarParser.NnextContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(GrammarsGrammarParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(GrammarsGrammarParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(GrammarsGrammarParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(GrammarsGrammarParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#rreturn}.
	 * @param ctx the parse tree
	 */
	void enterRreturn(GrammarsGrammarParser.RreturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#rreturn}.
	 * @param ctx the parse tree
	 */
	void exitRreturn(GrammarsGrammarParser.RreturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#llocal}.
	 * @param ctx the parse tree
	 */
	void enterLlocal(GrammarsGrammarParser.LlocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#llocal}.
	 * @param ctx the parse tree
	 */
	void exitLlocal(GrammarsGrammarParser.LlocalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(GrammarsGrammarParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(GrammarsGrammarParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#after}.
	 * @param ctx the parse tree
	 */
	void enterAfter(GrammarsGrammarParser.AfterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#after}.
	 * @param ctx the parse tree
	 */
	void exitAfter(GrammarsGrammarParser.AfterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(GrammarsGrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(GrammarsGrammarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarsGrammarParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(GrammarsGrammarParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarsGrammarParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(GrammarsGrammarParser.ParamsContext ctx);
}