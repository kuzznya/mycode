if [[ $1 != "clean" ]]; 
then
	cmake .
	make
	sudo make install
	sudo make permission
else
	rm -rf install_manifest.txt
	rm -rf CMakeCache.txt
	rm -rf cmake_install.cmake
	rm -rf Makefile
	rm -rf CMakeFiles/*
	rm -r  CMakeFiles
	rm -rf sandbox
fi