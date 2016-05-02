// Generated from Translator.g4 by ANTLR 4.5.3

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
public class TranslatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, TYPE=20, NAME=21, LOGICTERM=22, SIGN=23, BOOLEAN=24, 
		INT=25, FLOAT=26, EXP=27, WS=28, STR=29;
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
		null, "'='", "'print'", "'('", "','", "')'", "'read'", "'_'", "'if'", 
		"'{'", "'}'", "'else'", "'while'", "'for'", "'range'", "'or'", "'and'", 
		"'int'", "'float'", "'bool'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "TYPE", "NAME", "LOGICTERM", 
		"SIGN", "BOOLEAN", "INT", "FLOAT", "EXP", "WS", "STR"
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
	public String getGrammarFileName() { return "Translator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		Map<String, Character> memory = new HashMap<String, Character>();
		List<String> variables = new ArrayList<String>();

	public TranslatorParser(TokenStream input) {
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
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitStart(this);
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << NAME))) != 0) );
			}
			_ctx.stop = _input.LT(-1);

					for (String s: variables) System.out.println(s);
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
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitCommand(this);
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
		public String tt;
		public Token NAME;
		public ExprContext expr;
		public TerminalNode NAME() { return getToken(TranslatorParser.NAME, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssigmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assigment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterAssigment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitAssigment(this);
		}
	}

	public final AssigmentContext assigment() throws RecognitionException {
		AssigmentContext _localctx = new AssigmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assigment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			((AssigmentContext)_localctx).NAME = match(NAME);
			setState(57);
			match(T__0);
			setState(58);
			((AssigmentContext)_localctx).expr = expr(0);

					((AssigmentContext)_localctx).res =  (((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null) + " = " + (((AssigmentContext)_localctx).expr!=null?_input.getText(((AssigmentContext)_localctx).expr.start,((AssigmentContext)_localctx).expr.stop):null) + ";\n";
					if (!memory.containsKey((((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null))) {
						if (((AssigmentContext)_localctx).expr.t == 'd') ((AssigmentContext)_localctx).tt =  "int";
						if (((AssigmentContext)_localctx).expr.t == 'f') ((AssigmentContext)_localctx).tt =  "float";
						if (((AssigmentContext)_localctx).expr.t == 'b') ((AssigmentContext)_localctx).tt =  "bool";
						variables.add(_localctx.tt + " " + (((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null) + ";\n");
						memory.put((((AssigmentContext)_localctx).NAME!=null?((AssigmentContext)_localctx).NAME.getText():null), ((AssigmentContext)_localctx).expr.t);
					}
				
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
		public String tmp;
		public VarContext var;
		public VarContext n1;
		public Token STR;
		public ExprContext expr;
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public TerminalNode STR() { return getToken(TranslatorParser.STR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitPrint(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_print);

				String tmp = "";
				((PrintContext)_localctx).lst =  new ArrayList<String>();
			
		int _la;
		try {
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(T__1);
				setState(62);
				match(T__2);
				setState(63);
				((PrintContext)_localctx).var = var('d');
				 _localctx.lst.add((((PrintContext)_localctx).var!=null?_input.getText(((PrintContext)_localctx).var.start,((PrintContext)_localctx).var.stop):null)); 
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(65);
					match(T__3);
					setState(66);
					((PrintContext)_localctx).n1 = ((PrintContext)_localctx).var = var('d');
					 _localctx.lst.add((((PrintContext)_localctx).n1!=null?_input.getText(((PrintContext)_localctx).n1.start,((PrintContext)_localctx).n1.stop):null)); 
					}
					}
					setState(73);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(74);
				match(T__4);

						((PrintContext)_localctx).res =  "printf(\"";
						for (String n: _localctx.lst) {
							if (memory.containsKey(n)) {
								_localctx.res += "%" + memory.get(n) + " ";
							} else {
								_localctx.res += "%" + "d" + " ";
							}
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
				setState(77);
				match(T__1);
				setState(78);
				match(T__2);
				setState(79);
				((PrintContext)_localctx).STR = match(STR);
				setState(80);
				match(T__4);

						((PrintContext)_localctx).tmp =  (((PrintContext)_localctx).STR!=null?((PrintContext)_localctx).STR.getText():null);
						_localctx.tmp.replace('\'', '"');
						((PrintContext)_localctx).res =  "printf(" + _localctx.tmp + ");\n";
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				match(T__1);
				setState(83);
				match(T__2);
				setState(84);
				((PrintContext)_localctx).expr = expr(0);
				setState(85);
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
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterSwap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitSwap(this);
		}
	}

	public final SwapContext swap() throws RecognitionException {
		SwapContext _localctx = new SwapContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_swap);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			((SwapContext)_localctx).op1 = var('d');
			setState(91);
			match(T__3);
			setState(92);
			((SwapContext)_localctx).op2 = var('d');
			setState(93);
			match(T__0);
			setState(94);
			var('d');
			setState(95);
			match(T__3);
			setState(96);
			var('d');

					((SwapContext)_localctx).tt =  ((SwapContext)_localctx).op1.tt;
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
		public String tt;
		public Token NAME;
		public TypeContext type;
		public TerminalNode NAME() { return getToken(TranslatorParser.NAME, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitRead(this);
		}
	}

	public final ReadContext read() throws RecognitionException {
		ReadContext _localctx = new ReadContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_read);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			((ReadContext)_localctx).NAME = match(NAME);
			setState(100);
			match(T__0);
			setState(101);
			match(T__5);
			setState(102);
			match(T__6);
			setState(103);
			((ReadContext)_localctx).type = type();
			setState(104);
			match(T__2);
			setState(105);
			match(T__4);

					((ReadContext)_localctx).res =  "scanf(\"%" + ((ReadContext)_localctx).type.t + "\", &" + (((ReadContext)_localctx).NAME!=null?((ReadContext)_localctx).NAME.getText():null) + ");\n";
					if (!memory.containsKey((((ReadContext)_localctx).NAME!=null?((ReadContext)_localctx).NAME.getText():null))) {
						if (((ReadContext)_localctx).type.t == 'd') ((ReadContext)_localctx).tt =  "int";
						if (((ReadContext)_localctx).type.t == 'f') ((ReadContext)_localctx).tt =  "float";
						if (((ReadContext)_localctx).type.t == 'b') ((ReadContext)_localctx).tt =  "boolean";
						variables.add(_localctx.tt + " " + (((ReadContext)_localctx).NAME!=null?((ReadContext)_localctx).NAME.getText():null) + ";\n");
						memory.put((((ReadContext)_localctx).NAME!=null?((ReadContext)_localctx).NAME.getText():null), ((ReadContext)_localctx).type.t);
					}
				
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
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitStatement(this);
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
			setState(108);
			match(T__7);
			setState(109);
			match(T__2);
			setState(110);
			((StatementContext)_localctx).logicExpr = logicExpr(0);
			setState(111);
			match(T__4);
			setState(112);
			match(T__8);
			setState(116); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113);
				((StatementContext)_localctx).command = command();
				_localctx.if1 += ((StatementContext)_localctx).command.res;
				}
				}
				setState(118); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << NAME))) != 0) );
			setState(120);
			match(T__9);
			setState(132);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(121);
				match(T__10);
				setState(122);
				match(T__8);
				setState(126); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(123);
					((StatementContext)_localctx).command = command();
					_localctx.if2 += ((StatementContext)_localctx).command.res;
					}
					}
					setState(128); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << NAME))) != 0) );
				setState(130);
				match(T__9);
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
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitWhileStatement(this);
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
			setState(136);
			match(T__11);
			setState(137);
			match(T__2);
			setState(138);
			((WhileStatementContext)_localctx).logicExpr = logicExpr(0);
			setState(139);
			match(T__4);
			setState(140);
			match(T__8);
			setState(144); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(141);
				((WhileStatementContext)_localctx).command = command();
				_localctx.while1 += ((WhileStatementContext)_localctx).command.res;
				}
				}
				setState(146); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << NAME))) != 0) );
			setState(148);
			match(T__9);

					((WhileStatementContext)_localctx).res =  "while (" + (((WhileStatementContext)_localctx).logicExpr!=null?_input.getText(((WhileStatementContext)_localctx).logicExpr.start,((WhileStatementContext)_localctx).logicExpr.stop):null) + ") {\n";
				
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
		public VarContext var;
		public ExprContext expr;
		public CommandContext command;
		public ExprContext f1;
		public ExprContext f2;
		public ExprContext f11;
		public ExprContext f22;
		public ExprContext f33;
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
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
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitForStatement(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_forStatement);

		    	((ForStatementContext)_localctx).res =  "";
		    	((ForStatementContext)_localctx).for1 =  "";
		    
		int _la;
		try {
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				match(T__12);
				setState(152);
				((ForStatementContext)_localctx).var = var('d');
				setState(153);
				match(T__0);
				setState(154);
				match(T__13);
				setState(155);
				match(T__2);
				setState(156);
				((ForStatementContext)_localctx).expr = expr(0);
				setState(157);
				match(T__4);
				setState(158);
				match(T__8);
				setState(162); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(159);
					((ForStatementContext)_localctx).command = command();
					_localctx.for1 += ((ForStatementContext)_localctx).command.res;
					}
					}
					setState(164); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << NAME))) != 0) );
				setState(166);
				match(T__9);

						((ForStatementContext)_localctx).res =  "for (" + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + " = 0;" + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + " < "  + ((ForStatementContext)_localctx).expr.res + "; " + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + "++) ";
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				match(T__12);
				setState(170);
				((ForStatementContext)_localctx).var = var('d');
				setState(171);
				match(T__0);
				setState(172);
				match(T__13);
				setState(173);
				match(T__2);
				setState(174);
				((ForStatementContext)_localctx).f1 = expr(0);
				setState(175);
				match(T__3);
				setState(176);
				((ForStatementContext)_localctx).f2 = expr(0);
				setState(177);
				match(T__4);
				setState(178);
				match(T__8);
				setState(182); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(179);
					((ForStatementContext)_localctx).command = command();
					_localctx.for1 += ((ForStatementContext)_localctx).command.res;
					}
					}
					setState(184); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << NAME))) != 0) );
				setState(186);
				match(T__9);

						((ForStatementContext)_localctx).res =  "for (" + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + " = " + ((ForStatementContext)_localctx).f1.res + "; " + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + " < "  + ((ForStatementContext)_localctx).f2.res + "; " + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + "++) ";
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				match(T__12);
				setState(190);
				((ForStatementContext)_localctx).var = var('d');
				setState(191);
				match(T__0);
				setState(192);
				match(T__13);
				setState(193);
				match(T__2);
				setState(194);
				((ForStatementContext)_localctx).f11 = expr(0);
				setState(195);
				match(T__3);
				setState(196);
				((ForStatementContext)_localctx).f22 = expr(0);
				setState(197);
				match(T__3);
				setState(198);
				((ForStatementContext)_localctx).f33 = expr(0);
				setState(199);
				match(T__4);
				setState(200);
				match(T__8);
				setState(204); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(201);
					((ForStatementContext)_localctx).command = command();
					_localctx.for1 += ((ForStatementContext)_localctx).command.res;
					}
					}
					setState(206); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__11) | (1L << T__12) | (1L << NAME))) != 0) );
				setState(208);
				match(T__9);

						((ForStatementContext)_localctx).res =  "for (" + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + " = " + ((ForStatementContext)_localctx).f11.res + "; " + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + " < "  + ((ForStatementContext)_localctx).f22.res + "; " +
						(((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null) + " = " + (((ForStatementContext)_localctx).var!=null?_input.getText(((ForStatementContext)_localctx).var.start,((ForStatementContext)_localctx).var.stop):null)  + " + " + ((ForStatementContext)_localctx).f33.res + ") ";
					
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
		public TerminalNode INT() { return getToken(TranslatorParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(TranslatorParser.FLOAT, 0); }
		public TerminalNode SIGN() { return getToken(TranslatorParser.SIGN, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitExpr(this);
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
			setState(226);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(214);
				match(T__2);
				setState(215);
				((ExprContext)_localctx).expr = expr(0);
				setState(216);
				match(T__4);

						((ExprContext)_localctx).res =  '(' + _input.getText(_localctx.start, _input.LT(-1)) + ')';
						((ExprContext)_localctx).t =  _localctx.t;
					
				}
				break;
			case NAME:
				{
				setState(219);
				((ExprContext)_localctx).var = var('d');

						((ExprContext)_localctx).res =  (((ExprContext)_localctx).var!=null?_input.getText(((ExprContext)_localctx).var.start,((ExprContext)_localctx).var.stop):null);
						((ExprContext)_localctx).t =  ((ExprContext)_localctx).var.t;
					
				}
				break;
			case INT:
				{
				setState(222);
				((ExprContext)_localctx).op12 = match(INT);

						((ExprContext)_localctx).res =  (((ExprContext)_localctx).op12!=null?((ExprContext)_localctx).op12.getText():null);
						((ExprContext)_localctx).t =  'd';
					
				}
				break;
			case FLOAT:
				{
				setState(224);
				((ExprContext)_localctx).op13 = match(FLOAT);

						((ExprContext)_localctx).res =  (((ExprContext)_localctx).op13!=null?((ExprContext)_localctx).op13.getText():null);
						((ExprContext)_localctx).t =  'f';
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
					setState(228);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(229);
					((ExprContext)_localctx).op3 = match(SIGN);
					setState(230);
					((ExprContext)_localctx).op2 = ((ExprContext)_localctx).expr = expr(5);

					          		((ExprContext)_localctx).res =  (((ExprContext)_localctx).op11!=null?_input.getText(((ExprContext)_localctx).op11.start,((ExprContext)_localctx).op11.stop):null) + " " + (((ExprContext)_localctx).op3!=null?((ExprContext)_localctx).op3.getText():null) + " " + (((ExprContext)_localctx).op2!=null?_input.getText(((ExprContext)_localctx).op2.start,((ExprContext)_localctx).op2.stop):null);
					          		((ExprContext)_localctx).t =  ((ExprContext)_localctx).op11.t;
					          		if (((ExprContext)_localctx).op2.t == 'f' || ((ExprContext)_localctx).op11.t == 'f') ((ExprContext)_localctx).t =  'f';
					          	
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		public TerminalNode LOGICTERM() { return getToken(TranslatorParser.LOGICTERM, 0); }
		public TerminalNode BOOLEAN() { return getToken(TranslatorParser.BOOLEAN, 0); }
		public LogicExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterLogicExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitLogicExpr(this);
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
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(239);
				match(T__2);
				setState(240);
				((LogicExprContext)_localctx).op14 = logicExpr(0);
				setState(241);
				match(T__4);

						((LogicExprContext)_localctx).res =  "(" + (((LogicExprContext)_localctx).op14!=null?_input.getText(((LogicExprContext)_localctx).op14.start,((LogicExprContext)_localctx).op14.stop):null) + ")";
					
				}
				break;
			case 2:
				{
				setState(244);
				((LogicExprContext)_localctx).op16 = expr(0);
				setState(245);
				((LogicExprContext)_localctx).op22 = match(LOGICTERM);
				setState(246);
				((LogicExprContext)_localctx).op33 = expr(0);

						((LogicExprContext)_localctx).res =  (((LogicExprContext)_localctx).op16!=null?_input.getText(((LogicExprContext)_localctx).op16.start,((LogicExprContext)_localctx).op16.stop):null) + " " + (((LogicExprContext)_localctx).op22!=null?((LogicExprContext)_localctx).op22.getText():null) + " " + (((LogicExprContext)_localctx).op33!=null?_input.getText(((LogicExprContext)_localctx).op33.start,((LogicExprContext)_localctx).op33.stop):null);
					
				}
				break;
			case 3:
				{
				setState(249);
				((LogicExprContext)_localctx).BOOLEAN = match(BOOLEAN);

						((LogicExprContext)_localctx).res =  (((LogicExprContext)_localctx).BOOLEAN!=null?((LogicExprContext)_localctx).BOOLEAN.getText():null);
					
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(260);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
					setState(253);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(254);
					((LogicExprContext)_localctx).op2 = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__14 || _la==T__15) ) {
						((LogicExprContext)_localctx).op2 = (Token)_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(255);
					((LogicExprContext)_localctx).op3 = logicExpr(4);

					          		((LogicExprContext)_localctx).res =  (((LogicExprContext)_localctx).op15!=null?_input.getText(((LogicExprContext)_localctx).op15.start,((LogicExprContext)_localctx).op15.stop):null) + " " + (((LogicExprContext)_localctx).op2!=null?((LogicExprContext)_localctx).op2.getText():null) + " " + (((LogicExprContext)_localctx).op3!=null?_input.getText(((LogicExprContext)_localctx).op3.start,((LogicExprContext)_localctx).op3.stop):null);
					          	
					}
					} 
				}
				setState(262);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		public char in_t;
		public char t;
		public String tt;
		public Token NAME;
		public TerminalNode NAME() { return getToken(TranslatorParser.NAME, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public VarContext(ParserRuleContext parent, int invokingState, char in_t) {
			super(parent, invokingState);
			this.in_t = in_t;
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitVar(this);
		}
	}

	public final VarContext var(char in_t) throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState(), in_t);
		enterRule(_localctx, 22, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			((VarContext)_localctx).NAME = match(NAME);

					if (memory.containsKey((((VarContext)_localctx).NAME!=null?((VarContext)_localctx).NAME.getText():null))) {
						((VarContext)_localctx).t =  memory.get((((VarContext)_localctx).NAME!=null?((VarContext)_localctx).NAME.getText():null));
					} else {
						((VarContext)_localctx).t =  _localctx.in_t;
					}
					if (_localctx.t == 'd') ((VarContext)_localctx).tt =  "int";
			        if (_localctx.t == 'f') ((VarContext)_localctx).tt =  "float";
			        if (_localctx.t == 'b') ((VarContext)_localctx).tt =  "boolean";
					if (!memory.containsKey((((VarContext)_localctx).NAME!=null?((VarContext)_localctx).NAME.getText():null))) {
						memory.put((((VarContext)_localctx).NAME!=null?((VarContext)_localctx).NAME.getText():null), _localctx.t);
						variables.add(_localctx.tt + " " + (((VarContext)_localctx).NAME!=null?((VarContext)_localctx).NAME.getText():null) + ";\n");
					}
				
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
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TranslatorListener ) ((TranslatorListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_type);
		try {
			setState(272);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(T__16);

						((TypeContext)_localctx).t =  'd';
					
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(268);
				match(T__17);

						((TypeContext)_localctx).t =  'f';
					
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				match(T__18);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37\u0115\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\6\2 \n\2\r\2\16\2!\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\5\39\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7"+
		"\5H\n\5\f\5\16\5K\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\5\5[\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\6\bw\n\b\r\b\16\b"+
		"x\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u0081\n\b\r\b\16\b\u0082\3\b\3\b\5\b\u0087"+
		"\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u0093\n\t\r\t\16\t\u0094"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u00a5\n\n"+
		"\r\n\16\n\u00a6\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\6\n\u00b9\n\n\r\n\16\n\u00ba\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u00cf\n\n\r\n\16\n\u00d0"+
		"\3\n\3\n\3\n\5\n\u00d6\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13\u00e5\n\13\3\13\3\13\3\13\3\13\3\13\7\13\u00ec"+
		"\n\13\f\13\16\13\u00ef\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\5\f\u00fe\n\f\3\f\3\f\3\f\3\f\3\f\7\f\u0105\n\f\f\f\16\f\u0108"+
		"\13\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0113\n\16\3\16\2"+
		"\4\24\26\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3\3\2\21\22\u0123\2\37"+
		"\3\2\2\2\48\3\2\2\2\6:\3\2\2\2\bZ\3\2\2\2\n\\\3\2\2\2\fe\3\2\2\2\16n\3"+
		"\2\2\2\20\u008a\3\2\2\2\22\u00d5\3\2\2\2\24\u00e4\3\2\2\2\26\u00fd\3\2"+
		"\2\2\30\u0109\3\2\2\2\32\u0112\3\2\2\2\34\35\5\4\3\2\35\36\b\2\1\2\36"+
		" \3\2\2\2\37\34\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#"+
		"$\5\f\7\2$%\b\3\1\2%9\3\2\2\2&\'\5\6\4\2\'(\b\3\1\2(9\3\2\2\2)*\5\16\b"+
		"\2*+\b\3\1\2+9\3\2\2\2,-\5\22\n\2-.\b\3\1\2.9\3\2\2\2/\60\5\20\t\2\60"+
		"\61\b\3\1\2\619\3\2\2\2\62\63\5\n\6\2\63\64\b\3\1\2\649\3\2\2\2\65\66"+
		"\5\b\5\2\66\67\b\3\1\2\679\3\2\2\28#\3\2\2\28&\3\2\2\28)\3\2\2\28,\3\2"+
		"\2\28/\3\2\2\28\62\3\2\2\28\65\3\2\2\29\5\3\2\2\2:;\7\27\2\2;<\7\3\2\2"+
		"<=\5\24\13\2=>\b\4\1\2>\7\3\2\2\2?@\7\4\2\2@A\7\5\2\2AB\5\30\r\2BI\b\5"+
		"\1\2CD\7\6\2\2DE\5\30\r\2EF\b\5\1\2FH\3\2\2\2GC\3\2\2\2HK\3\2\2\2IG\3"+
		"\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\7\7\2\2MN\b\5\1\2N[\3\2\2\2OP\7"+
		"\4\2\2PQ\7\5\2\2QR\7\37\2\2RS\7\7\2\2S[\b\5\1\2TU\7\4\2\2UV\7\5\2\2VW"+
		"\5\24\13\2WX\7\7\2\2XY\b\5\1\2Y[\3\2\2\2Z?\3\2\2\2ZO\3\2\2\2ZT\3\2\2\2"+
		"[\t\3\2\2\2\\]\5\30\r\2]^\7\6\2\2^_\5\30\r\2_`\7\3\2\2`a\5\30\r\2ab\7"+
		"\6\2\2bc\5\30\r\2cd\b\6\1\2d\13\3\2\2\2ef\7\27\2\2fg\7\3\2\2gh\7\b\2\2"+
		"hi\7\t\2\2ij\5\32\16\2jk\7\5\2\2kl\7\7\2\2lm\b\7\1\2m\r\3\2\2\2no\7\n"+
		"\2\2op\7\5\2\2pq\5\26\f\2qr\7\7\2\2rv\7\13\2\2st\5\4\3\2tu\b\b\1\2uw\3"+
		"\2\2\2vs\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2yz\3\2\2\2z\u0086\7\f\2"+
		"\2{|\7\r\2\2|\u0080\7\13\2\2}~\5\4\3\2~\177\b\b\1\2\177\u0081\3\2\2\2"+
		"\u0080}\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3"+
		"\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\7\f\2\2\u0085\u0087\3\2\2\2\u0086"+
		"{\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\b\b\1\2"+
		"\u0089\17\3\2\2\2\u008a\u008b\7\16\2\2\u008b\u008c\7\5\2\2\u008c\u008d"+
		"\5\26\f\2\u008d\u008e\7\7\2\2\u008e\u0092\7\13\2\2\u008f\u0090\5\4\3\2"+
		"\u0090\u0091\b\t\1\2\u0091\u0093\3\2\2\2\u0092\u008f\3\2\2\2\u0093\u0094"+
		"\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0097\7\f\2\2\u0097\u0098\b\t\1\2\u0098\21\3\2\2\2\u0099\u009a\7\17\2"+
		"\2\u009a\u009b\5\30\r\2\u009b\u009c\7\3\2\2\u009c\u009d\7\20\2\2\u009d"+
		"\u009e\7\5\2\2\u009e\u009f\5\24\13\2\u009f\u00a0\7\7\2\2\u00a0\u00a4\7"+
		"\13\2\2\u00a1\u00a2\5\4\3\2\u00a2\u00a3\b\n\1\2\u00a3\u00a5\3\2\2\2\u00a4"+
		"\u00a1\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7\f\2\2\u00a9\u00aa\b\n\1\2\u00aa"+
		"\u00d6\3\2\2\2\u00ab\u00ac\7\17\2\2\u00ac\u00ad\5\30\r\2\u00ad\u00ae\7"+
		"\3\2\2\u00ae\u00af\7\20\2\2\u00af\u00b0\7\5\2\2\u00b0\u00b1\5\24\13\2"+
		"\u00b1\u00b2\7\6\2\2\u00b2\u00b3\5\24\13\2\u00b3\u00b4\7\7\2\2\u00b4\u00b8"+
		"\7\13\2\2\u00b5\u00b6\5\4\3\2\u00b6\u00b7\b\n\1\2\u00b7\u00b9\3\2\2\2"+
		"\u00b8\u00b5\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb"+
		"\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\7\f\2\2\u00bd\u00be\b\n\1\2\u00be"+
		"\u00d6\3\2\2\2\u00bf\u00c0\7\17\2\2\u00c0\u00c1\5\30\r\2\u00c1\u00c2\7"+
		"\3\2\2\u00c2\u00c3\7\20\2\2\u00c3\u00c4\7\5\2\2\u00c4\u00c5\5\24\13\2"+
		"\u00c5\u00c6\7\6\2\2\u00c6\u00c7\5\24\13\2\u00c7\u00c8\7\6\2\2\u00c8\u00c9"+
		"\5\24\13\2\u00c9\u00ca\7\7\2\2\u00ca\u00ce\7\13\2\2\u00cb\u00cc\5\4\3"+
		"\2\u00cc\u00cd\b\n\1\2\u00cd\u00cf\3\2\2\2\u00ce\u00cb\3\2\2\2\u00cf\u00d0"+
		"\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2"+
		"\u00d3\7\f\2\2\u00d3\u00d4\b\n\1\2\u00d4\u00d6\3\2\2\2\u00d5\u0099\3\2"+
		"\2\2\u00d5\u00ab\3\2\2\2\u00d5\u00bf\3\2\2\2\u00d6\23\3\2\2\2\u00d7\u00d8"+
		"\b\13\1\2\u00d8\u00d9\7\5\2\2\u00d9\u00da\5\24\13\2\u00da\u00db\7\7\2"+
		"\2\u00db\u00dc\b\13\1\2\u00dc\u00e5\3\2\2\2\u00dd\u00de\5\30\r\2\u00de"+
		"\u00df\b\13\1\2\u00df\u00e5\3\2\2\2\u00e0\u00e1\7\33\2\2\u00e1\u00e5\b"+
		"\13\1\2\u00e2\u00e3\7\34\2\2\u00e3\u00e5\b\13\1\2\u00e4\u00d7\3\2\2\2"+
		"\u00e4\u00dd\3\2\2\2\u00e4\u00e0\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00ed"+
		"\3\2\2\2\u00e6\u00e7\f\6\2\2\u00e7\u00e8\7\31\2\2\u00e8\u00e9\5\24\13"+
		"\7\u00e9\u00ea\b\13\1\2\u00ea\u00ec\3\2\2\2\u00eb\u00e6\3\2\2\2\u00ec"+
		"\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\25\3\2\2"+
		"\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1\b\f\1\2\u00f1\u00f2\7\5\2\2\u00f2\u00f3"+
		"\5\26\f\2\u00f3\u00f4\7\7\2\2\u00f4\u00f5\b\f\1\2\u00f5\u00fe\3\2\2\2"+
		"\u00f6\u00f7\5\24\13\2\u00f7\u00f8\7\30\2\2\u00f8\u00f9\5\24\13\2\u00f9"+
		"\u00fa\b\f\1\2\u00fa\u00fe\3\2\2\2\u00fb\u00fc\7\32\2\2\u00fc\u00fe\b"+
		"\f\1\2\u00fd\u00f0\3\2\2\2\u00fd\u00f6\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe"+
		"\u0106\3\2\2\2\u00ff\u0100\f\5\2\2\u0100\u0101\t\2\2\2\u0101\u0102\5\26"+
		"\f\6\u0102\u0103\b\f\1\2\u0103\u0105\3\2\2\2\u0104\u00ff\3\2\2\2\u0105"+
		"\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\27\3\2\2"+
		"\2\u0108\u0106\3\2\2\2\u0109\u010a\7\27\2\2\u010a\u010b\b\r\1\2\u010b"+
		"\31\3\2\2\2\u010c\u010d\7\23\2\2\u010d\u0113\b\16\1\2\u010e\u010f\7\24"+
		"\2\2\u010f\u0113\b\16\1\2\u0110\u0111\7\25\2\2\u0111\u0113\b\16\1\2\u0112"+
		"\u010c\3\2\2\2\u0112\u010e\3\2\2\2\u0112\u0110\3\2\2\2\u0113\33\3\2\2"+
		"\2\23!8IZx\u0082\u0086\u0094\u00a6\u00ba\u00d0\u00d5\u00e4\u00ed\u00fd"+
		"\u0106\u0112";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}