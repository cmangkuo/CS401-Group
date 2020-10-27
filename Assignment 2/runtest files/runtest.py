import json
import subprocess
import sys
import os
import argparse

# Crude version of testing tool for command-line programs

# read in json file representing test case
def loadTest(jsonfilename):
    jsonfile = open(jsonfilename)
    return json.load(jsonfile)

# For "build" step that must be done before running the test cases
def build(testcase):
    if 'build' in testcase.keys():
        compileResult = subprocess.run(testcase['build'], shell=True)
        assert(compileResult.returncode == 0)

# Comparing expected result to actual result of running the command
def check(expected, actual, ignoreLine):
    if expected == None:
        return actual == None
    success = True
    for line1 in expected:
    
      if bool(ignoreLine): #if flag tripped, will ignore lines that start with a specific character, X
      
        tempLine = actual.readline()
        
        while tempLine.startswith("X"):#skip past lines
          tempLine = actual.readline()
        
        if line1 != tempLine:
          success = False
          break   
        
        tempLine = actual.readline()
        while tempLine.startswith("X"):# skip past lines
          tempLine = actual.readline()
        
        line = tempLine
        if line and not tempLine.startswith("X"): # True if not at eof
          print('actual still has: ' + line)
          success = False
        return success  
      else:#if not tripped, will compare every line, no skipping
         if line1 != actual.readline():
            success = False
            break
         line = actual.readline()
         if line: # True if not at eof
            print('actual still has: ' + line)
            success = False
         return success
        

# Compares expected output to actual output. 
# Returns true as long as the lines from desired output can be found in the actual output in the correct order. 
def checkDesiredOnly(expected, actual):
    if expected == None:
        return actual == None
    success = True
    for line1 in expected:
        line2 = actual.readline()
        while line2:
            if line1 == line2:
                break
            line2 = actual.readline()
        if not line2 and line1: # actual reached eof but expected still has lines
            print('actual does not have: ' + line1)
            success = False
    return success


            


# Running the test cases
def run(cmd,ignoreLine, readOneLine):
    failures = 0
    successes = 0
    for case in cmd['cases']:
        case_pass = True
        case_keys = case.keys()
        #print(case.keys())
        has_infile = 'in' in case_keys
        has_args = 'args' in case_keys
        has_expected = 'expected' in case_keys
        has_err = 'expected_err' in case_keys
        has_desired_only = 'desired_only' in case_keys #if this is true then use checkDesiredOnly()
        if 'expected_return_code' in case_keys:
            expected_return_code = case['expected_return_code']
        else: expected_return_code = 0
        if has_infile:
            infile = open(case['in'])
        if has_args:
            cmd_text = cmd['cmd'] + ' ' + case['args']
        else: cmd_text = cmd['cmd']
        if has_expected:
            outname = case['name'] + '__actual.txt'
            actual = open(outname, 'w')
            expected = open(case['expected'])
            # Original code ends here
            # New code starts here
            Match = 0
            
            # Checks expected and actual text files to see if expected text exists in the actual file
            # Original code only passes if the actual file and expected file are identical
            # New code only checks if at least 1 line in the expected text file exists in the actual text filei
            expectedFile = open(case['expected'], 'r')
            expectedLines = expectedFile.readlines()
            
            actualFile = open(case['expected'], 'r')  # Is this opening the expected file twice? I thought actual file should only have output after subprocess.run() is called below
            actualLines = actualFile.readlines()
            for eLine in expectedLines:
                for aLine in actualLines:
                    if (eLine == aLine):
                        Match = 1
                
     
        else:
            actual = None
            expected = None
        if has_err:
            errname = '__actual_err.txt'
            actual_err = open(errname, 'w')
            expected_err = open(case['expected_err'])
        else:
            actual_err = None
            expected_err = None
        if not has_infile:
            runResult = subprocess.run(cmd_text,text=True,stdout=actual,stderr=actual_err, shell=True)
        else: runResult = subprocess.run(cmd_text,text=True,stdin=infile,stdout=actual,stderr=actual_err, shell=True)
        if runResult.returncode != expected_return_code:
            print("Case " + case['name'] + " expected " + str(expected_return_code) + ", but actual returncode = " + str(runResult.returncode))
            case_pass = False
        if has_expected: actual = open(outname)
        if has_err: actual_err = open(errname)

        #If -l or --line command used, only test to match one line, else test as normal 
        if readOneLine:
          if (Match==1) and check(expected_err, actual_err,ignoreLine):
              print("Case " + case['name'] + " passes")
          else:
              print("Case " + case['name'] + " fails because actual output did not match expected output")
              case_pass = False
        else:
          if check(expected,actual,ignoreLine) and check(expected_err, actual_err,ignoreLine):
              print("Case " + case['name'] + " passes")
          else:
              print("Case " + case['name'] + " fails because actual output did not match expected output")
              case_pass = False
        

        # New code: if has_desired_only is true
        if has_desired_only:
            case_pass = checkDesiredOnly(expected, actual)
        # end new code
        if case_pass:
            successes += 1
        else: failures += 1
        if has_infile: infile.close()
        if has_expected:
            actual.close()
            expected.close()
        if has_err:
            actual_err.close()
            expected_err.close()
    return (successes, failures)

usage = "python runtest.py testfile"

if __name__ == "__main__":
	
	skipLine = 0
	oneLine = 0
	parser = argparse.ArgumentParser() #implimented argparse to support commandline arguments
	parser.add_argument("-i", "--ignore", action ="store_true", help = "ignore lines begining with #")
	parser.add_argument("-l", "--line", action ="store_true", help = "only match one line")
	parser.add_argument('filename',action = 'store', type = str, help = "json file")
	#current arguments, filename for json file
	#-i or --ignore, ignores output that starts with certian character, in this case it is "X"
	#-l or --line, test output to see if one line matches, test passes if it does
	#can test both with testIgnore.json
	
	args = parser.parse_args()
	
	if len(sys.argv) < 2:
		print(usage)
		exit(1)
	THIS_FOLDER = os.path.dirname(os.path.abspath(__file__))
	my_file = os.path.join(THIS_FOLDER, str(args.filename))
	print("File: " + str(args.filename))
	
	if args.ignore:# if -i or --ignore argument, toggles flag to skip certian lines
	  skipLine = 1
	  
	if args.line:
	  oneLine = 1
	
	testcase = loadTest(my_file)
	build(testcase)
	print(run(testcase,skipLine,oneLine))


