# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.15

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2019.3.1\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2019.3.1\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\daltao\IdeaProjects\contest\c++

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\daltao\IdeaProjects\contest\c++\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/testrunner.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/testrunner.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/testrunner.dir/flags.make

CMakeFiles/testrunner.dir/testrunner/main.cpp.obj: CMakeFiles/testrunner.dir/flags.make
CMakeFiles/testrunner.dir/testrunner/main.cpp.obj: ../testrunner/main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\daltao\IdeaProjects\contest\c++\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/testrunner.dir/testrunner/main.cpp.obj"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\testrunner.dir\testrunner\main.cpp.obj -c C:\Users\daltao\IdeaProjects\contest\c++\testrunner\main.cpp

CMakeFiles/testrunner.dir/testrunner/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/testrunner.dir/testrunner/main.cpp.i"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\daltao\IdeaProjects\contest\c++\testrunner\main.cpp > CMakeFiles\testrunner.dir\testrunner\main.cpp.i

CMakeFiles/testrunner.dir/testrunner/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/testrunner.dir/testrunner/main.cpp.s"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\daltao\IdeaProjects\contest\c++\testrunner\main.cpp -o CMakeFiles\testrunner.dir\testrunner\main.cpp.s

# Object files for target testrunner
testrunner_OBJECTS = \
"CMakeFiles/testrunner.dir/testrunner/main.cpp.obj"

# External object files for target testrunner
testrunner_EXTERNAL_OBJECTS =

testrunner.exe: CMakeFiles/testrunner.dir/testrunner/main.cpp.obj
testrunner.exe: CMakeFiles/testrunner.dir/build.make
testrunner.exe: CMakeFiles/testrunner.dir/linklibs.rsp
testrunner.exe: CMakeFiles/testrunner.dir/objects1.rsp
testrunner.exe: CMakeFiles/testrunner.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\daltao\IdeaProjects\contest\c++\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable testrunner.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\testrunner.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/testrunner.dir/build: testrunner.exe

.PHONY : CMakeFiles/testrunner.dir/build

CMakeFiles/testrunner.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\testrunner.dir\cmake_clean.cmake
.PHONY : CMakeFiles/testrunner.dir/clean

CMakeFiles/testrunner.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\daltao\IdeaProjects\contest\c++ C:\Users\daltao\IdeaProjects\contest\c++ C:\Users\daltao\IdeaProjects\contest\c++\cmake-build-debug C:\Users\daltao\IdeaProjects\contest\c++\cmake-build-debug C:\Users\daltao\IdeaProjects\contest\c++\cmake-build-debug\CMakeFiles\testrunner.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/testrunner.dir/depend

