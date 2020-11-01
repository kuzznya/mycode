#include <cstdlib>
#include <string>
#include <fstream>
#include <random>
#include <cstring>

#include "ru_teamnull_mycode_service_TestChecker.h"
#include "ru_teamnull_mycode_service_ValidationChecker.h"


/*
struct Student  // Cache solution od a student
{
    std::string id;
    std::string solution;
};
*/

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


JNIEXPORT jint JNICALL Java_ru_teamnull_mycode_service_TestChecker_testCheck
        (JNIEnv* env, jclass, jfloat tl, jint ml, jstring code_file, jstring input_file, jstring output_file)
{
    const char* code_file_arg   = env->GetStringUTFChars(code_file, JNI_FALSE);
    const char* input_file_arg  = env->GetStringUTFChars(input_file, JNI_FALSE);
    const char* output_file_arg = env->GetStringUTFChars(output_file, JNI_FALSE);

    char code_file_arg_new[256];
    char input_file_arg_new[256];
    char output_file_arg_new[256];

    strcpy(const_cast<char*>(code_file_arg), code_file_arg_new);
    strcpy(const_cast<char*>(input_file_arg), input_file_arg_new);
    strcpy(const_cast<char*>(output_file_arg), output_file_arg_new);

    env->ReleaseStringUTFChars(code_file,   code_file_arg);
    env->ReleaseStringUTFChars(input_file,  input_file_arg);
    env->ReleaseStringUTFChars(output_file, output_file_arg);

    std::string sandbox = "cnative";
    std::string program = code_file_arg_new;
    float time_limit    = tl;
    int mem_limit       = ml;
    std::string flag    = "-std";
    std::string input   = input_file_arg_new;
    std::string output  = output_file_arg_new;
    
    printf( "\n[DEBUG] program: %s\n", program.c_str() );
    printf( "\n[DEBUG] time_limit: %f\n", time_limit );
    printf( "\n[DEBUG] mem_limit: %d\n", mem_limit );
    printf( "\n[DEBUG] input: %s\n", input.c_str() );
    printf( "\n[DEBUG] output: %s\n", output.c_str() );
    
    std::string extension = program.substr(program.find_last_of('.') + 1);

    // Compilation
    std::string command;
    std::string solution = random_filename();
    printf( "\n[DEBUG] solution: %s\n", solution.c_str() );
    std::string err_log  = random_filename();
    printf( "\n[DEBUG] err_log: %s\n", err_log.c_str() );
    if (extension == "c" || extension == "cpp")
    {
        command = (extension == "c" ? "gcc " : "g++ ") +
                  program + " -o " + solution + " > " + err_log;
    }
    else if (extension == "java")
    {
        command = "java " + program + " > " + solution;
    }
    else
    {
        printf("[ERROR] Wrong file extension. Aborting...\n");
        return 0;
    }

    int ret_val = system( command.c_str() );

    // CE
    if (ret_val != 0)
    {
        command = "cat " + err_log;
        system( command.c_str() );
        remove_dump(solution);
        remove_dump(err_log);
        return 1;
    }

    // Execution
    std::string verdict = random_filename();
    std::string result  = random_filename();
    printf( "\n[DEBUG] verdict: %s\n", verdict.c_str() );
    printf( "\n[DEBUG] result: %s\n", result.c_str() );
    /*
    if (flag == "-std")
    {

    }
    else if (flag == "-val")
    {

    }
    */

    //if (true) { // check if it is file input or stdin
    command = "cat " + input + " | ./" + sandbox + " --cpu " +
            std::to_string(time_limit) + " --mem " + std::to_string(mem_limit) +
            " --usage " + verdict + " --exec ./" + solution + " > " + result;
    //} else {
    //    command = "./sandbox --cpu " + std::to_string(time_limit) + " --mem " + std::to_string(mem_limit) + " --usage " + verdict + " --exec ./" + solution + " < " + input;
    //}
    system( command.c_str() );
    printf( "\n[DEBUG] command: %s\n", command.c_str() );

    //debug
    printf("\n[DEBUG] Input:\n");
    command = "cat " + input;
    system( command.c_str() );
    printf("\n[DEBUG] Result:\n");
    command = "cat " + result;
    system( command.c_str() );
    printf("\n[DEBUG] Output:\n");
    command = "cat " + output;
    system( command.c_str() );
    //debug

    // RE, TL, ML, WA, OK
    std::string verdict_out = parse_verdict(verdict);
    if (verdict_out == "OK")
    {
        command = "diff " + result + ' ' + output;
        int cmp = system( command.c_str() );
        if (cmp != 0)
        {
            return 3;
        }
        else
        {
            return 2;
        }
    }
    else
    {
        // Remove dump files
        remove_dump(solution);
        remove_dump(verdict);
        remove_dump(result);

        if (verdict_out == "Memory Limit Exceeded") //|| verdict_out == "Time Limit Exceeded")
        {
            //printf( "%s\n", verdict_out.c_str() );
            return 4;
        }
        else if (verdict_out == "Time Limit Exceeded")
        {
            return 5;
        }
        else
        {
            return 6;
        }
    }
}