# Build

Use `./build.sh` to build the sandbox. For cleaning build files use `./build.sh clean`.

# Usage

```
usage: ./sandbox <options> --exec <command>
Available options:
	--cpu     <seconds>           Default: 1 second(s)
	--mem     <kbytes>            Default: 32768 kbyte(s)
	--space   <kbytes>            Default: 0 kbyte(s)
	--uids    <minuid> <maxuid>   Default: 5000-65535
	--minuid  <uid>               Default: 5000
	--maxuid  <uid>               Default: 65535
	--core    <kbytes>            Default: 0 kbyte(s)
	--nproc   <number>            Default: 0 proccess(es)
	--fsize   <kbytes>            Default: 8192 kbyte(s)
	--stack   <kbytes>            Default: 8192 kbyte(s)
	--clock   <seconds>           Wall clock timeout (default: 10)
	--usage   <filename>          Report statistics to ... (default: stderr)
	--chroot  <path>              Directory to chrooted (default: /tmp)
	--error   <path>              Print stderr to file (default: /dev/null)
```

Example: `./sandbox --exec tests/hello.cpp` executes *hello.cpp* with default parameters.
