cmake_minimum_required(VERSION 3.3)
project(tower_circle_points)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(tower_circle_points ${SOURCE_FILES})