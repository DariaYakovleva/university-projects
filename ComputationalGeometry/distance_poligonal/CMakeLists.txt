cmake_minimum_required(VERSION 3.3)
project(distance_poligonal)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(distance_poligonal ${SOURCE_FILES})