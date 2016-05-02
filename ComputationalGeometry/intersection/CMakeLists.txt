cmake_minimum_required(VERSION 3.3)
project(intersection)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp tests.h)
add_executable(intersection ${SOURCE_FILES})
target_link_libraries(intersection gmp gmpxx)