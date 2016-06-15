// Generated from Translator.g4 by ANTLR 4.5.3

	import java.util.HashMap;
	import java.util.Map;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TranslatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, TYPE=21, NAME=22, LOGICTERM=23, SIGN=24, 
		BOOLEAN=25, INT=26, FLOAT=27, EXP=28, WS=29, STR=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "TYPE", "NAME", "LOGICTERM", "SIGN", "BOOLEAN", 
		"INT", "FLOAT", "EXP", "WS", "STR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'print'", "'('", "','", "')'", "'read'", "'_'", "'if'", 
		"'{'", "'}'", "'else'", "'while'", "'do'", "'for'", "'range'", "'or'", 
		"'and'", "'int'", "'float'", "'bool'"
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


	public TranslatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Translator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2 \u00ea\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0096\n\26\3\27\5\27\u0099\n\27"+
		"\3\27\7\27\u009c\n\27\f\27\16\27\u009f\13\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\5\30\u00a8\n\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\5\32\u00b5\n\32\3\33\3\33\3\33\7\33\u00ba\n\33\f\33\16\33\u00bd"+
		"\13\33\5\33\u00bf\n\33\3\34\5\34\u00c2\n\34\3\34\3\34\3\34\3\34\5\34\u00c8"+
		"\n\34\3\34\5\34\u00cb\n\34\3\34\3\34\3\34\3\34\5\34\u00d1\n\34\3\34\5"+
		"\34\u00d4\n\34\3\35\3\35\5\35\u00d8\n\35\3\35\3\35\3\36\6\36\u00dd\n\36"+
		"\r\36\16\36\u00de\3\36\3\36\3\37\3\37\6\37\u00e5\n\37\r\37\16\37\u00e6"+
		"\3\37\3\37\2\2 \3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= \3\2\f\4\2C\\c|\5\2\62;C\\c|\4\2>>@@\5\2,-//\61\61"+
		"\3\2\63;\3\2\62;\4\2GGgg\4\2--//\5\2\13\f\17\17\"\"\4\2))^^\u00fb\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\3?\3\2\2\2\5A\3\2\2\2\7G\3\2\2\2\tI\3\2\2\2\13K\3\2\2\2"+
		"\rM\3\2\2\2\17R\3\2\2\2\21T\3\2\2\2\23W\3\2\2\2\25Y\3\2\2\2\27[\3\2\2"+
		"\2\31`\3\2\2\2\33f\3\2\2\2\35i\3\2\2\2\37m\3\2\2\2!s\3\2\2\2#v\3\2\2\2"+
		"%z\3\2\2\2\'~\3\2\2\2)\u0084\3\2\2\2+\u0095\3\2\2\2-\u0098\3\2\2\2/\u00a7"+
		"\3\2\2\2\61\u00a9\3\2\2\2\63\u00b4\3\2\2\2\65\u00be\3\2\2\2\67\u00d3\3"+
		"\2\2\29\u00d5\3\2\2\2;\u00dc\3\2\2\2=\u00e2\3\2\2\2?@\7?\2\2@\4\3\2\2"+
		"\2AB\7r\2\2BC\7t\2\2CD\7k\2\2DE\7p\2\2EF\7v\2\2F\6\3\2\2\2GH\7*\2\2H\b"+
		"\3\2\2\2IJ\7.\2\2J\n\3\2\2\2KL\7+\2\2L\f\3\2\2\2MN\7t\2\2NO\7g\2\2OP\7"+
		"c\2\2PQ\7f\2\2Q\16\3\2\2\2RS\7a\2\2S\20\3\2\2\2TU\7k\2\2UV\7h\2\2V\22"+
		"\3\2\2\2WX\7}\2\2X\24\3\2\2\2YZ\7\177\2\2Z\26\3\2\2\2[\\\7g\2\2\\]\7n"+
		"\2\2]^\7u\2\2^_\7g\2\2_\30\3\2\2\2`a\7y\2\2ab\7j\2\2bc\7k\2\2cd\7n\2\2"+
		"de\7g\2\2e\32\3\2\2\2fg\7f\2\2gh\7q\2\2h\34\3\2\2\2ij\7h\2\2jk\7q\2\2"+
		"kl\7t\2\2l\36\3\2\2\2mn\7t\2\2no\7c\2\2op\7p\2\2pq\7i\2\2qr\7g\2\2r \3"+
		"\2\2\2st\7q\2\2tu\7t\2\2u\"\3\2\2\2vw\7c\2\2wx\7p\2\2xy\7f\2\2y$\3\2\2"+
		"\2z{\7k\2\2{|\7p\2\2|}\7v\2\2}&\3\2\2\2~\177\7h\2\2\177\u0080\7n\2\2\u0080"+
		"\u0081\7q\2\2\u0081\u0082\7c\2\2\u0082\u0083\7v\2\2\u0083(\3\2\2\2\u0084"+
		"\u0085\7d\2\2\u0085\u0086\7q\2\2\u0086\u0087\7q\2\2\u0087\u0088\7n\2\2"+
		"\u0088*\3\2\2\2\u0089\u008a\7d\2\2\u008a\u008b\7q\2\2\u008b\u008c\7q\2"+
		"\2\u008c\u0096\7n\2\2\u008d\u008e\7k\2\2\u008e\u008f\7p\2\2\u008f\u0096"+
		"\7v\2\2\u0090\u0091\7h\2\2\u0091\u0092\7n\2\2\u0092\u0093\7q\2\2\u0093"+
		"\u0094\7c\2\2\u0094\u0096\7v\2\2\u0095\u0089\3\2\2\2\u0095\u008d\3\2\2"+
		"\2\u0095\u0090\3\2\2\2\u0096,\3\2\2\2\u0097\u0099\t\2\2\2\u0098\u0097"+
		"\3\2\2\2\u0099\u009d\3\2\2\2\u009a\u009c\t\3\2\2\u009b\u009a\3\2\2\2\u009c"+
		"\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e.\3\2\2\2"+
		"\u009f\u009d\3\2\2\2\u00a0\u00a1\7?\2\2\u00a1\u00a8\7?\2\2\u00a2\u00a3"+
		"\7@\2\2\u00a3\u00a8\7?\2\2\u00a4\u00a5\7>\2\2\u00a5\u00a8\7?\2\2\u00a6"+
		"\u00a8\t\4\2\2\u00a7\u00a0\3\2\2\2\u00a7\u00a2\3\2\2\2\u00a7\u00a4\3\2"+
		"\2\2\u00a7\u00a6\3\2\2\2\u00a8\60\3\2\2\2\u00a9\u00aa\t\5\2\2\u00aa\62"+
		"\3\2\2\2\u00ab\u00ac\7V\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7w\2\2\u00ae"+
		"\u00b5\7g\2\2\u00af\u00b0\7H\2\2\u00b0\u00b1\7c\2\2\u00b1\u00b2\7n\2\2"+
		"\u00b2\u00b3\7u\2\2\u00b3\u00b5\7g\2\2\u00b4\u00ab\3\2\2\2\u00b4\u00af"+
		"\3\2\2\2\u00b5\64\3\2\2\2\u00b6\u00bf\7\62\2\2\u00b7\u00bb\t\6\2\2\u00b8"+
		"\u00ba\t\7\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be"+
		"\u00b6\3\2\2\2\u00be\u00b7\3\2\2\2\u00bf\66\3\2\2\2\u00c0\u00c2\7/\2\2"+
		"\u00c1\u00c0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4"+
		"\5\65\33\2\u00c4\u00c5\7\60\2\2\u00c5\u00c7\5\65\33\2\u00c6\u00c8\59\35"+
		"\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00d4\3\2\2\2\u00c9\u00cb"+
		"\7/\2\2\u00ca\u00c9\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00cd\5\65\33\2\u00cd\u00ce\59\35\2\u00ce\u00d4\3\2\2\2\u00cf\u00d1\7"+
		"/\2\2\u00d0\u00cf\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2"+
		"\u00d4\5\65\33\2\u00d3\u00c1\3\2\2\2\u00d3\u00ca\3\2\2\2\u00d3\u00d0\3"+
		"\2\2\2\u00d48\3\2\2\2\u00d5\u00d7\t\b\2\2\u00d6\u00d8\t\t\2\2\u00d7\u00d6"+
		"\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\5\65\33\2"+
		"\u00da:\3\2\2\2\u00db\u00dd\t\n\2\2\u00dc\u00db\3\2\2\2\u00dd\u00de\3"+
		"\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0"+
		"\u00e1\b\36\2\2\u00e1<\3\2\2\2\u00e2\u00e4\7)\2\2\u00e3\u00e5\n\13\2\2"+
		"\u00e4\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7"+
		"\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\7)\2\2\u00e9>\3\2\2\2\23\2\u0095"+
		"\u0098\u009b\u009d\u00a7\u00b4\u00bb\u00be\u00c1\u00c7\u00ca\u00d0\u00d3"+
		"\u00d7\u00de\u00e6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}