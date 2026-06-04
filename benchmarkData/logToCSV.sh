# ____ PID appears frequently in the log file
cat benchmarkData/boot-81ce9c0e8398402183c09e96f5a43492.log | grep _____ >> benchmarkData/cleaned_log.txt
rm benchmarkData/cleaned_clean_log.txt
cat benchmarkData/cleaned_log.txt | while IFS= read -r line; do printf '%s\n' "${line:35}"; done >> benchmarkData/cleaned_clean_log.txt
# then run dataFormatter.py with cleaned_clean_log.txt as fin
# then run avgCSV.py with systemcorePlace.csv
# run visualizer.py
