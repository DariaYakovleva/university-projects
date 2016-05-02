cmake_minimum_required (VERSION 2.6)

project (computation-methods-hw2-ng)

#find_package(GMP REQUIRED)
set(CMAKE_CXX_COMPILER clang++)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++14 -Wall -Wextra -Wconversion -pedantic")

include_directories(headers)


set(SRC_LIST src/main.cpp)

add_executable(main ${SRC_LIST})
target_link_libraries(main -lgmp -lgmpxx)
