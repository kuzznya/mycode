cmake_minimum_required(VERSION 3.8)

project(sandbox)

include_directories(${CMAKE_SOURCE_DIR})

if (${CMAKE_SYSTEM_NAME} MATCHES "Linux" OR ${CMAKE_SYSTEM_NAME} MATCHES "Darwin")
    set(PLATFORM linux)
else ()
    message(FATAL_ERROR "This OS is not supported.")
endif ()

add_executable(sandbox sandbox.c error.c safe.c setlimit.c platform/${PLATFORM}/setlimits.c platform/${PLATFORM}/memusage.c)

target_link_libraries(sandbox ${LINKED_LIBS})

add_custom_target(permission chown root sandbox
                  COMMAND chmod u+s sandbox)

install(TARGETS sandbox
        DESTINATION "bin"
        PERMISSIONS OWNER_READ OWNER_EXECUTE GROUP_READ GROUP_EXECUTE WORLD_READ WORLD_EXECUTE)

install(SCRIPT chown.cmake)
