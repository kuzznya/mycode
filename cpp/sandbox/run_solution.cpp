#include <cstdlib>
#include <string>
#include <fstream>

#define TIME_LIMIT       1
#define MEMORY_LIMIT 32768

int main(int argc, char** argv)
{
    printf("Execution started...\n");

    if (argc != 4)
    {
        printf("[ERROR] Incorrect number of arguments. Usage:\n");
        printf("./run_solution program input output\n");
        exit(1);
    }
    std::string program = argv[1];
    std::string input   = argv[2];
    std::string output  = argv[3];

    std::string extension = program.substr(program.find_last_of('.') + 1);

    std::ifstream in;

    if (extension == "c" || extension == "cpp")
    {
        // Compilation
        std::string command = "if ! ";
        command += (extension == "c" ? "gcc " : "g++ ") + program + " -o solution; then\n touch temp\n fi";
        system( command.c_str() );
        
        // CE
        in.open("temp");
        if ( in.is_open() )
        {
            printf("COMPILATION ERROR!\n");
            system("rm -rf temp");
            in.close();
            return 0;
        }
        
        // Execution
        command = "if ! cat " + input + " | ./sandbox --cpu " + std::to_string(TIME_LIMIT) + " --mem " + std::to_string(MEMORY_LIMIT) + " --usage verdict.log --exec ./solution > result.txt; then\n touch temp\n fi";
        system( command.c_str() );
        
        // RE
        in.open("temp");
        if ( in.is_open() )
        {
            printf("RUNTIME ERROR!\n");
            system("rm -rf temp");
            system("rm -rf solution");
            in.close();
            return 0;
        }
        
        // TL
        
        
        // ML
        
        
        // Remove solution's executable
        system("rm -rf solution");
        
        command = "diff result.txt " + output;
        int result = system( command.c_str() );
        if (result != 0)
        {
            // WA
            printf("WRONG ANSWER!\n");
            return 0;
        }
        else
        {
            // OK
            printf("OK!\n");
            return 0;
        }
        
    }
    else if (extension == "java")
    {
        // Execution
        std::string command = "cat " + input + "LD_PRELOAD=/$PWD/EasySandbox.so java " + program + "> result.txt";
        system( command.c_str() );
        
        
    }
    else
    {
        printf("[ERROR] Wrong file extension. Aborting...\n");
        return 1;
    }

    printf("Execution finished...\n");

    
    return 0;
}
