#!/bin/bash

# directory for compilation output and failure reports
WORK_DIRECTORY=testing
# resources to be copied to WORK_DIRECTORY
RESOURCES=("directions.txt" "locations.txt")

ROOT_DIRECTORY="$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
INTERACTIVE_MODE=0
SKIP_FILE_TEST=0
SKIP_OUTPUT_TEST=0
while getopts "sfi" options; do
    case "${options}" in
        i) INTERACTIVE_MODE=1 ;;
        s) SKIP_OUTPUT_TEST=1 ;;
        f) SKIP_FILE_TEST=1 ;;
        *) exit 1 ;;
    esac
done

[ -e $WORK_DIRECTORY ] && rm -r $WORK_DIRECTORY
mkdir -p $WORK_DIRECTORY

echo "=> Copying resources..."
if ! cp "${RESOURCES[@]}" $WORK_DIRECTORY ; then
    echo "Failed to copy specified resources: ${RESOURCES[*]}"
    exit 2
fi
chmod +w $WORK_DIRECTORY/*.txt
echo "=> Compiling source files..."
if ! javac src/*.java -d $WORK_DIRECTORY ; then
    echo "Compilation failed. Aborting."
    exit 2
fi

echo

run_application () {
    mkdir -p "${WORK_DIRECTORY}"/tests/"${1}"/
    cd $WORK_DIRECTORY || return 1
    cat "${ROOT_DIRECTORY}"/testInputs/"${1}"TestInput.txt | java Mapping > tests/"${1}"/ProgramOutput.txt 2>&1
    cp "${ROOT_DIRECTORY}"/expectedOutputs/"${1}"ExpectedOutput.txt tests/"${1}"/ExpectedOutput.txt
    cp StudentFileOutput.txt tests/"${1}"/StudentFileOutput.txt
    cd "$ROOT_DIRECTORY" || return 1
}

test_stdout () {
    diff "${WORK_DIRECTORY}"/tests/"${1}"/ProgramOutput.txt "${WORK_DIRECTORY}"/tests/"${1}"/ExpectedOutput.txt > "${WORK_DIRECTORY}"/tests/"${1}"/StdOutputDiff.txt
}

test_file () {
    diff "${WORK_DIRECTORY}"/tests/"${1}"/StudentFileOutput.txt "${WORK_DIRECTORY}"/tests/"${1}"/ExpectedOutput.txt > "${WORK_DIRECTORY}"/tests/"${1}"/FileOutputDiff.txt
}

rewrite_status () {
    printf '\033[1A'
    echo -n "Test $1 "
    printf "[$2]\n"
}

PASS_COUNT=0
FAIL_COUNT=0
DIFFS=()

for file in testInputs/*; do
    FILENAME="$(basename "$file")"
    CODE=${FILENAME:0:3}
    echo "Test $CODE [ ]"

    if [ ! -e expectedOutputs/"${CODE}"ExpectedOutput.txt ]; then
        rewrite_status "$CODE" "\033[0;37mSkipped\033[0m"
        echo " No output file exists for test $CODE"
        continue
    fi

    run_application "$CODE"

    FAIL_STDOUT=0
    FAIL_FILE=0
    if (( SKIP_OUTPUT_TEST == 0 )) && ! test_stdout "$CODE"; then
        DIFFS+=("vimdiff -R ${WORK_DIRECTORY}/tests/${CODE}/ProgramOutput.txt ${WORK_DIRECTORY}/tests/${CODE}/ExpectedOutput.txt")
        FAIL_STDOUT=1
    fi
    if (( SKIP_FILE_TEST == 0 )) && ! test_file "$CODE"; then
        DIFFS+=("vimdiff -R ${WORK_DIRECTORY}/tests/${CODE}/StudentFileOutput.txt ${WORK_DIRECTORY}/tests/${CODE}/ExpectedOutput.txt")
        FAIL_FILE=1
    fi
    if (( FAIL_STDOUT == 0 && FAIL_FILE == 0)); then
        rewrite_status "$CODE" "\033[0;32m\xE2\x9C\x94\033[0m"
        (( PASS_COUNT++ ))
        continue
    else
        rewrite_status "$CODE" "\033[0;31mFailed\033[0m"
        (( FAIL_COUNT++ ))
        (( FAIL_STDOUT == 1 )) && echo "  * Standard output of test $CODE was not an exact match"
        (( FAIL_FILE == 1 )) && echo "  * Output file (StudentFileOutput.txt) of test $CODE was not an exact match"
        echo " Inspect $WORK_DIRECTORY/tests/$CODE/ for more details"
    fi
done

echo

if (( FAIL_COUNT > 0 )); then
    echo -e "\033[0;31m\xE2\x9C\x97\033[0m Tests \033[0;31mfailed\033[0m with $PASS_COUNT passes and $FAIL_COUNT failures."
    echo
    if (( INTERACTIVE_MODE == 1 )); then
        for TEST in "${DIFFS[@]}"; do
            read -p "Interactive mode: run $TEST [Y/n]? " -n1 RESPONSE
            echo
            if [[ $RESPONSE =~ (Y|y) ]]; then
                $TEST
            fi
        done
        echo
    fi

    exit 3
else
    echo -e "\033[0;32m\xE2\x9C\x94\033[0m Tests \033[0;32mpassed\033[0m with $PASS_COUNT tests complete."
    echo
fi
