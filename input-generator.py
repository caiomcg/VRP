#!/usr/bin/env python3

import sys
import random

def randomize(shortest_path, longest_path):
    return random.randint(shortest_path, longest_path)

def dump(file_name, matrix_length):
    with open(sys.argv[2], "w") as output:
        output.write(matrix_length + "\n")
        for lines in range(0, int(matrix_length) - 1):
            for collumns in range(lines + 1, int(matrix_length)):
                output.write(str(randomize(1, 20)) + " ")
            output.write("\n")
            
            

if __name__ == "__main__":
    if len(sys.argv) > 1:
        dump(sys.argv[2], sys.argv[1])
    else:
        pass