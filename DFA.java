import java.util.Collections;
import java.util.List;
import java.util.*;

public class LexicalAnalyser {

	

	private enum State {
		STARTSTATE,	NUMSTATE, _STATE, SYMSTATE, ZEROSTATE, DECSTATE, NUMERR, EXERR
	};

	

	public static List<Token> analyse(String input) throws NumberException, ExpressionException {

		List<Token> tokens = new ArrayList<Token>();
			State state = State.STARTSTATE;
			//String num;

			for (int i = 0; i < input.length(); i++) {
				String num = ""; 
				if (input.charAt(i) == '1' || input.charAt(i) == '2' || input.charAt(i) == '3' || input.charAt(i) == '4' ||
				input.charAt(i) == '5' || input.charAt(i) == '6' || input.charAt(i) == '7' || input.charAt(i) == '8' || 
				input.charAt(i) == '9') {
					switch (state) {
						case STARTSTATE:
							state = State.NUMSTATE;
							
							break;
						case NUMSTATE:
							state = State.NUMSTATE;
							break;
						case _STATE:
							state = State.NUMERR;
							break;
						case SYMSTATE:
							state = State.NUMSTATE;
							break;
						case ZEROSTATE:
							state = State.NUMERR;
							
							break;
						case DECSTATE:
							state = State.NUMSTATE;
							break;
						case NUMERR:
							state = State.NUMERR;
							break;
						case EXERR:
							state = State.EXERR;
							break;
						}
						num += input.charAt(i);
						//if (state != State.NUMSTATE) tokens.add(new Token(Double.parseDouble(num)));
						tokens.add(new Token(Double.parseDouble(num)));
					if (state == State.NUMERR) throw new NumberException();


					}

				else if (input.charAt(i) == '0') {
					String zero = "";

					switch (state) {
						case STARTSTATE:
							state = State.ZEROSTATE;
							break;
						case NUMSTATE:
							state = State.NUMSTATE;
							break;
						case _STATE:
							state = State.NUMERR;
							break;
						case SYMSTATE:
							state = State.ZEROSTATE;
							break;
						case ZEROSTATE:
							state = State.NUMERR;
							break;
						case DECSTATE:
							state = State.NUMERR;
							break;
						case NUMERR:
							state = State.NUMERR;
							break;
						case EXERR:
							state = State.EXERR;
							break;

						}	
						zero += input.charAt(i);
						tokens.add(new Token(Double.parseDouble(zero)));
					}

				else if (input.charAt(i) == '.') {
					String dec = "";
					switch (state) {
						case STARTSTATE:
							state = State.NUMERR;
							break;
						case NUMSTATE:
							state = State.NUMERR;
							break;
						case _STATE:
							state = State.NUMERR;
							break;
						case SYMSTATE:
							state = State.EXERR;
							break;
						case ZEROSTATE:
							state = State.DECSTATE;
							break;
						case DECSTATE:
							state = State.EXERR;
							break;
						case NUMERR:
							state = State.NUMERR;
							break;
						case EXERR:
							state = State.EXERR;
							break;
						}	
						throw new NumberException();
						//tokens.add(token(Double.parseDouble(dec)));
					}
					
				else if (input.charAt(i) == ' ') {
					switch (state) {
						case STARTSTATE:
							state = State.STARTSTATE;
							break;
						case NUMSTATE:
							state = State._STATE;
							break;
						case _STATE:
							state = State._STATE;
							break;
						case SYMSTATE:
							state = State._STATE;
							break;
						case ZEROSTATE:
							state = State._STATE;
							break;
						case DECSTATE:
							state = State.EXERR;
							break;
						case NUMERR:
							state = State.NUMERR;
							break;
						case EXERR:
							state = State.EXERR;
							break;
						}	
					}

				else if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '/' || input.charAt(i) == '*') {
					String sym = "";
					switch (state) {
						case STARTSTATE:
							state = State.EXERR;
							break;
						case NUMSTATE:
							state = State.SYMSTATE;
							break;
						case _STATE:
							state = State.SYMSTATE;
							break;
						case SYMSTATE:
							state = State.EXERR;
							break;
						case ZEROSTATE:
							state = State.SYMSTATE;
							break;
						case DECSTATE:
							state = State.EXERR;
							break;
						case NUMERR:
							state = State.NUMERR;
							break;
						case EXERR:
							state = State.EXERR;
							break;
						}	
						//state = State.SYMSTATE;
						sym += input.charAt(i);
						tokens.add(new Token(Double.parseDouble(sym)));
					}
			}

			if (state == State.NUMERR) {
				throw new NumberException();
			}

			if (state == State.EXERR) {
				throw new ExpressionException();
			}


			return tokens;
		}

		

	}
