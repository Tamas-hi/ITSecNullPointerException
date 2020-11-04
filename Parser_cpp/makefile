parser: com_nullpointerexception_ParserJNI.o
	g++ -c -I"%JAVA_HOME%\include" -I "%JAVA_HOME%\include\win32" com_nullpointerexception_ParserJNI.cpp -o com_nullpointerexception_ParserJNI.o
	g++ -shared -o parser.dll com_nullpointerexception_ParserJNI.o -Wl,--add-stdcall-alias
