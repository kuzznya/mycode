program=$1
extension="${program##*.}"

case $extension in
	"c") 
		gcc $program -o solution
		;;
	"cpp")
		g++ $program -o solution
		;;
	*)
		echo "ERROR: Unsupported solution format"
		;;
esac

LD_PRELOAD=/$PWD/EasySandbox.so ./solution
rm -rf solution