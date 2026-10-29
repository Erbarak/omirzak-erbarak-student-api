#!/bin/bash
LOG="logs/Omirzak_Erbarak_system_report.log"
{
echo "===== System report ====="
echo "Student: Erbarak Omirzak"
echo "Date: $(date '+%Y-%m-%d %H:%M:%S')"
echo "User: $(whoami)"
echo "--- OS and kernel ---"
grep PRETTY_NAME /etc/os-release
echo "Kernel: $(uname -r)"
echo "--- CPU ---"
lscpu | grep -E 'Model name|^CPU[(]s[)]'
echo "--- Memory ---"
free -h
echo "--- Disk usage ---"
df -h
echo "--- Available space on / ---"
df -h / | awk 'NR==2 {print $4 " available"}'
echo
} | tee -a "$LOG"
