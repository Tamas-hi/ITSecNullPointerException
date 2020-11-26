parser:
	g++ -c -I "$(JAVA_HOME)\include" -I "$(JAVA_HOME)\include\win32" com_webshop_controller_HomeController.cpp -o com_webshop_controller_HomeController.o
	g++ -shared -o parser.dll com_webshop_controller_HomeController.o -Wl,--add-stdcall-alias