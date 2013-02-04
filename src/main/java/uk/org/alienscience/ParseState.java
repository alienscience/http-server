package uk.org.alienscience;

enum ParseState {
	METHOD_IS_UNSUPPORTED,	// The HTTP request method is not supported
	ERROR,                  // The request could not be parsed
	COMPLETE,				// The request was successfully parsed
	INCOMPLETE;				// The request has been parsed but is not complete
}
