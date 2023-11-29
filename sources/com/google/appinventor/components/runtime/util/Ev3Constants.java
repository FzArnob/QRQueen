package com.google.appinventor.components.runtime.util;

public class Ev3Constants {

    public static class DataFormat {
        public static final byte DATA_PCT = 16;
        public static final byte DATA_RAW = 18;
        public static final byte DATA_SI = 19;
    }

    public static class DirectCommandType {
        public static final byte DIRECT_COMMAND_NO_REPLY = Byte.MIN_VALUE;
        public static final byte DIRECT_COMMAND_REPLY = 0;
    }

    public static class DirectReplyType {
        public static final byte DIRECT_REPLY = 2;
        public static final byte DIRECT_REPLY_ERROR = 4;
    }

    public static class FontType {
        public static final byte LARGE_FONT = 2;
        public static final byte NORMAL_FONT = 0;
        public static final byte SMALL_FONT = 1;
        public static final byte TINY_FONT = 3;
    }

    public static class InputDeviceSubcode {
        public static final byte CAL_DEFAULT = 4;
        public static final byte CAL_MAX = 8;
        public static final byte CAL_MIN = 7;
        public static final byte CAL_MINMAX = 3;
        public static final byte CLR_ALL = 10;
        public static final byte CLR_CHANGES = 26;
        public static final byte GET_BUMPS = 31;
        public static final byte GET_CHANGES = 25;
        public static final byte GET_CONNECTION = 12;
        public static final byte GET_FIGURES = 24;
        public static final byte GET_FORMAT = 2;
        public static final byte GET_MINMAX = 30;
        public static final byte GET_MODENAME = 22;
        public static final byte GET_NAME = 21;
        public static final byte GET_RAW = 11;
        public static final byte GET_SYMBOL = 6;
        public static final byte GET_TYPEMODE = 5;
        public static final byte READY_PCT = 27;
        public static final byte READY_RAW = 28;
        public static final byte READY_SI = 29;
        public static final byte SETUP = 9;
        public static final byte SET_RAW = 23;
        public static final byte STOP_ALL = 13;
    }

    public static class Opcode {
        public static final byte ADD16 = 17;
        public static final byte ADD32 = 18;
        public static final byte ADD8 = 16;
        public static final byte ADDF = 19;
        public static final byte AND16 = 37;
        public static final byte AND32 = 38;
        public static final byte AND8 = 36;
        public static final byte ARRAY = -63;
        public static final byte ARRAY_APPEND = -60;
        public static final byte ARRAY_READ = -61;
        public static final byte ARRAY_WRITE = -62;
        public static final byte BP0 = -120;
        public static final byte BP1 = -119;
        public static final byte BP2 = -118;
        public static final byte BP3 = -117;
        public static final byte BP_SET = -116;
        public static final byte CALL = 9;
        public static final byte COM_GET = -45;
        public static final byte COM_READ = -111;
        public static final byte COM_READDATA = -47;
        public static final byte COM_READY = -48;
        public static final byte COM_REMOVE = -42;
        public static final byte COM_SET = -44;
        public static final byte COM_TEST = -43;
        public static final byte COM_WRITE = -110;
        public static final byte COM_WRITEDATA = -46;
        public static final byte COM_WRITEFILE = -41;
        public static final byte CP_EQ16 = 77;
        public static final byte CP_EQ32 = 78;
        public static final byte CP_EQ8 = 76;
        public static final byte CP_EQF = 79;
        public static final byte CP_GT16 = 73;
        public static final byte CP_GT32 = 74;
        public static final byte CP_GT8 = 72;
        public static final byte CP_GTEQ16 = 89;
        public static final byte CP_GTEQ32 = 90;
        public static final byte CP_GTEQ8 = 88;
        public static final byte CP_GTEQF = 91;
        public static final byte CP_GTF = 75;
        public static final byte CP_LT16 = 69;
        public static final byte CP_LT32 = 70;
        public static final byte CP_LT8 = 68;
        public static final byte CP_LTEQ16 = 85;
        public static final byte CP_LTEQ32 = 86;
        public static final byte CP_LTEQ8 = 84;
        public static final byte CP_LTEQF = 87;
        public static final byte CP_LTF = 71;
        public static final byte CP_NEQ16 = 81;
        public static final byte CP_NEQ32 = 82;
        public static final byte CP_NEQ8 = 80;
        public static final byte CP_NEQF = 83;
        public static final byte DIV16 = 29;
        public static final byte DIV32 = 30;
        public static final byte DIV8 = 28;
        public static final byte DIVF = 31;
        public static final byte DO = 15;
        public static final byte ERROR = 0;
        public static final byte FILE = -64;
        public static final byte FILENAME = -58;
        public static final byte INFO = 124;
        public static final byte INIT_BYTES = 47;
        public static final byte INPUT_DEVICE = -103;
        public static final byte INPUT_DEVICE_LIST = -104;
        public static final byte INPUT_READ = -102;
        public static final byte INPUT_READEXT = -98;
        public static final byte INPUT_READSI = -99;
        public static final byte INPUT_READY = -100;
        public static final byte INPUT_SAMPLE = -105;
        public static final byte INPUT_TEST = -101;
        public static final byte INPUT_WRITE = -97;
        public static final byte JR = 64;
        public static final byte JR_EQ16 = 109;
        public static final byte JR_EQ32 = 110;
        public static final byte JR_EQ8 = 108;
        public static final byte JR_EQF = 111;
        public static final byte JR_FALSE = 65;
        public static final byte JR_GT16 = 105;
        public static final byte JR_GT32 = 106;
        public static final byte JR_GT8 = 104;
        public static final byte JR_GTEQ16 = 121;
        public static final byte JR_GTEQ32 = 122;
        public static final byte JR_GTEQ8 = 120;
        public static final byte JR_GTEQF = 123;
        public static final byte JR_GTF = 107;
        public static final byte JR_LT16 = 101;
        public static final byte JR_LT32 = 102;
        public static final byte JR_LT8 = 100;
        public static final byte JR_LTEQ16 = 117;
        public static final byte JR_LTEQ32 = 118;
        public static final byte JR_LTEQ8 = 116;
        public static final byte JR_LTEQF = 119;
        public static final byte JR_LTF = 103;
        public static final byte JR_NAN = 67;
        public static final byte JR_NEQ16 = 113;
        public static final byte JR_NEQ32 = 114;
        public static final byte JR_NEQ8 = 112;
        public static final byte JR_NEQF = 115;
        public static final byte JR_TRUE = 66;
        public static final byte KEEP_ALIVE = -112;
        public static final byte LABEL = 13;
        public static final byte MAILBOX_CLOSE = -35;
        public static final byte MAILBOX_OPEN = -40;
        public static final byte MAILBOX_READ = -38;
        public static final byte MAILBOX_READY = -36;
        public static final byte MAILBOX_TEST = -37;
        public static final byte MAILBOX_WRITE = -39;
        public static final byte MATH = -115;
        public static final byte MEMORY_READ = Byte.MAX_VALUE;
        public static final byte MEMORY_USAGE = -59;
        public static final byte MEMORY_WRITE = 126;
        public static final byte MOVE16_16 = 53;
        public static final byte MOVE16_32 = 54;
        public static final byte MOVE16_8 = 52;
        public static final byte MOVE16_F = 55;
        public static final byte MOVE32_16 = 57;
        public static final byte MOVE32_32 = 58;
        public static final byte MOVE32_8 = 56;
        public static final byte MOVE32_F = 59;
        public static final byte MOVE8_16 = 49;
        public static final byte MOVE8_32 = 50;
        public static final byte MOVE8_8 = 48;
        public static final byte MOVE8_F = 51;
        public static final byte MOVEF_16 = 61;
        public static final byte MOVEF_32 = 62;
        public static final byte MOVEF_8 = 60;
        public static final byte MOVEF_F = 63;
        public static final byte MUL16 = 25;
        public static final byte MUL32 = 26;
        public static final byte MUL8 = 24;
        public static final byte MULF = 27;
        public static final byte NOP = 1;
        public static final byte NOTE_TO_FREQ = 99;
        public static final byte OBJECT_END = 10;
        public static final byte OBJECT_START = 5;
        public static final byte OBJECT_STOP = 4;
        public static final byte OBJECT_TRIG = 6;
        public static final byte OBJECT_WAIT = 7;
        public static final byte OR16 = 33;
        public static final byte OR32 = 34;
        public static final byte OR8 = 32;
        public static final byte OUTPUT_CLR_COUNT = -78;
        public static final byte OUTPUT_GET_COUNT = -77;
        public static final byte OUTPUT_GET_TYPE = -96;
        public static final byte OUTPUT_POLARITY = -89;
        public static final byte OUTPUT_POSITION = -85;
        public static final byte OUTPUT_POWER = -92;
        public static final byte OUTPUT_PRG_STOP = -76;
        public static final byte OUTPUT_READ = -88;
        public static final byte OUTPUT_READY = -86;
        public static final byte OUTPUT_RESET = -94;
        public static final byte OUTPUT_SET_TYPE = -95;
        public static final byte OUTPUT_SPEED = -91;
        public static final byte OUTPUT_START = -90;
        public static final byte OUTPUT_STEP_POWER = -84;
        public static final byte OUTPUT_STEP_SPEED = -82;
        public static final byte OUTPUT_STEP_SYNC = -80;
        public static final byte OUTPUT_STOP = -93;
        public static final byte OUTPUT_TEST = -87;
        public static final byte OUTPUT_TIME_POWER = -83;
        public static final byte OUTPUT_TIME_SPEED = -81;
        public static final byte OUTPUT_TIME_SYNC = -79;
        public static final byte PORT_CNV_INPUT = 98;
        public static final byte PORT_CNV_OUTPUT = 97;
        public static final byte PROBE = 14;
        public static final byte PROGRAM_INFO = 12;
        public static final byte PROGRAM_START = 3;
        public static final byte PROGRAM_STOP = 2;
        public static final byte RANDOM = -114;
        public static final byte READ16 = -55;
        public static final byte READ32 = -54;
        public static final byte READ8 = -56;
        public static final byte READF = -53;
        public static final byte RETURN = 8;
        public static final byte RL16 = 45;
        public static final byte RL32 = 46;
        public static final byte RL8 = 44;
        public static final byte SELECT16 = 93;
        public static final byte SELECT32 = 94;
        public static final byte SELECT8 = 92;
        public static final byte SELECTF = 95;
        public static final byte SLEEP = 11;
        public static final byte SOUND = -108;
        public static final byte SOUND_READY = -106;
        public static final byte SOUND_TEST = -107;
        public static final byte STRINGS = 125;
        public static final byte SUB16 = 21;
        public static final byte SUB32 = 22;
        public static final byte SUB8 = 20;
        public static final byte SUBF = 23;
        public static final byte SYSTEM = 96;
        public static final byte TIMER_READ = -121;
        public static final byte TIMER_READY = -122;
        public static final byte TIMER_READ_US = -113;
        public static final byte TIMER_WAIT = -123;
        public static final byte TST = -1;
        public static final byte UI_BUTTON = -125;
        public static final byte UI_DRAW = -124;
        public static final byte UI_FLUSH = Byte.MIN_VALUE;
        public static final byte UI_READ = -127;
        public static final byte UI_WRITE = -126;
        public static final byte WRITE16 = -51;
        public static final byte WRITE32 = -50;
        public static final byte WRITE8 = -52;
        public static final byte WRITEF = -49;
        public static final byte XOR16 = 41;
        public static final byte XOR32 = 42;
        public static final byte XOR8 = 40;
    }

    public static class SoundSubcode {
        public static final byte BREAK = 0;
        public static final byte PLAY = 2;
        public static final byte REPEAT = 3;
        public static final byte SERVICE = 4;
        public static final byte TONE = 1;
    }

    public static class SystemCommand {
        public static final byte BEGIN_DOWNLOAD = -110;
        public static final byte BEGIN_GETFILE = -106;
        public static final byte BEGIN_UPLOAD = -108;
        public static final byte BLUETOOTHPIN = -97;
        public static final byte CLOSE_FILEHANDLE = -104;
        public static final byte CONTINUE_DOWNLOAD = -109;
        public static final byte CONTINUE_GETFILE = -105;
        public static final byte CONTINUE_LIST_FILES = -102;
        public static final byte CONTINUE_UPLOAD = -107;
        public static final byte CREATE_DIR = -101;
        public static final byte DELETE_FILE = -100;
        public static final byte ENTERFWUPDATE = -96;
        public static final byte LIST_FILES = -103;
        public static final byte LIST_OPEN_HANDLES = -99;
        public static final byte WRITEMAILBOX = -98;
    }

    public static class SystemCommandType {
        public static final byte SYSTEM_COMMAND_NO_REPLY = -127;
        public static final byte SYSTEM_COMMAND_REPLY = 1;
    }

    public static class SystemReplyStatus {
        public static final byte CORRUPT_FILE = 3;
        public static final byte END_OF_FILE = 8;
        public static final byte FILE_EXITS = 7;
        public static final byte HANDLE_NOT_READY = 2;
        public static final byte ILLEGAL_CONNECTION = 12;
        public static final byte ILLEGAL_FILENAME = 11;
        public static final byte ILLEGAL_PATH = 6;
        public static final byte NO_HANDLES_AVAILABLE = 4;
        public static final byte NO_PERMISSION = 5;
        public static final byte SIZE_ERROR = 9;
        public static final byte SUCCESS = 0;
        public static final byte UNKNOWN_ERROR = 10;
        public static final byte UNKNOWN_HANDLE = 1;
    }

    public static class SystemReplyType {
        public static final byte SYSTEM_REPLY = 3;
        public static final byte SYSTEM_REPLY_ERROR = 5;
    }

    public static class UIButtonSubcode {
        public static final byte FLUSH = 4;
        public static final byte GET_BACK_BLOCK = 11;
        public static final byte GET_BUMBED = 14;
        public static final byte GET_CLICK = 15;
        public static final byte GET_HORZ = 7;
        public static final byte GET_VERT = 8;
        public static final byte LONGPRESS = 2;
        public static final byte PRESS = 5;
        public static final byte PRESSED = 9;
        public static final byte RELEASE = 6;
        public static final byte SET_BACK_BLOCK = 10;
        public static final byte SHORTPRESS = 1;
        public static final byte TESTLONGPRESS = 13;
        public static final byte TESTSHORTPRESS = 12;
        public static final byte WAIT_FOR_PRESS = 3;
    }

    public static class UIDrawSubcode {
        public static final byte BMPFILE = 28;
        public static final byte BROWSE = 14;
        public static final byte CIRCLE = 4;
        public static final byte CLEAN = 1;
        public static final byte DOTLINE = 21;
        public static final byte FILLCIRCLE = 24;
        public static final byte FILLRECT = 9;
        public static final byte FILLWINDOW = 19;
        public static final byte GRAPH_DRAW = 31;
        public static final byte GRAPH_SETUP = 30;
        public static final byte ICON = 6;
        public static final byte ICON_QUESTION = 27;
        public static final byte INVERSERECT = 16;
        public static final byte KEYBOARD = 13;
        public static final byte LINE = 3;
        public static final byte NOTIFICATION = 11;
        public static final byte PICTURE = 7;
        public static final byte PIXEL = 2;
        public static final byte POPUP = 29;
        public static final byte QUESTION = 12;
        public static final byte RECT = 10;
        public static final byte RESTORE = 26;
        public static final byte SCROLL = 20;
        public static final byte SELECT_FONT = 17;
        public static final byte STORE = 25;
        public static final byte TEXT = 5;
        public static final byte TEXTBOX = 32;
        public static final byte TOPLINE = 18;
        public static final byte UPDATE = 0;
        public static final byte VALUE = 8;
        public static final byte VERTBAR = 15;
        public static final byte VIEW_UNIT = 23;
        public static final byte VIEW_VALUE = 22;
    }

    public static class UIReadSubcode {
        public static final byte GET_ADDRESS = 13;
        public static final byte GET_CODE = 14;
        public static final byte GET_EVENT = 4;
        public static final byte GET_FW_BUILD = 11;
        public static final byte GET_FW_VERS = 10;
        public static final byte GET_HW_VERS = 9;
        public static final byte GET_IBATT = 2;
        public static final byte GET_IINT = 6;
        public static final byte GET_IMOTOR = 7;
        public static final byte GET_IP = 27;
        public static final byte GET_LBATT = 18;
        public static final byte GET_OS_BUILD = 12;
        public static final byte GET_OS_VERS = 3;
        public static final byte GET_POWER = 29;
        public static final byte GET_SDCARD = 30;
        public static final byte GET_SHUTDOWN = 16;
        public static final byte GET_STRING = 8;
        public static final byte GET_TBATT = 5;
        public static final byte GET_USBSTICK = 31;
        public static final byte GET_VBATT = 1;
        public static final byte GET_VERSION = 26;
        public static final byte GET_WARNING = 17;
        public static final byte KEY = 15;
        public static final byte TEXTBOX_READ = 21;
    }

    public static class UIWriteSubcode {
        public static final byte ADDRESS = 13;
        public static final byte CODE = 14;
        public static final byte DOWNLOAD_END = 15;
        public static final byte FLOATVALUE = 2;
        public static final byte GRAPH_SAMPLE = 30;
        public static final byte INIT_RUN = 25;
        public static final byte LED = 27;
        public static final byte POWER = 29;
        public static final byte PUT_STRING = 8;
        public static final byte SCREEN_BLOCK = 16;
        public static final byte SET_BUSY = 22;
        public static final byte SET_TESTPIN = 24;
        public static final byte STAMP = 3;
        public static final byte TERMINAL = 31;
        public static final byte TEXTBOX_APPEND = 21;
        public static final byte UPDATE_RUN = 26;
        public static final byte VALUE16 = 10;
        public static final byte VALUE32 = 11;
        public static final byte VALUE8 = 9;
        public static final byte VALUEF = 12;
        public static final byte WRITE_FLUSH = 1;
    }
}
