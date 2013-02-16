
// line 1 "HttpParser.rl"

package uk.org.alienscience;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

class HttpParser {

    //------------ Start of generated code ------------------------------------

// line 15 "HttpParser.java"
private static byte[] init__http_request_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,    9,    1,   10,    1,
	   11,    1,   12,    1,   13,    2,    7,    8,    2,    7,    9
	};
}

private static final byte _http_request_actions[] = init__http_request_actions_0();


private static short[] init__http_request_key_offsets_0()
{
	return new short [] {
	    0,    0,    8,   10,   12,   14,   16,   18,   21,   23,   39,   42,
	   46,   47,   48,   50,   52,   54,   56,   58,   60,   62,   64,   66,
	   68,   72,   78,   87,   92,   93,   94,   95,   96,   97,   98,   99,
	  100,  101,  103,  104,  105,  106,  107,  108,  110,  116,  118,  120,
	  122,  124,  128,  138,  151,  160,  171,  173,  177,  187,  198,  201,
	  205,  217,  229,  241,  253,  265,  277,  289,  301,  313,  324,  330,
	  342,  354,  366,  377,  383,  385,  387,  389,  391,  393,  395,  397,
	  399,  401,  403,  405,  407,  409,  411,  413,  414,  415,  416,  417,
	  418,  419,  421,  423,  425,  429,  432,  434,  436,  438,  440,  442,
	  445,  447,  449,  455,  461,  478,  495,  512,  529,  546,  563,  581,
	  598,  615,  616,  617,  618,  619,  620,  621,  627,  635,  652,  658,
	  664,  680,  686,  692,  709,  726,  743,  760,  777,  794,  812,  829,
	  846,  864,  882,  900,  918,  936,  954,  973,  991, 1009, 1011, 1014,
	 1016, 1018, 1021, 1023, 1025, 1027, 1030, 1034, 1036, 1038, 1041, 1043,
	 1046, 1046, 1050, 1055, 1064, 1073
	};
}

private static final short _http_request_key_offsets[] = init__http_request_key_offsets_0();


private static int[] init__http_request_trans_keys_0()
{
	return new int [] {
	   68,   71,   72,   80,  100,  103,  104,  112,   69,  101,   76,  108,
	   69,  101,   84,  116,   69,  101,   32,    9,   13,   47,  104,   10,
	   32,   33,   37,   61,   72,   95,  126,    9,   13,   36,   58,   64,
	   90,   97,  122,   10,   13,   72,   10,   13,   67,   72,   10,   10,
	   10,  111,   10,  110,   10,  110,   10,  101,   10,   99,   10,  116,
	   10,  105,   10,  111,   10,  110,   10,   58,   10,   32,    9,   13,
	   10,   32,   75,   99,    9,   13,   10,   13,   32,   67,   72,   75,
	   99,    9,   12,   32,   75,   99,    9,   13,  101,  101,  112,   45,
	   65,  108,  105,  118,  101,   10,   13,   10,  108,  111,  115,  101,
	   10,   13,   10,   32,   75,   99,    9,   13,   10,  111,   10,  115,
	   10,  116,   10,   58,   10,   32,    9,   13,   10,   32,    9,   13,
	   48,   57,   65,   90,   97,  122,   10,   13,   32,   67,   72,    9,
	   12,   48,   57,   65,   90,   97,  122,   32,    9,   13,   48,   57,
	   65,   90,   97,  122,   10,   13,   58,   45,   46,   48,   57,   65,
	   90,   97,  122,   48,   57,   10,   13,   48,   57,   10,   32,    9,
	   13,   48,   57,   65,   90,   97,  122,   10,   13,   58,   45,   46,
	   48,   57,   65,   90,   97,  122,   10,   48,   57,   10,   13,   48,
	   57,   10,   13,   58,  111,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,  110,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,  110,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,  101,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,   99,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,  116,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,  105,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,  111,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,  110,   45,   46,   48,   57,   65,   90,   97,
	  122,   10,   13,   58,   45,   46,   48,   57,   65,   90,   97,  122,
	   10,   32,    9,   13,   48,   57,   10,   13,   58,  111,   45,   46,
	   48,   57,   65,   90,   97,  122,   10,   13,   58,  115,   45,   46,
	   48,   57,   65,   90,   97,  122,   10,   13,   58,  116,   45,   46,
	   48,   57,   65,   90,   97,  122,   10,   13,   58,   45,   46,   48,
	   57,   65,   90,   97,  122,   10,   32,    9,   13,   48,   57,   10,
	  101,   10,  101,   10,  112,   10,   45,   10,   65,   10,  108,   10,
	  105,   10,  118,   10,  101,   10,   13,   10,  108,   10,  111,   10,
	  115,   10,  101,   10,   13,   84,   84,   80,   47,   49,   46,   48,
	   49,   10,   13,   10,   13,   10,   13,   67,   72,   10,   84,  111,
	   10,   84,   10,   80,   10,   47,   10,   49,   10,   46,   10,   48,
	   49,   10,   13,   10,   13,   48,   57,   65,   70,   97,  102,   48,
	   57,   65,   70,   97,  102,   10,   32,   33,   37,   61,   72,   84,
	   95,  126,    9,   13,   36,   58,   64,   90,   97,  122,   10,   32,
	   33,   37,   61,   72,   84,   95,  126,    9,   13,   36,   58,   64,
	   90,   97,  122,   10,   32,   33,   37,   61,   72,   80,   95,  126,
	    9,   13,   36,   58,   64,   90,   97,  122,   10,   32,   33,   37,
	   47,   61,   72,   95,  126,    9,   13,   36,   58,   64,   90,   97,
	  122,   10,   32,   33,   37,   49,   61,   72,   95,  126,    9,   13,
	   36,   58,   64,   90,   97,  122,   10,   32,   33,   37,   46,   61,
	   72,   95,  126,    9,   13,   36,   58,   64,   90,   97,  122,   10,
	   32,   33,   37,   48,   49,   61,   72,   95,  126,    9,   13,   36,
	   58,   64,   90,   97,  122,   10,   13,   32,   33,   37,   61,   72,
	   95,  126,    9,   12,   36,   58,   64,   90,   97,  122,   10,   13,
	   32,   33,   37,   61,   72,   95,  126,    9,   12,   36,   58,   64,
	   90,   97,  122,  116,  116,  112,   58,   47,   47,   48,   57,   65,
	   90,   97,  122,   47,   58,   45,   57,   65,   90,   97,  122,   10,
	   32,   33,   37,   61,   63,   72,   95,  126,    9,   13,   36,   58,
	   64,   90,   97,  122,   48,   57,   65,   70,   97,  102,   48,   57,
	   65,   70,   97,  102,   10,   32,   33,   37,   61,   72,   95,  126,
	    9,   13,   36,   59,   63,   90,   97,  122,   48,   57,   65,   70,
	   97,  102,   48,   57,   65,   70,   97,  102,   10,   32,   33,   37,
	   61,   72,   84,   95,  126,    9,   13,   36,   59,   63,   90,   97,
	  122,   10,   32,   33,   37,   61,   72,   84,   95,  126,    9,   13,
	   36,   59,   63,   90,   97,  122,   10,   32,   33,   37,   61,   72,
	   80,   95,  126,    9,   13,   36,   59,   63,   90,   97,  122,   10,
	   32,   33,   37,   47,   61,   72,   95,  126,    9,   13,   36,   59,
	   63,   90,   97,  122,   10,   32,   33,   37,   49,   61,   72,   95,
	  126,    9,   13,   36,   59,   63,   90,   97,  122,   10,   32,   33,
	   37,   46,   61,   72,   95,  126,    9,   13,   36,   59,   63,   90,
	   97,  122,   10,   32,   33,   37,   48,   49,   61,   72,   95,  126,
	    9,   13,   36,   59,   63,   90,   97,  122,   10,   13,   32,   33,
	   37,   61,   72,   95,  126,    9,   12,   36,   59,   63,   90,   97,
	  122,   10,   13,   32,   33,   37,   61,   72,   95,  126,    9,   12,
	   36,   59,   63,   90,   97,  122,   10,   32,   33,   37,   61,   63,
	   72,   84,   95,  126,    9,   13,   36,   58,   64,   90,   97,  122,
	   10,   32,   33,   37,   61,   63,   72,   84,   95,  126,    9,   13,
	   36,   58,   64,   90,   97,  122,   10,   32,   33,   37,   61,   63,
	   72,   80,   95,  126,    9,   13,   36,   58,   64,   90,   97,  122,
	   10,   32,   33,   37,   47,   61,   63,   72,   95,  126,    9,   13,
	   36,   58,   64,   90,   97,  122,   10,   32,   33,   37,   49,   61,
	   63,   72,   95,  126,    9,   13,   36,   58,   64,   90,   97,  122,
	   10,   32,   33,   37,   46,   61,   63,   72,   95,  126,    9,   13,
	   36,   58,   64,   90,   97,  122,   10,   32,   33,   37,   48,   49,
	   61,   63,   72,   95,  126,    9,   13,   36,   58,   64,   90,   97,
	  122,   10,   13,   32,   33,   37,   61,   63,   72,   95,  126,    9,
	   12,   36,   58,   64,   90,   97,  122,   10,   13,   32,   33,   37,
	   61,   63,   72,   95,  126,    9,   12,   36,   58,   64,   90,   97,
	  122,   48,   57,   47,   48,   57,   69,  101,   84,  116,   32,    9,
	   13,   69,  101,   65,   97,   68,  100,   32,    9,   13,   79,   85,
	  111,  117,   83,  115,   84,  116,   32,    9,   13,   84,  116,   32,
	    9,   13,   10,   13,   67,   72,   32,   75,   99,    9,   13,   10,
	   13,   32,   67,   72,   75,   99,    9,   12,   32,    9,   13,   48,
	   57,   65,   90,   97,  122,   10,   13,   32,   67,   72,    9,   12,
	   48,   57,   65,   90,   97,  122,    0
	};
}

private static final int _http_request_trans_keys[] = init__http_request_trans_keys_0();


private static byte[] init__http_request_single_lengths_0()
{
	return new byte [] {
	    0,    8,    2,    2,    2,    2,    2,    1,    2,    8,    3,    4,
	    1,    1,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    4,    7,    3,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    2,    1,    1,    1,    1,    1,    2,    4,    2,    2,    2,
	    2,    2,    2,    5,    1,    3,    0,    2,    2,    3,    1,    2,
	    4,    4,    4,    4,    4,    4,    4,    4,    4,    3,    2,    4,
	    4,    4,    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    1,    1,    1,    1,    1,
	    1,    2,    2,    2,    4,    3,    2,    2,    2,    2,    2,    3,
	    2,    2,    0,    0,    9,    9,    9,    9,    9,    9,   10,    9,
	    9,    1,    1,    1,    1,    1,    1,    0,    2,    9,    0,    0,
	    8,    0,    0,    9,    9,    9,    9,    9,    9,   10,    9,    9,
	   10,   10,   10,   10,   10,   10,   11,   10,   10,    0,    1,    2,
	    2,    1,    2,    2,    2,    1,    4,    2,    2,    1,    2,    1,
	    0,    4,    3,    7,    1,    5
	};
}

private static final byte _http_request_single_lengths[] = init__http_request_single_lengths_0();


private static byte[] init__http_request_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    1,    0,    4,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    1,    1,    1,    1,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
	    0,    1,    4,    4,    4,    4,    1,    1,    4,    4,    1,    1,
	    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,    2,    4,
	    4,    4,    4,    2,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    3,    3,    4,    4,    4,    4,    4,    4,    4,    4,
	    4,    0,    0,    0,    0,    0,    0,    3,    3,    4,    3,    3,
	    4,    3,    3,    4,    4,    4,    4,    4,    4,    4,    4,    4,
	    4,    4,    4,    4,    4,    4,    4,    4,    4,    1,    1,    0,
	    0,    1,    0,    0,    0,    1,    0,    0,    0,    1,    0,    1,
	    0,    0,    1,    1,    4,    4
	};
}

private static final byte _http_request_range_lengths[] = init__http_request_range_lengths_0();


private static short[] init__http_request_index_offsets_0()
{
	return new short [] {
	    0,    0,    9,   12,   15,   18,   21,   24,   27,   30,   43,   47,
	   52,   54,   56,   59,   62,   65,   68,   71,   74,   77,   80,   83,
	   86,   90,   96,  105,  110,  112,  114,  116,  118,  120,  122,  124,
	  126,  128,  131,  133,  135,  137,  139,  141,  144,  150,  153,  156,
	  159,  162,  166,  173,  183,  189,  197,  199,  203,  210,  218,  221,
	  225,  234,  243,  252,  261,  270,  279,  288,  297,  306,  314,  319,
	  328,  337,  346,  354,  359,  362,  365,  368,  371,  374,  377,  380,
	  383,  386,  389,  392,  395,  398,  401,  404,  406,  408,  410,  412,
	  414,  416,  419,  422,  425,  430,  434,  437,  440,  443,  446,  449,
	  453,  456,  459,  463,  467,  481,  495,  509,  523,  537,  551,  566,
	  580,  594,  596,  598,  600,  602,  604,  606,  610,  616,  630,  634,
	  638,  651,  655,  659,  673,  687,  701,  715,  729,  743,  758,  772,
	  786,  801,  816,  831,  846,  861,  876,  892,  907,  922,  924,  927,
	  930,  933,  936,  939,  942,  945,  948,  953,  956,  959,  962,  965,
	  968,  969,  974,  979,  988,  994
	};
}

private static final short _http_request_index_offsets[] = init__http_request_index_offsets_0();


private static short[] init__http_request_indicies_0()
{
	return new short [] {
	    1,    2,    3,    4,    1,    2,    3,    4,    0,    5,    5,    0,
	    6,    6,    0,    7,    7,    0,    8,    8,    0,    9,    9,    0,
	   10,   10,    0,   11,   13,   12,   15,   14,   16,   17,   16,   18,
	   16,   16,   14,   16,   16,   16,   12,   19,   20,   21,   12,   23,
	   24,   25,   26,   22,   19,   22,   27,   22,   19,   28,   22,   19,
	   29,   22,   19,   30,   22,   19,   31,   22,   19,   32,   22,   19,
	   33,   22,   19,   34,   22,   19,   35,   22,   19,   36,   22,   19,
	   37,   22,   39,   38,   38,   22,   39,   38,   40,   41,   38,   22,
	   42,   43,   38,   25,   26,   40,   41,   38,   22,   44,   45,   46,
	   44,   12,   47,   12,   48,   12,   49,   12,   50,   12,   51,   12,
	   52,   12,   53,   12,   54,   12,   55,   12,   56,   57,   12,   19,
	   12,   58,   12,   59,   12,   60,   12,   61,   12,   62,   63,   12,
	   64,   38,   40,   41,   38,   22,   19,   65,   22,   19,   66,   22,
	   19,   67,   22,   19,   68,   22,   70,   69,   69,   22,   70,   69,
	   69,   71,   71,   71,   22,   72,   73,   69,   74,   75,   69,   71,
	   71,   71,   22,   76,   76,   77,   77,   77,   12,   78,   79,   81,
	   80,   80,   80,   80,   12,   82,   12,   78,   79,   82,   12,   83,
	   69,   69,   71,   71,   71,   22,   78,   84,   86,   85,   85,   85,
	   85,   22,   19,   87,   22,   78,   84,   87,   22,   78,   84,   86,
	   88,   85,   85,   85,   85,   22,   78,   84,   86,   89,   85,   85,
	   85,   85,   22,   78,   84,   86,   90,   85,   85,   85,   85,   22,
	   78,   84,   86,   91,   85,   85,   85,   85,   22,   78,   84,   86,
	   92,   85,   85,   85,   85,   22,   78,   84,   86,   93,   85,   85,
	   85,   85,   22,   78,   84,   86,   94,   85,   85,   85,   85,   22,
	   78,   84,   86,   95,   85,   85,   85,   85,   22,   78,   84,   86,
	   96,   85,   85,   85,   85,   22,   78,   84,   97,   85,   85,   85,
	   85,   22,   39,   38,   38,   87,   22,   78,   84,   86,   98,   85,
	   85,   85,   85,   22,   78,   84,   86,   99,   85,   85,   85,   85,
	   22,   78,   84,   86,  100,   85,   85,   85,   85,   22,   78,   84,
	  101,   85,   85,   85,   85,   22,   70,   69,   69,   87,   22,   19,
	  102,   22,   19,  103,   22,   19,  104,   22,   19,  105,   22,   19,
	  106,   22,   19,  107,   22,   19,  108,   22,   19,  109,   22,   19,
	  110,   22,   56,  111,   22,   19,  112,   22,   19,  113,   22,   19,
	  114,   22,   19,  115,   22,   62,  116,   22,  117,   12,  118,   12,
	  119,   12,  120,   12,  121,   12,  122,   12,  123,  124,   12,  125,
	  126,   12,  127,  128,   12,   27,   24,   25,  129,   22,   19,  130,
	   65,   22,   19,  131,   22,   19,  132,   22,   19,  133,   22,   19,
	  134,   22,   19,  135,   22,   19,  136,  137,   22,  125,  138,   22,
	  127,  139,   22,  140,  140,  140,   12,   16,   16,   16,   12,   15,
	   14,   16,   17,   16,   18,  141,   16,   16,   14,   16,   16,   16,
	   12,   15,   14,   16,   17,   16,   18,  142,   16,   16,   14,   16,
	   16,   16,   12,   15,   14,   16,   17,   16,   18,  143,   16,   16,
	   14,   16,   16,   16,   12,   15,   14,   16,   17,  144,   16,   18,
	   16,   16,   14,   16,   16,   16,   12,   15,   14,   16,   17,  145,
	   16,   18,   16,   16,   14,   16,   16,   16,   12,   15,   14,   16,
	   17,  146,   16,   18,   16,   16,   14,   16,   16,   16,   12,   15,
	   14,   16,   17,  147,  148,   16,   18,   16,   16,   14,   16,   16,
	   16,   12,  149,  150,   14,   16,   17,   16,   18,   16,   16,   14,
	   16,   16,   16,   12,  151,  152,   14,   16,   17,   16,   18,   16,
	   16,   14,   16,   16,   16,   12,  153,   12,  154,   12,  155,   12,
	  156,   12,  157,   12,  158,   12,  159,  159,  159,   12,  160,  161,
	  159,  159,  159,   12,   15,   14,  162,  163,  162,  164,  165,  162,
	  162,   14,  162,  162,  162,   12,  166,  166,  166,   12,  162,  162,
	  162,   12,  168,  167,  169,  170,  169,  171,  169,  169,  167,  169,
	  169,  169,   12,  172,  172,  172,   12,  169,  169,  169,   12,  168,
	  167,  169,  170,  169,  171,  173,  169,  169,  167,  169,  169,  169,
	   12,  168,  167,  169,  170,  169,  171,  174,  169,  169,  167,  169,
	  169,  169,   12,  168,  167,  169,  170,  169,  171,  175,  169,  169,
	  167,  169,  169,  169,   12,  168,  167,  169,  170,  176,  169,  171,
	  169,  169,  167,  169,  169,  169,   12,  168,  167,  169,  170,  177,
	  169,  171,  169,  169,  167,  169,  169,  169,   12,  168,  167,  169,
	  170,  178,  169,  171,  169,  169,  167,  169,  169,  169,   12,  168,
	  167,  169,  170,  179,  180,  169,  171,  169,  169,  167,  169,  169,
	  169,   12,  181,  182,  167,  169,  170,  169,  171,  169,  169,  167,
	  169,  169,  169,   12,  183,  184,  167,  169,  170,  169,  171,  169,
	  169,  167,  169,  169,  169,   12,   15,   14,  162,  163,  162,  164,
	  165,  185,  162,  162,   14,  162,  162,  162,   12,   15,   14,  162,
	  163,  162,  164,  165,  186,  162,  162,   14,  162,  162,  162,   12,
	   15,   14,  162,  163,  162,  164,  165,  187,  162,  162,   14,  162,
	  162,  162,   12,   15,   14,  162,  163,  188,  162,  164,  165,  162,
	  162,   14,  162,  162,  162,   12,   15,   14,  162,  163,  189,  162,
	  164,  165,  162,  162,   14,  162,  162,  162,   12,   15,   14,  162,
	  163,  190,  162,  164,  165,  162,  162,   14,  162,  162,  162,   12,
	   15,   14,  162,  163,  191,  192,  162,  164,  165,  162,  162,   14,
	  162,  162,  162,   12,  149,  150,   14,  162,  163,  162,  164,  165,
	  162,  162,   14,  162,  162,  162,   12,  151,  152,   14,  162,  163,
	  162,  164,  165,  162,  162,   14,  162,  162,  162,   12,  193,   12,
	  160,  193,   12,  194,  194,   12,  195,  195,   12,  196,  196,   12,
	  197,  197,   12,  198,  198,   12,  199,  199,   12,  200,  200,   12,
	  201,  202,  201,  202,   12,  203,  203,   12,  204,  204,   12,  205,
	  205,   12,  206,  206,   12,  207,  207,   12,   12,   23,   24,   25,
	   26,   22,   44,   45,   46,   44,   12,   42,   43,   38,   25,   26,
	   40,   41,   38,   22,   76,   76,   77,   77,   77,   12,   72,   73,
	   69,   74,   75,   69,   71,   71,   71,   22,    0
	};
}

private static final short _http_request_indicies[] = init__http_request_indicies_0();


private static short[] init__http_request_trans_targs_0()
{
	return new short [] {
	    0,    2,  155,  158,  162,    3,    4,    5,    6,    7,    8,    9,
	    0,  121,   10,  100,    9,  110,  112,   11,   38,   91,   12,  168,
	   13,   14,   45,  169,   15,   16,   17,   18,   19,   20,   21,   22,
	   23,   24,   25,   26,   76,   86,  170,   44,   27,   28,   39,   29,
	   30,   31,   32,   33,   34,   35,   36,   37,   11,   38,   40,   41,
	   42,   43,   11,   38,  171,   46,   47,   48,   49,   50,   51,   57,
	  172,   56,   60,   71,   52,   53,   11,   38,   53,   54,   55,  173,
	   12,   57,   58,   59,   61,   62,   63,   64,   65,   66,   67,   68,
	   69,   70,   72,   73,   74,   75,   77,   78,   79,   80,   81,   82,
	   83,   84,   85,   12,   87,   88,   89,   90,   12,   92,   93,   94,
	   95,   96,   97,   98,   99,   11,   38,   11,   38,  101,  102,  103,
	  104,  105,  106,  107,  108,  109,   12,   12,  111,  113,  114,  115,
	  116,  117,  118,  119,  120,  100,   10,  100,   10,  122,  123,  124,
	  125,  126,  127,  128,  129,  153,  129,  130,  132,  144,  131,   10,
	  100,  132,  133,  135,  134,  136,  137,  138,  139,  140,  141,  142,
	  143,  100,   10,  100,   10,  145,  146,  147,  148,  149,  150,  151,
	  152,  154,  156,  157,    8,  159,  160,  161,    8,  163,  166,  164,
	  165,    8,  167,    8
	};
}

private static final short _http_request_trans_targs[] = init__http_request_trans_targs_0();


private static byte[] init__http_request_trans_actions_0()
{
	return new byte [] {
	   11,    0,    0,    0,    0,    0,    0,    0,    0,    0,    9,   13,
	    0,    0,   15,   15,    0,    0,   15,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   27,   27,    0,    0,
	    0,    0,   25,   25,    0,    0,    0,    0,    0,    0,    0,   21,
	    0,    0,   21,   21,    0,   21,   23,   23,    0,    0,    0,    0,
	   23,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,   27,    0,    0,    0,    0,   25,    0,    0,    0,
	    0,    0,    0,    0,    0,   17,   17,   19,   19,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   17,   19,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   29,   29,   32,   32,    0,    0,    0,
	    0,    0,    0,    0,   13,    0,    0,    0,   15,   15,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   17,   17,   19,   19,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    7,    0,    0,    0,
	    0,    5,    0,    3
	};
}

private static final byte _http_request_trans_actions[] = init__http_request_trans_actions_0();


private static byte[] init__http_request_eof_actions_0()
{
	return new byte [] {
	    0,   11,   11,   11,   11,   11,   11,   11,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0
	};
}

private static final byte _http_request_eof_actions[] = init__http_request_eof_actions_0();


static final int http_request_start = 1;
static final int http_request_first_final = 168;
static final int http_request_error = 0;

static final int http_request_en_http_request = 1;


// line 111 "HttpParser.rl"


    //------------ End of generated code --------------------------------------

    private HttpRequest request;

    // Member variables required by Ragel
    private int cs;   // Current state

    // Index used to mark the start of interesting byte ranges. A value of -1 indicates no mark
    // has been set
    private int markStart;

    // Partially built byte array
    // TODO: see if this could be better done as a reusable ByteBuffer
    byte[] marked;

    /**
     * Reset the parser and associate it with the given HttpRequest
     */
    public void reset(HttpRequest request) {
        this.request = request;
        this.markStart = -1;
        this.marked = new byte[0];

        // --- Generated code ---
        
// line 430 "HttpParser.java"
	{
	cs = http_request_start;
	}

// line 138 "HttpParser.rl"
    }


    // Save a marked byte array for the next round of parsing
    private void saveMark(byte[] data, int to) throws UnsupportedEncodingException {
        if (markStart >= 0) {
            int existingLength = marked.length;
            int extraLength = to - markStart;
            marked = Arrays.copyOf(marked, existingLength + extraLength);
            System.arraycopy(data, markStart, marked, existingLength, extraLength);
            markStart = 0;
        }
    }

    private byte[] saveBytes(byte[] data, int to) throws UnsupportedEncodingException {
	saveMark(data, to);
        markStart = -1;
        return marked;
    }

    /**
     * Parses a HTTP header
     * @param buffer The buffer to read from. This should be in a state that is ready for reading. The buffer will
     *        be left in a state where it can be written to.
     * @return Indicates if the parsing is complete, incomplete or has had an error.
     */
    ParseState parse(ByteBuffer buffer) throws UnsupportedEncodingException {

        ParseState ret = ParseState.INCOMPLETE;

        int eof = -1;     // EOF code - required by Ragel

        // Initialise the current position for the Ragel parser
        int offset = buffer.arrayOffset();
        int p = buffer.position() + offset;
        assert(p == offset);

        // The end of the data in the buffer
        int pe = buffer.limit() + offset;

        // The block of data to parse
        final byte[] data = buffer.array();

        //--- Start of generated code ---
        
        
// line 482 "HttpParser.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _http_request_key_offsets[cs];
	_trans = _http_request_index_offsets[cs];
	_klen = _http_request_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _http_request_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _http_request_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _http_request_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _http_request_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _http_request_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _http_request_indicies[_trans];
	cs = _http_request_trans_targs[_trans];

	if ( _http_request_trans_actions[_trans] != 0 ) {
		_acts = _http_request_trans_actions[_trans];
		_nacts = (int) _http_request_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _http_request_actions[_acts++] )
			{
	case 0:
// line 17 "HttpParser.rl"
	{
        request.setMethod(HttpRequest.Method.GET);
    }
	break;
	case 1:
// line 21 "HttpParser.rl"
	{ 
        request.setMethod(HttpRequest.Method.PUT);
    }
	break;
	case 2:
// line 25 "HttpParser.rl"
	{ 
        request.setMethod(HttpRequest.Method.POST);
    }
	break;
	case 3:
// line 29 "HttpParser.rl"
	{ 
        request.setMethod(HttpRequest.Method.HEAD);
    }
	break;
	case 4:
// line 33 "HttpParser.rl"
	{ 
        request.setMethod(HttpRequest.Method.DELETE);
    }
	break;
	case 5:
// line 37 "HttpParser.rl"
	{
        ret = ParseState.METHOD_IS_UNSUPPORTED;
        { p += 1; _goto_targ = 5; if (true)  continue _goto;}
    }
	break;
	case 6:
// line 50 "HttpParser.rl"
	{ 
        markStart = p;
    }
	break;
	case 7:
// line 54 "HttpParser.rl"
	{ 
        request.setPath(saveBytes(data, p));
    }
	break;
	case 8:
// line 76 "HttpParser.rl"
	{ 
        request.setHttpVersion(HttpVersion.HTTP_1_0);
    }
	break;
	case 9:
// line 79 "HttpParser.rl"
	{ 
        request.setHttpVersion(HttpVersion.HTTP_1_1);
    }
	break;
	case 10:
// line 86 "HttpParser.rl"
	{ markStart = p; }
	break;
	case 11:
// line 87 "HttpParser.rl"
	{ request.setHost(saveBytes(data, p)); }
	break;
	case 12:
// line 89 "HttpParser.rl"
	{
        request.setKeepAlive(false);
    }
	break;
	case 13:
// line 93 "HttpParser.rl"
	{
        request.setKeepAlive(true);
    }
	break;
// line 643 "HttpParser.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
	if ( p == eof )
	{
	int __acts = _http_request_eof_actions[cs];
	int __nacts = (int) _http_request_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _http_request_actions[__acts++] ) {
	case 5:
// line 37 "HttpParser.rl"
	{
        ret = ParseState.METHOD_IS_UNSUPPORTED;
        { p += 1; _goto_targ = 5; if (true)  continue _goto;}
    }
	break;
// line 671 "HttpParser.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 184 "HttpParser.rl"

        if (cs == 
// line 684 "HttpParser.java"
0
// line 185 "HttpParser.rl"
 ) {
            System.err.println("Failed to parse p=" + p);
            return ParseState.ERROR;
        }

        if (cs < 
// line 693 "HttpParser.java"
168
// line 190 "HttpParser.rl"
 ) {
            // Get the buffer ready for writing
            saveMark(data, p);
            buffer.position(p);
            buffer.compact();
            return ret;
        }

        return ParseState.COMPLETE;
    }

}

