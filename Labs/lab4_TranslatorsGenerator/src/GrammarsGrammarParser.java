// Generated from GrammarsGrammar.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarsGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, BLOCK2=8, BLOCK=9, 
		RETURNS=10, LOCALS=11, AFTER=12, INIT=13, TYPE=14, UPPERNAME=15, LOWERNAME=16, 
		STR=17, STR2=18, WS=19;
	public static final int
		RULE_start = 0, RULE_rule1 = 1, RULE_attrs = 2, RULE_state = 3, RULE_state2 = 4, 
		RULE_nnext = 5, RULE_terminal = 6, RULE_code = 7, RULE_rreturn = 8, RULE_llocal = 9, 
		RULE_init = 10, RULE_after = 11, RULE_variable = 12, RULE_params = 13;
	public static final String[] ruleNames = {
		"start", "rule1", "attrs", "state", "state2", "nnext", "terminal", "code", 
		"rreturn", "llocal", "init", "after", "variable", "params"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "'|'", "';'", "'['", "','", "']'", "'='", null, null, "'returns'", 
		"'locals'", "'@after'", "'@init'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "BLOCK2", "BLOCK", "RETURNS", 
		"LOCALS", "AFTER", "INIT", "TYPE", "UPPERNAME", "LOWERNAME", "STR", "STR2", 
		"WS"
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
	public String getGrammarFileName() { return "GrammarsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarsGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public List<Rule1Context> rule1() {
			return getRuleContexts(Rule1Context.class);
		}
		public Rule1Context rule1(int i) {
			return getRuleContext(Rule1Context.class,i);
		}
		public List<TerminalContext> terminal() {
			return getRuleContexts(TerminalContext.class);
		}
		public TerminalContext terminal(int i) {
			return getRuleContext(TerminalContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(30);
				switch (_input.LA(1)) {
				case LOWERNAME:
					{
					setState(28);
					rule1();
					}
					break;
				case UPPERNAME:
					{
					setState(29);
					terminal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==UPPERNAME || _la==LOWERNAME );
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

	public static class Rule1Context extends ParserRuleContext {
		public TerminalNode LOWERNAME() { return getToken(GrammarsGrammarParser.LOWERNAME, 0); }
		public List<StateContext> state() {
			return getRuleContexts(StateContext.class);
		}
		public StateContext state(int i) {
			return getRuleContext(StateContext.class,i);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public RreturnContext rreturn() {
			return getRuleContext(RreturnContext.class,0);
		}
		public LlocalContext llocal() {
			return getRuleContext(LlocalContext.class,0);
		}
		public InitContext init() {
			return getRuleContext(InitContext.class,0);
		}
		public AfterContext after() {
			return getRuleContext(AfterContext.class,0);
		}
		public Rule1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterRule1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitRule1(this);
		}
	}

	public final Rule1Context rule1() throws RecognitionException {
		Rule1Context _localctx = new Rule1Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_rule1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(LOWERNAME);
			setState(36);
			_la = _input.LA(1);
			if (_la==BLOCK2) {
				{
				setState(35);
				params();
				}
			}

			setState(39);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(38);
				rreturn();
				}
			}

			setState(42);
			_la = _input.LA(1);
			if (_la==LOCALS) {
				{
				setState(41);
				llocal();
				}
			}

			setState(45);
			_la = _input.LA(1);
			if (_la==INIT) {
				{
				setState(44);
				init();
				}
			}

			setState(48);
			_la = _input.LA(1);
			if (_la==AFTER) {
				{
				setState(47);
				after();
				}
			}

			setState(50);
			match(T__0);
			setState(51);
			state();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(52);
				match(T__1);
				setState(53);
				state();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			match(T__2);
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

	public static class AttrsContext extends ParserRuleContext {
		public List<TerminalNode> TYPE() { return getTokens(GrammarsGrammarParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(GrammarsGrammarParser.TYPE, i);
		}
		public List<TerminalNode> LOWERNAME() { return getTokens(GrammarsGrammarParser.LOWERNAME); }
		public TerminalNode LOWERNAME(int i) {
			return getToken(GrammarsGrammarParser.LOWERNAME, i);
		}
		public AttrsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterAttrs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitAttrs(this);
		}
	}

	public final AttrsContext attrs() throws RecognitionException {
		AttrsContext _localctx = new AttrsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_attrs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__3);
			setState(62);
			match(TYPE);
			setState(63);
			match(LOWERNAME);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(64);
				match(T__4);
				setState(65);
				match(TYPE);
				setState(66);
				match(LOWERNAME);
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			match(T__5);
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

	public static class StateContext extends ParserRuleContext {
		public List<State2Context> state2() {
			return getRuleContexts(State2Context.class);
		}
		public State2Context state2(int i) {
			return getRuleContext(State2Context.class,i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public StateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitState(this);
		}
	}

	public final StateContext state() throws RecognitionException {
		StateContext _localctx = new StateContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_state);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74);
				state2();
				}
				}
				setState(77); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==UPPERNAME || _la==LOWERNAME );
			setState(80);
			_la = _input.LA(1);
			if (_la==BLOCK) {
				{
				setState(79);
				code();
				}
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

	public static class State2Context extends ParserRuleContext {
		public NnextContext nnext() {
			return getRuleContext(NnextContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode BLOCK2() { return getToken(GrammarsGrammarParser.BLOCK2, 0); }
		public State2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterState2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitState2(this);
		}
	}

	public final State2Context state2() throws RecognitionException {
		State2Context _localctx = new State2Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_state2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(82);
				variable();
				}
				break;
			}
			setState(85);
			nnext();
			setState(87);
			_la = _input.LA(1);
			if (_la==BLOCK2) {
				{
				setState(86);
				match(BLOCK2);
				}
			}

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

	public static class NnextContext extends ParserRuleContext {
		public TerminalNode UPPERNAME() { return getToken(GrammarsGrammarParser.UPPERNAME, 0); }
		public TerminalNode LOWERNAME() { return getToken(GrammarsGrammarParser.LOWERNAME, 0); }
		public NnextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nnext; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterNnext(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitNnext(this);
		}
	}

	public final NnextContext nnext() throws RecognitionException {
		NnextContext _localctx = new NnextContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_nnext);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_la = _input.LA(1);
			if ( !(_la==UPPERNAME || _la==LOWERNAME) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class TerminalContext extends ParserRuleContext {
		public TerminalNode UPPERNAME() { return getToken(GrammarsGrammarParser.UPPERNAME, 0); }
		public List<TerminalNode> STR() { return getTokens(GrammarsGrammarParser.STR); }
		public TerminalNode STR(int i) {
			return getToken(GrammarsGrammarParser.STR, i);
		}
		public TerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitTerminal(this);
		}
	}

	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_terminal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(UPPERNAME);
			setState(92);
			match(T__0);
			setState(93);
			match(STR);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(94);
				match(T__1);
				setState(95);
				match(STR);
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			match(T__2);
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

	public static class CodeContext extends ParserRuleContext {
		public TerminalNode BLOCK() { return getToken(GrammarsGrammarParser.BLOCK, 0); }
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_code);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(BLOCK);
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

	public static class RreturnContext extends ParserRuleContext {
		public TerminalNode RETURNS() { return getToken(GrammarsGrammarParser.RETURNS, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public RreturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rreturn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterRreturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitRreturn(this);
		}
	}

	public final RreturnContext rreturn() throws RecognitionException {
		RreturnContext _localctx = new RreturnContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rreturn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(RETURNS);
			setState(106);
			params();
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

	public static class LlocalContext extends ParserRuleContext {
		public TerminalNode LOCALS() { return getToken(GrammarsGrammarParser.LOCALS, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public LlocalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llocal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterLlocal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitLlocal(this);
		}
	}

	public final LlocalContext llocal() throws RecognitionException {
		LlocalContext _localctx = new LlocalContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_llocal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(LOCALS);
			setState(109);
			params();
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

	public static class InitContext extends ParserRuleContext {
		public TerminalNode INIT() { return getToken(GrammarsGrammarParser.INIT, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitInit(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_init);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(INIT);
			setState(112);
			code();
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

	public static class AfterContext extends ParserRuleContext {
		public TerminalNode AFTER() { return getToken(GrammarsGrammarParser.AFTER, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public AfterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_after; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterAfter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitAfter(this);
		}
	}

	public final AfterContext after() throws RecognitionException {
		AfterContext _localctx = new AfterContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_after);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(AFTER);
			setState(115);
			code();
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode LOWERNAME() { return getToken(GrammarsGrammarParser.LOWERNAME, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(LOWERNAME);
			setState(118);
			match(T__6);
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

	public static class ParamsContext extends ParserRuleContext {
		public TerminalNode BLOCK2() { return getToken(GrammarsGrammarParser.BLOCK2, 0); }
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarsGrammarListener ) ((GrammarsGrammarListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_params);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(BLOCK2);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25}\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\6\2!\n\2\r\2\16\2\"\3\3\3\3"+
		"\5\3\'\n\3\3\3\5\3*\n\3\3\3\5\3-\n\3\3\3\5\3\60\n\3\3\3\5\3\63\n\3\3\3"+
		"\3\3\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\4F\n\4\f\4\16\4I\13\4\3\4\3\4\3\5\6\5N\n\5\r\5\16\5O\3\5\5\5S\n\5\3\6"+
		"\5\6V\n\6\3\6\3\6\5\6Z\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\bc\n\b\f\b\16"+
		"\bf\13\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\2\3\3\2\21\22|\2 \3\2\2\2\4$\3\2\2\2\6?\3\2\2\2\bM\3\2\2\2\n"+
		"U\3\2\2\2\f[\3\2\2\2\16]\3\2\2\2\20i\3\2\2\2\22k\3\2\2\2\24n\3\2\2\2\26"+
		"q\3\2\2\2\30t\3\2\2\2\32w\3\2\2\2\34z\3\2\2\2\36!\5\4\3\2\37!\5\16\b\2"+
		" \36\3\2\2\2 \37\3\2\2\2!\"\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\3\3\2\2\2$"+
		"&\7\22\2\2%\'\5\34\17\2&%\3\2\2\2&\'\3\2\2\2\')\3\2\2\2(*\5\22\n\2)(\3"+
		"\2\2\2)*\3\2\2\2*,\3\2\2\2+-\5\24\13\2,+\3\2\2\2,-\3\2\2\2-/\3\2\2\2."+
		"\60\5\26\f\2/.\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61\63\5\30\r\2\62\61"+
		"\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\7\3\2\2\65:\5\b\5\2\66\67\7"+
		"\4\2\2\679\5\b\5\28\66\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2"+
		"<:\3\2\2\2=>\7\5\2\2>\5\3\2\2\2?@\7\6\2\2@A\7\20\2\2AG\7\22\2\2BC\7\7"+
		"\2\2CD\7\20\2\2DF\7\22\2\2EB\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HJ\3"+
		"\2\2\2IG\3\2\2\2JK\7\b\2\2K\7\3\2\2\2LN\5\n\6\2ML\3\2\2\2NO\3\2\2\2OM"+
		"\3\2\2\2OP\3\2\2\2PR\3\2\2\2QS\5\20\t\2RQ\3\2\2\2RS\3\2\2\2S\t\3\2\2\2"+
		"TV\5\32\16\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WY\5\f\7\2XZ\7\n\2\2YX\3\2\2"+
		"\2YZ\3\2\2\2Z\13\3\2\2\2[\\\t\2\2\2\\\r\3\2\2\2]^\7\21\2\2^_\7\3\2\2_"+
		"d\7\23\2\2`a\7\4\2\2ac\7\23\2\2b`\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2"+
		"\2eg\3\2\2\2fd\3\2\2\2gh\7\5\2\2h\17\3\2\2\2ij\7\13\2\2j\21\3\2\2\2kl"+
		"\7\f\2\2lm\5\34\17\2m\23\3\2\2\2no\7\r\2\2op\5\34\17\2p\25\3\2\2\2qr\7"+
		"\17\2\2rs\5\20\t\2s\27\3\2\2\2tu\7\16\2\2uv\5\20\t\2v\31\3\2\2\2wx\7\22"+
		"\2\2xy\7\t\2\2y\33\3\2\2\2z{\7\n\2\2{\35\3\2\2\2\20 \"&),/\62:GORUYd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}