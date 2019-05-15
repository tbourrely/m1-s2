#!/bin/bash
rules=(cleancode codesize controversial design naming unusedcode)
output="./../rendu/rapports/objectifB"
for entry in "./AllFormClasses-DatabaseClass-FormClass/AllFormClasses/"*
do
    f="$(basename -- $entry)"
    name=${f%.php}
    dirpath="$output/$name"

    if [[ ! -e $dirpath ]]; then
        mkdir $dirpath
    fi

    for rule in ${rules[*]}
    do
        outputpath="$dirpath/$rule.html"
        commandToRun="php vendor/bin/phpmd $entry html $rule > $outputpath"
        eval $commandToRun
    done
done

for entry in "./AllFormClasses-DatabaseClass-FormClass/DatabaseClass/"*
do
    f="$(basename -- $entry)"
    name=${f%.php}
    dirpath="$output/$name"

    if [[ ! -e $dirpath ]]; then
        mkdir $dirpath
    fi

    for rule in ${rules[*]}
    do
        outputpath="$dirpath/$rule.html"
        commandToRun="php vendor/bin/phpmd $entry html $rule > $outputpath"
        eval $commandToRun
    done
done