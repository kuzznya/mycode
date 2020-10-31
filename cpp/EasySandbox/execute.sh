program=$1
extension="${program##*.}"

case $extension in
	"c") 
		gcc $program -o solution
		LD_PRELOAD=/$PWD/EasySandbox.so ./solution
		rm -rf solution
		;;
	"cpp")
		g++ $program -o solution
		LD_PRELOAD=/$PWD/EasySandbox.so ./solution
		rm -rf solution
		;;
	"java")
		LD_PRELOAD=/$PWD/EasySandbox.so java $program
		;;
	*)
		echo "ERROR: Unsupported solution format"
		;;
esac