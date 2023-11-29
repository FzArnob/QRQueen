package org.jose4j.json.internal.json_simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;

public class JSONParser {
    public static final int S_END = 6;
    public static final int S_INIT = 0;
    public static final int S_IN_ARRAY = 3;
    public static final int S_IN_ERROR = -1;
    public static final int S_IN_FINISHED_VALUE = 1;
    public static final int S_IN_OBJECT = 2;
    public static final int S_IN_PAIR_VALUE = 5;
    public static final int S_PASSED_PAIR_KEY = 4;
    private LinkedList handlerStatusStack;
    private Yylex lexer = new Yylex((Reader) null);
    private int status = 0;
    private Yytoken token = null;

    public JSONParser() {
        Reader reader = null;
    }

    private int peekStatus(LinkedList linkedList) {
        if (linkedList.size() == 0) {
            return -1;
        }
        return ((Integer) linkedList.getFirst()).intValue();
    }

    public void reset() {
        this.token = null;
        this.status = 0;
        this.handlerStatusStack = null;
    }

    public void reset(Reader reader) {
        this.lexer.yyreset(reader);
        reset();
    }

    public int getPosition() {
        return this.lexer.getPosition();
    }

    public Object parse(String str) throws ParseException {
        ContainerFactory containerFactory = null;
        return parse(str, (ContainerFactory) null);
    }

    public Object parse(String str, ContainerFactory containerFactory) throws ParseException {
        try {
            return parse((Reader) new StringReader(str), containerFactory);
        } catch (IOException e) {
            throw new ParseException(-1, 2, e);
        }
    }

    public Object parse(Reader reader) throws IOException, ParseException {
        ContainerFactory containerFactory = null;
        return parse(reader, (ContainerFactory) null);
    }

    public Object parse(Reader reader, ContainerFactory containerFactory) throws IOException, ParseException {
        reset(reader);
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        do {
            try {
                nextToken();
                int i = this.status;
                if (i != -1) {
                    if (i == 0) {
                        int i2 = this.token.type;
                        if (i2 == 0) {
                            this.status = 1;
                            linkedList.addFirst(new Integer(1));
                            linkedList2.addFirst(this.token.value);
                        } else if (i2 == 1) {
                            this.status = 2;
                            linkedList.addFirst(new Integer(2));
                            linkedList2.addFirst(createObjectContainer(containerFactory));
                        } else if (i2 != 3) {
                            this.status = -1;
                        } else {
                            this.status = 3;
                            linkedList.addFirst(new Integer(3));
                            linkedList2.addFirst(createArrayContainer(containerFactory));
                        }
                    } else if (i != 1) {
                        if (i == 2) {
                            int i3 = this.token.type;
                            if (i3 != 0) {
                                if (i3 != 2) {
                                    if (i3 != 5) {
                                        this.status = -1;
                                    }
                                } else if (linkedList2.size() > 1) {
                                    linkedList.removeFirst();
                                    linkedList2.removeFirst();
                                    this.status = peekStatus(linkedList);
                                } else {
                                    this.status = 1;
                                }
                            } else if (this.token.value instanceof String) {
                                linkedList2.addFirst((String) this.token.value);
                                this.status = 4;
                                linkedList.addFirst(new Integer(4));
                            } else {
                                this.status = -1;
                            }
                        } else if (i == 3) {
                            int i4 = this.token.type;
                            if (i4 == 0) {
                                ((List) linkedList2.getFirst()).add(this.token.value);
                            } else if (i4 == 1) {
                                Map createObjectContainer = createObjectContainer(containerFactory);
                                ((List) linkedList2.getFirst()).add(createObjectContainer);
                                this.status = 2;
                                linkedList.addFirst(new Integer(2));
                                linkedList2.addFirst(createObjectContainer);
                            } else if (i4 == 3) {
                                List createArrayContainer = createArrayContainer(containerFactory);
                                ((List) linkedList2.getFirst()).add(createArrayContainer);
                                this.status = 3;
                                linkedList.addFirst(new Integer(3));
                                linkedList2.addFirst(createArrayContainer);
                            } else if (i4 != 4) {
                                if (i4 != 5) {
                                    this.status = -1;
                                }
                            } else if (linkedList2.size() > 1) {
                                linkedList.removeFirst();
                                linkedList2.removeFirst();
                                this.status = peekStatus(linkedList);
                            } else {
                                this.status = 1;
                            }
                        } else if (i == 4) {
                            int i5 = this.token.type;
                            if (i5 == 0) {
                                linkedList.removeFirst();
                                ((Map) linkedList2.getFirst()).put((String) linkedList2.removeFirst(), this.token.value);
                                this.status = peekStatus(linkedList);
                            } else if (i5 == 1) {
                                linkedList.removeFirst();
                                Map map = (Map) linkedList2.getFirst();
                                Map createObjectContainer2 = createObjectContainer(containerFactory);
                                map.put((String) linkedList2.removeFirst(), createObjectContainer2);
                                this.status = 2;
                                linkedList.addFirst(new Integer(2));
                                linkedList2.addFirst(createObjectContainer2);
                            } else if (i5 == 3) {
                                linkedList.removeFirst();
                                Map map2 = (Map) linkedList2.getFirst();
                                List createArrayContainer2 = createArrayContainer(containerFactory);
                                map2.put((String) linkedList2.removeFirst(), createArrayContainer2);
                                this.status = 3;
                                linkedList.addFirst(new Integer(3));
                                linkedList2.addFirst(createArrayContainer2);
                            } else if (i5 != 6) {
                                this.status = -1;
                            }
                        }
                    } else if (this.token.type == -1) {
                        return linkedList2.removeFirst();
                    } else {
                        throw new ParseException(getPosition(), 1, this.token);
                    }
                    if (this.status == -1) {
                        throw new ParseException(getPosition(), 1, this.token);
                    }
                } else {
                    throw new ParseException(getPosition(), 1, this.token);
                }
            } catch (IOException e) {
                throw e;
            }
        } while (this.token.type != -1);
        throw new ParseException(getPosition(), 1, this.token);
    }

    private void nextToken() throws ParseException, IOException {
        Yytoken yylex = this.lexer.yylex();
        this.token = yylex;
        if (yylex == null) {
            this.token = new Yytoken(-1, (Object) null);
        }
    }

    private Map createObjectContainer(ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONObject();
        }
        Map createObjectContainer = containerFactory.createObjectContainer();
        return createObjectContainer == null ? new JSONObject() : createObjectContainer;
    }

    private List createArrayContainer(ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONArray();
        }
        List creatArrayContainer = containerFactory.creatArrayContainer();
        return creatArrayContainer == null ? new JSONArray() : creatArrayContainer;
    }

    public void parse(String str, ContentHandler contentHandler) throws ParseException {
        parse(str, contentHandler, false);
    }

    public void parse(String str, ContentHandler contentHandler, boolean z) throws ParseException {
        try {
            parse((Reader) new StringReader(str), contentHandler, z);
        } catch (IOException e) {
            throw new ParseException(-1, 2, e);
        }
    }

    public void parse(Reader reader, ContentHandler contentHandler) throws IOException, ParseException {
        parse(reader, contentHandler, false);
    }

    public void parse(Reader reader, ContentHandler contentHandler, boolean z) throws IOException, ParseException {
        if (!z) {
            reset(reader);
            this.handlerStatusStack = new LinkedList();
        } else if (this.handlerStatusStack == null) {
            reset(reader);
            this.handlerStatusStack = new LinkedList();
        }
        LinkedList linkedList = this.handlerStatusStack;
        do {
            try {
                switch (this.status) {
                    case -1:
                        throw new ParseException(getPosition(), 1, this.token);
                    case 0:
                        contentHandler.startJSON();
                        nextToken();
                        int i = this.token.type;
                        if (i == 0) {
                            this.status = 1;
                            linkedList.addFirst(new Integer(1));
                            if (!contentHandler.primitive(this.token.value)) {
                                return;
                            }
                        } else if (i == 1) {
                            this.status = 2;
                            linkedList.addFirst(new Integer(2));
                            if (!contentHandler.startObject()) {
                                return;
                            }
                        } else if (i != 3) {
                            this.status = -1;
                            break;
                        } else {
                            this.status = 3;
                            linkedList.addFirst(new Integer(3));
                            if (!contentHandler.startArray()) {
                                return;
                            }
                        }
                        break;
                    case 1:
                        nextToken();
                        if (this.token.type == -1) {
                            contentHandler.endJSON();
                            this.status = 6;
                            return;
                        }
                        this.status = -1;
                        throw new ParseException(getPosition(), 1, this.token);
                    case 2:
                        nextToken();
                        int i2 = this.token.type;
                        if (i2 == 0) {
                            if (!(this.token.value instanceof String)) {
                                this.status = -1;
                                break;
                            } else {
                                this.status = 4;
                                linkedList.addFirst(new Integer(4));
                                if (!contentHandler.startObjectEntry((String) this.token.value)) {
                                    return;
                                }
                            }
                        } else if (i2 != 2) {
                            if (i2 != 5) {
                                this.status = -1;
                                break;
                            }
                        } else {
                            if (linkedList.size() > 1) {
                                linkedList.removeFirst();
                                this.status = peekStatus(linkedList);
                            } else {
                                this.status = 1;
                            }
                            if (!contentHandler.endObject()) {
                                return;
                            }
                        }
                        break;
                    case 3:
                        nextToken();
                        int i3 = this.token.type;
                        if (i3 != 0) {
                            if (i3 == 1) {
                                this.status = 2;
                                linkedList.addFirst(new Integer(2));
                                if (!contentHandler.startObject()) {
                                    return;
                                }
                            } else if (i3 == 3) {
                                this.status = 3;
                                linkedList.addFirst(new Integer(3));
                                if (!contentHandler.startArray()) {
                                    return;
                                }
                            } else if (i3 != 4) {
                                if (i3 != 5) {
                                    this.status = -1;
                                    break;
                                }
                            } else {
                                if (linkedList.size() > 1) {
                                    linkedList.removeFirst();
                                    this.status = peekStatus(linkedList);
                                } else {
                                    this.status = 1;
                                }
                                if (!contentHandler.endArray()) {
                                    return;
                                }
                            }
                        } else if (!contentHandler.primitive(this.token.value)) {
                            return;
                        }
                        break;
                    case 4:
                        nextToken();
                        int i4 = this.token.type;
                        if (i4 == 0) {
                            linkedList.removeFirst();
                            this.status = peekStatus(linkedList);
                            if (!contentHandler.primitive(this.token.value) || !contentHandler.endObjectEntry()) {
                                return;
                            }
                        } else if (i4 == 1) {
                            linkedList.removeFirst();
                            linkedList.addFirst(new Integer(5));
                            this.status = 2;
                            linkedList.addFirst(new Integer(2));
                            if (!contentHandler.startObject()) {
                                return;
                            }
                        } else if (i4 != 3) {
                            if (i4 != 6) {
                                this.status = -1;
                                break;
                            }
                        } else {
                            linkedList.removeFirst();
                            linkedList.addFirst(new Integer(5));
                            this.status = 3;
                            linkedList.addFirst(new Integer(3));
                            if (!contentHandler.startArray()) {
                                return;
                            }
                        }
                        break;
                    case 5:
                        linkedList.removeFirst();
                        this.status = peekStatus(linkedList);
                        if (!contentHandler.endObjectEntry()) {
                            return;
                        }
                        break;
                    case 6:
                        return;
                }
                if (this.status == -1) {
                    throw new ParseException(getPosition(), 1, this.token);
                }
            } catch (IOException e) {
                this.status = -1;
                throw e;
            } catch (ParseException e2) {
                this.status = -1;
                throw e2;
            } catch (RuntimeException e3) {
                this.status = -1;
                throw e3;
            } catch (Error e4) {
                this.status = -1;
                throw e4;
            }
        } while (this.token.type != -1);
        this.status = -1;
        throw new ParseException(getPosition(), 1, this.token);
    }
}
