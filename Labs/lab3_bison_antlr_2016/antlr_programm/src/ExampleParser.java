// Generated from Example.g4 by ANTLR 4.5.3

	import java.util.HashMap;
	import java.util.Map;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExampleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, TYPE=21, NAME=22, LOGICTERM=23, SIGN=24, 
		BOOLEAN=25, INT=26, FLOAT=27, EXP=28, WS=29, STR=30;
	public static final int
		RULE_start = 0, RULE_command = 1, RULE_assigment = 2, RULE_print = 3, 
		RULE_swap = 4, RULE_read = 5, RULE_statement = 6, RULE_whileStatement = 7, 
		RULE_forStatement = 8, RULE_expr = 9, RULE_logicExpr = 10, RULE_var = 11, 
		RULE_type = 12;
	public static final String[] ruleNames = {
		"start", "command", "assigment", "print", "swap", "read", "statement", 
		"whileStatement", "forStatement", "expr", "logicExpr", "var", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'print'", "'('", "','", "')'", "'''", "'read'", "'_'", "'if'", 
		"'{'", "'}'", "'else'", "'while'", "'for'", "'range'", "'or'", "'and'", 
		"'int'", "'float'", "'bool'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "TYPE", "NAME", 
		"LOGICTERM", "SIGN", "BOOLEAN", "INT", "FLOAT", "EXP", "WS", "STR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Example.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		Map<String, Character> memory = new HashMap<String, Character>();

	public ExampleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public String res;
		public CommandContext command;
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);

				((StartContext)_localctx).res =  "int main() {\n";
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				((StartContext)_localctx).command = command();
				_localctx.res += ((StartContext)_localctx).command.res;
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << NAME))) != 0) );
			}
			_ctx.stop = _input.LT(-1);

					_localctx.res +=  "}\n";
					System.out.println(_localctx.res);
				
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandContext extends ParserRuleContext {
		public String res;
		public ReadContext read;
		public AssigmentContext assigment;
		public StatementContext statement;
		public ForStatementContext forStatement;
		public WhileStatementContext whileStatement;
		public SwapContext swap;
		public PrintContext print;
		public ReadContext read() {
			return getRuleContext(ReadContext.class,0);
		}
		public AssigmentContext assigment() {
			return getRuleContext(AssigmentContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public SwapContext swap() {
			return getRuleContext(SwapContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				((CommandContext)_localctx).read = read();

				    	((CommandContext)_localctx).res =  ((CommandContext)_localctx).read.res;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				((CommandContext)_localctx).assigment = assigment();

						((CommandContext)_localctx).res =  ((CommandContext)_localctx).assigment.res;
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				((CommandContext)_localctx).statement = statement();

						((CommandContext)_localctx).res =  ((CommandContext)_localctx).statement.res;
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(42);
				((CommandContext)_localctx).forStatement = forStatement();

						((CommandContext)_localctx).res =  ((CommandContext)_localctx).forStatement.res;
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(45);
				((CommandContext)_localctx).whileStatement = whileStatement();

						((CommandContext)_localctx).res =  ((CommandContext)_localctx).whileStatement.res;
					
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(48);
				((CommandContext)_localctx).swap = swap();

						((CommandContext)_localctx).res =  ((CommandContext)_localctx).swap.res;
					
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(51);
				((CommandContext)_localctx).print = print();

						((CommandContext)_localctx).res =  ((CommandContext)_localctx).print.res;
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssigmentContext extends ParserRuleContext {
		public String res;
		public Token NAME;
		public Token BOOLEAN;
		public Token INT;
		public Token FLOAT;
		public ExprContext expr;
		public TerminalNode NAME() { return getToken(ExampleParser.NAME, 0); }
		public TerminalNode BOOLEAN() { return getToken(ExampleParser.BOOLEAN, 0); }
		public TerminalNode INT() { return getToken(ExampleParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(ExampleParser.FLOAT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssigmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assigment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterAssigment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitAssigment(this);
		}
	}

	public final AssigmentContext assigment() throws RecognitionException {
		AssigmentContext _localctx = new AssigmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assigment);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				((AssigmentContext)_localctx).NAME = match(NAME);
				setState(57);
				match(T__0);
				setState(58);
				((AssigmentContext)_localctx).BOOLEAN = match(BOOLEAN);

				      	((AssigmentContext)_localctx).res =  "bool " + (((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null) + " = " + (((AssigmentContext)_localctx).BOOLEAN!=null?((AssigmentContext)_localctx).BOOLEAN.getText():null) + ";\n";
				      	memory.put((((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null), 'b');
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				((AssigmentContext)_localctx).NAME = match(NAME);
				setState(61);
				match(T__0);
				setState(62);
				((AssigmentContext)_localctx).INT = match(INT);

						((AssigmentContext)_localctx).res =  "int " + (((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null) + " = " + (((AssigmentContext)_localctx).INT!=null?((AssigmentContext)_localctx).INT.getText():null) + ";\n";
						memory.put((((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null), 'd');
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				((AssigmentContext)_localctx).NAME = match(NAME);
				setState(65);
				match(T__0);
				setState(66);
				((AssigmentContext)_localctx).FLOAT = match(FLOAT);

						((AssigmentContext)_localctx).res =  "float " + (((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null) + " = " + (((AssigmentContext)_localctx).FLOAT!=null?((AssigmentContext)_localctx).FLOAT.getText():null) + ";\n";
						memory.put((((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null), 'f');
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(68);
				((AssigmentContext)_localctx).NAME = match(NAME);
				setState(69);
				match(T__0);
				setState(70);
				((AssigmentContext)_localctx).expr = expr(0);

						((AssigmentContext)_localctx).res =  "int " + (((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null) + " = " + (((AssigmentContext)_localctx).expr!=null?_input.getText(((AssigmentContext)_localctx).expr.start,((AssigmentContext)_localctx).expr.stop):null) + ";\n";
						memory.put((((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null), ((AssigmentContext)_localctx).expr.t);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintContext extends ParserRuleContext {
		public String res;
		public List<String> lst;
		public Token NAME;
		public Token n1;
		public Token STR;
		public ExprContext expr;
		public List<TerminalNode> NAME() { return getTokens(ExampleParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(ExampleParser.NAME, i);
		}
		public TerminalNode STR() { return getToken(ExampleParser.STR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitPrint(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_print);

				String tmp = "";
				((PrintContext)_localctx).lst =  new ArrayList<String>();
			
		int _la;
		try {
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(T__1);
				setState(76);
				match(T__2);
				setState(77);
				((PrintContext)_localctx).NAME = match(NAME);
				 _localctx.lst.add((((PrintContext)_localctx).NAME!=null?((PrintContext)_localctx).NAME.getText():null)); 
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(79);
					match(T__3);
					setState(80);
					((PrintContext)_localctx).n1 = match(NAME);
					 _localctx.lst.add((((PrintContext)_localctx).n1!=null?((PrintContext)_localctx).n1.getText():null)); 
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(87);
				match(T__4);

						((PrintContext)_localctx).res =  "printf(\"";
						for (String n: _localctx.lst) {
				//			if (memory.containsKey(n)) {
								_localctx.res += "%" + memory.get(n) + " ";
				//			} else {
				//				_localctx.res += "%" + "d" + " ";
				//			}
						}
						_localctx.res += "\", ";
						for (int i = 0; i < _localctx.lst.size(); i++) {
							String n = _localctx.lst.get(i);
							if (i > 0) _localctx.res += ", ";
				        	_localctx.res += n;
				        }
				        _localctx.res += ");\n";
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				match(T__1);
				setState(90);
				match(T__2);
				setState(91);
				match(T__5);
				setState(92);
				((PrintContext)_localctx).STR = match(STR);
				setState(93);
				match(T__5);
				setState(94);
				match(T__4);

						((PrintContext)_localctx).res =  "printf(\"" + (((PrintContext)_localctx).STR!=null?((PrintContext)_localctx).STR.getText():null) + "\");\n";
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				match(T__1);
				setState(97);
				match(T__2);
				setState(98);
				((PrintContext)_localctx).expr = expr(0);
				setState(99);
				match(T__4);

				    	((PrintContext)_localctx).res =  "printf(%" + ((PrintContext)_localctx).expr.t + ", " + ((PrintContext)_localctx).expr.res + ");\n";
				    
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwapContext extends ParserRuleContext {
		public String res;
		public String tt;
		public VarContext op1;
		public VarContext op2;
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public SwapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_swap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterSwap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitSwap(this);
		}
	}

	public final SwapContext swap() throws RecognitionException {
		SwapContext _localctx = new SwapContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_swap);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			((SwapContext)_localctx).op1 = var();
			setState(105);
			match(T__3);
			setState(106);
			((SwapContext)_localctx).op2 = var();
			setState(107);
			match(T__0);
			setState(108);
			var();
			setState(109);
			match(T__3);
			setState(110);
			var();

					if (((SwapContext)_localctx).op1.t == 'd') ((SwapContext)_localctx).tt =  "int";
					if (((SwapContext)_localctx).op1.t == 'f') ((SwapContext)_localctx).tt =  "float";
					if (((SwapContext)_localctx).op1.t == 'b') ((SwapContext)_localctx).tt =  "boolean";
					((SwapContext)_localctx).res =  "";
					_localctx.res += _localctx.tt + " tmp = " + (((SwapContext)_localctx).op1!=null?_input.getText(((SwapContext)_localctx).op1.start,((SwapContext)_localctx).op1.stop):null) + ";\n";
					_localctx.res += (((SwapContext)_localctx).op1!=null?_input.getText(((SwapContext)_localctx).op1.start,((SwapContext)_localctx).op1.stop):null) + " = " + (((SwapContext)_localctx).op2!=null?_input.getText(((SwapContext)_localctx).op2.start,((SwapContext)_localctx).op2.stop):null)  + ";\n";
					_localctx.res += (((SwapContext)_localctx).op2!=null?_input.getText(((SwapContext)_localctx).op2.start,((SwapContext)_localctx).op2.stop):null) + " = tmp" + ";\n";
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReadContext extends ParserRuleContext {
		public String res;
		public Token NAME;
		public TypeContext type;
		public TerminalNode NAME() { return getToken(ExampleParser.NAME, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitRead(this);
		}
	}

	public final ReadContext read() throws RecognitionException {
		ReadContext _localctx = new ReadContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_read);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			((ReadContext)_localctx).NAME = match(NAME);
			setState(114);
			match(T__0);
			setState(115);
			match(T__6);
			setState(116);
			match(T__7);
			setState(117);
			((ReadContext)_localctx).type = type();
			setState(118);
			match(T__2);
			setState(119);
			match(T__4);

					((ReadContext)_localctx).res =  "scanf(%" + ((ReadContext)_localctx).type.t + ", &" + (((ReadContext)_localctx).NAME!=null?((ReadContext)_localctx).NAME.getText():null) + ");\n";
					memory.put((((ReadContext)_localctx).NAME!=null?((ReadContext)_localctx).NAME.getText():null), ((ReadContext)_localctx).type.t);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public String res;
		public String if1;
		public String if2;
		public LogicExprContext logicExpr;
		public CommandContext command;
		public LogicExprContext logicExpr() {
			return getRuleContext(LogicExprContext.class,0);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);

		    	((StatementContext)_localctx).res =  "";
		    	((StatementContext)_localctx).if1 =  "";
		    	((StatementContext)_localctx).if2 =  "";
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__8);
			setState(123);
			match(T__2);
			setState(124);
			((StatementContext)_localctx).logicExpr = logicExpr(0);
			setState(125);
			match(T__4);
			setState(126);
			match(T__9);
			setState(130); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(127);
				((StatementContext)_localctx).command = command();
				_localctx.if1 += ((StatementContext)_localctx).command.res;
				}
				}
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << NAME))) != 0) );
			setState(134);
			match(T__10);
			setState(146);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(135);
				match(T__11);
				setState(136);
				match(T__9);
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(137);
					((StatementContext)_localctx).command = command();
					_localctx.if2 += ((StatementContext)_localctx).command.res;
					}
					}
					setState(142); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << NAME))) != 0) );
				setState(144);
				match(T__10);
				}
			}


					((StatementContext)_localctx).res =  "if (" + (((StatementContext)_localctx).logicExpr!=null?_input.getText(((StatementContext)_localctx).logicExpr.start,((StatementContext)_localctx).logicExpr.stop):null) + ") {\n";
				
			}
			_ctx.stop = _input.LT(-1);

			        _localctx.res += _localctx.if1 + "}\n";
			        if (!_localctx.if2.isEmpty()) _localctx.res += "else {\n" + _localctx.if2 + "\n}\n";
			    
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public String res;
		public String while1;
		public LogicExprContext logicExpr;
		public CommandContext command;
		public LogicExprContext logicExpr() {
			return getRuleContext(LogicExprContext.class,0);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_whileStatement);

		    	((WhileStatementContext)_localctx).res =  "";
		    	((WhileStatementContext)_localctx).while1 =  "";
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__12);
			setState(151);
			match(T__2);
			setState(152);
			((WhileStatementContext)_localctx).logicExpr = logicExpr(0);
			setState(153);
			match(T__4);
			setState(154);
			match(T__9);
			setState(158); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(155);
				((WhileStatementContext)_localctx).command = command();
				_localctx.while1 += ((WhileStatementContext)_localctx).command.res;
				}
				}
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << NAME))) != 0) );
			setState(162);
			match(T__10);

					((WhileStatementContext)_localctx).res =  "if (" + (((WhileStatementContext)_localctx).logicExpr!=null?_input.getText(((WhileStatementContext)_localctx).logicExpr.start,((WhileStatementContext)_localctx).logicExpr.stop):null) + ") {\n";
				
			}
			_ctx.stop = _input.LT(-1);

			        _localctx.res += _localctx.while1 + "}\n";
			    
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public String res;
		public String for1;
		public Token NAME;
		public ExprContext expr;
		public CommandContext command;
		public ExprContext f1;
		public ExprContext f2;
		public ExprContext f11;
		public ExprContext f22;
		public ExprContext f33;
		public TerminalNode NAME() { return getToken(ExampleParser.NAME, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitForStatement(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_forStatement);

		    	((ForStatementContext)_localctx).res =  "";
		    	((ForStatementContext)_localctx).for1 =  "";
		    
		int _la;
		try {
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				match(T__13);
				setState(166);
				((ForStatementContext)_localctx).NAME = match(NAME);
				memory.put((((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null), 'd');
				setState(168);
				match(T__0);
				setState(169);
				match(T__14);
				setState(170);
				match(T__2);
				setState(171);
				((ForStatementContext)_localctx).expr = expr(0);
				setState(172);
				match(T__4);
				setState(173);
				match(T__9);
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(174);
					((ForStatementContext)_localctx).command = command();
					_localctx.for1 += ((ForStatementContext)_localctx).command.res;
					}
					}
					setState(179); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << NAME))) != 0) );
				setState(181);
				match(T__10);

						((ForStatementContext)_localctx).res =  "for (int " + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + " = 0;" + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + " < "  + ((ForStatementContext)_localctx).expr.res + "; " + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + "++) ";
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				match(T__13);
				setState(185);
				((ForStatementContext)_localctx).NAME = match(NAME);
				memory.put((((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null), 'd');
				setState(187);
				match(T__0);
				setState(188);
				match(T__14);
				setState(189);
				match(T__2);
				setState(190);
				((ForStatementContext)_localctx).f1 = expr(0);
				setState(191);
				match(T__3);
				setState(192);
				((ForStatementContext)_localctx).f2 = expr(0);
				setState(193);
				match(T__4);
				setState(194);
				match(T__9);
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(195);
					((ForStatementContext)_localctx).command = command();
					_localctx.for1 += ((ForStatementContext)_localctx).command.res;
					}
					}
					setState(200); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << NAME))) != 0) );
				setState(202);
				match(T__10);

						((ForStatementContext)_localctx).res =  "for (int " + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + " = " + ((ForStatementContext)_localctx).f1.res + "; " + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + " < "  + ((ForStatementContext)_localctx).f2.res + "; " + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + "++) ";
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(205);
				match(T__13);
				setState(206);
				((ForStatementContext)_localctx).NAME = match(NAME);
				memory.put((((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null), 'd');
				setState(208);
				match(T__0);
				setState(209);
				match(T__14);
				setState(210);
				match(T__2);
				setState(211);
				((ForStatementContext)_localctx).f11 = expr(0);
				setState(212);
				match(T__3);
				setState(213);
				((ForStatementContext)_localctx).f22 = expr(0);
				setState(214);
				match(T__3);
				setState(215);
				((ForStatementContext)_localctx).f33 = expr(0);
				setState(216);
				match(T__4);
				setState(217);
				match(T__9);
				setState(221); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(218);
					((ForStatementContext)_localctx).command = command();
					_localctx.for1 += ((ForStatementContext)_localctx).command.res;
					}
					}
					setState(223); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << NAME))) != 0) );
				setState(225);
				match(T__10);

						((ForStatementContext)_localctx).res =  "for (int " + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + " = " + ((ForStatementContext)_localctx).f11.res + "; " + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + " < "  + ((ForStatementContext)_localctx).f22.res + "; " +
						(((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null) + " = " + (((ForStatementContext)_localctx).NAME!=null?((ForStatementContext)_localctx).NAME.getText():null)  + " + " + ((ForStatementContext)_localctx).f33.res + ") ";
					
				}
				break;
			}
			_ctx.stop = _input.LT(-1);

			        _localctx.res += "{\n" + _localctx.for1 + "}\n";
			    
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public String res;
		public char t;
		public ExprContext op11;
		public ExprContext expr;
		public VarContext var;
		public Token op12;
		public Token op13;
		public Token op3;
		public ExprContext op2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode INT() { return getToken(ExampleParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(ExampleParser.FLOAT, 0); }
		public TerminalNode SIGN() { return getToken(ExampleParser.SIGN, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(231);
				match(T__2);
				setState(232);
				((ExprContext)_localctx).expr = expr(0);
				setState(233);
				match(T__4);

						((ExprContext)_localctx).res =  '(' + _input.getText(_localctx.start, _input.LT(-1)) + ')';
						((ExprContext)_localctx).t =  _localctx.t;
					
				}
				break;
			case NAME:
				{
				setState(236);
				((ExprContext)_localctx).var = var();

						((ExprContext)_localctx).res =  (((ExprContext)_localctx).var!=null?_input.getText(((ExprContext)_localctx).var.start,((ExprContext)_localctx).var.stop):null);
						((ExprContext)_localctx).t =  ((ExprContext)_localctx).var.t;
					
				}
				break;
			case INT:
				{
				setState(239);
				((ExprContext)_localctx).op12 = match(INT);

						((ExprContext)_localctx).res =  (((ExprContext)_localctx).op12!=null?((ExprContext)_localctx).op12.getText():null);
						((ExprContext)_localctx).t =  'd';
					
				}
				break;
			case FLOAT:
				{
				setState(241);
				((ExprContext)_localctx).op13 = match(FLOAT);

						((ExprContext)_localctx).res =  (((ExprContext)_localctx).op13!=null?((ExprContext)_localctx).op13.getText():null);
						((ExprContext)_localctx).t =  'f';
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					_localctx.op11 = _prevctx;
					_localctx.op11 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(245);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(246);
					((ExprContext)_localctx).op3 = match(SIGN);
					setState(247);
					((ExprContext)_localctx).op2 = ((ExprContext)_localctx).expr = expr(5);

					          		((ExprContext)_localctx).res =  (((ExprContext)_localctx).op11!=null?_input.getText(((ExprContext)_localctx).op11.start,((ExprContext)_localctx).op11.stop):null) + " " + (((ExprContext)_localctx).op3!=null?((ExprContext)_localctx).op3.getText():null) + " " + (((ExprContext)_localctx).op2!=null?_input.getText(((ExprContext)_localctx).op2.start,((ExprContext)_localctx).op2.stop):null);
					          		((ExprContext)_localctx).t =  ((ExprContext)_localctx).op11.t;
					          		if (((ExprContext)_localctx).op2.t == 'f') ((ExprContext)_localctx).t =  ((ExprContext)_localctx).op2.t;
					          	
					}
					} 
				}
				setState(254);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LogicExprContext extends ParserRuleContext {
		public String res;
		public LogicExprContext op15;
		public LogicExprContext op14;
		public ExprContext op16;
		public Token op22;
		public ExprContext op33;
		public Token BOOLEAN;
		public Token op2;
		public LogicExprContext op3;
		public List<LogicExprContext> logicExpr() {
			return getRuleContexts(LogicExprContext.class);
		}
		public LogicExprContext logicExpr(int i) {
			return getRuleContext(LogicExprContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LOGICTERM() { return getToken(ExampleParser.LOGICTERM, 0); }
		public TerminalNode BOOLEAN() { return getToken(ExampleParser.BOOLEAN, 0); }
		public LogicExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterLogicExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitLogicExpr(this);
		}
	}

	public final LogicExprContext logicExpr() throws RecognitionException {
		return logicExpr(0);
	}

	private LogicExprContext logicExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicExprContext _localctx = new LogicExprContext(_ctx, _parentState);
		LogicExprContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_logicExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(256);
				match(T__2);
				setState(257);
				((LogicExprContext)_localctx).op14 = logicExpr(0);
				setState(258);
				match(T__4);

						((LogicExprContext)_localctx).res =  "(" + (((LogicExprContext)_localctx).op14!=null?_input.getText(((LogicExprContext)_localctx).op14.start,((LogicExprContext)_localctx).op14.stop):null) + ")";
					
				}
				break;
			case 2:
				{
				setState(261);
				((LogicExprContext)_localctx).op16 = expr(0);
				setState(262);
				((LogicExprContext)_localctx).op22 = match(LOGICTERM);
				setState(263);
				((LogicExprContext)_localctx).op33 = expr(0);

						((LogicExprContext)_localctx).res =  (((LogicExprContext)_localctx).op16!=null?_input.getText(((LogicExprContext)_localctx).op16.start,((LogicExprContext)_localctx).op16.stop):null) + " " + (((LogicExprContext)_localctx).op22!=null?((LogicExprContext)_localctx).op22.getText():null) + " " + (((LogicExprContext)_localctx).op33!=null?_input.getText(((LogicExprContext)_localctx).op33.start,((LogicExprContext)_localctx).op33.stop):null);
					
				}
				break;
			case 3:
				{
				setState(266);
				((LogicExprContext)_localctx).BOOLEAN = match(BOOLEAN);

						((LogicExprContext)_localctx).res =  (((LogicExprContext)_localctx).BOOLEAN!=null?((LogicExprContext)_localctx).BOOLEAN.getText():null);
					
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(277);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicExprContext(_parentctx, _parentState);
					_localctx.op15 = _prevctx;
					_localctx.op15 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_logicExpr);
					setState(270);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(271);
					((LogicExprContext)_localctx).op2 = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__15 || _la==T__16) ) {
						((LogicExprContext)_localctx).op2 = (Token)_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(272);
					((LogicExprContext)_localctx).op3 = logicExpr(4);

					          		((LogicExprContext)_localctx).res =  (((LogicExprContext)_localctx).op15!=null?_input.getText(((LogicExprContext)_localctx).op15.start,((LogicExprContext)_localctx).op15.stop):null) + " " + (((LogicExprContext)_localctx).op2!=null?((LogicExprContext)_localctx).op2.getText():null) + " " + (((LogicExprContext)_localctx).op3!=null?_input.getText(((LogicExprContext)_localctx).op3.start,((LogicExprContext)_localctx).op3.stop):null);
					          	
					}
					} 
				}
				setState(279);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public char t;
		public Token NAME;
		public TerminalNode NAME() { return getToken(ExampleParser.NAME, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitVar(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			((VarContext)_localctx).NAME = match(NAME);

			//		if (memory.containsKey((((VarContext)_localctx).NAME!=null?((VarContext)_localctx).NAME.getText():null)))
			//			((VarContext)_localctx).t =  memory.get((((VarContext)_localctx).NAME!=null?((VarContext)_localctx).NAME.getText():null));
			//		else
						((VarContext)_localctx).t =  'd';
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public char t;
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleListener ) ((ExampleListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_type);
		try {
			setState(289);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				match(T__17);

						((TypeContext)_localctx).t =  'd';
					
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				match(T__18);

						((TypeContext)_localctx).t =  'f';
					
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				setState(287);
				match(T__19);

						((TypeContext)_localctx).t =  'b';
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 10:
			return logicExpr_sempred((LogicExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean logicExpr_sempred(LogicExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3 \u0126\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\6\2 \n\2\r\2\16\2!\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\39\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\5\4L\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5U\n\5\f\5\16\5X\13"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5i\n"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u0085\n\b\r\b\16\b\u0086\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\6\b\u008f\n\b\r\b\16\b\u0090\3\b\3\b\5\b\u0095\n"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u00a1\n\t\r\t\16\t\u00a2"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u00b4"+
		"\n\n\r\n\16\n\u00b5\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\6\n\u00c9\n\n\r\n\16\n\u00ca\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u00e0\n\n\r"+
		"\n\16\n\u00e1\3\n\3\n\3\n\5\n\u00e7\n\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00f6\n\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13\u00fd\n\13\f\13\16\13\u0100\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u010f\n\f\3\f\3\f\3\f\3\f\3\f\7\f\u0116"+
		"\n\f\f\f\16\f\u0119\13\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u0124\n\16\3\16\2\4\24\26\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3\3\2"+
		"\22\23\u0137\2\37\3\2\2\2\48\3\2\2\2\6K\3\2\2\2\bh\3\2\2\2\nj\3\2\2\2"+
		"\fs\3\2\2\2\16|\3\2\2\2\20\u0098\3\2\2\2\22\u00e6\3\2\2\2\24\u00f5\3\2"+
		"\2\2\26\u010e\3\2\2\2\30\u011a\3\2\2\2\32\u0123\3\2\2\2\34\35\5\4\3\2"+
		"\35\36\b\2\1\2\36 \3\2\2\2\37\34\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2"+
		"\2\2\"\3\3\2\2\2#$\5\f\7\2$%\b\3\1\2%9\3\2\2\2&\'\5\6\4\2\'(\b\3\1\2("+
		"9\3\2\2\2)*\5\16\b\2*+\b\3\1\2+9\3\2\2\2,-\5\22\n\2-.\b\3\1\2.9\3\2\2"+
		"\2/\60\5\20\t\2\60\61\b\3\1\2\619\3\2\2\2\62\63\5\n\6\2\63\64\b\3\1\2"+
		"\649\3\2\2\2\65\66\5\b\5\2\66\67\b\3\1\2\679\3\2\2\28#\3\2\2\28&\3\2\2"+
		"\28)\3\2\2\28,\3\2\2\28/\3\2\2\28\62\3\2\2\28\65\3\2\2\29\5\3\2\2\2:;"+
		"\7\30\2\2;<\7\3\2\2<=\7\33\2\2=L\b\4\1\2>?\7\30\2\2?@\7\3\2\2@A\7\34\2"+
		"\2AL\b\4\1\2BC\7\30\2\2CD\7\3\2\2DE\7\35\2\2EL\b\4\1\2FG\7\30\2\2GH\7"+
		"\3\2\2HI\5\24\13\2IJ\b\4\1\2JL\3\2\2\2K:\3\2\2\2K>\3\2\2\2KB\3\2\2\2K"+
		"F\3\2\2\2L\7\3\2\2\2MN\7\4\2\2NO\7\5\2\2OP\7\30\2\2PV\b\5\1\2QR\7\6\2"+
		"\2RS\7\30\2\2SU\b\5\1\2TQ\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2"+
		"\2\2XV\3\2\2\2YZ\7\7\2\2Zi\b\5\1\2[\\\7\4\2\2\\]\7\5\2\2]^\7\b\2\2^_\7"+
		" \2\2_`\7\b\2\2`a\7\7\2\2ai\b\5\1\2bc\7\4\2\2cd\7\5\2\2de\5\24\13\2ef"+
		"\7\7\2\2fg\b\5\1\2gi\3\2\2\2hM\3\2\2\2h[\3\2\2\2hb\3\2\2\2i\t\3\2\2\2"+
		"jk\5\30\r\2kl\7\6\2\2lm\5\30\r\2mn\7\3\2\2no\5\30\r\2op\7\6\2\2pq\5\30"+
		"\r\2qr\b\6\1\2r\13\3\2\2\2st\7\30\2\2tu\7\3\2\2uv\7\t\2\2vw\7\n\2\2wx"+
		"\5\32\16\2xy\7\5\2\2yz\7\7\2\2z{\b\7\1\2{\r\3\2\2\2|}\7\13\2\2}~\7\5\2"+
		"\2~\177\5\26\f\2\177\u0080\7\7\2\2\u0080\u0084\7\f\2\2\u0081\u0082\5\4"+
		"\3\2\u0082\u0083\b\b\1\2\u0083\u0085\3\2\2\2\u0084\u0081\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0094\7\r\2\2\u0089\u008a\7\16\2\2\u008a\u008e\7\f\2\2\u008b"+
		"\u008c\5\4\3\2\u008c\u008d\b\b\1\2\u008d\u008f\3\2\2\2\u008e\u008b\3\2"+
		"\2\2\u008f\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\7\r\2\2\u0093\u0095\3\2\2\2\u0094\u0089\3\2"+
		"\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\b\b\1\2\u0097"+
		"\17\3\2\2\2\u0098\u0099\7\17\2\2\u0099\u009a\7\5\2\2\u009a\u009b\5\26"+
		"\f\2\u009b\u009c\7\7\2\2\u009c\u00a0\7\f\2\2\u009d\u009e\5\4\3\2\u009e"+
		"\u009f\b\t\1\2\u009f\u00a1\3\2\2\2\u00a0\u009d\3\2\2\2\u00a1\u00a2\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a5\7\r\2\2\u00a5\u00a6\b\t\1\2\u00a6\21\3\2\2\2\u00a7\u00a8\7\20\2"+
		"\2\u00a8\u00a9\7\30\2\2\u00a9\u00aa\b\n\1\2\u00aa\u00ab\7\3\2\2\u00ab"+
		"\u00ac\7\21\2\2\u00ac\u00ad\7\5\2\2\u00ad\u00ae\5\24\13\2\u00ae\u00af"+
		"\7\7\2\2\u00af\u00b3\7\f\2\2\u00b0\u00b1\5\4\3\2\u00b1\u00b2\b\n\1\2\u00b2"+
		"\u00b4\3\2\2\2\u00b3\u00b0\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b3\3\2"+
		"\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\7\r\2\2\u00b8"+
		"\u00b9\b\n\1\2\u00b9\u00e7\3\2\2\2\u00ba\u00bb\7\20\2\2\u00bb\u00bc\7"+
		"\30\2\2\u00bc\u00bd\b\n\1\2\u00bd\u00be\7\3\2\2\u00be\u00bf\7\21\2\2\u00bf"+
		"\u00c0\7\5\2\2\u00c0\u00c1\5\24\13\2\u00c1\u00c2\7\6\2\2\u00c2\u00c3\5"+
		"\24\13\2\u00c3\u00c4\7\7\2\2\u00c4\u00c8\7\f\2\2\u00c5\u00c6\5\4\3\2\u00c6"+
		"\u00c7\b\n\1\2\u00c7\u00c9\3\2\2\2\u00c8\u00c5\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00cd\7\r\2\2\u00cd\u00ce\b\n\1\2\u00ce\u00e7\3\2\2\2\u00cf\u00d0\7\20"+
		"\2\2\u00d0\u00d1\7\30\2\2\u00d1\u00d2\b\n\1\2\u00d2\u00d3\7\3\2\2\u00d3"+
		"\u00d4\7\21\2\2\u00d4\u00d5\7\5\2\2\u00d5\u00d6\5\24\13\2\u00d6\u00d7"+
		"\7\6\2\2\u00d7\u00d8\5\24\13\2\u00d8\u00d9\7\6\2\2\u00d9\u00da\5\24\13"+
		"\2\u00da\u00db\7\7\2\2\u00db\u00df\7\f\2\2\u00dc\u00dd\5\4\3\2\u00dd\u00de"+
		"\b\n\1\2\u00de\u00e0\3\2\2\2\u00df\u00dc\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\7\r"+
		"\2\2\u00e4\u00e5\b\n\1\2\u00e5\u00e7\3\2\2\2\u00e6\u00a7\3\2\2\2\u00e6"+
		"\u00ba\3\2\2\2\u00e6\u00cf\3\2\2\2\u00e7\23\3\2\2\2\u00e8\u00e9\b\13\1"+
		"\2\u00e9\u00ea\7\5\2\2\u00ea\u00eb\5\24\13\2\u00eb\u00ec\7\7\2\2\u00ec"+
		"\u00ed\b\13\1\2\u00ed\u00f6\3\2\2\2\u00ee\u00ef\5\30\r\2\u00ef\u00f0\b"+
		"\13\1\2\u00f0\u00f6\3\2\2\2\u00f1\u00f2\7\34\2\2\u00f2\u00f6\b\13\1\2"+
		"\u00f3\u00f4\7\35\2\2\u00f4\u00f6\b\13\1\2\u00f5\u00e8\3\2\2\2\u00f5\u00ee"+
		"\3\2\2\2\u00f5\u00f1\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00fe\3\2\2\2\u00f7"+
		"\u00f8\f\6\2\2\u00f8\u00f9\7\32\2\2\u00f9\u00fa\5\24\13\7\u00fa\u00fb"+
		"\b\13\1\2\u00fb\u00fd\3\2\2\2\u00fc\u00f7\3\2\2\2\u00fd\u0100\3\2\2\2"+
		"\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\25\3\2\2\2\u0100\u00fe"+
		"\3\2\2\2\u0101\u0102\b\f\1\2\u0102\u0103\7\5\2\2\u0103\u0104\5\26\f\2"+
		"\u0104\u0105\7\7\2\2\u0105\u0106\b\f\1\2\u0106\u010f\3\2\2\2\u0107\u0108"+
		"\5\24\13\2\u0108\u0109\7\31\2\2\u0109\u010a\5\24\13\2\u010a\u010b\b\f"+
		"\1\2\u010b\u010f\3\2\2\2\u010c\u010d\7\33\2\2\u010d\u010f\b\f\1\2\u010e"+
		"\u0101\3\2\2\2\u010e\u0107\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0117\3\2"+
		"\2\2\u0110\u0111\f\5\2\2\u0111\u0112\t\2\2\2\u0112\u0113\5\26\f\6\u0113"+
		"\u0114\b\f\1\2\u0114\u0116\3\2\2\2\u0115\u0110\3\2\2\2\u0116\u0119\3\2"+
		"\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\27\3\2\2\2\u0119\u0117"+
		"\3\2\2\2\u011a\u011b\7\30\2\2\u011b\u011c\b\r\1\2\u011c\31\3\2\2\2\u011d"+
		"\u011e\7\24\2\2\u011e\u0124\b\16\1\2\u011f\u0120\7\25\2\2\u0120\u0124"+
		"\b\16\1\2\u0121\u0122\7\26\2\2\u0122\u0124\b\16\1\2\u0123\u011d\3\2\2"+
		"\2\u0123\u011f\3\2\2\2\u0123\u0121\3\2\2\2\u0124\33\3\2\2\2\24!8KVh\u0086"+
		"\u0090\u0094\u00a2\u00b5\u00ca\u00e1\u00e6\u00f5\u00fe\u010e\u0117\u0123";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}