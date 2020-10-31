#include <cstdlib>
#include <string>
#include <fstream>
#include <random>

std::string random_filename()
{
     std::string str("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");

     std::random_device rd;
     std::mt19937 generator(rd());

     std::shuffle(str.begin(), str.end(), generator);

     return str.substr(0, 32);
}

std::string parse_verdict(std::string& verdict)
{
    std::ifstream in( verdict.c_str() );
    std::string value;
    getline(in, value);
    return value;
}

void remove_dump(std::string& filename)
{
    std::string tmp = "rm -rf " + filename;
    system( tmp.c_str() );
}

void show_usage()
{
    printf("Usage:\n");
    printf("./run_solution program time_limit memory_limit flag input output\n");
    printf("\t program      - path to the file with the source code;\n");
    printf("\t time_limit   - maximum amount of time (in seconds) that the code can execute;\n");
    printf("\t memory_limit - maximum amount of memory (in kbytes) that the code can consume;\n");
    printf("\t flag         - can be \"-std\" (standard checker) or \"-val\" (validator);\n");
    printf("\t input        - input file for standard checker or generator source code;\n");
    printf("\t output       - output file for standard checker or validator source code.\n");
}


int main(int argc, char** argv)
{
    if (argc != 7)
    {
        printf("[ERROR] Incorrect number of arguments.\n");
        show_usage();
        return 1;
    }
    
    std::string program = argv[1];
    unsigned time_limit = std::atoi(argv[2]);
    unsigned mem_limit  = std::atoi(argv[3]);
    std::string flag    = argv[4];
    std::string input   = argv[5];
    std::string output  = argv[6];
    
    if (flag != "-std" && flag != "-val")
    {
        printf("[ERROR] Incorrect flag (may be \"-std\" or \"-val\").\n");
        return 1;
    }

    std::string extension = program.substr(program.find_last_of('.') + 1);

    // Compilation
    std::string command;
    std::string solution = random_filename();
    if (extension == "c" || extension == "cpp")
    {
        command = (extension == "c" ? "gcc " : "g++ ") + program + " -o " + solution;
    }
    else if (extension == "java")
    {
        command = "java " + program + " > " + solution;
    }
    else
    {
        printf("[ERROR] Wrong file extension. Aborting...\n");
        return 1;
    }
    
    int ret_val = system( command.c_str() );
    
    // CE
    if (ret_val != 0)
    {
        printf("Compilation Error\n");
        remove_dump(solution);
        return 0;
    }
    
    // Execution
    std::string verdict = random_filename();
    std::string result  = random_filename();
    
    /*
    if (flag == "-std")
    {
        
    }
    else if (flag == "-val")
    {
        
    }
    */
    
    if (false) {
    command = "cat " + input + " | ./sandbox --cpu " + std::to_string(time_limit) + " --mem " + std::to_string(mem_limit) + " --usage " + verdict + " --exec ./" + solution + " > " + result;
    } else {
        command = "./sandbox --cpu " + std::to_string(time_limit) + " --mem " + std::to_string(mem_limit) + " --usage " + verdict + " --exec ./" + solution + " < " + input;
    }
    system( command.c_str() );
    
    //debug
    printf("[DEBUG] Input:\n");
    command = "cat " + input;
    system( command.c_str() );
    printf("[DEBUG] Result:\n");
    command = "cat " + result;
    system( command.c_str() );
    printf("[DEBUG] Output:\n");
    command = "cat " + output;
    system( command.c_str() );
    printf("[DEBUG]\n");
    //debug
    
    // RE, TL, ML, WA, OK
    std::string verdict_out = parse_verdict(verdict);
    if (verdict_out == "OK")
    {
        command = "diff " + result + ' ' + output;
        int cmp = system( command.c_str() );
        if (cmp != 0)
        {
            printf("Wrong Answer\n");
        }
        else
        {
            printf("OK\n");
        }
    }
    else
    {
        if (verdict_out == "Memory Limit Exceeded" || verdict_out == "Time Limit Exceeded")
        {
            printf( "%s\n", verdict_out.c_str() );
        }
        else
        {
            printf("Runtime Error\n");
        }
    }
    
    // Remove dump files
    remove_dump(solution);
    remove_dump(verdict);
    remove_dump(result);
    
    return 0;
}
